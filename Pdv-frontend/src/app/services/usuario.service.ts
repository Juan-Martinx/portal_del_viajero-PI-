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

  login(usuario: IUsuarioDTO): Observable<IGenericApiMessageDTO> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<IGenericApiMessageDTO>(environment.oauth_api + "login", usuario, { headers });
  }
}
