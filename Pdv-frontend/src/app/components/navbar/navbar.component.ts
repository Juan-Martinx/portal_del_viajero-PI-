import { Component} from '@angular/core';
import { MenuComponent } from '../menu/menu.component';
import { BarraBusquedaInicioComponent } from '../barra-busqueda-inicio/barra-busqueda-inicio.component';
import { RouterLink } from '@angular/router';
import { TokenService } from '../../services/token.service';
import { UsuarioService } from '../../services/usuario.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MenuComponent, BarraBusquedaInicioComponent, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent{

  constructor(private tokenService: TokenService) { }

  isLogged(): boolean {
    return this.tokenService.isLogged();
  }

  isGestor(): boolean{
    return this.tokenService.isGestor();
  }

}
