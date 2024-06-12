import { IAlojamientoDTO } from "./IAlojamientoDTO";
import { IComodidadAlojamientoDTO } from "./IComodidadAlojamientoDTO";

export interface IAlojamientoComodidadAlojamientoDTO {
    id?: number;
    idAlojamiento?: IAlojamientoDTO;
    idComodidadAlojamiento?: IComodidadAlojamientoDTO;
}