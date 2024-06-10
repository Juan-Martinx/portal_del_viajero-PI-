import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlojamientosUsuarioComponent } from './alojamientos-usuario.component';

describe('AlojamientosUsuarioComponent', () => {
  let component: AlojamientosUsuarioComponent;
  let fixture: ComponentFixture<AlojamientosUsuarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlojamientosUsuarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AlojamientosUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
