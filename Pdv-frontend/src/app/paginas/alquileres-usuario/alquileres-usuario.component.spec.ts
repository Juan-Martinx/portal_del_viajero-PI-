import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlquileresUsuarioComponent } from './alquileres-usuario.component';

describe('AlquileresUsuarioComponent', () => {
  let component: AlquileresUsuarioComponent;
  let fixture: ComponentFixture<AlquileresUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlquileresUsuarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AlquileresUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
