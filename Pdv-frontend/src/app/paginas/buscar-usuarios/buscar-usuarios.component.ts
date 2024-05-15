import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-buscar-usuarios',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule],
  templateUrl: './buscar-usuarios.component.html',
  styleUrl: './buscar-usuarios.component.css'
})
export class BuscarUsuariosComponent {

  usuarios = new FormGroup({
    nombre: new FormControl(''),
    telefono: new FormControl(''),
    correoElectronico: new FormControl(''),
    dni: new FormControl('')
  });

  numeroUsuario: any[] = [
    {
      usuario: "Usuario 1"
    },
    {
      usuario: "Usuario 2"
    },
    {
      usuario: "Usuario 3"
    },
    {
      usuario: "Usuario 4"
    },
    {
      usuario: "Usuario 5"
    },
    {
      usuario: "Usuario 6"
    },
    {
      usuario: "Usuario 7"
    },
    {
      usuario: "Usuario 8"
    },
    {
      usuario: "Usuario 9"
    },
    {
      usuario: "Usuario 10"
    },
    {
      usuario: "Usuario 11"
    }
  ]

}
