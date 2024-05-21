import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { IUsuarioDTO } from '../../../dto/IUsuarioDTO';
import { UsuarioService } from '../../services/usuario.service';


@Component({
  selector: 'app-inicio-sesion',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule, MatIconModule],
  templateUrl: './inicio-sesion.component.html',
  styleUrl: './inicio-sesion.component.css'
})
export class InicioSesionComponent {

  constructor(private usuarioService: UsuarioService){}
  formLogin = new FormGroup({
    usuario: new FormControl('', Validators.required),
    contrasena: new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(12)]),
  })

  iniciarSesion(){
    if(this.formLogin.valid){
      console.log("Iniciando sesión...");
      const sesion: IUsuarioDTO = {
        username: this.formLogin.get('usuario')?.value as string,
        password: this.formLogin.get('contrasena')?.value as string
      }
      this.usuarioService.login(sesion).subscribe(
        genericApiMessage => {
          console.log(genericApiMessage.mensaje + "\n\r Con fecha " + genericApiMessage.fechaYHora);
        }
      );
    }else{
      alert("Por favor, rellene los campos correctamente");
    }
  }
}
