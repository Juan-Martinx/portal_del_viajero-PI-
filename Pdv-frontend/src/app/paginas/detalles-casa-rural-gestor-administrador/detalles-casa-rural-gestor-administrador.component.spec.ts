import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesCasaRuralGestorAdministradorComponent } from './detalles-casa-rural-gestor-administrador.component';

describe('DetallesCasaRuralGestorAdministradorComponent', () => {
  let component: DetallesCasaRuralGestorAdministradorComponent;
  let fixture: ComponentFixture<DetallesCasaRuralGestorAdministradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetallesCasaRuralGestorAdministradorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetallesCasaRuralGestorAdministradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
