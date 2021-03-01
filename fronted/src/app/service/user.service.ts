import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = 'http://localhost:8080/login';

  constructor(private httpClient: HttpClient) {
  }

  getUser(): Observable<User> {
    return this.httpClient.get<User>(this.url);
  }

}
