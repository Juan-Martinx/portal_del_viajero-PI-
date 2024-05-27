import { Component } from '@angular/core';
import { MenuComponent } from '../menu/menu.component';
import { BarraBusquedaInicioComponent } from '../barra-busqueda-inicio/barra-busqueda-inicio.component';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MenuComponent, BarraBusquedaInicioComponent],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

}
