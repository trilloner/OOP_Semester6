package service;

import dao.FactoryDao;
import dao.RoomDao;
import model.room.Room;

import java.util.List;

public class RoomService {

    private FactoryDao factoryDao = FactoryDao.getInstance();

    public List<Room> findAllRooms() throws Exception {
        try (RoomDao roomDao = factoryDao.createRoomDao()) {
            return roomDao.findAll();
        }
    }

    public void setFactoryDao(FactoryDao factoryDao) {
        this.factoryDao = factoryDao;
    }
}
