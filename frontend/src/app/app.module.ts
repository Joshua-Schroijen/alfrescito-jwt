import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './routing/app-routing.module';
import { AppComponent } from './components/app-component/app.component';
import { CreateAldocComponent } from './components/create-aldoc/create-aldoc.component';
import { AldocDetailsComponent } from './components/aldoc-details/aldoc-details.component';
import { AldocsListComponent } from './components/aldocs-list/aldocs-list.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { RegisterFormComponent } from './components/register-form/register-form.component';

import { AuthService } from './services/auth.service';
import { AldocsService } from './services/aldocs.service';
import { AuthGuard } from './routing/auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    CreateAldocComponent,
    AldocDetailsComponent,
    AldocsListComponent,
    LoginFormComponent,
    RegisterFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [AuthService, AldocsService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
