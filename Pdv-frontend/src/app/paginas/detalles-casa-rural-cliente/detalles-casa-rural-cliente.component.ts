import { Component, OnInit } from '@angular/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {provideNativeDateAdapter} from '@angular/material/core';

@Component({
  selector: 'app-detalles-casa-rural-cliente',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [MatDatepickerModule, MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule],
  templateUrl: './detalles-casa-rural-cliente.component.html',
  styleUrl: './detalles-casa-rural-cliente.component.css'
})
export class DetallesCasaRuralClienteComponent implements OnInit {

  ver: boolean = false;

  casasRurales: any[] = [
    {
      titulo: "Casa Rural en Málaga",
      localizacion: "Málaga, Andalucía",
      precio: "520",
      detalles: "Es una casa Rural que cuenta con piscina, un restaurante cerca, la playa está situada a 5 minutos andando y además, cuenta con una gran tranquilidad alrededor",
    },
  ];

  anuncio = new FormGroup({
    precio: new FormControl(''),
    minHuespedes: new FormControl(''),
    maxHuespedes: new FormControl('')
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
    }
  ];

  comentarios: any[] = [
    {
      pais: "España"
    },
    {
      pais: "Francia"
    },
    {
      pais: "Alemania"
    },
    {
      pais: "Georgia"
    }

];

reservar = new FormGroup({
  llegada: new FormGroup(''),
  salida: new FormGroup(''),
  huespedes: new FormControl('')
});

primerosComentarios: any[] = [];

ngOnInit(): void {
  this.ver = false;
  this.cogerPrimerosComentarios();
}

cogerPrimerosComentarios(){
  this.primerosComentarios = this.comentarios.slice(-2);

}

verMas(){
  this.ver = true;
}


}
