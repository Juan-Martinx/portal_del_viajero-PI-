import { ComodidadAlojamientoDTO } from "./ComodidadAlojamientoDTO";
import { AlojamientoDTO } from "./AlojamientoDTO";

export class AlojamientoComodidadAlojamientoDTO{

    private id?: number;
    private idComodidadAlojamiento?: ComodidadAlojamientoDTO;
    private idAlojamiento?: AlojamientoDTO;

    constructor(){
        
    }

    set setId(id: number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setIdComodidadAlojamiento(idComodidadAlojamiento: ComodidadAlojamientoDTO){
        this.idComodidadAlojamiento = idComodidadAlojamiento;
    }

    get getIdComodidadAlojamiento(){
        return this.idComodidadAlojamiento;
    }

    set setIdAlojamiento(idAlojamiento: AlojamientoDTO){
        this.idAlojamiento = idAlojamiento;
    }

    get getIdAlojamiento(){
        return this.idAlojamiento;
    }

}