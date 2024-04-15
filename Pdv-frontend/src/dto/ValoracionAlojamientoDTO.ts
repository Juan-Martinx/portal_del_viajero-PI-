import { UsuarioDTO } from "./UsuarioDTO";
import { AlojamientoDTO } from "./AlojamientoDTO";

export class ValoracionAlojamientoDTO{
    
    private id?: number;
    private txtMensaje?: string;
    private txtAsunto?: string;
    private puntuacion?: number;
    private idUsuario?: UsuarioDTO;
    private idAlojamiento?: AlojamientoDTO;

    constructor(){

    }

    set setId(id:number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }
    
    set setTxtMensaje(txtMensaje: string) {
        this.txtMensaje = txtMensaje;
    }
    
    get getTxtMensaje() {
        return this.txtMensaje;
    }
    
    set setTxtAsunto(txtAsunto: string) {
        this.txtAsunto = txtAsunto;
    }
    
    get getTxtAsunto() {
        return this.txtAsunto;
    }
    
    set setPuntuacion(puntuacion: number) {
        this.puntuacion = puntuacion;
    }
    
    get getPuntuacion() {
        return this.puntuacion;
    }
    
    set setIdUsuario(idUsuario: UsuarioDTO) {
        this.idUsuario = idUsuario;
    }
    
    get getIdUsuario() {
        return this.idUsuario;
    }
    
    set setIdAlojamiento(idAlojamiento: AlojamientoDTO) {
        this.idAlojamiento = idAlojamiento;
    }
    
    get getIdAlojamiento() {
        return this.idAlojamiento;
    }
}