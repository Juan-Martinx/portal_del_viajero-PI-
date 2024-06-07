import { Component, OnInit } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule} from '@angular/material/icon';
import {FormsModule} from '@angular/forms';
import { FormGroupDirective, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { FiltroComponent } from '../../components/filtro/filtro.component';
import {MatSliderModule} from '@angular/material/slider';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatMenuModule} from '@angular/material/menu';
import { MenuComponent } from '../../components/menu/menu.component';
import { BarraBusquedaInicioComponent } from '../../components/barra-busqueda-inicio/barra-busqueda-inicio.component';
import { TokenService } from '../../services/token.service';
import { IComodidadAlojamientoDTO } from '../../../dto/IComodidadAlojamientoDTO';
import { ComodidadService } from '../../services/comodidad.service';
import { IPageableDTO } from '../../../dto/IPageableDTO';
import { IAlojamientoDTO } from '../../../dto/IAlojamientoDTO';
import { AlojamientoService } from '../../services/alojamiento.service';

@Component({
  selector: 'app-pagina-inicio',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [MatInputModule, MatIconModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, RouterLink, FiltroComponent
    , MatSliderModule, MatDatepickerModule, MatMenuModule, MatMenuModule, MenuComponent, BarraBusquedaInicioComponent],
  templateUrl: './pagina-inicio.component.html',
  styleUrl: './pagina-inicio.component.css'
})
export class PaginaInicioComponent implements OnInit{

  instalaciones:IComodidadAlojamientoDTO[] = [];
  pageInstalaciones = 0;
  comodidades:IComodidadAlojamientoDTO[] = [];
  pageComodidades = 0;
  paginaActual = 0;
  comodidadesSelected: number[] = [];

  constructor(private tokenService: TokenService, private comodidadService: ComodidadService, private alojamientoService: AlojamientoService, private route: ActivatedRoute){}

  ngOnInit(): void {
    this.buscarComodidades(true);
    this.buscarComodidades(false);
    const provincia = this.route.snapshot.queryParamMap.get('provincia');
    const fechaLlegada = this.route.snapshot.queryParamMap.get('llegada');
    const fechaSalida = this.route.snapshot.queryParamMap.get('salida');
    this.busqueda.get('destino')?.setValue(provincia);
    this.filtros.get('fechaLlegada')?.setValue(fechaLlegada? new Date(fechaLlegada) : "");
    this.filtros.get('fechaSalida')?.setValue(fechaSalida? new Date(fechaSalida) : "");
    this.cambiarPage(0);
  }

  isLogged(): boolean {
    return this.tokenService.isLogged();
  }

  isGestor(): boolean{
    return this.tokenService.isGestor();
  }

  busqueda = new FormGroup({
    destino: new FormControl(''),
  });

  casasRurales: IAlojamientoDTO[] = [];

  formatLabel(value: number): string {
    return `${value}€`;
  }


  filtros = new FormGroup({
    precioMin: new FormControl(0),
    precioMax: new FormControl(1500),
    fechaLlegada: new FormControl("" as string | Date),
    fechaSalida: new FormControl("" as string | Date)
  });

  menu: boolean = true;

  mostrarMenu(){
    this.menu = !this.menu;
    this.comodidadesSelected = [];
  }

  resetearMenu(){
    this.filtros.reset();
    this.mostrarMenu();
  }

  /**
   * Busca las comodidades e instalciones y las 
   * añade en sus respectivas listas
   * @param isSearchingInstalacion 
   */
  buscarComodidades(isSearchingInstalacion: boolean){

    let comodidad: IComodidadAlojamientoDTO = {txtNombre: ""};
    let paginacion: IPageableDTO = {
      size: 10
    }

    if(isSearchingInstalacion){
      comodidad.idTipoComodidad = {codigoTipoComodidad: "INST"};
      paginacion.page = this.pageInstalaciones;
    }else{
      comodidad.idTipoComodidad = {codigoTipoComodidad: "COM"};
      paginacion.page = this.pageComodidades;
    }

    this.comodidadService.buscarComodidades(comodidad, paginacion).subscribe(comodidades => {
      if(comodidades.length != 0){
        if(isSearchingInstalacion){
          this.pageInstalaciones++;
        }else{
          this.pageComodidades++;
        }
        comodidades.forEach(comodidad => {
          if(isSearchingInstalacion){
            this.instalaciones.push(comodidad);
          }else{
            this.comodidades.push(comodidad);
          }
        });
      }else{
        alert("No se han encontrado más resultados");
      }

    });
  }

  /**
   * Añade en una lista aparte las comodidaes seleccionadas
   * @param comodidad
   * @param event 
   */
  filtroCheckboxComodidadEvent(comodidad: IComodidadAlojamientoDTO, event: Event){
    const isChecked = (event.target as HTMLInputElement).checked;
    if(isChecked){
      this.comodidadesSelected.push(comodidad.id as number);
    }else{
      this.comodidadesSelected = this.comodidadesSelected.filter(comodidadSelected => comodidadSelected != comodidad.id);
    }
  }

    // Filtro para las fechas
    salidaFilter = (d: Date | null): boolean => {
      return !d || d > new Date();
    }

    buscarAlojamientoWithFilters(avanzarPagina: boolean){
      const fechaLlegada = this.filtros.get('fechaLlegada')?.value;
      const fechaSalida = this.filtros.get('fechaSalida')?.value;
      const precioMin = this.filtros.get('precioMin')?.value;
      const precioMax = this.filtros.get('precioMax')?.value;
      const provincia = this.busqueda.get('destino')?.value;
      const page: IPageableDTO = {
        page: this.paginaActual,
        size: 6
      };
      if(fechaLlegada && fechaSalida && fechaLlegada > fechaSalida){
        alert("La fecha de llegada no puede ser mayor que la de salida");
        return;
      }else if(precioMin && precioMax && precioMin > precioMax){
        alert("El precio mínimo no puede ser mayor que el máximo");
        return;
      }else if(fechaLlegada && fechaLlegada < new Date()){
        alert("La fecha de llegada no puede ser anterior a la fecha actual");
        return;
      }else if(fechaSalida && fechaSalida < new Date()){
        alert("La fecha de salida no puede ser anterior a la fecha actual");
        return;
      }else if((fechaLlegada && !fechaSalida) || (fechaSalida && !fechaLlegada)){
        alert("Debes introducir ambas fechas o ninguna para realizar la búsqueda");
        return;
      }
      this.alojamientoService.buscarAloajmientoWithFilters(provincia,precioMin,precioMax,fechaLlegada, fechaSalida, this.comodidadesSelected, page).subscribe(alojamientos => {
        if(alojamientos.length == 0){
          this.paginaActual = avanzarPagina? --this.paginaActual : ++this.paginaActual;
          alert("No hay resultados para mostrar");
        }else{
          this.casasRurales = alojamientos;
          if(this.menu){
            this.mostrarMenu();
          }
        }
      });
    }

    cambiarPage(page: number) {
      if(this.paginaActual < page){
        this.paginaActual = page;
        this.buscarAlojamientoWithFilters(true);
      }else{
        this.paginaActual==0? 0 : this.paginaActual = page;
        this.buscarAlojamientoWithFilters(false);
      }
    }

}
