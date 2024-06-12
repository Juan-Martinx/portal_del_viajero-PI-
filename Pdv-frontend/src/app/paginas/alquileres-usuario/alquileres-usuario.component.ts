import { Component } from '@angular/core';
import { TokenService } from '../../services/token.service';
import { AlquilerAlojamientoService } from '../../services/alquiler-alojamiento.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { IAlquilerAlojamientoDTO } from '../../../dto/IAlquilerAlojamientoDTO';

@Component({
  selector: 'app-alquileres-usuario',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './alquileres-usuario.component.html',
  styleUrl: './alquileres-usuario.component.css'
})
export class AlquileresUsuarioComponent {
  constructor(private tokenService: TokenService,private alquilerAlojamientoService: AlquilerAlojamientoService, private router: Router, private route: ActivatedRoute) { }

  reservas: IAlquilerAlojamientoDTO[] = [];
  username: string = "";
  /**
   * Buscar las reservas realizadas por el usuario logueado
   */
  ngOnInit(): void {
    if(!this.tokenService.isAdmin()){
      this.router.navigate(['/']);
    }
    this.route.paramMap.subscribe(params => {
      const username = params.get('username');
      if (typeof username === 'string' && username.length > 0) {
        this.username = username as string;
        this.alquilerAlojamientoService.buscarReservasUsuarioByUsername(username).subscribe(reservas => {
          this.reservas = reservas;
        });
      }
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
