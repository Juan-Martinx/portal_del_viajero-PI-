import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterOutlet, Router, NavigationEnd } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { filter } from 'rxjs/operators';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MAT_DATE_FORMATS, DateAdapter } from '@angular/material/core';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HttpClientModule, NavbarComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'Pdv-frontend';

  @ViewChild('menu') menu?: MenuComponent;

  mostrarNavbar: boolean = true;

  constructor(
    private router: Router
  ) {}

  ngOnInit(): void {
    this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe(()=> {
      if (this.menu) {
        this.menu.getLogged();
      }

      if(this.router.url == '/registro' || this.router.url == '/inicio-sesion'){
        this.mostrarNavbar = false;
      }else{
        this.mostrarNavbar = true;
      }
    });
  }
}