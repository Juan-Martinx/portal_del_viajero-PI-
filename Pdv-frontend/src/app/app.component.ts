import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PaginaRegistroComponent } from './paginas/pagina-registro/pagina-registro.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PaginaRegistroComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Pdv-frontend';
}
