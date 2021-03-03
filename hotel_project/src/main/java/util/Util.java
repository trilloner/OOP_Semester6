package util;

import model.reservation.Reservation;
import model.reservation.ReservationBuilder;
import model.room.RoomBuilder;

import javax.servlet.http.HttpServletRequest;

public class Util {

    public static Reservation buildAdminReservation(HttpServletRequest request) {

        return new ReservationBuilder()
                .setRoomId(new RoomBuilder().setId(Long.parseLong(request.getParameter("room_id")) ).build())
                .setId(Long.parseLong(request.getParameter("id")))
                .build();
    }
}
