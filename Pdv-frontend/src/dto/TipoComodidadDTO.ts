import {ComodidadAlojamientoDTO} from "./ComodidadAlojamientoDTO";

export class TipoComodidadDTO{
    
    private id?: Number;

    private codigoTipoComodidad?: String;

    private txtNombre?: String;

    private idComodidadAlojamientos?: Set<ComodidadAlojamientoDTO>;

    constructor(){

    }

    set setId(id: Number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setCodigoTipoComodidad(codigoTipoComodidad: String){
        this.codigoTipoComodidad = codigoTipoComodidad;
    }

    get getCodigoTipoComodidad(){
        return this.codigoTipoComodidad;
    }

    set setTextNombre(txtNombre: String){
        this.txtNombre = txtNombre;
    }

    get getTxtNombre(){
        return this.txtNombre;
    }

    set setIdComodidadAlojamiento(idComodidadAlojamientos: Set<ComodidadAlojamientoDTO>){
        this.idComodidadAlojamientos = idComodidadAlojamientos;
    }

    get getIdComodidadAlojamiento(){
        return this.idComodidadAlojamientos;
    }
}