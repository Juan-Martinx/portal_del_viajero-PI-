import { AlojamientoDTO } from "./AlojamientoDTO";

export class UbicacionAlojamientoDTO{

    private id?: number;
    private codigoPostal?: number;
    private ciudad?: string;
    private provincia?: string;
    private lineaDireccion?: string;
    private longitud?: string;
    private latitud?: string;
    private idAlojamiento?: AlojamientoDTO;

    constructor(){

    }

    set setId(id: number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setCodigoPostal(codigoPostal: number){
        this.codigoPostal = codigoPostal;
    }

    get getCodigoPostal(){
        return this.codigoPostal;
    }

    set setCiudad(ciudad: string){
        this.ciudad = ciudad;
    }

    get getCiudad(){
        return this.ciudad;
    }

    set setProvincia(provincia: string){
        this.provincia = provincia;
    }

    get getProvincia(){
        return this.provincia;
    }

    set setLineaDireccion(lineaDireccion: string){
        this.lineaDireccion = lineaDireccion;
    }

    get getLineaDireccion(){
        return this.lineaDireccion;
    }

    set setLongitud(longitud: string){
        this.longitud = longitud;
    }

    get getLongitud(){
        return this.longitud;
    }

    set setLatitud(latitud: string){
        this.latitud = latitud;
    }

    get getLatitud(){
        return this.latitud;
    }

    set setIdAlojamiento(idAlojamiento: AlojamientoDTO){
        this.idAlojamiento = idAlojamiento;
    }

    get getIdAlojamiento(){
        return this.idAlojamiento;
    }

}