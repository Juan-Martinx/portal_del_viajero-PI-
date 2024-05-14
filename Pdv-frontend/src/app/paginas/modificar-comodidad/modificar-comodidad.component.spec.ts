import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarComodidadComponent } from './modificar-comodidad.component';

describe('ModificarComodidadComponent', () => {
  let component: ModificarComodidadComponent;
  let fixture: ComponentFixture<ModificarComodidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificarComodidadComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModificarComodidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
