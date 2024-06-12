import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environments';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  tokne_url = environment.token_uri;

  constructor(private httpClient: HttpClient) { }

    /**
   * Obtiene el token de acceso mediante el intercambio de código de autorización.
   * @param code El código de autorización recibido del proveedor de OAuth.
   * @param code_verifier El valor del código de verificación utilizado para proteger contra ataques de código de autorización.
   * @returns Un Observable que emite el token de acceso y otros detalles de autenticación.
   */
  public getToken(code: string, code_verifier: string): Observable<any> {
    let body = new URLSearchParams();
    body.set('grant_type', environment.grant_type);
    body.set('client_id', environment.client_id);
    body.set('redirect_uri', environment.redirect_uri);
    body.set('scope', environment.scope);
    body.set('code_verifier', code_verifier);
    body.set('code', code);
    const basic_auth = 'Basic '+ btoa('client:secret');
    const headers_object = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Accept': '*/*',
      'Authorization': basic_auth
    });
    const httpOptions = { headers: headers_object};
    return this.httpClient.post<any>(this.tokne_url, body, httpOptions);
  }
  
}