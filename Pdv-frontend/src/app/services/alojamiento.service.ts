import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IGenericApiMessageDTO } from '../../dto/IGenericApiMessageDTO';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';
import { IAlojamientoDTO } from '../../dto/IAlojamientoDTO';

@Injectable({
  providedIn: 'root'
})
export class AlojamientoService {

  alojamientoAPI = "/alojamiento";

  constructor(private http: HttpClient) { }

  buscarAlojamientosUsuario(): Observable<IAlojamientoDTO[]> {
    return this.http.get<IAlojamientoDTO[]>(environment.api + this.alojamientoAPI);
  }

  buscarAloajmientoByUsername(username: string): Observable<IAlojamientoDTO[]> {
    return this.http.get<IAlojamientoDTO[]>(environment.api + this.alojamientoAPI + "/public/username/" + username);
  }

  buscarAlojamientoById(id:number): Observable<IAlojamientoDTO> {
    return this.http.get<IAlojamientoDTO>(environment.api + this.alojamientoAPI + "/" + id);
  }

  buscarAlojamientoByIdForGestion(id:number): Observable<IAlojamientoDTO> {
    return this.http.get<IAlojamientoDTO>(environment.api + this.alojamientoAPI + "/gestion/" + id);
  }

  aniadirAlojamiento(alojamiento: IAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.alojamientoAPI, alojamiento);
  }

  modificarAlojamiento(alojamiento: IAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.put<IGenericApiMessageDTO>(environment.api + this.alojamientoAPI, alojamiento);
  }
  
  eliminarAlojamiento(id:number): Observable<IGenericApiMessageDTO> {
    return this.http.delete<IGenericApiMessageDTO>(environment.api + this.alojamientoAPI + "/" + id);
  }

}
