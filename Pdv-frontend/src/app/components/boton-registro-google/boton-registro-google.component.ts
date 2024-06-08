import { Component } from '@angular/core';
import { environment } from '../../../environments/environments';
import { TokenService } from '../../services/token.service';
import { HttpParams } from '@angular/common/http';
import * as CryptoJS from 'crypto-js';

const CHARACTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

@Component({
  selector: 'app-boton-registro-google',
  standalone: true,
  imports: [],
  templateUrl: './boton-registro-google.component.html',
  styleUrl: './boton-registro-google.component.css'
})
export class BotonRegistroGoogleComponent {

      
  constructor(private tokenService: TokenService) { }

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

    onLogin(): void {
      const code_verifier = this.generateCodeVerifier();
      this.tokenService.setVerifier(code_verifier);
      this.params.code_challenge = this.generateCodeChallenge(code_verifier);
      const httpParams = new HttpParams({fromObject: this.params});
      const codeUrl = this.authorize_uri + httpParams.toString();
      location.href = codeUrl;
    }

    generateCodeVerifier(): string {
      let result = '';
      const char_length = CHARACTERS.length;
      for(let i =0; i < 44; i++) {
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
}
