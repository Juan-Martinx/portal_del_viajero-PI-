import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../services/token.service';
@Component({
  selector: 'app-logout',
  standalone: true,
  imports: [],
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent implements OnInit {

  constructor(
    private router: Router,
    private tokenService: TokenService
  ) { }

  /**
   * Método para cerrar sesión.
   */
  ngOnInit(): void {
    this.tokenService.clear();
    this.router.navigate(['']).then(() => {window.location.reload();});
  }
}