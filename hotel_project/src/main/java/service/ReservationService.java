package service;

import dao.FactoryDao;
import dao.ReservationDao;
import model.reservation.Reservation;

import java.util.List;

public class ReservationService {

    private FactoryDao factoryDao = FactoryDao.getInstance();

    public Reservation createNewReserve(Reservation reservation) throws Exception {
        try (ReservationDao reservationDao = factoryDao.createReservationDao()) {
            return reservationDao.create(reservation)
                    .orElseThrow(() -> new Exception("reservation cannot be created"));
        }
    }
    public List<Reservation> findAllReservationByUser(Long id) throws Exception {
        try (ReservationDao reservationDao = factoryDao.createReservationDao()) {
            return reservationDao.findReservationsByUser(id);
        }
    }

    public List<Reservation> findAllReservations() throws Exception {
        try (ReservationDao reservationDao = factoryDao.createReservationDao()) {
            return reservationDao.findAll();
        }
    }

    public Reservation updateReservation(Reservation reservation) throws Exception {
        try (ReservationDao reservationDao = factoryDao.createReservationDao()) {
            return reservationDao.update(reservation)
                    .orElseThrow(() -> new Exception("Reservation can`t be updated"));
        }
    }

    public void setFactoryDao(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }
}
