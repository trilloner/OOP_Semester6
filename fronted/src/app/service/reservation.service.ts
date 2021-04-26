import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Reservation} from '../model/reservation';
import {Reservations} from '../model/reservations';
import {User} from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  url = 'http://localhost:8080/order';
  urlCabinet = 'http://localhost:8080/cabinet';
  urlAdmin = 'http://localhost:8080/admin';

  constructor(private httpClient: HttpClient) {
  }


  getAllReservationsByUser(user: User): Observable<Reservation[]> {
    return this.httpClient.post<Reservation[]>(this.urlCabinet, user);
  }

  createReservationByUser(reservation: Reservation): any {

    return this.httpClient.post<Reservation>(this.url, reservation);
  }

  getAllReservations(): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>(this.url);
  }

  updateReservation(roomId: number, id: number): Observable<any> {
    return this.httpClient.post<any>(this.urlAdmin + '?orderId=' + id + '&roomId=' + roomId, {});
  }

  deleteReservation(id: number): Observable<any> {
    return this.httpClient.get<any>(this.urlAdmin + '?orderId=' + id);
  }
}
