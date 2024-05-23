import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BarraBusquedaInicioComponent } from './barra-busqueda-inicio.component';

describe('BarraBusquedaInicioComponent', () => {
  let component: BarraBusquedaInicioComponent;
  let fixture: ComponentFixture<BarraBusquedaInicioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BarraBusquedaInicioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BarraBusquedaInicioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
