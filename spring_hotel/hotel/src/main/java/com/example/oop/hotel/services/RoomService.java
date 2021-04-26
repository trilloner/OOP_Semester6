package com.example.oop.hotel.services;

import com.example.oop.hotel.dto.RoomsDto;
import com.example.oop.hotel.models.Room;
import com.example.oop.hotel.repositories.RoomRepository;
import org.springframework.stereotype.Service;

/**
 * Room service class
 */
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomsDto getAllRooms() {
        return new RoomsDto(roomRepository.findAll());
    }


    public Room findByRoomId(Long id) throws Exception {
        return roomRepository.findById(id)
                .orElseThrow(() -> new Exception("Could not find user by id"));
    }

}
