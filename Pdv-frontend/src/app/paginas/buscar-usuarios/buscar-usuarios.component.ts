import { Component, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { IUsuarioDTO } from '../../../dto/IUsuarioDTO';
import { IPageableDTO } from '../../../dto/IPageableDTO';
import { Router, RouterLink } from '@angular/router';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-buscar-usuarios',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './buscar-usuarios.component.html',
  styleUrl: './buscar-usuarios.component.css'
})
export class BuscarUsuariosComponent implements OnInit{

  usuarios: IUsuarioDTO[] = [];

  paginaActual = 0;

  constructor(private usuarioService: UsuarioService, private tokenService: TokenService, private router: Router) { }

  ngOnInit(): void {
    this.cambiarPage(0);
    if(!this.tokenService.isAdmin()){
      this.router.navigate(['/']);
    }
  }

  buscadorUsuarios = new FormGroup({
    nombre: new FormControl(''),
    correoElectronico: new FormControl(''),
    dni: new FormControl('')
  });

  /**
   * En caso de avanzar de página será true
   */
  buscarUsuarios(avanzarPagina: boolean) {
    const usuario: IUsuarioDTO = {
      username: this.buscadorUsuarios.value.nombre as string,
      txtDni: this.buscadorUsuarios.value.dni as string,
      txtEmail: this.buscadorUsuarios.value.correoElectronico as string,
    }

    const pageable: IPageableDTO = {
      page: this.paginaActual,
      size: 4
    }

    this.usuarioService.buscarUsuariosApp(usuario, pageable).subscribe(usuarios => {
      if (usuarios.length == 0) {
        this.paginaActual = avanzarPagina? --this.paginaActual : ++this.paginaActual;
        alert("No hay resultados para mostrar");
      } else {
        this.usuarios = usuarios;
      }
    });
  }

  cambiarPage(page: number) {
    if(this.paginaActual < page){
      this.paginaActual = page;
      this.buscarUsuarios(true);
    }else{
      this.paginaActual==0? 0 : this.paginaActual = page;
      this.buscarUsuarios(false);
    }
  }

}
