import { Routes } from '@angular/router';
import { AuthorizedComponent } from './paginas/authorized/authorized.component';
import { LogoutComponent } from './components/logout/logout.component';
import { PaginaRegistroComponent } from './paginas/pagina-registro/pagina-registro.component';
import { MenuComponent } from './components/menu/menu.component';
import { EditarPerfilComponent } from './paginas/editar-perfil/editar-perfil.component';
import { VerReservasComponent } from './paginas/ver-reservas/ver-reservas.component';
import { BuscarComodidadComponent } from './paginas/buscar-comodidad/buscar-comodidad.component';
import { BuscarUsuariosComponent } from './paginas/buscar-usuarios/buscar-usuarios.component';
import { BarraBusquedaInicioComponent } from './components/barra-busqueda-inicio/barra-busqueda-inicio.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DetallesCasaRuralClienteComponent } from './paginas/detalles-casa-rural-cliente/detalles-casa-rural-cliente.component';
import { DetallesCasaRuralGestorAdministradorComponent } from './paginas/detalles-casa-rural-gestor-administrador/detalles-casa-rural-gestor-administrador.component';
import { PaginaInicioComponent } from './paginas/pagina-inicio/pagina-inicio.component';
import { FiltroComponent } from './components/filtro/filtro.component';
import { ModificarComodidadComponent } from './paginas/modificar-comodidad/modificar-comodidad.component';
import { CasasDisponiblesComponent } from './paginas/casas-disponibles/casas-disponibles.component';
import { CasasAlquilerComponent } from './paginas/casas-alquiler/casas-alquiler.component';
import { ConvertirGestorComponent } from './paginas/convertir-gestor/convertir-gestor.component';
import { VerPerfilComponent } from './paginas/ver-perfil/ver-perfil.component';

export const routes: Routes = [
    { path: '', component: DetallesCasaRuralGestorAdministradorComponent, title: 'Inicio'},
    { path: 'registro', component: PaginaRegistroComponent, title: 'Inicio'},
    { path: 'convertir-gestor', component: ConvertirGestorComponent, title: 'Convertir en Gestor'},
    { path: 'editar-perfil', component: EditarPerfilComponent, title: 'Editar Perfil'},
    { path: 'ver-perfil/:username', component: VerPerfilComponent, title: 'Ver Perfil'},
    { path: 'modificar-comodidad', component: ModificarComodidadComponent, title: 'Modificar Comodidad'},
    { path: 'reservas-realizadas', component: VerReservasComponent, title: 'Ver Reservas Realizadas'},
    { path: 'buscador-comodidades', component: BuscarComodidadComponent, title: 'Buscar Comodidades y Administrarlas'},
    { path: 'usuarios-registrados', component: BuscarUsuariosComponent, title: 'Buscar Usuarios que están registrados'},
    { path: 'detalles-casas-gestor', component: DetallesCasaRuralGestorAdministradorComponent, title: 'Detalles de las Casas Rurales de los Gestores y Administradores'},
    { path: 'detalles-casas-cliente', component: DetallesCasaRuralClienteComponent, title: 'Detalles de las Casas Rurales para los Clientes'},
    { path: 'authorized', component: AuthorizedComponent },
    { path: 'logout', component: LogoutComponent, title: 'Logout'},
    { path: '**', redirectTo: '', pathMatch: 'full' }
];
