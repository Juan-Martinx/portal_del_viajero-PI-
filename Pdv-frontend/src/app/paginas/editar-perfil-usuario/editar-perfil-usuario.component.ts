import { Component } from '@angular/core';
import { TokenService } from '../../services/token.service';
import { UsuarioService } from '../../services/usuario.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MediaService } from '../../services/media.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { IUsuarioDTO } from '../../../dto/IUsuarioDTO';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-editar-perfil-usuario',
  standalone: true,
  imports: [ReactiveFormsModule, MatInputModule, MatFormFieldModule, MatIconModule, FormsModule, RouterLink],
  templateUrl: './editar-perfil-usuario.component.html',
  styleUrl: './editar-perfil-usuario.component.css'
})
export class EditarPerfilUsuarioComponent {
  usuario: IUsuarioDTO = {};
  username = "";
  urlFotoPerfil = "";
  rutaActual = this.route.snapshot.url[0].path;
  constructor(private tokenService: TokenService, private usuarioService: UsuarioService, private route: ActivatedRoute, private mediaService: MediaService, private router: Router) { }
  ngOnInit(): void {
    if(!this.tokenService.isAdmin()){
      this.router.navigate(['/']);
    }
    this.route.paramMap.subscribe(params => {
      this.username = params.get('username') as string;
      if (typeof this.username === 'string' && this.username.length > 0) {
        this.usuarioService.buscarUsuarioByAdmin(this.username).subscribe(usuario => {
          this.usuario = usuario;
          this.urlFotoPerfil = usuario.urlImagenUsuario as string;
          this.editarUsuarioForm.get('usuario')?.setValue(usuario.username as string);
          this.editarUsuarioForm.get('dni')?.setValue(usuario.txtDni as string);
          this.editarUsuarioForm.get('descripcion')?.setValue(usuario.txtDescripcion as string);
          this.editarUsuarioForm.get('telefono')?.setValue(usuario.numTelefono?.toString() as string);
        });
      }
    });
  }

  isUsuarioEnEdicion: boolean = false;

  editarUsuarioForm = new FormGroup({
    usuario: new FormControl('', [Validators.required, Validators.maxLength(15)]),
    dni: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{8}[A-Z]')]),
    descripcion: new FormControl('', []),
    telefono: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{9}')])
  });

  editarUsuario() {
    this.isUsuarioEnEdicion = !this.isUsuarioEnEdicion;
  }

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

      this.usuarioService.editarUsuarioOtro(this.username, usuarioEditado).subscribe(mensaje => {
        alert(mensaje.mensaje);
        if (mensaje.estado == "OK") {
          this.router.navigate(['/editar-perfil-usuario/' + usuarioEditado.username]);
          this.isUsuarioEnEdicion = false;
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

  eliminarUsuario(){
    if(confirm("¿Estas seguro de eliminar este usuario?")){
      this.usuarioService.eliminarUsuario(this.username).subscribe(mensaje => {
        alert(mensaje.mensaje);
        this.router.navigate(['/buscar-usuarios']);
      });
  }
}
}
