import { UsuarioDTO } from "./UsuarioDTO";
import { AlojamientoDTO } from "./AlojamientoDTO";

export class AlquilerAlojamientoDTO{

    private id?: number;
    private fechaInicioAlquiler?: Date;
    private fechaFinAlquiler?: Date;
    private precioTotalAlquiler?: number;
    private numPlazasReservadas?: number;
    private idUsuario?: UsuarioDTO;
    private idAlojamiento?: AlojamientoDTO;

    set setId(id:number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setFechaInicioAlquiler(fechaInicioAlquiler: Date) {
        this.fechaInicioAlquiler = fechaInicioAlquiler;
    }

    get getFechaInicioAlquiler() {
        return this.fechaInicioAlquiler;
    }

    set setFechaFinAlquiler(fechaFinAlquiler: Date) {
        this.fechaFinAlquiler = fechaFinAlquiler;
    }

    get getFechaFinAlquiler() {
        return this.fechaFinAlquiler;
    }

    set setPrecioTotalAlquiler(precioTotalAlquiler: number) {
        this.precioTotalAlquiler = precioTotalAlquiler;
    }

    get getPrecioTotalAlquiler() {
        return this.precioTotalAlquiler;
    }

    set setNumPlazasReservadas(numPlazasReservadas: number) {
        this.numPlazasReservadas = numPlazasReservadas;
    }

    get getNumPlazasReservadas() {
        return this.numPlazasReservadas;
    }

    set setIdUsuario(idUsuario: UsuarioDTO) {
        this.idUsuario = idUsuario;
    }

    get getIdUsuario() {
        return this.idUsuario;
    }

    set setIdAlojamiento(idAlojamiento: AlojamientoDTO) {
        this.idAlojamiento = idAlojamiento;
    }

    get getIdAlojamiento() {
        return this.idAlojamiento;
    }
}