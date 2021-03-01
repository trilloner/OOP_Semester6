package dao.impl;

import dao.ReservationDao;
import dao.mapper.ReservationMapper;
import model.reservation.Reservation;
import model.reservation.Status;
import model.room.Room;
import model.room.RoomBuilder;
import model.user.UserBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReservationDaoImpl implements ReservationDao {

    private final Logger logger = LogManager.getLogger(ReservationDaoImpl.class);
    private final Connection connection;
    private ResourceBundle bundle = ResourceBundle.getBundle("sql");
    private ReservationMapper reservationMapper = new ReservationMapper();

    public ReservationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Reservation> create(Reservation entity) {
        ResultSet set = null;
        try {
            PreparedStatement statement = connection.prepareStatement(bundle.getString("reservation.create"),
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getNumberOfSeats());
            statement.setString(2, entity.getApartments());
            statement.setDate(3, Date.valueOf(entity.getCheckIn()));
            statement.setDate(4, Date.valueOf(entity.getCheckOut()));
            statement.setLong(5, entity.getUserByUserId().getId());
            statement.setString(6, entity.getStatus());
            statement.executeUpdate();
            set = statement.getGeneratedKeys();
            if (set.next()) {
                entity.setId(set.getLong("id"));
            }
            return Optional.of(entity);


        } catch (SQLException ex) {
            logger.warn("Reservation can`t be created: {}", ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Reservation findById(int id) {
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservationList = new ArrayList<>();
        ResultSet set;
        try {
            PreparedStatement statement = connection.prepareStatement(bundle.getString("reservation.admin.find_all"));
            set = statement.executeQuery();
            while (set.next()) {
                Reservation reservation = reservationMapper.getReservationFromResultSet(set);
                reservation.setUserByUserId(new UserBuilder()
                        .setId(set.getLong("user_id"))
                        .setEmail(set.getString("email"))
                        .build());
                reservation.setRoomId(new RoomBuilder()
                        .setId(set.getLong("room_id"))
                        .setName(set.getString("name"))
                        .build());
                reservationList.add(reservation);
            }
        } catch (SQLException ex) {
            logger.warn("Reservations can`t be find: {}", ex.getMessage());
        }
        return reservationList;
    }

    @Override
    public List<Reservation> findReservationsByUser(Long id) {
        List<Reservation> reservationList = new ArrayList<>();
        ResultSet set;
        try {
            PreparedStatement statement = connection.prepareStatement(bundle.getString("reservation.find_by_user"));
            statement.setLong(1, id);
            set = statement.executeQuery();
            while (set.next()) {
                Reservation reservation = reservationMapper.getReservationFromResultSet(set);
                reservation.setRoomId(new RoomBuilder()
                        .setId(Long.parseLong(set.getString("room_id")))
                        .setName(set.getString("name"))
                        .build());
                reservationList.add(reservation);
            }

        } catch (SQLException ex) {
            logger.warn("Reservations can`t be find by user: {}", ex.getMessage());
        }
        return reservationList;
    }

    //TODO return Reservation
    @Override
    public void update(Reservation entity) {
        try {

            PreparedStatement statement = connection.prepareStatement(bundle.getString("reservation.update"));
            statement.setLong(1, entity.getRoomId().getId());
            statement.setString(2, Status.CONFIRMED.getName());
            statement.setLong(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.warn("Reservation could not be created: {}", ex.getMessage());
        }

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }


}
