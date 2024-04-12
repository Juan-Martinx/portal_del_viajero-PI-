import { AlojamientoComodidadAlojamientoDTO } from "./AlojamientoComodidadAlojamientoDTO";
import{TipoComodidadDTO} from "./TipoComodidadDTO";

export class ComodidadAlojamientoDTO{
   
    private id?: number;
    private codigoComodidad?: string;
    private txtNombre?: string;
    private txtDescripcion?: string;
    private idTipoComodidad?: TipoComodidadDTO;
    private idAlojamientoComodidades?: Set<AlojamientoComodidadAlojamientoDTO>;

    constructor(){

    }

    set setId(id: number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setCodigoComodidad(codigoComodidad: string){
        this.codigoComodidad = codigoComodidad;
    }

    get getCodigoComodidad(){
        return this.codigoComodidad;
    }

    set setTxtNombre(txtNombre: string){
        this.txtNombre = txtNombre;
    }

    get getTxtNombre(){
        return this.txtNombre;
    }

    set setTxtDescripcion(txtDescripcion: string){
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