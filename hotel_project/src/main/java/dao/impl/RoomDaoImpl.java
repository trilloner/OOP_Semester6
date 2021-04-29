package dao.impl;

import dao.RoomDao;
import dao.mapper.RoomMapper;
import model.reservation.Reservation;
import model.room.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RoomDaoImpl implements RoomDao {
    private final Logger logger = LogManager.getLogger(RoomDaoImpl.class);
    private final Connection connection;
    private final RoomMapper roomMapper = new RoomMapper();

    ResourceBundle bundle = ResourceBundle.getBundle("sql");

    public RoomDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Optional<Room> create(Room entity) {
        return Optional.empty();
    }

    @Override
    public Room findById(long id) {
        Room room = new Room();
        try {
            PreparedStatement firstStatement = connection.prepareStatement("SELECT * FROM reservation WHERE id=?");
            firstStatement.setLong(1, id);
            ResultSet resultSet = firstStatement.executeQuery();

            while (resultSet.next()) {
                room.setId(resultSet.getLong("id"));
                return room;
            }
        } catch (SQLException e) {
            logger.error("Room can`t be found: {}", e.getMessage());
        }
        return room;
    }

    @Override
    public List<Room> findAll() {
        ResultSet set;
        List<Room> rooms = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(bundle.getString("room.find_all"));
            set = statement.executeQuery();
            while (set.next()) {
                rooms.add(roomMapper.getRoomFromResultSet(set));
            }
        } catch (SQLException ex) {
            logger.warn("Rooms can`t be found: {}", ex.getMessage());
        }
        return rooms;
    }

    @Override
    public Optional<Room> update(Room entity) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
