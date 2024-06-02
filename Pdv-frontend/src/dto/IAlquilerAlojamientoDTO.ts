import { IAlojamientoDTO } from "./IAlojamientoDTO";
import { IUsuarioDTO } from "./IUsuarioDTO";

export interface IAlquilerAlojamientoDTO {
    id?: number;
    fechaInicioAlquiler?: Date;
    fechaFinAlquiler?: Date;
    precioTotalAlquiler?: number;
    numPlazasReservadas?: number;
    idUsuario?: IUsuarioDTO;
    idAlojamiento?: number;
}