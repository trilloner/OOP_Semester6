package com.example.oop.hotel.controllers;

import com.example.oop.hotel.dto.RoomsDto;
import com.example.oop.hotel.services.RoomService;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/rooms")
@CrossOrigin( "http://localhost:4200")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<RoomsDto> findAllRooms() {

        return ResponseEntity.ok(roomService.getAllRooms());
    }


}
