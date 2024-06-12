import { Component, OnInit } from '@angular/core';
import {MatSliderModule} from '@angular/material/slider';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule, ReactiveFormsModule, FormGroup, FormControl} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatMenuModule} from '@angular/material/menu';

@Component({
  selector: 'app-filtro',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [ReactiveFormsModule, MatSliderModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule, MatIconModule, MatDatepickerModule, MatMenuModule],
  templateUrl: './filtro.component.html',
  styleUrl: './filtro.component.css'
})
export class FiltroComponent implements OnInit{
  ngOnInit(): void {
    /*const header = document.getElementById('close');
    const filtrosContainer = document.getElementById('filtros');
    filtrosContainer!.addEventListener('scroll', () => {
      
      const containerReactivo = filtrosContainer?.getBoundingClientRect();
      const hearReactivo = header?.getBoundingClientRect();

      if(containerReactivo!.top > hearReactivo!.bottom){
        header!.style.top = '0';
        header!.style.position = 'fixed';
        header!.classList.add('fixed');
      }else{
        header!.style.top = '';
        header!.classList.remove('fixed');
        header!.style.position = '';
      }
    })*/
  }

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
  }

  resetearMenu(){
    this.filtros.reset();
  }

}
