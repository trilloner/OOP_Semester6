package servlets;

import model.reservation.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ReservationService;
import util.JsonConverter;
import util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private final ReservationService reservationService = new ReservationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Executing reservation post controller");
        try {

            Reservation reservation = reservationService.updateReservation(Util.buildAdminReservation(req));
            JsonConverter.makeJsonAnswer(reservation, resp);
        } catch (Exception e) {
            logger.error("Can`t update reservation:{}", e.getMessage());
            resp.sendError(500);
        }
    }
}
