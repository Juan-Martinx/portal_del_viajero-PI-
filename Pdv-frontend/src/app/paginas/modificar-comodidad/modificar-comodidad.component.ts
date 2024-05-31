import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { ActivatedRoute, Route, RouterLink, RouterLinkActive } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TipoComodidadService } from '../../services/tipo-comodidad.service';
import { iTipoComodidadDTO } from '../../../dto/ITipoComodidadDTO';
import { ComodidadService } from '../../services/comodidad.service';
import { IComodidadAlojamientoDTO } from '../../../dto/IComodidadAlojamientoDTO';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modificar-comodidad',
  standalone: true,
  imports: [MatInputModule, MatFormFieldModule, MatIconModule, FormsModule, ReactiveFormsModule, MatSelectModule],
  templateUrl: './modificar-comodidad.component.html',
  styleUrl: './modificar-comodidad.component.css'
})
export class ModificarComodidadComponent implements OnInit{

  IsActionNew: boolean = false;
  tiposComodidades: iTipoComodidadDTO[] = [];

  constructor(private route: ActivatedRoute, private titleService: Title, private tipoComodidadesService: TipoComodidadService, private comodidadService: ComodidadService, private router: Router) { }

  ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        if(params['action'] == 'new'){
          this.IsActionNew = true;
          this.titleService.setTitle('Añadir Comodidad');
        }
      });
      this.cargarTiposComodidades();
  }

  comodidadesForm = new FormGroup({
    icono: new FormControl('', Validators.required),
    descripcion: new FormControl('', Validators.required),
    nombre: new FormControl('', Validators.required),
    codigo: new FormControl('', Validators.required),
    nombreTipoComodidad: new FormControl('')
  });

  cargarTiposComodidades(){
    this.tipoComodidadesService.buscarTiposComodidades().subscribe(tiposComodidades => {
      this.tiposComodidades = tiposComodidades;
    });
  }

  aniadirComodidad(){
    if(this.comodidadesForm.valid){
      const comodidad: IComodidadAlojamientoDTO = {
        codigoComodidad: this.comodidadesForm.get('codigo')?.value as string,
        txtDescripcion: this.comodidadesForm.get('descripcion')?.value as string,
        txtNombre: this.comodidadesForm.get('nombre')?.value as string,
        idTipoComodidad: {id: parseInt(this.comodidadesForm.get('nombreTipoComodidad')?.value as string) as number}
      }
      this.comodidadService.aniadirComodidad(comodidad).subscribe(mensaje=> {
        alert(mensaje.mensaje + "\n\r Con fecha " + mensaje.fechaYHora);
        this.router.navigate(['/buscador-comodidades']);
      }, err => {
        alert("Ya existe una comodidad con ese código.");
      });
    }else{
      alert("Formulario no válido");
    }
  }
}
