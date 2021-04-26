package servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.RoomService;
import util.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/rooms")

public class RoomsServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(LoginServlet.class);
    private final RoomService roomService = new RoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Executing room controller");
        try {
            JsonConverter.makeJsonAnswer(roomService.findAllRooms(), resp);
        } catch (Exception e) {
            logger.error("Something went wrong in room controller");
            resp.sendError(400, e.getMessage());
            //TODO remove 500. (400)
        }
    }
}
