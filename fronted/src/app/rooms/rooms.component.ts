import {Component, OnInit} from '@angular/core';
import {Room} from '../model/room';
import {RoomService} from '../service/room.service';
// import {OAuthService, NullValidationHandler, AuthConfig} from 'angular-oauth2-oidc';
import {OAuthService} from 'angular2-oauth2/oauth-service';
import {Rooms} from '../model/rooms';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {
  rooms: Rooms;

  constructor(private roomService: RoomService) {
  }


  ngOnInit(): void {
    this.getAllRooms();
  }

  getAllRooms(): void {
    this.roomService.getAllRooms().subscribe(rooms => {
      console.log(rooms);
      this.rooms = rooms;
    });
  }

}
