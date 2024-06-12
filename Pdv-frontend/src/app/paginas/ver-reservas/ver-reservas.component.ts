import { Component, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AlquilerAlojamientoService } from '../../services/alquiler-alojamiento.service';
import { IAlquilerAlojamientoDTO } from '../../../dto/IAlquilerAlojamientoDTO';
import { Router, RouterLink } from '@angular/router';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-ver-reservas',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './ver-reservas.component.html',
  styleUrl: './ver-reservas.component.css'
})
export class VerReservasComponent implements OnInit {

  constructor(private tokenService: TokenService,private alquilerAlojamientoService: AlquilerAlojamientoService, private router: Router) { }

  reservas: IAlquilerAlojamientoDTO[] = [];

  /**
   * Buscar las reservas realizadas por el usuario logueado
   */
  ngOnInit(): void {
    if(!this.tokenService.isLogged()){
      this.router.navigate(['/']);
    }
    this.alquilerAlojamientoService.buscarReservasUsuario().subscribe(reservas => {
      this.reservas = reservas;
    });
  }

  /**
   * Cancela una reserva dado su id.
   * @param id
   */
  cancelarReserva(id?: number): void {
    if(confirm("¿Está seguro de que desea cancelar la reserva?")){
      this.alquilerAlojamientoService.cancelarReserva(id as number).subscribe(response => {
        alert(response.mensaje);
        location.reload();
      });
    }
  }
}
