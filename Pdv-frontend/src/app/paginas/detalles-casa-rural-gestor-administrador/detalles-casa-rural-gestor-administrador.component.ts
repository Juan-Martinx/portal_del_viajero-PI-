import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detalles-casa-rural-gestor-administrador',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule],
  templateUrl: './detalles-casa-rural-gestor-administrador.component.html',
  styleUrl: './detalles-casa-rural-gestor-administrador.component.css'
})
export class DetallesCasaRuralGestorAdministradorComponent implements OnInit {
 

  isActionNew: boolean = false;
  editarTituloEnable = false;
  
  ver: boolean = false;

  constructor(private route: ActivatedRoute){}

  ngOnInit(): void {
  
    this.route.queryParams.subscribe(params => {
      if(params['action'] == 'new'){
        this.isActionNew = true;
      }
    });

  /**QUITAR LO DE ABAJO */
  this.ver = false;
  this.cogerPrimerosComentarios();
}

  alojamientoForm = new FormGroup({
    titulo: new FormControl('TÍTULO DE CASA RURAL', [Validators.required, Validators.maxLength(50)]),
    precioNoche: new FormControl(10, [Validators.required, Validators.min(10)]),
    numeroMinimoHuespedes: new FormControl(1, [Validators.required, Validators.min(1)]),
    numeroMaximoHuespedes: new FormControl(2, [Validators.required, Validators.min(2)]),
  });

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

primerosComentarios: any[] = [];



cogerPrimerosComentarios(){
  this.primerosComentarios = this.comentarios.slice(-2);

}

verMas(){
  this.ver = true;
}

}
