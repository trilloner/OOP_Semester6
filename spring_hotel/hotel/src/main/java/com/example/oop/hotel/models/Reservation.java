package com.example.oop.hotel.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Basic
    @Column(name = "apartments")
    private String apartments;

    @Basic
    @Column(name = "check_in")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkIn;

    @Basic
    @Column(name = "check_out")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate checkOut;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room roomId;

    public Reservation addStatusAndUser(Status status, User user) {
        this.setStatus(status);
        this.setUserByUserId(user);
        return this;
    }

    public Reservation addStatusAndRoom(Status status, Room room) {
        this.setStatus(status);
        this.setRoomId(room);
        return this;
    }


}
