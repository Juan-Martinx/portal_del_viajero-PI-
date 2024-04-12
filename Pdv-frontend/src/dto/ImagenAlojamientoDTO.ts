import { AlojamientoDTO } from "./AlojamientoDTO";

export class ImagenAlojamientoDTO{

    private id?: Number;

    private datosImagen?: Uint8Array;

    private numOrden?: Number;

    private idAlojamiento?: AlojamientoDTO;

    constructor(){
        
    }

    set setId(id: Number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setDatosImagen(datosImagen: Uint8Array){
        this.datosImagen = datosImagen;
    }

    get getDatosImagen(){
        return this.datosImagen;
    }

    set setNumOrden(numOrden: Number){
        this.numOrden = numOrden;
    }

    get getNumOrden(){
        return this.numOrden;
    }

    set setIdAlojamiento(idAlojamiento: AlojamientoDTO){
        this.idAlojamiento = idAlojamiento;
    }

    get getIdAlojamiento(){
        return this.idAlojamiento;
    }



}