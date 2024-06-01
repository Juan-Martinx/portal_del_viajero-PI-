import { IAlojamientoDTO } from "./IAlojamientoDTO";
import { IUsuarioDTO } from "./IUsuarioDTO";

export interface IValoracionAlojamientoDTO {
    id?: number;
    txtMensaje?: string;
    puntuacion?: number;
    idUsuario?: IUsuarioDTO;
    idAlojamiento?: IAlojamientoDTO;
}