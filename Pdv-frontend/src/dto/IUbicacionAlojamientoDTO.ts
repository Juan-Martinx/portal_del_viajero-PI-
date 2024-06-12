import { IAlojamientoDTO } from "./IAlojamientoDTO";

export interface IUbicacionAlojamientoDTO {
    id?: number;
    lineaDireccion?: string;
    codigoPostal?: number;
    ciudad?: string;
    provincia?: string;
    latitud?: number;
    longitud?: number;
    idAlojamiento?: IAlojamientoDTO;
}