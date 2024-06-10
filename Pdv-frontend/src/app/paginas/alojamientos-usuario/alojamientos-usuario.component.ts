import { Component } from '@angular/core';
import { AlojamientoService } from '../../services/alojamiento.service';
import { TokenService } from '../../services/token.service';
import { IAlojamientoDTO } from '../../../dto/IAlojamientoDTO';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-alojamientos-usuario',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './alojamientos-usuario.component.html',
  styleUrl: './alojamientos-usuario.component.css'
})
export class AlojamientosUsuarioComponent {

  constructor(private alojamientoService: AlojamientoService, private router: Router, private tokenService: TokenService, private route: ActivatedRoute) { }

  casasRurales: IAlojamientoDTO[] = [];
  username: string = "";
  /**
   * Carga las casas rurales del usuario y comprueba si es gestor, sino te redirige al inicio.
   */
  ngOnInit(): void {
    if(!this.tokenService.isAdmin()){
      this.router.navigate(['/']);
    }
    this.route.paramMap.subscribe(params => {
      const username = params.get('username');
      if (typeof username === 'string' && username.length > 0) {
        this.username = username as string;
        this.alojamientoService.buscarAloajmientoByUsername(username).subscribe(alojamientos => {
          this.casasRurales = alojamientos;
        });
      }
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
