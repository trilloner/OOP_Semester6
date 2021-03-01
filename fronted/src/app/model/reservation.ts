export interface Reservation {
  id: number;
  number_of_seats: number;
  apartments: string;
  checkIn: Date;
  checkOut: Date;
  user_id: number;
  room_id: number;
  status: string;

}
