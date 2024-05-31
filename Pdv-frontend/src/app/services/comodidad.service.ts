import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { iTipoComodidadDTO } from '../../dto/ITipoComodidadDTO';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';
import { IComodidadAlojamientoDTO } from '../../dto/IComodidadAlojamientoDTO';
import { IGenericApiMessageDTO } from '../../dto/IGenericApiMessageDTO';
@Injectable({
  providedIn: 'root'
})
export class ComodidadService {

  comodidadAPI = "/comodidad-alojamiento";

  constructor(private http: HttpClient) { }

  aniadirComodidad(comodidad: IComodidadAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.comodidadAPI, comodidad);
  }

  buscarComodidadPorCodigo(codigo: string): Observable<IComodidadAlojamientoDTO> {
    return this.http.get<IComodidadAlojamientoDTO>(environment.api + this.comodidadAPI + `/${codigo}`);
  }

  buscarTodasComodidades(): Observable<IComodidadAlojamientoDTO[]> {
    return this.http.get<IComodidadAlojamientoDTO[]>(environment.api + this.comodidadAPI);
  }
}
