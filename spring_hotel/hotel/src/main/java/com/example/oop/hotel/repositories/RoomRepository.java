package com.example.oop.hotel.repositories;

import com.example.oop.hotel.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoomRepository extends JpaRepository<Room, Long> {
}
