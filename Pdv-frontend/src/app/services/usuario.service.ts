import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IUsuarioDTO } from '../../dto/IUsuarioDTO';
import { environment } from '../../environments/environments';
import { IGenericApiMessageDTO } from '../../dto/IGenericApiMessageDTO';

@Injectable({
  providedIn: 'root'
})

export class UsuarioService {


  constructor(private http: HttpClient) { }

  crearUsuario(usuario: IUsuarioDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.oauth_api + "pdv-backend/auth/crear", usuario);
  }

  buscarUsuarioLogueado(): Observable<IUsuarioDTO>{
    return this.http.get<IUsuarioDTO>(environment.api + '/usuarios');
  }

  editarUsuario(usuario: IUsuarioDTO): Observable<IGenericApiMessageDTO>{
    return this.http.put<IGenericApiMessageDTO>(environment.api + '/usuarios/editar', usuario);
  }

  convertirEnGestor(id: number): Observable<IGenericApiMessageDTO>{
    return this.http.post<IGenericApiMessageDTO>(environment.api + '/usuarios/convertir-gestor?id=' + id, null);
  }

}
