package dao.impl;

import dao.FactoryDao;
import dao.ReservationDao;
import dao.RoomDao;
import dao.UserDao;

import java.sql.Connection;

public class FactoryDaoImpl extends FactoryDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public UserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public ReservationDao createReservationDao() {
        return new ReservationDaoImpl(getConnection());
    }

    @Override
    public RoomDao createRoomDao() {
        return new RoomDaoImpl(getConnection());
    }

    private Connection getConnection() {
        return connectionPool.getConnection();
    }
}
