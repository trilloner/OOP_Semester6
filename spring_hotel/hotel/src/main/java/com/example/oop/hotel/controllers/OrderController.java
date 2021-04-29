package com.example.oop.hotel.controllers;

import com.example.oop.hotel.dto.ReservationDTO;
import com.example.oop.hotel.models.Reservation;
import com.example.oop.hotel.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {

        this.orderService = orderService;
    }

    @GetMapping("/order")
    public ResponseEntity<List<Reservation>> getAllOrders() {

        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/order")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Reservation> createNewOrder(@RequestBody ReservationDTO reservation) {
        try {
            return ResponseEntity.ok(orderService.createOrder(reservation));
        } catch (Exception e) {
            log.error("Cannot create order");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
