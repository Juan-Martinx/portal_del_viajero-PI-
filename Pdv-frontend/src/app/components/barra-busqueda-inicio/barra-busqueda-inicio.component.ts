import { Component } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormGroup, FormControl } from '@angular/forms';

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

}
