import {Component, OnInit} from '@angular/core';
import {Room} from '../model/room';
import {RoomService} from '../service/room.service';

@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.css']
})
export class RoomsComponent implements OnInit {
  rooms: Room[];

  constructor(private roomService: RoomService) {
  }

  ngOnInit(): void {
    this.getAllRooms();
  }

  getAllRooms(): void {
    this.roomService.getAllRooms().subscribe(rooms => {
      this.rooms = rooms;
    });
  }

}
