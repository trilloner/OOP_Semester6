package dao.mapper;

import model.room.Room;
import model.room.RoomBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper {

    public Room getRoomFromResultSet(ResultSet resultSet) throws SQLException {

        return new RoomBuilder()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setPrice(resultSet.getInt("price"))
                .setState(resultSet.getString("state"))
                .setApartment(resultSet.getString("apartment"))
                .setNumberOfSeats(resultSet.getInt("number_of_seats"))
                .build();
    }

}

