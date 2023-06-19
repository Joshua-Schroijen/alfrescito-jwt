import { Component } from '@angular/core';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent {
  public constructor(private authService: AuthService) {
  }

  protected onSubmit(inputs: {[key: string]: string}): void {
    this.authService.register(inputs['email'], inputs['password']);
  }
}
