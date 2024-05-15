import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CasasAlquilerComponent } from './casas-alquiler.component';

describe('CasasAlquilerComponent', () => {
  let component: CasasAlquilerComponent;
  let fixture: ComponentFixture<CasasAlquilerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CasasAlquilerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CasasAlquilerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
