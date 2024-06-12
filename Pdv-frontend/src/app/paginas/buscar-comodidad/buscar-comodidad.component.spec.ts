import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarComodidadComponent } from './buscar-comodidad.component';

describe('BuscarComodidadComponent', () => {
  let component: BuscarComodidadComponent;
  let fixture: ComponentFixture<BuscarComodidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuscarComodidadComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BuscarComodidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
