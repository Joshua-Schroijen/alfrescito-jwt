import { Component } from '@angular/core';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent {
  public constructor(private authService: AuthService) {
  }

  protected onSubmit(inputs: {[key: string]: string}): void {
    this.authService.authenticate(inputs["email"], inputs["password"]);
  }
}