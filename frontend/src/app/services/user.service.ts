import { User } from './../models/user.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  saveUser(user: User) {
    return this.http.post('api/register', user);
  }

  loginUser(user: User) {
    return this.http.post('api/login', user, { responseType: 'text' });
  }
}
