import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PaginaRegistroComponent } from './paginas/pagina-registro/pagina-registro.component';
import { InicioSesionComponent } from './paginas/inicio-sesion/inicio-sesion.component';
import { ConvertirGestorComponent } from './paginas/convertir-gestor/convertir-gestor.component';
import { EditarPerfilComponent } from './paginas/editar-perfil/editar-perfil.component';
import { VerPerfilComponent } from './paginas/ver-perfil/ver-perfil.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PaginaRegistroComponent, InicioSesionComponent, ConvertirGestorComponent, EditarPerfilComponent, VerPerfilComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Pdv-frontend';
}
