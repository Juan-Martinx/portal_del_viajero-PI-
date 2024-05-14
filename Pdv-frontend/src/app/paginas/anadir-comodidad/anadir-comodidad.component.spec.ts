import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnadirComodidadComponent } from './anadir-comodidad.component';

describe('AnadirComodidadComponent', () => {
  let component: AnadirComodidadComponent;
  let fixture: ComponentFixture<AnadirComodidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnadirComodidadComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AnadirComodidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
