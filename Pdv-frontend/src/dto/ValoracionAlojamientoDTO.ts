import { UsuarioDTO } from "./UsuarioDTO";
import { AlojamientoDTO } from "./AlojamientoDTO";

export class ValoracionAlojamientoDTO{
    
    private id?: number;
    private txtMensaje?: string;
    private txtAsunto?: string;
    private provincia?: string;
    private puntuacion?: number;
    private idUsuarioValorador?: UsuarioDTO;
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
    
    set setProvincia(provincia: string) {
        this.provincia = provincia;
    }
    
    get getProvincia() {
        return this.provincia;
    }
    
    set setPuntuacion(puntuacion: number) {
        this.puntuacion = puntuacion;
    }
    
    get getPuntuacion() {
        return this.puntuacion;
    }
    
    set setIdUsuarioValorador(idUsuarioValorador: UsuarioDTO) {
        this.idUsuarioValorador = idUsuarioValorador;
    }
    
    get getIdUsuarioValorador() {
        return this.idUsuarioValorador;
    }
    
    set setIdAlojamiento(idAlojamiento: AlojamientoDTO) {
        this.idAlojamiento = idAlojamiento;
    }
    
    get getIdAlojamiento() {
        return this.idAlojamiento;
    }
}