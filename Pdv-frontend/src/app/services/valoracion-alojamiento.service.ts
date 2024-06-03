import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IValoracionAlojamientoDTO } from '../../dto/IValoracionAlojamientoDTO';
import { IGenericApiMessageDTO } from '../../dto/IGenericApiMessageDTO';
import { environment } from '../../environments/environments';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ValoracionAlojamientoService {

  alquilerAlojamientoAPI = "/valoracion-alojamiento";

  constructor(private http: HttpClient) { }

  realizarReserva(valoracion: IValoracionAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.alquilerAlojamientoAPI, valoracion);
  }
}
