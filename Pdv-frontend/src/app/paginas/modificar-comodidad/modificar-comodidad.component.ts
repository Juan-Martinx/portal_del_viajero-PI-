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
  imports: [MatInputModule, MatFormFieldModule, MatIconModule, FormsModule, ReactiveFormsModule, MatSelectModule, RouterLink],
  templateUrl: './modificar-comodidad.component.html',
  styleUrl: './modificar-comodidad.component.css'
})
export class ModificarComodidadComponent implements OnInit{

  IsActionNew: boolean = false;
  tiposComodidades: iTipoComodidadDTO[] = [];
  comodidadModificada?: IComodidadAlojamientoDTO;
  selectedTipoComodidadId = 0;

  constructor(private route: ActivatedRoute, private titleService: Title, private tipoComodidadesService: TipoComodidadService, private comodidadService: ComodidadService, private router: Router) { }

  ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        if(params['action'] == 'new'){
          this.IsActionNew = true;
          this.titleService.setTitle('Añadir Comodidad');
        }else{
          this.buscarComodidadByCodigo(params['codigo']);
        }
      });
      this.cargarTiposComodidades();
  }

  comodidadesForm = new FormGroup({
    icono: new FormControl('', Validators.required),
    descripcion: new FormControl('', Validators.required),
    nombre: new FormControl('', Validators.required),
    codigo: new FormControl('', Validators.required),
    nombreTipoComodidad: new FormControl(this.selectedTipoComodidadId, Validators.required)
  });

  cargarTiposComodidades(){
    this.tipoComodidadesService.buscarTiposComodidades().subscribe(tiposComodidades => {
      this.tiposComodidades = tiposComodidades;
    });
  }

  buscarComodidadByCodigo(codigo: string){
    this.comodidadService.buscarComodidadPorCodigo(codigo).subscribe(comodidad => {
      this.comodidadModificada = comodidad;
      this.comodidadesForm.get('icono')?.setValue(comodidad.iconoComodidad as string);
      this.comodidadesForm.get('descripcion')?.setValue(comodidad.txtDescripcion as string);
      this.comodidadesForm.get('nombre')?.setValue(comodidad.txtNombre as string);
      this.comodidadesForm.get('codigo')?.setValue(comodidad.codigoComodidad as string);
      this.selectedTipoComodidadId = (comodidad.idTipoComodidad?.id as number);
      this.comodidadesForm.patchValue({
        nombreTipoComodidad: this.selectedTipoComodidadId
      });
    });
  }

  aniadirComodidad(){
    if(this.comodidadesForm.valid){
      this.comodidadService.aniadirComodidad(this.obtenerDatosComodidad()).subscribe(mensaje=> {
        alert(mensaje.mensaje + "\n\r Con fecha " + mensaje.fechaYHora);
        this.router.navigate(['/buscador-comodidades']);
      }, err => {
        alert("Ya existe una comodidad con ese código.");
      });
    }else{
      alert("Formulario no válido");
    }
  }

  modificarComodidad(){
    if(this.comodidadesForm.valid){
      this.comodidadService.modificarComodidad(this.obtenerDatosComodidad()).subscribe(mensaje=> {
        alert(mensaje.mensaje + "\n\r Con fecha " + mensaje.fechaYHora);
        this.router.navigate(['/buscador-comodidades']);
      }, err => {
        alert("El código que le está intentando asignar a esta comodida ya pertenece a otra.");
      });
    }else{
      alert("Formulario no válido");
    }
  }

  obtenerDatosComodidad(): IComodidadAlojamientoDTO{
    let comodidad: IComodidadAlojamientoDTO = {};
    if(this.IsActionNew){
      comodidad = {
        iconoComodidad: this.comodidadesForm.get('icono')?.value as string,
        codigoComodidad: this.comodidadesForm.get('codigo')?.value as string,
        txtDescripcion: this.comodidadesForm.get('descripcion')?.value as string,
        txtNombre: this.comodidadesForm.get('nombre')?.value as string,
        idTipoComodidad: {id: this.comodidadesForm.get('nombreTipoComodidad')?.value as number}
      }
    }else{
      comodidad = {
        id: this.comodidadModificada?.id as number,
        iconoComodidad: this.comodidadesForm.get('icono')?.value as string,
        codigoComodidad: this.comodidadesForm.get('codigo')?.value as string,
        txtDescripcion: this.comodidadesForm.get('descripcion')?.value as string,
        txtNombre: this.comodidadesForm.get('nombre')?.value as string,
        idTipoComodidad: {id: this.comodidadesForm.get('nombreTipoComodidad')?.value as number}
      }
    }
    return comodidad;
  }
}
