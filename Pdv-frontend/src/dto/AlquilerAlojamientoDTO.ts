import { UsuarioDTO } from "./UsuarioDTO";
import { AlojamientoDTO } from "./AlojamientoDTO";

export class AlquilerAlojamientoDTO{

    private id?: Number;

    private fechaInicioAlquiler?: Date;

    private fechaFinAlquiler?: Date;

    private precioTotalAlquiler?: Number;

    private numPlazasReservadas?: Number;

    private idUsuarioCliente?: UsuarioDTO;

    private idAlojamiento?: AlojamientoDTO;

}