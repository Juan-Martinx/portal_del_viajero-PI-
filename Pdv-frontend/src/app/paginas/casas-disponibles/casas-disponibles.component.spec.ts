import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasasDisponiblesComponent } from './casas-disponibles.component';

describe('CasasDisponiblesComponent', () => {
  let component: CasasDisponiblesComponent;
  let fixture: ComponentFixture<CasasDisponiblesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CasasDisponiblesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CasasDisponiblesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
