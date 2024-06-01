import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { IComodidadAlojamientoDTO } from '../../../dto/IComodidadAlojamientoDTO';
import { ComodidadService } from '../../services/comodidad.service';
import { Router, RouterLink} from '@angular/router';
@Component({
  selector: 'app-buscar-comodidad',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, RouterLink ],
  templateUrl: './buscar-comodidad.component.html',
  styleUrl: './buscar-comodidad.component.css'
})
export class BuscarComodidadComponent implements OnInit{

  constructor(private comodidadService: ComodidadService, private router: Router){}
  comodidades: IComodidadAlojamientoDTO[] = [];

  ngOnInit(): void {
      this.comodidadService.buscarTodasComodidades().subscribe(comodidades => {
        this.comodidades = comodidades;
      });
  }
  comodidadesForm = new FormGroup({
    nombre: new FormControl(''),
    codigo: new FormControl(''),
    codigoTipoComodidad: new FormControl(''),
    nombreTipoComodidad: new FormControl('')
  });

  modificarComodidad(codigo?: string){
    this.router.navigate(['/modificar-comodidad'], { queryParams: { codigo: codigo } });
  }

  eliminarComodidad(codigo?: string){
    if(confirm("¿Estas seguro de eliminar esta comodidad?")){
      this.comodidadService.eliminarComodidad(codigo as string).subscribe(mensaje => {
        alert(mensaje.mensaje);
        location.reload();
      });
    }
  }
}
