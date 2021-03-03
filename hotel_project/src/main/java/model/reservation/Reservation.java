package model.reservation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import model.room.Room;
import model.user.User;
import util.LocalDateDeserializer;
import util.LocalDateSerializer;

import java.time.LocalDate;

public class Reservation {
    private Long id;

    private Integer numberOfSeats;

    private String apartments;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkIn;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate checkOut;

    private User userByUserId;

    private String status;

    private Room roomId;

    public Reservation() {
    }

    public Reservation(Long id, Integer numberOfSeats, String apartments, LocalDate checkIn, LocalDate checkOut, User userByUserId, String status, Room roomId) {
        this.id = id;
        this.numberOfSeats = numberOfSeats;
        this.apartments = apartments;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.userByUserId = userByUserId;
        this.status = status;
        this.roomId = roomId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getApartments() {
        return apartments;
    }

    public void setApartments(String apartments) {
        this.apartments = apartments;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }
}
