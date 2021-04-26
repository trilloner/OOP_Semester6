package com.example.oop.hotel.controllers;


import com.example.oop.hotel.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")

public class AdminController {
    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    // remove exception
    @PostMapping()
    public ResponseEntity updateOrder(@RequestParam Long orderId,
                                      @RequestParam Long roomId) {
        try {

            return ResponseEntity.ok(orderService.updateOrder(orderId, roomId));
        } catch (Exception e) {
            log.error("{} Can`t update order", e.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @GetMapping()
    public ResponseEntity deleteOrder(@RequestParam Long orderId) {
        try {
            orderService.deleteById(orderId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            log.error("The order cannot be deleted: {}", e.getMessage());
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
