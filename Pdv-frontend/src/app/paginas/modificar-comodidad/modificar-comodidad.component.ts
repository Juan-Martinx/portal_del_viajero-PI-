import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-modificar-comodidad',
  standalone: true,
  imports: [MatInputModule, MatFormFieldModule, MatIconModule, FormsModule, ReactiveFormsModule],
  templateUrl: './modificar-comodidad.component.html',
  styleUrl: './modificar-comodidad.component.css'
})
export class ModificarComodidadComponent {

  comodidades = new FormGroup({
    icono: new FormControl(''),
    descripcion: new FormControl(''),
    nombre: new FormControl(''),
    codigo: new FormControl(''),
    codigoTipoComodidad: new FormControl(''),
    nombreTipoComodidad: new FormControl('')
  });

  comodidad: any [] = [
    {
      icono: 'restaurant_menu',
      nombre: 'Resturante',
      codigo: 'COD-BAN2',
      codigoTipoComodidad: 'INST',
      nombreTipoComodidad: 'Instalaciones'
    }
  ]

}
