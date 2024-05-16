import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterOutlet, RouterEvent, Router, NavigationEnd } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { ResourceInterceptor } from './interceptors/resource.interceptor';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MenuComponent, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [{provide: HTTP_INTERCEPTORS, useClass: ResourceInterceptor, multi: true}]
})
export class AppComponent implements OnInit {
  title = 'Pdv-frontend';

  @ViewChild('menu') menu?: MenuComponent;

  constructor(
    private router: Router
  ) {}

  ngOnInit(): void {
    this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe(()=> {
      if (this.menu) {
        this.menu.getLogged();
      }
    });
  }
}