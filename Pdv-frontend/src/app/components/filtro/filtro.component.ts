import { Component } from '@angular/core';
import {MatSliderModule} from '@angular/material/slider';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';

@Component({
  selector: 'app-filtro',
  standalone: true,
  providers: [provideNativeDateAdapter()],
  imports: [MatSliderModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule, MatIconModule, MatDatepickerModule],
  templateUrl: './filtro.component.html',
  styleUrl: './filtro.component.css'
})
export class FiltroComponent {

  formatLabel(value: number): string {
   /* if (value >= 1000) {
      return Math.round(value / 1000) + 'k';
    }*/

    return `${value}€`;
  }
}
