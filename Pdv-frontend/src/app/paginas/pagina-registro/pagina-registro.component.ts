import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';


@Component({
  selector: 'app-pagina-registro',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, MatInputModule, ReactiveFormsModule, MatIconModule],
  templateUrl: './pagina-registro.component.html',
  styleUrl: './pagina-registro.component.css'
})
export class PaginaRegistroComponent {

  usuarios = new FormGroup({
    usuario: new FormControl('', Validators.required),
    contrasena: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(12)]),
    dni: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{8}[A-Z]')]),
    telefono: new FormControl('', [Validators.required, Validators.pattern('^[0-9]{9}')]),
    correoElectronico: new FormControl('', [Validators.required, Validators.email])
  })

}
