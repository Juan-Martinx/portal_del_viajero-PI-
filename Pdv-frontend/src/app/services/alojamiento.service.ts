import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IGenericApiMessageDTO } from '../../dto/IGenericApiMessageDTO';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';
import { IAlojamientoDTO } from '../../dto/IAlojamientoDTO';
import { IPageableDTO } from '../../dto/IPageableDTO';

@Injectable({
  providedIn: 'root'
})
export class AlojamientoService {

  alojamientoAPI = "/alojamiento";

  constructor(private http: HttpClient) { }

  buscarAlojamientosUsuario(): Observable<IAlojamientoDTO[]> {
    return this.http.get<IAlojamientoDTO[]>(environment.api + this.alojamientoAPI);
  }

  buscarAloajmientoWithFilters(provincia?: any, numPrecioNocheMin?: any, numPrecioNocheMax?: any,fechaLlegada?: any, fechaSalida?: any, idComodidades?: number[], page?: IPageableDTO): Observable<IAlojamientoDTO[]> {
    provincia = typeof provincia === 'string' ? provincia : "";
    const formattedFechaLlegada = fechaLlegada instanceof Date ? `${fechaLlegada.getFullYear()}-${String(fechaLlegada.getMonth() + 1).padStart(2, '0')}-${String(fechaLlegada.getDate()).padStart(2, '0')}` : "";
    const formattedFechaSalida = fechaSalida instanceof Date ? `${fechaSalida.getFullYear()}-${String(fechaSalida.getMonth() + 1).padStart(2, '0')}-${String(fechaSalida.getDate()).padStart(2, '0')}` : "";
    numPrecioNocheMin = typeof numPrecioNocheMin === 'number' ? numPrecioNocheMin : "";
    numPrecioNocheMax = typeof numPrecioNocheMax === 'number' ? numPrecioNocheMax : "";

    // Formatear la lista de idComodidades
    const formattedIdComodidades = idComodidades && idComodidades.length > 0 ? idComodidades.join(',') : "";

    // Construir la URL con los parámetros
    const url = `${environment.api}${this.alojamientoAPI}/public/findWithFilters?` +
        `numPrecioNocheMin=${numPrecioNocheMin}&` +
        `numPrecioNocheMax=${numPrecioNocheMax}&` +
        `fechaLlegada=${formattedFechaLlegada}&` +
        `fechaSalida=${formattedFechaSalida}&` +
        `idComodidades=${formattedIdComodidades}&` +
        `provincia=${provincia}&` +
        `page=${page?.page}&` +
        `size=${page?.size}`;

    return this.http.get<IAlojamientoDTO[]>(url);
  }

  buscarAloajmientoByUsername(username: string): Observable<IAlojamientoDTO[]> {
    return this.http.get<IAlojamientoDTO[]>(environment.api + this.alojamientoAPI + "/public/username/" + username);
  }

  buscarAlojamientoById(id:number): Observable<IAlojamientoDTO> {
    return this.http.get<IAlojamientoDTO>(environment.api + this.alojamientoAPI + "/public/" + id);
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
