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

  ngOnInit(): void {
    if(!this.tokenService.isGestor()){
      this.router.navigate(['/']);
    }
    this.alquilerAlojamientoService.buscarReservasGestor().subscribe(reservas => {
      this.reservas = reservas;
    });
  }

  cancelarReserva(id?: number): void {
    if(confirm("¿Está seguro de que desea cancelar la reserva?")){
      console.log(id);
      this.alquilerAlojamientoService.cancelarReserva(id as number).subscribe(response => {
        alert(response.mensaje);
        location.reload();
      });
    }
  }

}
