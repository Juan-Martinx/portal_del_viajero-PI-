import { Component, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { IAlojamientoDTO } from '../../../dto/IAlojamientoDTO';
import { AlojamientoService } from '../../services/alojamiento.service';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-casas-alquiler',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './casas-alquiler.component.html',
  styleUrl: './casas-alquiler.component.css'
})
export class CasasAlquilerComponent implements OnInit {

  constructor(private alojamientoService: AlojamientoService, private router: Router, private tokenService: TokenService) { }

  casasRurales: IAlojamientoDTO[] = [];

  /**
   * Carga las casas rurales del usuario y comprueba si es gestor, sino te redirige al inicio.
   */
  ngOnInit(): void {
    if(!this.tokenService.isGestor()){
      this.router.navigate(['/']);
    }
    this.alojamientoService.buscarAlojamientosUsuario().subscribe(alojamientos => {
      this.casasRurales = alojamientos;
    });
  }

  /**
   * Elimina una casa rural dado su id.
   * @param id 
   */
  eliminarCasaRural(id?: number){
    if(confirm("¿Estás seguro de que quieres eliminar esta casa rural?")){
      this.alojamientoService.eliminarAlojamiento(id as number).subscribe(mensaje => {
        alert(mensaje.mensaje);
        this.casasRurales = this.casasRurales.filter(casa => casa.id != id);
      }, err => {
        alert("Hubo un error al eliminar la casa rural");
      });
    }
  }
  
}
