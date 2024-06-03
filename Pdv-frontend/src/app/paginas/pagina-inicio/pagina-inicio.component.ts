import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { FiltroComponent } from '../../components/filtro/filtro.component';
import {MatSliderModule} from '@angular/material/slider';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatMenuModule} from '@angular/material/menu';
import { MenuComponent } from '../../components/menu/menu.component';
import { BarraBusquedaInicioComponent } from '../../components/barra-busqueda-inicio/barra-busqueda-inicio.component';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-pagina-inicio',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, RouterLink, FiltroComponent
    , MatSliderModule, MatDatepickerModule, MatMenuModule, MatMenuModule, MenuComponent, BarraBusquedaInicioComponent],
  templateUrl: './pagina-inicio.component.html',
  styleUrl: './pagina-inicio.component.css'
})
export class PaginaInicioComponent {

  constructor(private tokenService: TokenService){}

  isLogged(): boolean {
    return this.tokenService.isLogged();
  }

  isGestor(): boolean{
    return this.tokenService.isGestor();
  }

  usuario: string = "Pepe123";

  busqueda = new FormGroup({
    destino: new FormControl(),
    llegada: new FormControl(),
    salida: new FormControl()
  });

  casasRurales: any[] = [
    {
      titulo: "Casa Rural en Málaga",
      localizacion: "Málaga, Andalucía",
      precio: "520",
      detalles: "Es una casa Rural que cuenta con piscina, un restaurante cerca, la playa está situada a 5 minutos andando y además, cuenta con una gran tranquilidad alrededor",
    },
    {
      titulo: "Urbanización privada",
      localizacion: "Marbella, Andalucía",
      precio: "700",
      detalles: "Es una casa Rural que cuenta con piscina, un restaurante cerca, la playa está situada a 5 minutos andando y además, cuenta con una gran tranquilidad alrededor",
    },
    {
      titulo: "Zona de despeje total",
      localizacion: "Gran Alacant, Alicante",
      precio: "350",
      detalles: "Es una casa Rural que cuenta con piscina, un restaurante cerca, la playa está situada a 5 minutos andando y además, cuenta con una gran tranquilidad alrededor",
    },
    {
      titulo: "Casa Rural en Málaga",
      localizacion: "Málaga, Andalucía",
      precio: "520",
      detalles: "Es una casa Rural que cuenta con piscina, un restaurante cerca, la playa está situada a 5 minutos andando y además, cuenta con una gran tranquilidad alrededor",
    }
  ];

  formatLabel(value: number): string {
    return `${value}€`;
  }

  menu: boolean = false;

  filtros = new FormGroup({
    precioMin: new FormControl(0),
    precioMax: new FormControl(1500),
    fechaLlegada: new FormControl(new Date()),
    fechaSalida: new FormControl(new Date()),
    instalaciones: new FormControl(''),
    comodidades: new FormControl('')
  });

  instalaciones: any [] = [
    {
      icono: 'pool',
      nombre: 'Piscina',
      codigo: 'COD-BAN',
      codigoTipoComodidad: 'INST',
      nombreTipoComodidad: 'Instalaciones'
    },
    {
      icono: 'golf_course',
      nombre: 'Campo de golf',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'INST',
      nombreTipoComodidad: 'Instalaciones'
    },
    {
      icono: 'restaurant_menu',
      nombre: 'Resturante',
      codigo: 'COD-BAN2',
      codigoTipoComodidad: 'INST',
      nombreTipoComodidad: 'Instalaciones'
    },
    {
      icono: 'golf_course',
      nombre: 'Campo de golf',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'INST',
      nombreTipoComodidad: 'Instalaciones'
    },
    {
      icono: 'golf_course',
      nombre: 'Campo de golf',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'INST',
      nombreTipoComodidad: 'Instalaciones'
    }
  ];

  comodidades: any [] = [
    {
      icono: 'pool',
      nombre: 'Cocina',
      codigo: 'COD-BAN',
      codigoTipoComodidad: 'COC',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'golf_course',
      nombre: 'Televisión',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'TEL',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'restaurant_menu',
      nombre: 'Cubiertos',
      codigo: 'COD-BAN2',
      codigoTipoComodidad: 'CUB',
      nombreTipoComodidad: 'Cubiertos'
    },
    {
      icono: 'golf_course',
      nombre: 'Televisión',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'TEL',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'golf_course',
      nombre: 'Televisión',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'TEL',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'pool',
      nombre: 'Cocina',
      codigo: 'COD-BAN',
      codigoTipoComodidad: 'COC',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'golf_course',
      nombre: 'Televisión',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'TEL',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'restaurant_menu',
      nombre: 'Cubiertos',
      codigo: 'COD-BAN2',
      codigoTipoComodidad: 'CUB',
      nombreTipoComodidad: 'Cubiertos'
    },
    {
      icono: 'golf_course',
      nombre: 'Televisión',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'TEL',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'golf_course',
      nombre: 'Televisión',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'TEL',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'pool',
      nombre: 'Cocina',
      codigo: 'COD-BAN',
      codigoTipoComodidad: 'COC',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'golf_course',
      nombre: 'Televisión',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'TEL',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'restaurant_menu',
      nombre: 'Cubiertos',
      codigo: 'COD-BAN2',
      codigoTipoComodidad: 'CUB',
      nombreTipoComodidad: 'Cubiertos'
    },
    {
      icono: 'golf_course',
      nombre: 'Televisión',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'TEL',
      nombreTipoComodidad: 'Comodidades'
    },
    {
      icono: 'golf_course',
      nombre: 'Televisión',
      codigo: 'COD-BAN1',
      codigoTipoComodidad: 'TEL',
      nombreTipoComodidad: 'Comodidades'
    }


  ];

  mostrarMenu(){
    this.menu = !this.menu;
    console.log(this.menu);
  }

  resetearMenu(){
    this.filtros.reset();
  }


}
