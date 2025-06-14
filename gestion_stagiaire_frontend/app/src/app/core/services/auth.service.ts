import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:9090/api/auth';
  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/signin`, { username: email, password }).pipe(
      map((res: any) => {
        if (res && res.token) {
          localStorage.setItem('token', res.token);
          localStorage.setItem('roles', JSON.stringify(res.roles));

          return res;
        }
        return null;
      })
    );
  }

  register(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/signup`, data).pipe(
      map((res: any) => {
        if (res && res.token) {
          localStorage.setItem('token', res.token);
          localStorage.setItem('roles', JSON.stringify(res.roles));
          return res;
        }
        return null;
      })
    );
  }
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('roles');
  }
  isAdmin(): boolean {
    const roles = JSON.parse(localStorage.getItem('roles') || '[]');
    return roles.includes('ROLE_ADMIN');
  }
  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}
