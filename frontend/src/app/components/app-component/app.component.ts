import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Alfrescito';

  constructor(private router: Router) { }

  protected toLogin(): void {
    this.router.navigate(['/login']);
  }

  protected toRegister(): void {
    this.router.navigate(['/register']);
  }
}
