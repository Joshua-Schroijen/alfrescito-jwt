import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './auth.guard';
import { CreateAldocComponent } from '../components/create-aldoc/create-aldoc.component';
import { AldocDetailsComponent } from '../components/aldoc-details/aldoc-details.component';
import { AldocsListComponent } from '../components/aldocs-list/aldocs-list.component';
import { LoginFormComponent } from '../components/login-form/login-form.component';
import { RegisterFormComponent } from '../components/register-form/register-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'aldocs', pathMatch: 'full' },
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: 'aldocs', component: AldocsListComponent, canActivate: [AuthGuard] },
  { path: 'aldocs/:id', component: AldocDetailsComponent, canActivate: [AuthGuard] },
  { path: 'add', component: CreateAldocComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }