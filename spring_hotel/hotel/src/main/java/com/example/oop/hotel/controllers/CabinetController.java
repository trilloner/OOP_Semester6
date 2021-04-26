package com.example.oop.hotel.controllers;

import com.example.oop.hotel.models.Reservation;
import com.example.oop.hotel.models.User;
import com.example.oop.hotel.models.UserDetailsImpl;
import com.example.oop.hotel.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")

public class CabinetController {

    private final OrderService orderService;

    public CabinetController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/cabinet")
    public ResponseEntity getAllOrdersByUser(@RequestBody User user) {
        List<Reservation> allOrdersByUser = orderService.getAllOrdersByUser(user);
        return ResponseEntity.ok(allOrdersByUser);
    }
}
