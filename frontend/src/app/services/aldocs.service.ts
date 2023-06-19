import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, ignoreElements } from 'rxjs';

import { environment } from '../../environments/environment';
import { AlDoc } from '../models/aldoc.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AldocsService {
  public constructor(private authService: AuthService, private http: HttpClient) { }

  public getAll(): Observable<AlDoc[]> {
    const headers = this.authService.addAuthorizationHeaders(new HttpHeaders());
    return this.http.get<AlDoc[]>(environment.apiUrl, { headers });
  }

  public get(id: string): Observable<AlDoc> {
    const headers = this.authService.addAuthorizationHeaders(new HttpHeaders());
    return this.http.get<AlDoc>(`${environment.apiUrl}/${id}`, { headers });
  }

  public create(file: File): Observable<Object> {
    const formData: FormData = new FormData();
    formData.append('file', file);
  
    let headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    headers = this.authService.addAuthorizationHeaders(headers);
  
    let creationProcess: Observable<Object> = this.http.post(environment.apiUrl, formData, { headers });
    creationProcess.subscribe({
      error: (error) => {
        console.error('AlDoc creation failed:', error);
      }
    });
    return creationProcess.pipe(ignoreElements());
  }

  public delete(id: string): Observable<Object> {
    const headers = this.authService.addAuthorizationHeaders(new HttpHeaders());

    let removalProcess: Observable<Object> = this.http.delete(`${environment.apiUrl}/${id}`, { headers });
    removalProcess.subscribe({
      error: (error) => {
        console.error('AlDoc removal failed:', error);
      }
    });
    return removalProcess.pipe(ignoreElements());
  }
}