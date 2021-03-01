package dao;

import dao.impl.FactoryDaoImpl;

public abstract class FactoryDao {
    private static FactoryDao daoFactory;

    public abstract UserDao createUserDao();

    public abstract ReservationDao createReservationDao();

    public abstract RoomDao createRoomDao();

    public static FactoryDao getInstance() {
        if (daoFactory == null) {
            synchronized (FactoryDao.class) {
                if (daoFactory == null) {
                    FactoryDao temp = new FactoryDaoImpl();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }

}
