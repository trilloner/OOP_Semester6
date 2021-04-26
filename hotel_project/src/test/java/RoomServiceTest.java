import dao.FactoryDao;
import dao.RoomDao;
import org.junit.Before;
import org.junit.Test;
import service.RoomService;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

public class RoomServiceTest {
    private RoomService roomService;
    private final FactoryDao factoryDao = mock(FactoryDao.class);
    private final RoomDao roomDao = mock(RoomDao.class);

    @Before
    public void init() {
        roomService = new RoomService();
        roomService.setFactoryDao(factoryDao);
    }

    @Test
    public void shouldGetAllRooms() throws Exception {
        when(factoryDao.createRoomDao()).thenReturn(roomDao);
        when(roomDao.findAll()).thenReturn(new LinkedList<>());
        roomService.findAllRooms();
        verify(factoryDao, times(1)).createRoomDao();
        verify(roomDao, times(1)).findAll();
    }
}
