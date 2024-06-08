import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  mediaAPI = "/media";

  constructor(private http: HttpClient) { }
  
 /**
   * Sube un archivo al servidor.
   * @param formData Los datos del formulario que contienen el archivo a subir.
   * @returns Un Observable que emite la respuesta del servidor.
   */
  uploadFile(formData: FormData): Observable<any> {
    return this.http.post(environment.api + this.mediaAPI + "/upload", formData);
  }
}
