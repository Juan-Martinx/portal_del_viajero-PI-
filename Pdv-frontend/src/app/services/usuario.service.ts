import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IUsuarioDTO } from '../../dto/IUsuarioDTO';
import { environment } from '../../environments/environments';
import { IGenericApiMessageDTO } from '../../dto/IGenericApiMessageDTO';
import { TokenService } from './token.service';
import { IPageableDTO } from '../../dto/IPageableDTO';

@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  usuariosAPI = "/usuarios";

  constructor(private http: HttpClient, private tokenService: TokenService) { }

  subirFotoPerfil(url: string): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.usuariosAPI + "/foto-perfil", {url: url});
  }
  
  crearUsuario(usuario: IUsuarioDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.oauth_api + "pdv-backend/auth/crear", usuario);
  }

  buscarUsuarioLogueado(): Observable<IUsuarioDTO>{
    return this.http.get<IUsuarioDTO>(environment.api + this.usuariosAPI);
  }

  buscarUsuariosApp(usuario: IUsuarioDTO, pageable: IPageableDTO): Observable<IUsuarioDTO[]>{
    return this.http.get<IUsuarioDTO[]>(environment.api + `${this.usuariosAPI}/buscar?username=${usuario.username}&txtDni=${usuario.txtDni}&txtEmail=${usuario.txtEmail}&page=${pageable.page}&size=${pageable.size}`);
  }

  buscarUsuarioPorUsername(username: string): Observable<IUsuarioDTO>{
    return this.http.get<IUsuarioDTO>(environment.api + this.usuariosAPI + '/public/' + username);
  }

  editarUsuario(usuario: IUsuarioDTO): Observable<IGenericApiMessageDTO>{
    return this.http.put<IGenericApiMessageDTO>(environment.api +  this.usuariosAPI + '/editar', usuario);
  }

  convertirEnGestor(id: number): Observable<IGenericApiMessageDTO>{
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.usuariosAPI + '/convertir-gestor?id=' + id, null);
  }

}
