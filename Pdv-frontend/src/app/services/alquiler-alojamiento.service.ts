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

  /**
   * Realiza una reserva de alojamiento.
   * @param alquilerAlojamiento El objeto IAlquilerAlojamientoDTO que representa la reserva a realizar.
   * @returns Un Observable que emite un objeto IGenericApiMessageDTO como mensaje de respuesta.
   */
  realizarReserva(alquilerAlojamiento: IAlquilerAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.alquilerAlojamientoAPI, alquilerAlojamiento);
  }

  /**
   * Busca las reservas de alojamiento del usuario actual.
   * @returns Un Observable que emite una matriz de objetos IAlquilerAlojamientoDTO.
   */
  buscarReservasUsuario(): Observable<IAlquilerAlojamientoDTO[]> {
    return this.http.get<IAlquilerAlojamientoDTO[]>(environment.api + this.alquilerAlojamientoAPI);
  }

    /**
   * Busca las reservas de alojamiento del usuario por su username.
   * @returns Un Observable que emite una matriz de objetos IAlquilerAlojamientoDTO.
   */
    buscarReservasUsuarioByUsername(username: string): Observable<IAlquilerAlojamientoDTO[]> {
      return this.http.get<IAlquilerAlojamientoDTO[]>(environment.api + this.alquilerAlojamientoAPI + "/" + username);
    }

  /**
   * Busca las reservas de alojamiento para el gestor.
   * @returns Un Observable que emite una matriz de objetos IAlquilerAlojamientoDTO.
   */
  buscarReservasGestor(): Observable<IAlquilerAlojamientoDTO[]> {
    return this.http.get<IAlquilerAlojamientoDTO[]>(environment.api + this.alquilerAlojamientoAPI + "/gestor");
  }
  
  /**
   * Cancela una reserva de alojamiento.
   * @param id El ID de la reserva a cancelar.
   * @returns Un Observable que emite un objeto IGenericApiMessageDTO como mensaje de respuesta.
   */
  cancelarReserva(id: number): Observable<IGenericApiMessageDTO> {
    return this.http.delete<IGenericApiMessageDTO>(environment.api + this.alquilerAlojamientoAPI + "/" + id);
  }

}
