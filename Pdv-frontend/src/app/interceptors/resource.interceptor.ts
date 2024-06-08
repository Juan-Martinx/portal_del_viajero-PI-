import { TokenService } from './../services/token.service';
import { inject } from '@angular/core';
import { HttpRequest, HttpHandlerFn, HTTP_INTERCEPTORS, HttpInterceptorFn } from '@angular/common/http';

/**
 * Interceptor de solicitudes HTTP para añadir el token de acceso a las peticiones a recursos protegidos.
 * @param request 
 * @param next 
 * @returns 
 */
export const resourceInterceptor: HttpInterceptorFn = (request: HttpRequest<unknown>, next: HttpHandlerFn) => {
  const tokenService = inject(TokenService);
  const token = tokenService.getAccessToken();
  
  if (token != null && request.url.includes('resource')) {
    const intReq = request.clone({ headers: request.headers.set('Authorization', 'Bearer ' + token) });
    return next(intReq);
  }
  
  return next(request);
};

export const resourceInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useValue: resourceInterceptor,
  multi: true
};
