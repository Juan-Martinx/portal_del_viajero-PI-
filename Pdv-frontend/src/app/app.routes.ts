import { Routes } from '@angular/router';
import { AuthorizedComponent } from './paginas/authorized/authorized.component';
import { LogoutComponent } from './components/logout/logout.component';
import { PaginaRegistroComponent } from './paginas/pagina-registro/pagina-registro.component';
import { EditarPerfilComponent } from './paginas/editar-perfil/editar-perfil.component';
import { VerReservasComponent } from './paginas/ver-reservas/ver-reservas.component';
import { BuscarComodidadComponent } from './paginas/buscar-comodidad/buscar-comodidad.component';
import { BuscarUsuariosComponent } from './paginas/buscar-usuarios/buscar-usuarios.component';
import { DetallesCasaRuralClienteComponent } from './paginas/detalles-casa-rural-cliente/detalles-casa-rural-cliente.component';
import { DetallesCasaRuralGestorAdministradorComponent } from './paginas/detalles-casa-rural-gestor-administrador/detalles-casa-rural-gestor-administrador.component';
import { ModificarComodidadComponent } from './paginas/modificar-comodidad/modificar-comodidad.component';
import { ConvertirGestorComponent } from './paginas/convertir-gestor/convertir-gestor.component';
import { VerPerfilComponent } from './paginas/ver-perfil/ver-perfil.component';
import { CasasDisponiblesComponent } from './paginas/casas-disponibles/casas-disponibles.component';
import { CasasAlquilerComponent } from './paginas/casas-alquiler/casas-alquiler.component';
import { PaginaInicioComponent } from './paginas/pagina-inicio/pagina-inicio.component';
import { VerReservasGestorComponent } from './paginas/ver-reservas-gestor/ver-reservas-gestor.component';
import { EditarPerfilUsuarioComponent } from './paginas/editar-perfil-usuario/editar-perfil-usuario.component';

export const routes: Routes = [
    { path: '', redirectTo:"/inicio", pathMatch: 'full'},
    { path: 'inicio', component: PaginaInicioComponent, title: 'Inicio'},
    { path: 'registro', component: PaginaRegistroComponent, title: 'Página de Registro'},
    { path: 'convertir-gestor', component: ConvertirGestorComponent, title: 'Convertir en Gestor'},
    { path: 'editar-perfil', component: EditarPerfilComponent, title: 'Editar Perfil'},
    { path: 'editar-perfil-usuario/:username', component: EditarPerfilUsuarioComponent, title: 'Editar Perfil [ADMIN]'},
    { path: 'ver-perfil/:username', component: VerPerfilComponent, title: 'Ver Perfil'},
    { path: 'modificar-comodidad', component: ModificarComodidadComponent, title: 'Modificar Comodidad'},
    { path: 'reservas-realizadas', component: VerReservasComponent, title: 'Ver Reservas Realizadas'},
    { path: 'reservas-gestion', component: VerReservasGestorComponent, title: 'Gestionar Reservas Alojamientos'},
    { path: 'buscador-comodidades', component: BuscarComodidadComponent, title: 'Buscar Comodidades y Administrarlas'},
    { path: 'usuarios-registrados', component: BuscarUsuariosComponent, title: 'Buscar Usuarios que están registrados en la app'},
    { path: 'detalles-casas-gestor', component: DetallesCasaRuralGestorAdministradorComponent, title: 'Detalles de las Casas Rurales de los Gestores y Administradores'},
    { path: 'detalles-casas', component: DetallesCasaRuralClienteComponent, title: 'Detalles de las Casa Rural'},
    { path: 'casas-alquiler', component: CasasAlquilerComponent, title: 'Tus Casas en Alquiler'},
    { path: 'casas-disponibles/:username', component: CasasDisponiblesComponent, title: 'Casas Disponibles para Alquilar de otros usuarios'},
    { path: 'authorized', component: AuthorizedComponent },
    { path: 'logout', component: LogoutComponent, title: 'Logout'},
    { path: '**', redirectTo: 'inicio', pathMatch: 'full' }
];
