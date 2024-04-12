import { AlojamientoDTO } from "./AlojamientoDTO";

export class UbicacionAlojamientoDTO{

    private id?: Number;

    private codigoPostal?: Number;

    private ciudad?: String;

    private provincia?: String;

    private lineaDireccion?: String;

    private longitud?: String;

    private latitud?: String;

    private idAlojamiento?: AlojamientoDTO;

    constructor(){

    }

    set setId(id: Number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setCodigoPostal(codigoPostal: Number){
        this.codigoPostal = codigoPostal;
    }

    get getCodigoPostal(){
        return this.codigoPostal;
    }

    set setCiudad(ciudad: String){
        this.ciudad = ciudad;
    }

    get getCiudad(){
        return this.ciudad;
    }

    set setProvincia(provincia: String){
        this.provincia = provincia;
    }

    get getProvincia(){
        return this.provincia;
    }

    set setLineaDireccion(lineaDireccion: String){
        this.lineaDireccion = lineaDireccion;
    }

    get getLineaDireccion(){
        return this.lineaDireccion;
    }

    set setLongitud(longitud: String){
        this.longitud = longitud;
    }

    get getLongitud(){
        return this.longitud;
    }

    set setLatitud(latitud: String){
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