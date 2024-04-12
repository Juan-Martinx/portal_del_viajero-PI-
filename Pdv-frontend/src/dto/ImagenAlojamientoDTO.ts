import { AlojamientoDTO } from "./AlojamientoDTO";

export class ImagenAlojamientoDTO{

    private id?: number;
    private datosImagen?: Uint8Array;
    private numOrden?: number;
    private idAlojamiento?: AlojamientoDTO;

    constructor(){
        
    }

    set setId(id: number){
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

    set setNumOrden(numOrden: number){
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