import dao.impl.ConnectionPool;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {


    @Test
    public void shouldGetConnectionAndReturnBackToPool() throws SQLException {
        ConnectionPool connectionPool = new ConnectionPool();
        Connection connection = connectionPool.getConnection();
        Assert.assertEquals(4, connectionPool.getFreeConnectionCount());
        connection.close();
        Assert.assertEquals(5, connectionPool.getFreeConnectionCount());
    }
}
