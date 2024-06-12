import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';
import { environment } from '../../environments/environments';
import { HttpParams } from '@angular/common/http';
import { CodPerfiles } from '../../dto/enumCodPerfiles';

const ACCESS_TOKEN = 'access_token';
const REFRESH_TOKEN = 'refresh_token';
const CODE_VERIFIER = 'code_verifier';
const CHARACTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  /* OAUTH CONFIGURATIONS */
  authorize_uri = environment.authorizeUri;
  logout_url = environment.logout_url;

  params: any = {
    client_id: environment.client_id,
    redirect_uri: environment.redirect_uri,
    scope: environment.scope,
    response_type: environment.response_type,
    response_mode: environment.response_mode,
    code_challenge_method: environment.code_challenge_method
  }

  constructor() { }

    /**
   * Establece los tokens de acceso y de actualización en el almacenamiento local.
   * @param access_token Token de acceso.
   * @param refresh_token Token de actualización.
   */
  setTokens(access_token: string, refresh_token: string): void {
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.setItem(ACCESS_TOKEN, access_token);
    localStorage.removeItem(REFRESH_TOKEN);
    localStorage.setItem(REFRESH_TOKEN, refresh_token);
  }

  /**
   * Obtiene el token de acceso del almacenamiento local.
   * @returns El token de acceso o null si no está presente.
   */
  getAccessToken(): string | null {
    return localStorage.getItem(ACCESS_TOKEN);
  }

  /**
   * Obtiene el token de actualización del almacenamiento local.
   * @returns El token de actualización o null si no está presente.
   */
  getRefreshToken(): string | null {
    return localStorage.getItem(REFRESH_TOKEN);
  }

  /**
   * Borra los tokens de acceso y de actualización del almacenamiento local.
   */
  clear(): void {
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.removeItem(REFRESH_TOKEN);
  }

  /**
   * Verifica si el usuario ha iniciado sesión.
   * @returns true si el usuario ha iniciado sesión, de lo contrario false.
   */
  isLogged(): boolean {
    return localStorage.getItem(ACCESS_TOKEN) != null;
  }

  isCliente(): boolean {
    return this.tokenWithProfile(CodPerfiles.CLIENTE);
  }


  isGestor(): boolean {
    return this.tokenWithProfile(CodPerfiles.GESTOR);
  }

  isAdmin(): boolean {
    return this.tokenWithProfile(CodPerfiles.ADMIN);
  }

  isGoogleUser(): boolean {
    return this.tokenWithProfile(CodPerfiles.GOOGLE_USER);
  }

  /**
   * Recibe un perfil y verifica si el token tiene ese perfil.
   * @param profile 
   * @returns 
   */
  tokenWithProfile(profile: string): boolean {
    if (!this.isLogged()) {
      return false;
    }
    const token = this.getAccessToken();
    const payload = token?.split(".")[1] as string;
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const perfiles = values.perfiles;
    if (perfiles.indexOf(profile) < 0) {
      return false;
    }
    return true;
  }

  /**
   * Establece el código de verificación en el almacenamiento local.
   * @param code_verifier Código de verificación generado.
   */
  setVerifier(code_verifier: string): void {
    if (localStorage.getItem(CODE_VERIFIER)) {
      this.deleteVerifier();
    }
    const encrypted = CryptoJS.AES.encrypt(code_verifier, environment.secret_pkce);
    localStorage.setItem(CODE_VERIFIER, encrypted.toString());
  }

  /**
   * Obtiene el código de verificación del almacenamiento local.
   * @returns El código de verificación o una cadena vacía si no está presente.
   */
  getVerifier(): string {
    const encrypted = localStorage.getItem(CODE_VERIFIER) as string;
    const decrypted = CryptoJS.AES.decrypt(encrypted, environment.secret_pkce).toString(CryptoJS.enc.Utf8);
    return decrypted;
  }
  
  /**
   * Elimina el código de verificación del almacenamiento local.
   */
  deleteVerifier(): void {
    localStorage.removeItem(CODE_VERIFIER);
  }

  /**
   * Genera un código de verificación aleatorio.
   * @returns El código de verificación generado.
   */
  generateCodeVerifier(): string {
    let result = '';
    const char_length = CHARACTERS.length;
    for (let i = 0; i < 44; i++) {
      result += CHARACTERS.charAt(Math.floor(Math.random() * char_length));
    }
    return result;
  }

  /**
   * Genera un desafío de código a partir del código de verificación.
   * @param code_verifier Código de verificación.
   * @returns El desafío de código generado.
   */
  generateCodeChallenge(code_verifier: string): string {
    const codeverifierHash = CryptoJS.SHA256(code_verifier).toString(CryptoJS.enc.Base64);
    const code_challenge = codeverifierHash
      .replace(/=/g, '')
      .replace(/\+/g, '-')
      .replace(/\//g, '_');
    return code_challenge;
  }

  /**
   * Inicia el proceso de autenticación.
   * Genera un código de verificación, lo establece y construye la URL de autorización.
   */
  onLogin(): void {
    const code_verifier = this.generateCodeVerifier();
    this.setVerifier(code_verifier);
    this.params.code_challenge = this.generateCodeChallenge(code_verifier);
    const httpParams = new HttpParams({ fromObject: this.params });
    const codeUrl = this.authorize_uri + httpParams.toString();
    location.href = codeUrl;
  }

}
