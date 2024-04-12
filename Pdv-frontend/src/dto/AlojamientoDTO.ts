import { UsuarioDTO } from "./UsuarioDTO";

export class AlojamientoDTO{

    private id?: number;
    private txtNombre?: string;
    private txtDescripcion?: string;
    private numPlazaMin?: number;
    private numPlazaMax?: number;
    private numPrecioPlaza?: number;
    private numCamas?: number;
    private numBanyos?: number;
    private idGestorAlojamiento?: UsuarioDTO;

    constructor(){

    }

    set setId(id:number){
        this.id = id;
    }

    get getId(){
        return this.id;
    }

    set setTxtNombre(txtNombre: string){
        this.txtNombre = txtNombre;
    }

    get getTxtNombre(){
        return this.txtNombre;
    }

    set setTxtDescripcion(txtDescripcion: string) {
        this.txtDescripcion = txtDescripcion;
    }

    get getTxtDescripcion() {
        return this.txtDescripcion;
    }

    set setNumPlazaMin(numPlazaMin: number) {
        this.numPlazaMin = numPlazaMin;
    }

    get getNumPlazaMin() {
        return this.numPlazaMin;
    }

    set setNumPlazaMax(numPlazaMax: number) {
        this.numPlazaMax = numPlazaMax;
    }

    get getNumPlazaMax() {
        return this.numPlazaMax;
    }

    set setNumPrecioPlaza(numPrecioPlaza: number) {
        this.numPrecioPlaza = numPrecioPlaza;
    }

    get getNumPrecioPlaza() {
        return this.numPrecioPlaza;
    }

    set setNumCamas(numCamas: number) {
        this.numCamas = numCamas;
    }

    get getNumCamas() {
        return this.numCamas;
    }

    set setNumBanyos(numBanyos: number) {
        this.numBanyos = numBanyos;
    }

    get getNumBanyos() {
        return this.numBanyos;
    }

    set setIdGestorAlojamiento(idGestorAlojamiento: UsuarioDTO) {
        this.idGestorAlojamiento = idGestorAlojamiento;
    }

    get getIdGestorAlojamiento() {
        return this.idGestorAlojamiento;
    }
    
}