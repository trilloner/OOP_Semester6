import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Reservation} from '../model/reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  url = 'http://localhost:8080/reservation';

  constructor(private httpClient: HttpClient) {
  }

  getAllReservationsByUser(): Observable<Reservation> {
    return this.httpClient.get<Reservation>(this.url);
  }
}
