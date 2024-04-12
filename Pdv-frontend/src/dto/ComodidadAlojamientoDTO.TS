import { AlojamientoComodidadAlojamientoDTO } from "./AlojamientoComodidadAlojamientoDTO";
import{TipoComodidadDTO} from "./TipoComodidadDTO";

export class ComodidadAlojamientoDTO{
    private id?: Number;

    private codigoComodidad?: String;

    private txtNombre?: String;

    private txtDescripcion?: String;

    private idTipoComodidad?: TipoComodidadDTO;

    private idAlojamientoComodidades?: Set<AlojamientoComodidadAlojamientoDTO>;

    constructor(){

    }

    set setId(id: Number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setCodigoComodidad(codigoComodidad: String){
        this.codigoComodidad = codigoComodidad;
    }

    get getCodigoComodidad(){
        return this.codigoComodidad;
    }

    set setTxtNombre(txtNombre: String){
        this.txtNombre = txtNombre;
    }

    get getTxtNombre(){
        return this.txtNombre;
    }

    set setTxtDescripcion(txtDescripcion: String){
        this.txtDescripcion = txtDescripcion;
    }

    get getTxtDescripcion(){
        return this.txtDescripcion;
    }

    set setIdTipoComodidad(idTipoComodidad: TipoComodidadDTO){
        this.idTipoComodidad = idTipoComodidad;
    }

    get getIdTipoComodidad(){
        return this.idTipoComodidad;
    }

    set setIdAlojamientoComodidades(idAlojamientoComodidades: Set<AlojamientoComodidadAlojamientoDTO>){
        this.idAlojamientoComodidades = idAlojamientoComodidades;
    }

    get getIdAlojamientoComodidades(){
        return this.idAlojamientoComodidades;
    }
}