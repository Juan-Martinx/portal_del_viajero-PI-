import { PerfilDTO } from "./PerfilDTO";

export class UsuarioDTO{

    private id?: number;
    private txtDescripcion?: string;
    private txtDni?: string;
    private numTelefono?: number;
    private txtEmail?: string;
    private txtPassword?: string;
    private idPerfil?: PerfilDTO;

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

    get getTxtPassword(){
        return this.txtPassword;
    }

    set setIdPerfil(perfil: PerfilDTO){
        this.idPerfil = perfil;
    }

    get getIdPerfil(){
        return this.idPerfil;
    }

}