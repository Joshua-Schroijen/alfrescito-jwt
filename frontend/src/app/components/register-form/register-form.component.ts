import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.scss']
})
export class RegisterFormComponent {
  @ViewChild('registrationForm', { read: NgForm }) form!: NgForm;

  public constructor(private authService: AuthService) {
  }

  protected onSubmit(inputs: {[key: string]: string}): void {
    if (! (this.form.controls['password'].value === this.form.controls['repeatPassword'].value)) {
      this.form.controls['repeatPassword'].setErrors({ invalidCondition: true });
    }

    if (this.form.valid) {
      this.authService.register(inputs['email'], inputs['password']);
    }
  }
}
