import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-ver-reservas',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule],
  templateUrl: './ver-reservas.component.html',
  styleUrl: './ver-reservas.component.css'
})
export class VerReservasComponent {

  casasRurales: any[] = [
    {
      titulo: "Casa Rural en Málaga",
      localizacion: "Málaga, Andalucía",
      precio: "520",
      detalles:[
        {
          descripcion: "Cuenta con piscina"
        },
        {
          descripcion: "Restaurante cerca"
        },
        {
          descripcion: "Playa a 5 minutos andando"
        },
        {
          descripcion: "Gran tranquilidad alrededor"
        }
      ] 
    },
    {
      titulo: "Urbanización privada",
      localizacion: "Marbella, Andalucía",
      precio: "700",
      detalles:[
        {
          descripcion: "Cuenta con piscina"
        },
        {
          descripcion: "Restaurante cerca"
        },
        {
          descripcion: "Playa a 5 minutos andando"
        },
        {
          descripcion: "Gran tranquilidad alrededor"
        }
      ] 

    },
    {
      titulo: "Zona de despeje total",
      localizacion: "Gran Alacant, Alicante",
      precio: "350",
      detalles:[
        {
          descripcion: "Cuenta con piscina"
        },
        {
          descripcion: "Restaurante cerca"
        },
        {
          descripcion: "Playa a 5 minutos andando"
        },
        {
          descripcion: "Gran tranquilidad alrededor"
        }
      ] 
    },
    {
      titulo: "Casa Rural en Málaga",
      localizacion: "Málaga, Andalucía",
      precio: "520",
      detalles:[
        {
          descripcion: "Cuenta con piscina"
        },
        {
          descripcion: "Restaurante cerca"
        },
        {
          descripcion: "Playa a 5 minutos andando"
        },
        {
          descripcion: "Gran tranquilidad alrededor"
        }
      ] 
    }
  ]

}
