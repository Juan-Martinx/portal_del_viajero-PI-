import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { Validators } from '@angular/forms';
import { TokenService } from '../../services/token.service';
import { UsuarioService } from '../../services/usuario.service';
import { IUsuarioDTO } from '../../../dto/IUsuarioDTO';
import { catchError } from 'rxjs';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { MediaService } from '../../services/media.service';

@Component({
  selector: 'app-editar-perfil',
  standalone: true,
  imports: [ReactiveFormsModule, MatInputModule, MatFormFieldModule, MatIconModule, FormsModule, RouterLink],
  templateUrl: './editar-perfil.component.html',
  styleUrl: './editar-perfil.component.css'
})
export class EditarPerfilComponent implements OnInit {

  usuario: IUsuarioDTO = {};

  rutaActual = this.route.snapshot.url[0].path;
  constructor(private tokenService: TokenService, private usuarioService: UsuarioService, private route: ActivatedRoute, private mediaService: MediaService) { }
  /**
   * Busca el usuario logueado y carga sus datos.
   */
  ngOnInit(): void {
    this.usuarioService.buscarUsuarioLogueado().subscribe(usuario => {
      this.usuario = usuario;
      this.editarUsuarioForm.get('usuario')?.setValue(usuario.username as string);
      this.editarUsuarioForm.get('dni')?.setValue(usuario.txtDni as string);
      this.editarUsuarioForm.get('descripcion')?.setValue(usuario.txtDescripcion as string);
      this.editarUsuarioForm.get('telefono')?.setValue(usuario.numTelefono?.toString() as string);
    });
  }

  isUsuarioEnEdicion: boolean = false;

  editarUsuarioForm = new FormGroup({
    usuario: new FormControl('', [Validators.required, Validators.maxLength(15)]),
    dni: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{8}[A-Z]')]),
    descripcion: new FormControl('', []),
    telefono: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{9}')])
  });

  /**
   * Habilita el formulario de edición
   */
  editarUsuario() {
    this.isUsuarioEnEdicion = !this.isUsuarioEnEdicion;
  }

  /**
   * Guarda los cambios realizados en el formulario.
   */
  guardarCambios() {

    if (this.editarUsuarioForm.valid){
      const usuarioEditado: IUsuarioDTO = {
        id: this.usuario.id,
        username: this.editarUsuarioForm.get('usuario')?.value as string,
        txtDescripcion: this.editarUsuarioForm.get('descripcion')?.value as string,
        txtDni: this.editarUsuarioForm.get('dni')?.value as string,
        numTelefono: parseInt(this.editarUsuarioForm.get('telefono')?.value as string),
        txtEmail: this.usuario.txtEmail
      };

      this.usuarioService.editarUsuario(usuarioEditado).subscribe(mensaje => {
        alert(mensaje.mensaje);
        if (mensaje.estado == "OK") {
          location.reload();
        }
      }, error => {
        alert('No se puede editar el usuario debido a que los datos introducidos corresponden ya otro usuario distinto');
      });

    } else {
      alert('Por favor, rellene todos los campos correctamente');
    }
  }

  isGoogleUser(): boolean {
    return this.tokenService.isGoogleUser();
  }

  isGestor(): boolean {
    return this.tokenService.isGestor();
  }

  /**
   * Sube la imágen del usuario al servidor.
   * @param event 
   */
  uploadFile(event: any) {
    const file = event.target.files[0];

    if(file) {
      const formData = new FormData();
      formData.append('file', file);

      this.mediaService.uploadFile(formData).subscribe(res => {
        this.usuarioService.subirFotoPerfil(res.url).subscribe(mensaje => {
          alert(mensaje.mensaje);
          location.reload();
        });
      },
      error => {
        alert('No se ha podido subir la imagen');
      })
    }
  }

}
