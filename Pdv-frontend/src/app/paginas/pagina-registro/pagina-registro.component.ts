import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BotonRegistroGoogleComponent } from '../../components/boton-registro-google/boton-registro-google.component';
import { IUsuarioDTO } from '../../../dto/IUsuarioDTO';
import { CodPerfiles } from '../../../dto/enumCodPerfiles';
import { UsuarioService } from '../../services/usuario.service';
import { environment } from '../../../environments/environments';
import { TokenService } from '../../services/token.service';
import { HttpParams } from '@angular/common/http';
import * as CryptoJS from 'crypto-js';
import { RouterLink } from '@angular/router';
const CHARACTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

@Component({
  selector: 'app-pagina-registro',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule, MatIconModule, BotonRegistroGoogleComponent, RouterLink],
  templateUrl: './pagina-registro.component.html',
  styleUrl: './pagina-registro.component.css'
})

export class PaginaRegistroComponent {

  constructor(private usuarioService: UsuarioService, private tokenService: TokenService){}

  // Formulario de Registro Controller
  formRegistro = new FormGroup({
    usuario: new FormControl('', [Validators.required, Validators.maxLength(12)]),
    contrasena: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(12)]),
    dni: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{8}[A-Z]')]),
    telefono: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{9}')]),
    correoElectronico: new FormControl('', [Validators.required, Validators.email])
  })

  perfilGestorSeleccionado: boolean = false;
  logAceptaTerminosYCondiciones: boolean = false;

  registrarUsuario(){
    //Si el formulario es válido
    if(this.formRegistro.valid){
      if(this.logAceptaTerminosYCondiciones){
        const usuario: IUsuarioDTO ={
          username: this.formRegistro.get('usuario')?.value as string,
          password: this.formRegistro.get('contrasena')?.value as string,
          txtDni: this.formRegistro.get('dni')?.value as string,
          numTelefono: parseInt(this.formRegistro.get('telefono')?.value as string),
          txtEmail: this.formRegistro.get('correoElectronico')?.value as string,
          perfiles: [CodPerfiles.CLIENTE]
        }
  
        if(this.perfilGestorSeleccionado){
          usuario.perfiles?.push(CodPerfiles.GESTOR);
        }
        
        this.usuarioService.crearUsuario(usuario).subscribe(
          genericApiMessage => {
            alert(genericApiMessage.mensaje + "\n\r Con fecha " + genericApiMessage.fechaYHora);
            this.onLogin();
          },
          err => {
            alert("Error al registrar usuario: Ya se encuentra registrado otro usuario con estas credenciales");
          }
        );

      }else{
        alert("Para registrarte debes aceptar los Términos y Condiciones");
      }
    }else{
      alert("Debe rellenar todos los campos correctamente");
    }
    
  }

  onLogin(){
    this.tokenService.onLogin();
  }
}
