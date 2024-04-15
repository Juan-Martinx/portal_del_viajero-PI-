import {ComodidadAlojamientoDTO} from "./ComodidadAlojamientoDTO";

export class TipoComodidadDTO{
    
    private id?: number;
    private codigoTipoComodidad?: string;
    private txtNombre?: string;
    private idComodidadAlojamientos?: Set<ComodidadAlojamientoDTO>;

    constructor(){

    }

    set setId(id: number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setCodigoTipoComodidad(codigoTipoComodidad: string){
        this.codigoTipoComodidad = codigoTipoComodidad;
    }

    get getCodigoTipoComodidad(){
        return this.codigoTipoComodidad;
    }

    set setTextNombre(txtNombre: string){
        this.txtNombre = txtNombre;
    }

    get getTxtNombre(){
        return this.txtNombre;
    }

    set setIdComodidadAlojamientos(idComodidadAlojamientos: Set<ComodidadAlojamientoDTO>){
        this.idComodidadAlojamientos = idComodidadAlojamientos;
    }

    get getIdComodidadAlojamientos(){
        return this.idComodidadAlojamientos;
    }
}