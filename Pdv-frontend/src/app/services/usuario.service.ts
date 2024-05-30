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


  constructor(private http: HttpClient, private tokenService: TokenService) { }

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

  buscarUsuariosApp(usuario: IUsuarioDTO, pageable: IPageableDTO): Observable<IUsuarioDTO[]>{
    return this.http.get<IUsuarioDTO[]>(environment.api + `/usuarios/buscar?username=${usuario.username}&txtDni=${usuario.txtDni}&txtEmail=${usuario.txtEmail}&numTelefono=${usuario.numTelefono}&page=${pageable.page}&size=${pageable.size}`);
  }
}
