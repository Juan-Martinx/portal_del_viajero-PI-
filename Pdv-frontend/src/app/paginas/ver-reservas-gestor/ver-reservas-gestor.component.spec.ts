import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerReservasGestorComponent } from './ver-reservas-gestor.component';

describe('VerReservasGestorComponent', () => {
  let component: VerReservasGestorComponent;
  let fixture: ComponentFixture<VerReservasGestorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerReservasGestorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VerReservasGestorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
