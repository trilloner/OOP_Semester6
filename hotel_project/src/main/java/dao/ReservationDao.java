package dao;

import model.reservation.Reservation;

import java.util.List;

public interface ReservationDao extends GenericDao<Reservation> {
    List<Reservation> findReservationsByUser(Long id);
}
