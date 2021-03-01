package service;

import dao.FactoryDao;
import dao.ReservationDao;
import model.reservation.Reservation;

import java.util.List;

public class ReservationService {

    private final FactoryDao factoryDao = FactoryDao.getInstance();

    public Reservation createNewReserve(Reservation reservation) throws Exception {
        try (ReservationDao reservationDao = factoryDao.createReservationDao()) {
            return reservationDao.create(reservation)
                    .orElseThrow(() -> new Exception("reservation cannot be created"));
        }
    }

    public List<Reservation> findAllReservationByUser(Long id) {
        try (ReservationDao reservationDao = factoryDao.createReservationDao()) {
            return reservationDao.findReservationsByUser(id);
        }
    }

    public List<Reservation> findAllReservations() {
        try (ReservationDao reservationDao = factoryDao.createReservationDao()) {
            return reservationDao.findAll();
        }
    }

    public void updateReservation(Reservation reservation) {
        try (ReservationDao reservationDao = factoryDao.createReservationDao()) {
            reservationDao.update(reservation);
        }
    }

}
