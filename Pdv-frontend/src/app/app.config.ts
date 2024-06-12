import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { resourceInterceptor } from './interceptors/resource.interceptor';
import { MAT_DATE_FORMATS, DateAdapter, MatDateFormats, MAT_DATE_LOCALE } from '@angular/material/core';

// Define el formato de fecha
export const MY_DATE_FORMATS: MatDateFormats = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMMM YYYY',
    dateA11yLabel: 'DD/MM/YYYY',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideClientHydration(), provideAnimationsAsync(),provideHttpClient(withInterceptors([resourceInterceptor])), { provide: MAT_DATE_FORMATS, useValue: MY_DATE_FORMATS },  { provide: MAT_DATE_LOCALE, useValue: 'es-ES' }]
};
