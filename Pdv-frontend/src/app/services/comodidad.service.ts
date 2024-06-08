import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { iTipoComodidadDTO } from '../../dto/ITipoComodidadDTO';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';
import { IComodidadAlojamientoDTO } from '../../dto/IComodidadAlojamientoDTO';
import { IGenericApiMessageDTO } from '../../dto/IGenericApiMessageDTO';
import { IPageableDTO } from '../../dto/IPageableDTO';
@Injectable({
  providedIn: 'root'
})
export class ComodidadService {

  comodidadAPI = "/comodidad-alojamiento";

  constructor(private http: HttpClient) { }

    /**
   * Añade una nueva comodidad de alojamiento.
   * @param comodidad La comodidad de alojamiento a añadir.
   * @returns Un Observable que emite un mensaje genérico de la API.
   */
  aniadirComodidad(comodidad: IComodidadAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.comodidadAPI, comodidad);
  }
  /**
   * Modifica una comodidad de alojamiento existente.
   * @param comodidad La comodidad de alojamiento modificada.
   * @returns Un Observable que emite un mensaje genérico de la API.
   */
  modificarComodidad(comodidad: IComodidadAlojamientoDTO): Observable<IGenericApiMessageDTO> {
    return this.http.put<IGenericApiMessageDTO>(environment.api + this.comodidadAPI, comodidad);
  }

  /**
   * Elimina una comodidad de alojamiento por su código.
   * @param codigo El código de la comodidad a eliminar.
   * @returns Un Observable que emite un mensaje genérico de la API.
   */
  eliminarComodidad(codigo: string): Observable<IGenericApiMessageDTO> {
    return this.http.delete<IGenericApiMessageDTO>(environment.api + this.comodidadAPI + `/${codigo}`);
  }

  /**
   * Busca una comodidad de alojamiento por su código.
   * @param codigo El código de la comodidad a buscar.
   * @returns Un Observable que emite la comodidad de alojamiento encontrada.
   */
  buscarComodidadPorCodigo(codigo: string): Observable<IComodidadAlojamientoDTO> {
    return this.http.get<IComodidadAlojamientoDTO>(environment.api + this.comodidadAPI + `/${codigo}`);
  }
  
  /**
   * Busca comodidades de alojamiento con filtros opcionales y paginación.
   * @param comodidad La comodidad de alojamiento con filtros opcionales.
   * @param page La información de paginación.
   * @returns Un Observable que emite un array de comodidades de alojamiento que coinciden con los criterios de búsqueda.
   */
  buscarComodidades(comodidad: IComodidadAlojamientoDTO, page: IPageableDTO): Observable<IComodidadAlojamientoDTO[]> {
    if(comodidad.idTipoComodidad?.id){
      return this.http.get<IComodidadAlojamientoDTO[]>(environment.api + this.comodidadAPI + "/public/" + `?txtNombre=${comodidad.txtNombre}&codigoComodidad=${comodidad.codigoComodidad}&codigoTipoComodidad=&tipoComodidadId=${comodidad.idTipoComodidad?.id}&page=${page.page}&size=${page.size}`);
    }else{
      return this.http.get<IComodidadAlojamientoDTO[]>(environment.api + this.comodidadAPI + "/public/" + `?txtNombre=${comodidad.txtNombre}&codigoComodidad=&codigoTipoComodidad=${comodidad.idTipoComodidad?.codigoTipoComodidad}&page=${page.page}&size=${page.size}`);
    }
  }
}
