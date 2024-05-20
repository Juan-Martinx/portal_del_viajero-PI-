import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PaginaRegistroComponent } from './paginas/pagina-registro/pagina-registro.component';
import { InicioSesionComponent } from './paginas/inicio-sesion/inicio-sesion.component';
import { ConvertirGestorComponent } from './paginas/convertir-gestor/convertir-gestor.component';
import { EditarPerfilComponent } from './paginas/editar-perfil/editar-perfil.component';
import { VerPerfilComponent } from './paginas/ver-perfil/ver-perfil.component';
import { BuscarComodidadComponent } from './paginas/buscar-comodidad/buscar-comodidad.component';
import { ModificarComodidadComponent } from './paginas/modificar-comodidad/modificar-comodidad.component';
import { AnadirComodidadComponent } from './paginas/anadir-comodidad/anadir-comodidad.component';
import { CasasAlquilerComponent } from './paginas/casas-alquiler/casas-alquiler.component';
import { CasasDisponiblesComponent } from './paginas/casas-disponibles/casas-disponibles.component';
import { VerReservasComponent } from './paginas/ver-reservas/ver-reservas.component';
import { BuscarUsuariosComponent } from './paginas/buscar-usuarios/buscar-usuarios.component';
import { DetallesCasaRuralGestorAdministradorComponent } from './paginas/detalles-casa-rural-gestor-administrador/detalles-casa-rural-gestor-administrador.component';
import { DetallesCasaRuralClienteComponent } from './paginas/detalles-casa-rural-cliente/detalles-casa-rural-cliente.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PaginaRegistroComponent, InicioSesionComponent, ConvertirGestorComponent, EditarPerfilComponent, 
    VerPerfilComponent, BuscarComodidadComponent, ModificarComodidadComponent, AnadirComodidadComponent, CasasAlquilerComponent, 
    CasasDisponiblesComponent, VerReservasComponent, BuscarUsuariosComponent, DetallesCasaRuralGestorAdministradorComponent,
    DetallesCasaRuralClienteComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Pdv-frontend';
}
