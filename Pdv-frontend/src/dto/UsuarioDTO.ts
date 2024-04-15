import { AlojamientoDTO } from "./AlojamientoDTO";
import { AlquilerAlojamientoDTO } from "./AlquilerAlojamientoDTO";
import { PerfilDTO } from "./PerfilDTO";
import { ValoracionAlojamientoDTO } from "./ValoracionAlojamientoDTO";

export class UsuarioDTO{

    private id?: number;
    private txtNombreUsuario?: string;
    private txtDescripcion?: string;
    private txtDni?: string;
    private numTelefono?: number;
    private txtEmail?: string;
    private txtPassword?: string;
    private idPerfil?: PerfilDTO;
    private idAlquileres?: Set<AlquilerAlojamientoDTO>;
    private idAlojamiento?: Set<AlojamientoDTO>;
    private idValoracionesAlojamientos?: Set<ValoracionAlojamientoDTO>;


    constructor(){

    }

    set setId(id:number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }
    
    set setTxtDescripcion(txtDescripcion: string){
        this.txtDescripcion = txtDescripcion;
    }

    get getTxtDescripcion(){
        return this.txtDescripcion;
    }

    set setTxtDni(txtDni: string){
        this.txtDni = txtDni;
    }

    get getTxtDni(){
        return this.txtDni;
    }

    set setNumTelefono(numTelefono: number){
        this.numTelefono = numTelefono;
    }

    get getNumTelefono(){
        return this.numTelefono;
    }

    set setTxtEmail(txtEmail: string){
        this.txtEmail = txtEmail;
    }

    get getTxtEmail(){
        return this.txtEmail;
    }

    set setTxtPassword(txtPassword: string){
        this.txtPassword = txtPassword;
    }

    get getTxtPassword() {
        return this.txtPassword;
    }
    
    set setIdPerfil(idPerfil: PerfilDTO) {
        this.idPerfil = idPerfil;
    }
    
    get getIdPerfil() {
        return this.idPerfil;
    }
    
    set setIdAlquileres(idAlquileres: Set<AlquilerAlojamientoDTO>) {
        this.idAlquileres = idAlquileres;
    }
    
    get getIdAlquileres() {
        return this.idAlquileres;
    }
    
    set setIdAlojamiento(idAlojamiento: Set<AlojamientoDTO>) {
        this.idAlojamiento = idAlojamiento;
    }
    
    get getIdAlojamiento() {
        return this.idAlojamiento;
    }
    
    set setIdValoracionesAlojamientos(idValoracionesAlojamientos: Set<ValoracionAlojamientoDTO>) {
        this.idValoracionesAlojamientos = idValoracionesAlojamientos;
    }
    
    get getIdValoracionesAlojamientos() {
        return this.idValoracionesAlojamientos;
    }
    
    set setTxtNombreUsuario(txtNombreUsuario: string) {
        this.txtNombreUsuario = txtNombreUsuario;
    }
    
    get getTxtNombreUsuario() {
        return this.txtNombreUsuario;
    }

}