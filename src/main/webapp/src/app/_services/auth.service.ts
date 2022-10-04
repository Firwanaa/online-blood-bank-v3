import {BloodType} from '../enumerations/bloodType.enum';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/api/donor/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions);
  }

  register(name: string, username: string, email: string, password: string, city: string, postalCode: string, address: string,bloodType: BloodType, isAvailable: boolean, lat: number, lng: number): Observable<any> {
    return this.http.post(AUTH_API + 'register', {
      username,
      email,
      password,
      city,
      postalCode,
      address,
      bloodType,
      isAvailable,
      lat,
      lng
    }, httpOptions);
  }
}
