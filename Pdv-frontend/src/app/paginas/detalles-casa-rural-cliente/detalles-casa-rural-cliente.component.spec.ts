import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallesCasaRuralClienteComponent } from './detalles-casa-rural-cliente.component';

describe('DetallesCasaRuralClienteComponent', () => {
  let component: DetallesCasaRuralClienteComponent;
  let fixture: ComponentFixture<DetallesCasaRuralClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetallesCasaRuralClienteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DetallesCasaRuralClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
