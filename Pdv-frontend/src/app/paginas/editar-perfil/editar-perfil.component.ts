import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-editar-perfil',
  standalone: true,
  imports: [ReactiveFormsModule, MatInputModule, MatFormFieldModule, MatIconModule, FormsModule],
  templateUrl: './editar-perfil.component.html',
  styleUrl: './editar-perfil.component.css'
})
export class EditarPerfilComponent implements OnInit{
  ngOnInit(): void {
    this.editar = false;
  }

  editar: boolean = false;

  usuarios = new FormGroup({
    usuario: new FormControl('', Validators.required),
    dni: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{8}[A-Z]')]),
    descripcion: new FormControl('', [Validators.required]),
    telefono: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{9}')]),
    correoElectronico: new FormControl('', [Validators.required, Validators.email])
  });

  editarUsuario(){
    this.editar = true;
  }

  cancelarEditarUsuario(){
    this.editar = false;
  }

}
