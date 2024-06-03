import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { IAlojamientoDTO } from '../../../dto/IAlojamientoDTO';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { AlojamientoService } from '../../services/alojamiento.service';

@Component({
  selector: 'app-casas-disponibles',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './casas-disponibles.component.html',
  styleUrl: './casas-disponibles.component.css'
})
export class CasasDisponiblesComponent implements OnInit{

  alojamientos: IAlojamientoDTO[] = [];
  usuario = "";

  constructor(private route: ActivatedRoute, private alojamientoService: AlojamientoService){}

  ngOnInit(): void {
          this.route.paramMap.subscribe(params => {
      const username = params.get('username');
      if (typeof username === 'string' && username.length > 0) {
       this.usuario = username;
       this.alojamientoService.buscarAloajmientoByUsername(username).subscribe(alojamientos => {
        this.alojamientos = alojamientos;
       });
      }
    });
  }

}
