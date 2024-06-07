import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { environment } from '../../../environments/environments';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-convertir-gestor',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule, MatIconModule, RouterLink],
  templateUrl: './convertir-gestor.component.html',
  styleUrl: './convertir-gestor.component.css'
})
export class ConvertirGestorComponent {

  constructor(private usuarioService: UsuarioService){ }

  ngOnInit(): void {
    this.usuarioService.buscarUsuarioLogueado().subscribe(usuario => {
      this.userId = usuario.id;
    })
  }

  userId?: number;

  /* 
  * Al convertirse en gestor se cierra sesión
  * para reiniciar los tokens de autoridad
  */
  convertirEnGestor(){
    if(this.userId != null){
      this.usuarioService.convertirEnGestor(this.userId).subscribe(
        mensaje => {
          alert(mensaje.mensaje + "\n\r Con fecha " + mensaje.fechaYHora);
          location.href = environment.oauth_api + "logout";
        }
      );
    }
  }
}
