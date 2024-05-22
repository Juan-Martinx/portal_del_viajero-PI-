import { CodPerfiles } from "./enumCodPerfiles";

export interface IUsuarioDTO {
    id?: number;
    username?: string;
    txtDescripcion?: string;
    txtDni?: string;
    numTelefono?: number;
    txtEmail?: string;
    password?: string;
    datosImagenUsuario?: Uint8Array;
    perfiles?: CodPerfiles[];
}