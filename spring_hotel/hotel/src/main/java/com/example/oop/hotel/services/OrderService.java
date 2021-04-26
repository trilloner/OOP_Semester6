package com.example.oop.hotel.services;

import com.example.oop.hotel.dto.ReservationDTO;
import com.example.oop.hotel.mapper.ReservationMap;
import com.example.oop.hotel.models.Reservation;
import com.example.oop.hotel.models.Room;
import com.example.oop.hotel.models.Status;
import com.example.oop.hotel.models.User;
import com.example.oop.hotel.repositories.OrderRepository;
import com.example.oop.hotel.repositories.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Reservation service class
 */
@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ReservationMap reservationMapper;
    private final RoomRepository roomRepository;

    public OrderService(OrderRepository orderRepository, UserService userService,
                        ReservationMap reservationMapper, RoomRepository roomRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.reservationMapper = reservationMapper;

        this.roomRepository = roomRepository;
    }

    public Reservation createOrder(ReservationDTO reservationDTO)
            throws Exception {
        try {
            Reservation reservation = reservationMapper.toEntity(reservationDTO);
            return orderRepository.save(reservation);

        } catch (Exception e) {
            log.error("User cannot create order");
            throw new Exception("Something went wrong. Try again");
        }
    }

    public List<Reservation> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Reservation> getAllOrdersByUser(User user) {
        return orderRepository.getAllByUserByUserId(user);
    }


    @Transactional
    public Reservation updateOrder(Long reservationId, Long roomdId) throws Exception {
        try {
            Reservation reservation = orderRepository.findById(reservationId)
                    .orElseThrow(() -> new Exception("Can`t find user by reservationId"));
            Room room = roomRepository.findById(roomdId)
                    .orElseThrow(() -> new Exception("Can`t find room by reservationId"));
            return orderRepository.save(reservation.addStatusAndRoom(Status.CONFIRMED, room));
        } catch (Exception e) {
            log.info("Can`t update user: {}", e.getMessage());
            throw new Exception("Can`t update order");
        }
    }


    public boolean deleteById(Long id) throws Exception {
        try {
            orderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("Order id must not be null, {}", e.getMessage());
            throw new Exception("Order id must not be null");
        }
    }
}
