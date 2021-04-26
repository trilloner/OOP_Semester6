package com.example.oop.hotel.mapper;

import com.example.oop.hotel.dto.ReservationDTO;
import com.example.oop.hotel.models.Reservation;
import com.example.oop.hotel.models.User;

//@Mapper(componentModel = "spring")
public interface ReservationMapper {

    default Reservation toEntity(ReservationDTO reservationDTO) {
        return Reservation.builder()
                .apartments(reservationDTO.getApartments())
                .numberOfSeats(reservationDTO.getNumberOfSeats())
                .checkIn(reservationDTO.getCheckIn())
                .checkOut(reservationDTO.getCheckOut())
                .status(reservationDTO.getStatus())
                .userByUserId(new User().builder().id(reservationDTO.getUserByUserId()).build())
                .build();
    }
}
