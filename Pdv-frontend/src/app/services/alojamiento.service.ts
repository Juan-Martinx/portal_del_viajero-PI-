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

  buscarAlojamientoById(id:number): Observable<IAlojamientoDTO> {
    return this.http.get<IAlojamientoDTO>(environment.api + this.alojamientoAPI + "/" + id);
  }

  aniadirAlojamiento(comodidad: IAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.alojamientoAPI, comodidad);
  }
}
