import { UsuarioDTO } from "./UsuarioDTO";

export class PerfilDTO{

    private id?: number;
    private codPerfil?: string;
    private txtPerfil?: string;
    private idUsuario?: UsuarioDTO;

    constructor(){

    }

    set setId(id:number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setCodPerfil(codPerfil: string){
        this.codPerfil = codPerfil;
    }

    get getCodPerfil(){
        return this.codPerfil;
    }

    set setTxtPerfil(txtPerfil: string){
        this.txtPerfil = txtPerfil;
    }

    get getTxtPerfil(){
        return this.txtPerfil;
    }
    
    set setIdUsuario(usuario: UsuarioDTO){
        this.idUsuario = usuario;
    }

    get getIdUsuario(){
        return this.idUsuario;
    }
}