import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {
  public constructor(private router: Router, private authService: AuthService) {
  }

  protected onSubmit(inputs: {[key: string]: string}): void {
    this.authService.authenticate(inputs["email"], inputs["password"])
      .subscribe({
        complete: () => {
          this.router.navigate(['/aldocs']);
        }
      });
  }
}