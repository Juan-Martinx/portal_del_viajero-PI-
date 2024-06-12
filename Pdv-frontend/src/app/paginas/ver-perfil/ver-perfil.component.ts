import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { Route, ActivatedRoute, RouterLink, Router } from '@angular/router';
import { IUsuarioDTO } from '../../../dto/IUsuarioDTO';
import { TokenService } from '../../services/token.service';
@Component({
  selector: 'app-ver-perfil',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './ver-perfil.component.html',
  styleUrl: './ver-perfil.component.css'
})
export class VerPerfilComponent implements OnInit {

  constructor(private usuarioService: UsuarioService, private route: ActivatedRoute, private tokenService: TokenService, private router: Router) { }

  usuario?: IUsuarioDTO;

  /**
   * Carga los datos de perfil del usuario.
   */
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const username = params.get('username');
      if (typeof username === 'string' && username.length > 0) {
        if(this.tokenService.isAdmin()){
          this.router.navigate(['/editar-perfil-usuario/' + username]);
        }
        this.usuarioService.buscarUsuarioPorUsername(username).subscribe(usuario => {
          this.usuario = usuario;
        });
      }
    });

  }
}
