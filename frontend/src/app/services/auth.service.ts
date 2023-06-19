import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ignoreElements } from 'rxjs/operators';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private jwtToken: string | undefined = undefined;

  public constructor(private http: HttpClient) { }

  public authenticate(email: string, password: string): Observable<string> {
    const credentials: { [key: string]: string } = {
      email: email,
      password: password
    };

    let loginProcess: Observable<string> = this.http.post<string>(`${environment.apiUrl}/authenticate`, credentials);
    loginProcess.subscribe({
      next: (response: string) => {
        this.jwtToken = response;
        console.log('Authentication successful:', response);
      },
      error: (error) => {
        console.error('Authentication failed:', error);
      }
    });
    return loginProcess.pipe(ignoreElements());
  }

  public register(email: string, password: string): Observable<string> {
    const userData: { [key: string]: string } = {
      email: email,
      password: password
    };

    let registrationProcess: Observable<string> = this.http.post<string>(`${environment.apiUrl}/authenticate`, userData);
    registrationProcess.subscribe({
      error: (error) => {
        console.error('Authentication failed:', error);
      }
    });
    return registrationProcess.pipe(ignoreElements());
  }

  public get authenticated(): boolean {
    return this.jwtToken !== undefined;
  }

  public addAuthorizationHeaders(headers: HttpHeaders): HttpHeaders {
    if (! this.authenticated) {
      throw new Error("foo");
    }
    headers.set('Authorization', `Bearer ${this.jwtToken}`);
    return headers;
  }
}