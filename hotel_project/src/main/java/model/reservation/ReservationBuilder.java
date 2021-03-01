package model.reservation;

import model.room.Room;
import model.user.User;

import java.time.LocalDate;

public class ReservationBuilder {
    private Long id;

    private Integer numberOfSeats;

    private String apartments;
    private LocalDate checkIn;

    private LocalDate checkOut;

    private User userByUserId;

    private String status;

    private Room roomId;

    public ReservationBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ReservationBuilder setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;
    }

    public ReservationBuilder setApartments(String apartments) {
        this.apartments = apartments;
        return this;

    }

    public ReservationBuilder setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
        return this;

    }

    public ReservationBuilder setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
        return this;

    }

    public ReservationBuilder setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
        return this;

    }

    public ReservationBuilder setStatus(String status) {
        this.status = status;
        return this;

    }

    public ReservationBuilder setRoomId(Room roomId) {
        this.roomId = roomId;
        return this;

    }

    public Reservation build() {
        return new Reservation(id, numberOfSeats, apartments, checkIn, checkOut, userByUserId, status, roomId);
    }
}
