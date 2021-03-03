import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Room} from '../model/room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  url = 'http://localhost:8080/rooms';

  constructor(private httpClient: HttpClient) {
  }

  getAllRooms(): Observable<Room[]> {
    return this.httpClient.get<Room[]>(this.url);
  }
}
