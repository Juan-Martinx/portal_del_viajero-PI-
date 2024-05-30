import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';
import { environment } from '../../environments/environments';
import { HttpParams } from '@angular/common/http';

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

  setTokens(access_token: string, refresh_token: string): void {
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.setItem(ACCESS_TOKEN, access_token);
    localStorage.removeItem(REFRESH_TOKEN);
    localStorage.setItem(REFRESH_TOKEN, refresh_token);
  }

  getAccessToken(): string | null {
    return localStorage.getItem(ACCESS_TOKEN);
  }

  getRefreshToken(): string | null {
    return localStorage.getItem(REFRESH_TOKEN);
  }

  clear(): void {
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.removeItem(REFRESH_TOKEN);
  }

  isLogged(): boolean {
    return localStorage.getItem(ACCESS_TOKEN) != null;
  }

  isCliente(): boolean {
    return this.tokenWithProfile('PERFIL_CLIENTE');
  }

  isGestor(): boolean {
    return this.tokenWithProfile('PERFIL_GESTOR');
  }

  isAdmin(): boolean {
    return this.tokenWithProfile('PERFIL_ADMIN');
  }

  isGoogleUser(): boolean {
    return this.tokenWithProfile('OIDC_USER');
  }

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

  getInfoFromToken(infoSolicitada: string): string{
    if (!this.isLogged()) {
      return "";
    }
    const token = this.getAccessToken();
    const payload = token?.split(".")[1] as string;
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const info = values[infoSolicitada];
    return info;
  }

  setVerifier(code_verifier: string): void {
    if (localStorage.getItem(CODE_VERIFIER)) {
      this.deleteVerifier();
    }
    const encrypted = CryptoJS.AES.encrypt(code_verifier, environment.secret_pkce);
    localStorage.setItem(CODE_VERIFIER, encrypted.toString());
  }

  getVerifier(): string {
    const encrypted = localStorage.getItem(CODE_VERIFIER) as string;
    const decrypted = CryptoJS.AES.decrypt(encrypted, environment.secret_pkce).toString(CryptoJS.enc.Utf8);
    return decrypted;
  }

  deleteVerifier(): void {
    localStorage.removeItem(CODE_VERIFIER);
  }

  generateCodeVerifier(): string {
    let result = '';
    const char_length = CHARACTERS.length;
    for (let i = 0; i < 44; i++) {
      result += CHARACTERS.charAt(Math.floor(Math.random() * char_length));
    }
    return result;
  }

  generateCodeChallenge(code_verifier: string): string {
    const codeverifierHash = CryptoJS.SHA256(code_verifier).toString(CryptoJS.enc.Base64);
    const code_challenge = codeverifierHash
      .replace(/=/g, '')
      .replace(/\+/g, '-')
      .replace(/\//g, '_');
    return code_challenge;
  }

  onLogin(): void {
    const code_verifier = this.generateCodeVerifier();
    this.setVerifier(code_verifier);
    this.params.code_challenge = this.generateCodeChallenge(code_verifier);
    const httpParams = new HttpParams({ fromObject: this.params });
    const codeUrl = this.authorize_uri + httpParams.toString();
    location.href = codeUrl;
  }

}
