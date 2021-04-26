import {Component, OnInit} from '@angular/core';
import {ReservationService} from '../service/reservation.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginComponent} from '../login/login.component';
import {OAuthService} from 'angular-oauth2-oidc';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  reservationForm: FormGroup;
  user = 1;
  state = 'PENDING';

  constructor(private reservationService: ReservationService,
              private formBuilder: FormBuilder,
              private oauthService: OAuthService) {
  }

  ngOnInit(): void {
    this.reservationForm = this.formBuilder.group(
      {
        number_of_seats: ['', Validators.required],
        checkIn: ['', Validators.required],
        checkOut: ['', Validators.required],
        apartments: ['', Validators.required]
      }
    );
    console.log(this.oauthService.getIdentityClaims());
  }

  buildReservation(): any {
    const reservation = {
      numberOfSeats: this.inputs.number_of_seats.value,
      apartments: this.inputs.apartments.value,
      checkIn: this.inputs.checkIn.value,
      checkOut: this.inputs.checkOut.value,
      userByUserId: this.user,
      status: this.state
    };
    console.log(reservation);
    return reservation;
  }

  get inputs(): any {
    return this.reservationForm.controls;
  }

  createNewReservationByUser(): any {
    return this.reservationService.createReservationByUser(this.buildReservation()).subscribe(d => console.log(d));
  }

}
