import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConvertirGestorComponent } from './convertir-gestor.component';

describe('ConvertirGestorComponent', () => {
  let component: ConvertirGestorComponent;
  let fixture: ComponentFixture<ConvertirGestorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConvertirGestorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConvertirGestorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
