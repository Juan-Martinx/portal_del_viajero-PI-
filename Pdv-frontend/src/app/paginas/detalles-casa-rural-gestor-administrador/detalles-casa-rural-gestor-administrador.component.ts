import { Component, NgModule, OnInit } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, NgModel } from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { IComodidadAlojamientoDTO } from '../../../dto/IComodidadAlojamientoDTO';
import { CodTipoComodidad } from '../../../dto/enumCodTipoComodidad';
import { ComodidadService } from '../../services/comodidad.service';
import { IPageableDTO } from '../../../dto/IPageableDTO';
import { computeMsgId } from '@angular/compiler';
import { IAlojamientoDTO } from '../../../dto/IAlojamientoDTO';
import { AlojamientoService } from '../../services/alojamiento.service';

@Component({
  selector: 'app-detalles-casa-rural-gestor-administrador',
  standalone: true,
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule],
  templateUrl: './detalles-casa-rural-gestor-administrador.component.html',
  styleUrl: './detalles-casa-rural-gestor-administrador.component.css'
})

export class DetallesCasaRuralGestorAdministradorComponent implements OnInit {

  codTipoComodidadComodidad: CodTipoComodidad = CodTipoComodidad.COMODIDAD;
  codTipoComodidadInstalacion: CodTipoComodidad = CodTipoComodidad.INSTALACION;

  isActionNew: boolean = false;
  paginaActual = 0;
  editarTituloEnable = false;
  comodidades: IComodidadAlojamientoDTO[] = [];
  alojamientoComodidades: IComodidadAlojamientoDTO[] = [];
  alojamientoInstalaciones: IComodidadAlojamientoDTO[] = [];

  constructor(private route: ActivatedRoute, private comodidadService: ComodidadService, private alojamientoService: AlojamientoService) { }

  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      if (params['action'] == 'new') {
        this.isActionNew = true;
      }
    });
  }

  alojamientoForm = new FormGroup({
    titulo: new FormControl('TÍTULO DE CASA RURAL', [Validators.required, Validators.maxLength(50)]),
    descripcion: new FormControl('Descripción de la casa rural', [Validators.required]),
    precioNoche: new FormControl(10, [Validators.required, Validators.min(10)]),
    numeroMinimoHuespedes: new FormControl(1, [Validators.required, Validators.min(1)]),
    numeroMaximoHuespedes: new FormControl(2, [Validators.required, Validators.min(2)]),
    selectedComodidadNombre: new FormControl(''),
    lineaDireccion: new FormControl('', [Validators.required]),
    codigoPostal: new FormControl(0, [Validators.required]),
    provincia: new FormControl('', [Validators.required])
  });

  buscarComodidades(avanzarPagina: boolean,codigoTipoComodidad: CodTipoComodidad){

    const comodidad: IComodidadAlojamientoDTO = {
      txtNombre: this.alojamientoForm.get('selectedComodidadNombre')?.value as string,
      idTipoComodidad: {codigoTipoComodidad: codigoTipoComodidad}
    }
    
    const pageable: IPageableDTO = {
      page: this.paginaActual,
      size: 5
    }

    this.comodidadService.buscarComodidades(comodidad, pageable).subscribe(comodidades => {
      if (comodidades.length == 0) {
        this.paginaActual = avanzarPagina? --this.paginaActual : ++this.paginaActual;
        alert("No hay resultados para mostrar");
      } else {
        this.comodidades = comodidades;
      }
    });
  }

  cambiarPage(codigoTipoComodidad: CodTipoComodidad ,page: number) {
    if(this.paginaActual < page){
      this.paginaActual = page;
      this.buscarComodidades(true, codigoTipoComodidad);
    }else{
      this.paginaActual==0? 0 : this.paginaActual = page;
      this.buscarComodidades(false, codigoTipoComodidad);
    }
  }

  aniadirComodidad(comodidad: IComodidadAlojamientoDTO){
    if(comodidad.idTipoComodidad?.codigoTipoComodidad == CodTipoComodidad.COMODIDAD && this.alojamientoComodidades.filter(comodidadAlojamiento => comodidadAlojamiento.id == comodidad.id).length == 0){
      this.alojamientoComodidades.push(comodidad);
    }else if(comodidad.idTipoComodidad?.codigoTipoComodidad == CodTipoComodidad.INSTALACION && this.alojamientoInstalaciones.filter(comodidadAlojamiento => comodidadAlojamiento.id == comodidad.id).length == 0){
      this.alojamientoInstalaciones.push(comodidad);
    }
  }

  guardarAlojamiento(){
    if(this.alojamientoForm.valid){
      const alojamiento: IAlojamientoDTO = this.getAlojamiento();
      this.alojamientoService.aniadirAlojamiento(alojamiento).subscribe(response => {
        alert(response.mensaje);
      });
    }else{
      alert("Rellene todos los campos obligatorios");
    }
  }

  getAlojamiento(): IAlojamientoDTO{

    const idComodidades: number[] = [];
    this.alojamientoComodidades.forEach(comodidad => idComodidades.push(comodidad.id as number));
    this.alojamientoInstalaciones.forEach(comodidad => idComodidades.push(comodidad.id as number));

    const alojamiento: IAlojamientoDTO = {
      txtNombre: this.alojamientoForm.get('titulo')?.value as string,
      txtDescripcion: this.alojamientoForm.get('descripcion')?.value as string,
      numPlazaMin: this.alojamientoForm.get('numeroMinimoHuespedes')?.value as number,
      numPlazaMax: this.alojamientoForm.get('numeroMaximoHuespedes')?.value as number,
      numPrecioNoche: this.alojamientoForm.get('precioNoche')?.value as number,
      idUbicacion: {
        lineaDireccion: this.alojamientoForm.get('lineaDireccion')?.value as string,
        codigoPostal: this.alojamientoForm.get('codigoPostal')?.value as number,
        provincia: this.alojamientoForm.get('provincia')?.value as string
      },
      idComodidades: idComodidades
    }
    return alojamiento;
  }

}