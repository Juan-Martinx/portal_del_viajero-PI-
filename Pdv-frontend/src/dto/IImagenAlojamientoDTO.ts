import { IAlojamientoDTO } from "./IAlojamientoDTO";

export interface IImagenAlojamientoDTO {
    id?: number;
    urlDatosImagen?: string;
    idAlojamiento?: IAlojamientoDTO;
    numOrden?: number;
}