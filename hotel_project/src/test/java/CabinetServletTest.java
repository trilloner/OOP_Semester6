import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import service.ReservationService;
import servlets.CabinetServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ReservationService.class})
public class CabinetServletTest {

    private CabinetServlet servlet;
    private ReservationService reservationService;
    private HttpServletResponse response;
    private HttpServletRequest request;


    @Before
    public void init() throws Exception {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        reservationService = PowerMockito.mock(ReservationService.class);
        servlet = new CabinetServlet();
    }

    @Test
    public void whenCallDoGetThenReturnJson() throws Exception {

        when(request.getParameter("user")).thenReturn("1");
//        when(reservationService.findAllReservationByUser(anyLong())).thenReturn(new LinkedList<>());
        PowerMockito.verifyNew(ReservationService.class).withNoArguments();
        PowerMockito.when(reservationService.findAllReservationByUser(anyLong())).thenReturn(new LinkedList<>());
        servlet.doGet(request, response);
        verify(request, times(1)).getParameter("user");
//        verify(reservationService, times(1)).findAllReservationByUser(anyLong());
    }
}
