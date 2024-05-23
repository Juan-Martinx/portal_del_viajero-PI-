import { Routes } from '@angular/router';
import { AuthorizedComponent } from './paginas/authorized/authorized.component';
import { LogoutComponent } from './components/logout/logout.component';
import { PaginaRegistroComponent } from './paginas/pagina-registro/pagina-registro.component';
import { InicioSesionComponent } from './paginas/inicio-sesion/inicio-sesion.component';
import { MenuComponent } from './components/menu/menu.component';
import { EditarPerfilComponent } from './paginas/editar-perfil/editar-perfil.component';
import { VerReservasComponent } from './paginas/ver-reservas/ver-reservas.component';
import { BuscarComodidadComponent } from './paginas/buscar-comodidad/buscar-comodidad.component';
import { BuscarUsuariosComponent } from './paginas/buscar-usuarios/buscar-usuarios.component';
import { BarraBusquedaInicioComponent } from './components/barra-busqueda-inicio/barra-busqueda-inicio.component';

export const routes: Routes = [
    { path: '', component: BarraBusquedaInicioComponent, title: 'Inicio'},
    { path: 'registro', component: PaginaRegistroComponent, title: 'Inicio'},
    { path: 'inicio-sesion', component: InicioSesionComponent, title: 'Inicio'},
    { path: 'editar-perfil', component: EditarPerfilComponent, title: 'Editar Perfil'},
    { path: 'reservas-realizadas', component: VerReservasComponent, title: 'Ver Reservas Realizadas'},
    { path: 'buscador-comodidades', component: BuscarComodidadComponent, title: 'Buscar Comodidades y Administrarlas'},
    { path: 'usuarios-registrados', component: BuscarUsuariosComponent, title: 'Buscar Usuarios que están registrados'},
    { path: 'authorized', component: AuthorizedComponent },
    { path: 'logout', component: LogoutComponent, title: 'Logout'},
    { path: '**', redirectTo: '', pathMatch: 'full' }
];
