import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environments';
import { Router, RouterLink } from '@angular/router';
import { HttpParams } from '@angular/common/http';
import { TokenService } from '../../services/token.service';
import * as CryptoJS from 'crypto-js';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import { MatIconModule} from '@angular/material/icon';

const CHARACTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [MatButtonModule, MatMenuModule, MatIconModule, RouterLink],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})

export class MenuComponent implements OnInit {

  /* OAUTH CONFIGURATIONS */
  authorize_uri = environment.authorizeUri;
  logout_url = environment.logout_url;

  isLogged?: boolean;
  isAdmin?: boolean;

  params: any = {
    client_id: environment.client_id,
    redirect_uri: environment.redirect_uri,
    scope: environment.scope,
    response_type: environment.response_type,
    response_mode: environment.response_mode,
    code_challenge_method: environment.code_challenge_method
  }

  constructor(
    private tokenService: TokenService
  ) { }

  ngOnInit(): void {
    this.getLogged();
  }

  onLogin(): void {
    this.tokenService.onLogin();
  }

  onLogout(): void {
    location.href = this.logout_url;
  }

  getLogged(): void {
    this.isLogged = this.tokenService.isLogged();
    this.isAdmin = this.tokenService.isAdmin();
  }
  /*  OAUTH CONFIGURATION END */
}
