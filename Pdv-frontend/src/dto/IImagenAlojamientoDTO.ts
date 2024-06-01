import { IAlojamientoDTO } from "./IAlojamientoDTO";

export interface IImagenAlojamientoDTO {
    id?: number;
    txtUrlImagen?: string;
    idAlojamiento?: IAlojamientoDTO;
    numOrden?: number;
}