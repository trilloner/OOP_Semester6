package com.example.oop.hotel;


import com.example.oop.hotel.dto.ReservationDTO;
import com.example.oop.hotel.models.Reservation;
import com.example.oop.hotel.models.Status;
import com.example.oop.hotel.models.User;
import com.example.oop.hotel.repositories.OrderRepository;
import com.example.oop.hotel.services.OrderService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrderRepository orderRepository;


    @Autowired
    private OrderService orderService;


    @Test
    public void getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        when(orderRepository.findAllByOrderByStatusDesc()).thenReturn(list);
        assertEquals(list, orderService.getAllOrders());
        verify(orderRepository, times(1)).findAllByOrderByStatusDesc();
    }

    @Test
    public void getAllReservationsByUser() {
        List<Reservation> list = new ArrayList<>();
        User user = new User();
        user.setName("Ivan");
        user.setId(1L);

        when(orderRepository.getAllByUserByUserId(any())).thenReturn(list);

        assertEquals(list, orderService.getAllOrdersByUser(user));

        verify(orderRepository, times(1)).getAllByUserByUserId(user);
    }

    @Test
    public void createNewReservationByUser() throws Exception {
        Reservation reservation = new Reservation();
        ReservationDTO reservationDTO = new ReservationDTO();
        User user = new User();
        user.setName("Ivan");

        when(orderRepository.save(any())).thenReturn(reservation);

        assertEquals(reservation, orderService.createOrder(reservationDTO));

        verify(orderRepository, times(1)).save(any());
    }

    @Test
    public void shouldUpdateReservationById() throws Exception {

        Reservation oldReservation = new Reservation();
        oldReservation.setStatus(Status.PENDING);
        Reservation newReservation = new Reservation();
        newReservation.setStatus(Status.CONFIRMED);

        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(orderRepository.save(any())).thenReturn(newReservation);

        orderService.updateOrder(1L, 3L);

        verify(orderRepository, times(2)).findById(anyLong());
    }
}
