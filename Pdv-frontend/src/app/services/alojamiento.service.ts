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

    /**
   * Busca los alojamientos del usuario actual.
   * @returns Un Observable que emite una matriz de objetos IAlojamientoDTO.
   */
  buscarAlojamientosUsuario(): Observable<IAlojamientoDTO[]> {
    return this.http.get<IAlojamientoDTO[]>(environment.api + this.alojamientoAPI);
  }

    /**
   * Busca alojamientos con filtros opcionales.
   * @param provincia La provincia del alojamiento.
   * @param numPrecioNocheMin El precio mínimo por noche.
   * @param numPrecioNocheMax El precio máximo por noche.
   * @param fechaLlegada La fecha de llegada.
   * @param fechaSalida La fecha de salida.
   * @param idComodidades Los IDs de las comodidades.
   * @param page La información de paginación.
   * @returns Un Observable que emite una matriz de objetos IAlojamientoDTO.
   */
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

    /**
   * Busca alojamientos por nombre de usuario.
   * @param username El nombre de usuario.
   * @returns Un Observable que emite una matriz de objetos IAlojamientoDTO.
   */
  buscarAloajmientoByUsername(username: string): Observable<IAlojamientoDTO[]> {
    return this.http.get<IAlojamientoDTO[]>(environment.api + this.alojamientoAPI + "/public/username/" + username);
  }

  /**
   * Busca un alojamiento por su ID.
   * @param id El ID del alojamiento.
   * @returns Un Observable que emite un objeto IAlojamientoDTO.
   */
  buscarAlojamientoById(id:number): Observable<IAlojamientoDTO> {
    return this.http.get<IAlojamientoDTO>(environment.api + this.alojamientoAPI + "/public/" + id);
  }

  /**
   * Busca un alojamiento por su ID para gestión.
   * @param id El ID del alojamiento.
   * @returns Un Observable que emite un objeto IAlojamientoDTO.
   */
  buscarAlojamientoByIdForGestion(id:number): Observable<IAlojamientoDTO> {
    return this.http.get<IAlojamientoDTO>(environment.api + this.alojamientoAPI + "/gestion/" + id);
  }

  /**
   * Añade un nuevo alojamiento.
   * @param alojamiento El objeto IAlojamientoDTO que representa el alojamiento a añadir.
   * @returns Un Observable que emite un objeto IGenericApiMessageDTO como mensaje de respuesta.
   */
  aniadirAlojamiento(alojamiento: IAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.alojamientoAPI, alojamiento);
  }

  /**
   * Modifica un alojamiento existente.
   * @param alojamiento El objeto IAlojamientoDTO que representa el alojamiento a modificar.
   * @returns Un Observable que emite un objeto IGenericApiMessageDTO como mensaje de respuesta.
   */
  modificarAlojamiento(alojamiento: IAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.put<IGenericApiMessageDTO>(environment.api + this.alojamientoAPI, alojamiento);
  }

  /**
   * Elimina un alojamiento por su ID.
   * @param id El ID del alojamiento a eliminar.
   * @returns Un Observable que emite un objeto IGenericApiMessageDTO como mensaje de respuesta.
   */
  eliminarAlojamiento(id:number): Observable<IGenericApiMessageDTO> {
    return this.http.delete<IGenericApiMessageDTO>(environment.api + this.alojamientoAPI + "/" + id);
  }

}
