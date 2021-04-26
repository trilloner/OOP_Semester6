import dao.impl.ConnectionPool;
import org.junit.Test;
import org.postgresql.jdbc.PgConnection;

public class ConnectionTest {


    @Test
    public void shouldReturnFakeConnection() {
        ConnectionPool connectionPool = new ConnectionPool();
        PgConnection connection = (PgConnection) connectionPool.getConnection();
        FakeConnection fakeConnection = (FakeConnection) connection;
        System.out.println(fakeConnection);

    }
}
