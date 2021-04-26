package com.example.oop.hotel.repositories;

import com.example.oop.hotel.models.Reservation;
import com.example.oop.hotel.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> getAllByUserByUserId(User userByUserId);

    List<Reservation> findAllByOrderByStatusDesc();

}
