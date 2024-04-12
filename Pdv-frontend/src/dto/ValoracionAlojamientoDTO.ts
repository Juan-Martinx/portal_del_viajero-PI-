import { UsuarioDTO } from "./UsuarioDTO";
import { AlojamientoDTO } from "./AlojamientoDTO";

export class ValoracionAlojamientoDTO{
    
    private id?: Number;

    private txtMensaje?: String;

    private txtAsunto?: String;

    private provincia?: String;

    private puntuacion?: Number;

    private idUsuarioValorador?: UsuarioDTO;

    private idAlojamiento?: AlojamientoDTO;
}