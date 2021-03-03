import {Room} from './room';

export interface Reservation {
  id?: number;
  numberOfSeats: number;
  checkIn: Date;
  checkOut: Date;
  apartments: string;
  userByUserId: number;
  roomId?: Room;
  status: string;

}
