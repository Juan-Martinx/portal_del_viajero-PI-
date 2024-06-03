import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IGenericApiMessageDTO } from '../../dto/IGenericApiMessageDTO';
import { environment } from '../../environments/environments';
import { IAlquilerAlojamientoDTO } from '../../dto/IAlquilerAlojamientoDTO';

@Injectable({
  providedIn: 'root'
})
export class AlquilerAlojamientoService {

  alquilerAlojamientoAPI = "/alquiler-alojamiento";

  constructor(private http: HttpClient) { }

  realizarReserva(alquilerAlojamiento: IAlquilerAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.alquilerAlojamientoAPI, alquilerAlojamiento);
  }

  buscarReservasUsuario(): Observable<IAlquilerAlojamientoDTO[]> {
    return this.http.get<IAlquilerAlojamientoDTO[]>(environment.api + this.alquilerAlojamientoAPI);
  }

  cancelarReserva(id: number): Observable<IGenericApiMessageDTO> {
    return this.http.delete<IGenericApiMessageDTO>(environment.api + this.alquilerAlojamientoAPI + "/" + id);
  }

}
