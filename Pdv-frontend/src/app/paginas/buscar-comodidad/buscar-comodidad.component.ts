import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { IComodidadAlojamientoDTO } from '../../../dto/IComodidadAlojamientoDTO';
import { ComodidadService } from '../../services/comodidad.service';
import { Router, RouterLink} from '@angular/router';
import { iTipoComodidadDTO } from '../../../dto/ITipoComodidadDTO';
import { MatSelectModule } from '@angular/material/select';
import { TipoComodidadService } from '../../services/tipo-comodidad.service';
import { IPageableDTO } from '../../../dto/IPageableDTO';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-buscar-comodidad',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, RouterLink, MatSelectModule ],
  templateUrl: './buscar-comodidad.component.html',
  styleUrl: './buscar-comodidad.component.css'
})
export class BuscarComodidadComponent implements OnInit{

  constructor(private comodidadService: ComodidadService, private router: Router, private tipoComodidadesService: TipoComodidadService, private tokenService: TokenService){}
  comodidades: IComodidadAlojamientoDTO[] = [];
  tiposComodidades: iTipoComodidadDTO[] = [];
  paginaActual = 0;

  /**
   * Carga los tipos de comodidades y comprueba si el usuario 
   * es administrador, sino te redirige al inicio.
   */
  ngOnInit(): void {
      this.cargarTiposComodidades();
      if(!this.tokenService.isAdmin()){
        this.router.navigate(['/']);
      }
  }

  comodidadesForm = new FormGroup({
    nombre: new FormControl(''),
    codigo: new FormControl(''),
    nombreTipoComodidad: new FormControl(-1)
  });

  /**
   * Te envía a la página de mofidicar comodidad con el código de la comodidad.
   * @param codigo 
   */
  modificarComodidad(codigo?: string){
    this.router.navigate(['/modificar-comodidad'], { queryParams: { codigo: codigo } });
  }

  /**
   * Elimina la comodidad seleccionada.
   * @param codigo
   */
  eliminarComodidad(codigo?: string){
    if(confirm("¿Estas seguro de eliminar esta comodidad?")){
      this.comodidadService.eliminarComodidad(codigo as string).subscribe(mensaje => {
        alert(mensaje.mensaje);
        location.reload();
      });
    }
  }

  /**
   * Carga los tipos de comodidades.
   */
  cargarTiposComodidades(){
    this.tipoComodidadesService.buscarTiposComodidades().subscribe(tiposComodidades => {
      this.tiposComodidades = tiposComodidades;
    });
  }

  /**
   * Realiza una búsqueda de comodidades según los parámetros introducidos.
   * @param avanzarPagina
   */
  buscarComodidades(avanzarPagina: boolean){

    const comodidad: IComodidadAlojamientoDTO = {
      txtNombre: this.comodidadesForm.get('nombre')?.value as string,
      codigoComodidad: this.comodidadesForm.get('codigo')?.value as string,
      idTipoComodidad: {id: this.comodidadesForm.get('nombreTipoComodidad')?.value as number}
    }

    const pageable: IPageableDTO = {
      page: this.paginaActual,
      size: 5
    }

    this.comodidadService.buscarComodidades(comodidad, pageable).subscribe(comodidades => {
      if (comodidades.length == 0) {
        this.paginaActual = avanzarPagina? --this.paginaActual : ++this.paginaActual;
        alert("No hay comodidades para mostrar");
      } else {
        this.comodidades = comodidades;
      }
    });

  }

  /**
   * Sirve para cambiar la página de comodidades.
   * @param page 
   */
  cambiarPage(page: number) {
    if(this.paginaActual < page){
      this.paginaActual = page;
      this.buscarComodidades(true);
    }else{
      this.paginaActual==0? 0 : this.paginaActual = page;
      this.buscarComodidades(false);
    }
  }
}
