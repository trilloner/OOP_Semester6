package servlets;

import model.reservation.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReservationService;
import util.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/cabinet")
public class CabinetServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private final ReservationService reservationService = new ReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Executing cabinet get controller");
        try {
            List<Reservation> listOfReservations = reservationService.findAllReservationByUser(
                    Long.parseLong(req.getParameter("user")));
            JsonConverter.makeJsonAnswer(listOfReservations, resp);
        } catch (Exception ex) {
            logger.info("Reservation can`t be founded: {}", ex.getMessage());
            resp.sendError(500);
        }
    }
}
