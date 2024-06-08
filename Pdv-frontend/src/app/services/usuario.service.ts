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

  /**
   * Sube una foto de perfil para el usuario.
   * @param url La URL de la foto de perfil.
   * @returns Un observable con el mensaje genérico de la API.
   */
  subirFotoPerfil(url: string): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.usuariosAPI + "/foto-perfil", {url: url});
  }

  /**
   * Crea un nuevo usuario.
   * @param usuario Los datos del nuevo usuario.
   * @returns Un observable con el mensaje genérico de la API.
   */  
  crearUsuario(usuario: IUsuarioDTO): Observable<IGenericApiMessageDTO> {
    return this.http.post<IGenericApiMessageDTO>(environment.oauth_api + "pdv-oauth/auth/crear", usuario);
  }

  /**
   * Busca el usuario logueado.
   * @returns Un observable con los datos del usuario.
   */
  buscarUsuarioLogueado(): Observable<IUsuarioDTO>{
    return this.http.get<IUsuarioDTO>(environment.api + this.usuariosAPI);
  }

  /**
   * Busca usuarios en la aplicación según los criterios especificados.
   * @param usuario Los datos del usuario a buscar.
   * @param pageable Datos de paginación.
   * @returns Un observable con una lista de usuarios que coinciden con los criterios de búsqueda.
   */
  buscarUsuariosApp(usuario: IUsuarioDTO, pageable: IPageableDTO): Observable<IUsuarioDTO[]>{
    return this.http.get<IUsuarioDTO[]>(environment.api + `${this.usuariosAPI}/buscar?username=${usuario.username}&txtDni=${usuario.txtDni}&txtEmail=${usuario.txtEmail}&page=${pageable.page}&size=${pageable.size}`);
  }

  /**
   * Busca un usuario por su nombre de usuario.
   * @param username El nombre de usuario del usuario a buscar.
   * @returns Un observable con los datos del usuario encontrado.
   */
  buscarUsuarioPorUsername(username: string): Observable<IUsuarioDTO>{
    return this.http.get<IUsuarioDTO>(environment.api + this.usuariosAPI + '/public/' + username);
  }

  /**
   * Busca un usuario por su nombre de usuario para un administrador.
   * @param username El nombre de usuario del usuario a buscar.
   * @returns Un observable con los datos del usuario encontrado.
   */
  buscarUsuarioByAdmin(username: string): Observable<IUsuarioDTO>{
    return this.http.get<IUsuarioDTO>(environment.api + this.usuariosAPI + '/admin/' + username);
  }

  /**
   * Edita los datos de un usuario.
   * @param usuario Los nuevos datos del usuario.
   * @returns Un observable con el mensaje genérico de la API.
   */
  editarUsuario(usuario: IUsuarioDTO): Observable<IGenericApiMessageDTO>{
    return this.http.put<IGenericApiMessageDTO>(environment.api +  this.usuariosAPI + '/editar', usuario);
  }

  /**
   * Edita los datos de otro usuario (para un administrador).
   * @param username El nombre de usuario del usuario a editar.
   * @param usuario Los nuevos datos del usuario.
   * @returns Un observable con el mensaje genérico de la API.
   */
  editarUsuarioOtro(username: string,usuario: IUsuarioDTO): Observable<IGenericApiMessageDTO>{
    return this.http.put<IGenericApiMessageDTO>(environment.api +  this.usuariosAPI + '/editar-otro/' + username, usuario);
  }

  /**
   * Elimina un usuario.
   * @param username El nombre de usuario del usuario a eliminar.
   * @returns Un observable con el mensaje genérico de la API.
   */
  eliminarUsuario(username: string): Observable<IGenericApiMessageDTO>{
    return this.http.delete<IGenericApiMessageDTO>(environment.api +  this.usuariosAPI + "/" + username);
  }
  
  /**
   * Convierte un usuario en gestor.
   * @param id El ID del usuario a convertir.
   * @returns Un observable con el mensaje genérico de la API.
   */
  convertirEnGestor(id: number): Observable<IGenericApiMessageDTO>{
    return this.http.post<IGenericApiMessageDTO>(environment.api + this.usuariosAPI + '/convertir-gestor?id=' + id, null);
  }

}
