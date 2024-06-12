import { CodPerfiles } from "./enumCodPerfiles";

export interface IUsuarioDTO {
    id?: number;
    username?: string;
    txtDescripcion?: string;
    txtDni?: string;
    numTelefono?: number;
    txtEmail?: string;
    password?: string;
    urlImagenUsuario?: string;
    perfiles?: CodPerfiles[];
}