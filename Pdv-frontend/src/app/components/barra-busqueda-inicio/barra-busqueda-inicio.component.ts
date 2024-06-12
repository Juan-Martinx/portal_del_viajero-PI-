import { Component } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-barra-busqueda-inicio',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './barra-busqueda-inicio.component.html',
  styleUrl: './barra-busqueda-inicio.component.css'
})
export class BarraBusquedaInicioComponent {

  busqueda = new FormGroup({
    destino: new FormControl(),
    llegada: new FormControl(),
    salida: new FormControl()
  });

  constructor(private router: Router, private route: ActivatedRoute) { }

  /**
   * Método para realizar la búsqueda.
   * Obtiene los valores del formulario, valida las fechas y navega a la página de inicio con los parámetros de búsqueda.
   */
  buscar() {
    
  // Obtén los valores del formulario
  const destino = this.busqueda.get('destino')?.value;
  const llegada = this.busqueda.get('llegada')?.value ?  new Date(this.busqueda.get('llegada')?.value) : null;
  const salida = this.busqueda.get('salida')?.value ? new Date(this.busqueda.get('salida')?.value) : null;
  
  if(llegada && salida && llegada > salida){
    alert('La fecha de llegada no puede ser posterior a la de salida');
    return;
  }else if(llegada && llegada < new Date()){
    alert('La fecha de llegada no puede ser anterior a la fecha actual');
    return;
  }else if(salida && salida < new Date()){
    alert('La fecha de salida no puede ser anterior a la fecha actual');
    return;
  }else if((salida && !llegada) || llegada && !salida){
    alert('Debes introducir ambas fechas o ninguna para realizar la búsqueda')
    return;
  }
  
  // Formatea las fechas
  const formattedLlegada = llegada instanceof Date ? `${llegada.getFullYear()}-${String(llegada.getMonth() + 1).padStart(2, '0')}-${String(llegada.getDate()).padStart(2, '0')}` : null;
  const formattedSalida = salida instanceof Date ? `${salida.getFullYear()}-${String(salida.getMonth() + 1).padStart(2, '0')}-${String(salida.getDate()).padStart(2, '0')}` : null;

  // Navega a la ruta con los queryParams
  this.router.navigate(['/inicio'], {
    queryParams: {
      provincia: destino,
      llegada: formattedLlegada,
      salida: formattedSalida
    }
  });  
  }

}
