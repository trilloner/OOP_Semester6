import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Room} from '../model/room';
import {Rooms} from '../model/rooms';

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  url = 'http://localhost:8080/rooms';

  constructor(private httpClient: HttpClient) {
  }

  getAllRooms(): Observable<Rooms> {
    return this.httpClient.get<Rooms>(this.url);
  }
}
