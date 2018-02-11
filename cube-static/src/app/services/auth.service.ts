import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';

import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/catch';

@Injectable()
export class AuthService {
  private pingApi = '/api/ping';
  private headers = new Headers({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  login(userName: string, password: string) {
    return this.http.post<any>('/api/auth/login?userName=' + userName + '&password=' + password, { userName: userName, password: password }, this.headers).
      map((user: User) => {
        localStorage.setItem('currentUser', JSON.stringify(user));
        return user;
      });

  }

  ping(): Observable<boolean> {
    return this.http.get(this.pingApi).map((response: Response) => {
      console.log(response);
      return true;
    }
    ).catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }
}
