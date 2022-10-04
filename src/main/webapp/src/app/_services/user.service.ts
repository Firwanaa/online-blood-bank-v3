import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/donor/';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) { }

  getSignInUser(): Observable<any> {
    return this.http.get(API_URL + 'signin', { responseType: 'text' });
  }

  getDonors(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getRegisterUser(): Observable<any> {
    return this.http.get(API_URL + 'register', { responseType: 'text' });
  }

  getHomePage(): Observable<any> {
    return this.http.get(API_URL + '/', { responseType: 'text' });
  }
}
