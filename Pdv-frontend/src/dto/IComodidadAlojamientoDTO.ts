import { iTipoComodidadDTO } from "./ITipoComodidadDTO";

export interface IComodidadAlojamientoDTO {
    id?: number;
    codigoComodidad?: string;
    txtNombre?: string;
    txtDescripcion?: string;
    idTipoComodidad?: iTipoComodidadDTO;
    iconoComodidad?: string;
}