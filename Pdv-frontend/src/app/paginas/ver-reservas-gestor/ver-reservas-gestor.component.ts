import { Component } from '@angular/core';
import { AlquilerAlojamientoService } from '../../services/alquiler-alojamiento.service';
import { IAlquilerAlojamientoDTO } from '../../../dto/IAlquilerAlojamientoDTO';
import { Router, RouterLink } from '@angular/router';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-ver-reservas-gestor',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './ver-reservas-gestor.component.html',
  styleUrl: './ver-reservas-gestor.component.css'
})
export class VerReservasGestorComponent {

  constructor(private alquilerAlojamientoService: AlquilerAlojamientoService, private tokenService: TokenService, private router: Router) { }

  reservas: IAlquilerAlojamientoDTO[] = [];

  /**
   * Buscar las reservas que han solicitado los usuarios sobre tus alojamientos.
   */
  ngOnInit(): void {
    if(!this.tokenService.isGestor()){
      this.router.navigate(['/']);
    }
    this.alquilerAlojamientoService.buscarReservasGestor().subscribe(reservas => {
      this.reservas = reservas;
    });
  }

  /**
   * Cancela la reserva
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
