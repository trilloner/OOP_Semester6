import model.user.User;
import model.user.UserBuilder;
import org.junit.Before;
import org.junit.Test;
import service.UserService;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    private final UserService userService = new UserService();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void givenUsedEmail() throws Exception {
        User user = new UserBuilder().setId(1L).setName("Alex").setPassword("1").setEmail("12@gmail.com").build();
        when(userService.loginUser(anyString(), anyString())).thenReturn(user);
        userService.loginUser("name", "pass");
        verify(userService, times(1)).loginUser(anyString(), anyString());
    }


}



