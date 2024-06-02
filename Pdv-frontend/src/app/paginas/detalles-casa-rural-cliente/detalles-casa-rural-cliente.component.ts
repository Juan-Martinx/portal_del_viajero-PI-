import { Component, OnInit } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { provideNativeDateAdapter } from '@angular/material/core';
import { IComodidadAlojamientoDTO } from '../../../dto/IComodidadAlojamientoDTO';
import { IValoracionAlojamientoDTO } from '../../../dto/IValoracionAlojamientoDTO';
import { IAlojamientoDTO } from '../../../dto/IAlojamientoDTO';
import { AlojamientoService } from '../../services/alojamiento.service';
import { ActivatedRoute } from '@angular/router';
import { CodTipoComodidad } from '../../../dto/enumCodTipoComodidad';
import { AlquilerAlojamientoService } from '../../services/alquiler-alojamiento.service';
import { IAlquilerAlojamientoDTO } from '../../../dto/IAlquilerAlojamientoDTO';

@Component({
  selector: 'app-detalles-casa-rural-cliente',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [MatDatepickerModule, MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule],
  templateUrl: './detalles-casa-rural-cliente.component.html',
  styleUrl: './detalles-casa-rural-cliente.component.css'
})
export class DetallesCasaRuralClienteComponent implements OnInit {

  alojamiento: IAlojamientoDTO = {};
  alojamientoComodidades: IComodidadAlojamientoDTO[] = [];
  alojamientoInstalaciones: IComodidadAlojamientoDTO[] = [];
  valoraciones: IValoracionAlojamientoDTO[] = [];
  diasDiferencia: number = 0;
  precioTotal: number = 0;

  constructor(private alojamientoService: AlojamientoService, private route: ActivatedRoute, private alquilerAlojamientoService: AlquilerAlojamientoService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.alojamientoService.buscarAlojamientoById(params['id']).subscribe(alojamiento => {
        this.alojamiento = alojamiento;
        alojamiento.idAlojamientoComodidades?.forEach(comodidad => {
          if (comodidad.idComodidadAlojamiento?.idTipoComodidad?.codigoTipoComodidad == CodTipoComodidad.COMODIDAD) {
            this.alojamientoComodidades.push(comodidad.idComodidadAlojamiento);
          } else if (comodidad.idComodidadAlojamiento?.idTipoComodidad?.codigoTipoComodidad == CodTipoComodidad.INSTALACION) {
            this.alojamientoInstalaciones.push(comodidad.idComodidadAlojamiento);
          }
        });
      });
    });
  }

  reservar = new FormGroup({
    llegada: new FormControl(new Date(), [Validators.required]),
    salida: new FormControl(new Date(), [Validators.required]),
    huespedes: new FormControl(this.alojamiento.numPlazaMin, [Validators.required, Validators.min(this.alojamiento.numPlazaMin as number), Validators.max(this.alojamiento.numPlazaMax as number)])
  });

  // Filtro para la fecha de salida
  salidaFilter = (d: Date | null): boolean => {
    return !d || d >= new Date();
  }

  // Método para calcular la diferencia de días
  getDaysDifference(): void {
    const llegada = this.reservar.get('llegada')?.value;
    const salida = this.reservar.get('salida')?.value;
    if (llegada && salida) {
      const llegadaDate = new Date(llegada);
      const salidaDate = new Date(salida);
      const diffTime = Math.abs(salidaDate.getTime() - llegadaDate.getTime());
      const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
      this.diasDiferencia = diffDays;
      this.precioTotal = this.diasDiferencia * (this.alojamiento.numPrecioNoche as number);
    }
  }

  realizarReserva(): void {
    if(this.reservar.valid){
      if(confirm("¿Está seguro de que desea realizar la reserva?")){
        const llegada = this.reservar.get('llegada')?.value;
        const salida = this.reservar.get('salida')?.value;
        if (llegada && salida) {
          const llegadaDate = new Date(llegada);
          const salidaDate = new Date(salida);
          if(llegadaDate > salidaDate){
            alert("La fecha de llegada no puede ser posterior a la fecha de salida");
          }else{
            const alquilerAlojamiento: IAlquilerAlojamientoDTO = {
              idAlojamiento: this.alojamiento.id,
              fechaInicioAlquiler: llegadaDate,
              fechaFinAlquiler: salidaDate,
              numPlazasReservadas: this.reservar.get('huespedes')?.value as number,
            };
            this.alquilerAlojamientoService.realizarReserva(alquilerAlojamiento).subscribe(response => {
              alert(response.mensaje);
            });
          }
        }
      }
    }else{
      alert("Por favor, rellene todos los campos para realizar la reserva");
    }

  }

}
