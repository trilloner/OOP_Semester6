package com.example.oop.hotel;


import com.example.oop.hotel.dto.RoomsDto;
import com.example.oop.hotel.models.Room;
import com.example.oop.hotel.repositories.RoomRepository;
import com.example.oop.hotel.services.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class RoomServiceTest {

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;


    @Test
    public void getAllRooms() {
        List<Room> list = new ArrayList<>();
        when(roomRepository.findAll()).thenReturn(list);
        RoomsDto dto = roomService.getAllRooms();
        Assertions.assertEquals(list, dto.getRooms());
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void findRoomById() throws Exception {
        Room room = new Room();
        room.setId(1L);
        room.setApartment("lux");
        room.setName("Standart Luxe");
        room.setNumberOfSeats(1);
        room.setPrice(500);
        room.setState("available");
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(room));
        Assertions.assertEquals(room, roomService.findByRoomId(anyLong()));
        verify(roomRepository, times(1)).findById(anyLong());
    }



}
