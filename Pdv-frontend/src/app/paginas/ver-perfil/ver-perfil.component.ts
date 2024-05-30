import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { Route, ActivatedRoute } from '@angular/router';
import { IUsuarioDTO } from '../../../dto/IUsuarioDTO';
@Component({
  selector: 'app-ver-perfil',
  standalone: true,
  imports: [],
  templateUrl: './ver-perfil.component.html',
  styleUrl: './ver-perfil.component.css'
})
export class VerPerfilComponent implements OnInit {

  constructor(private usuarioService: UsuarioService, private route: ActivatedRoute) { }

  usuario?: IUsuarioDTO;

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const username = params.get('username');
      if (typeof username === 'string' && username.length > 0) {
        this.usuarioService.buscarUsuarioPorUsername(username).subscribe(usuario => {
          this.usuario = usuario;
        });
      }
    });

  }
}
