import { Routes } from '@angular/router';
import { AuthorizedComponent } from './paginas/authorized/authorized.component';
import { LogoutComponent } from './components/logout/logout.component';
import { PaginaRegistroComponent } from './paginas/pagina-registro/pagina-registro.component';

export const routes: Routes = [
    { path: '', component: LogoutComponent, title: 'Inicio'},
    { path: 'registro', component: PaginaRegistroComponent, title: 'Inicio'},
    { path: 'authorized', component: AuthorizedComponent },
    { path: 'logout', component: LogoutComponent, title: 'Logout'},
    { path: '**', redirectTo: '', pathMatch: 'full' }
];
