import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {User} from '../../model/user';
import {AuthService} from '../../service/auth-service';
import {Reservation} from '../../model/reservation';
import {ReservationService} from '../../service/reservation.service';

@Component({
  selector: 'app-cabinet',
  templateUrl: './cabinet.component.html',
  styleUrls: ['./cabinet.component.css']
})
export class CabinetComponent implements OnInit {

  user: User;
  reservations: Reservation[];

  constructor(private userService: UserService,
              private authService: AuthService,
              private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.getAllRooms();
  }

  getAllRooms(): void {
    this.reservationService.getAllReservationsByUser(this.authService.currentUserValue.id).pipe().subscribe(
      data => {
        this.reservations = data;
      }
    );
  }

  logout(): void {
    this.authService.logout();
  }

}
