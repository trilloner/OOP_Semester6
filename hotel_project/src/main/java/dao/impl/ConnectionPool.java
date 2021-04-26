package dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConnectionPool {
    private final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private List<Connection> availableConnections = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();
    private final ResourceBundle bundle = ResourceBundle.getBundle("sql");

    private final int MAX_SIZE = 5;

    public ConnectionPool() {
        for (int count = 0; count < MAX_SIZE; count++) {
            availableConnections.add(this.createConnection());
        }
    }

    private Connection createConnection() {
        Connection conn = null;
        try {
            Class.forName(bundle.getString("database.driver"));
            conn = DriverManager.getConnection(bundle.getString("database.url"),
                    bundle.getString("database.user"), bundle.getString("database.pass"));
        } catch (SQLException | ClassNotFoundException e) {
            logger.info("Connection can`t be created");
        }
        return conn;
    }



    public Connection getConnection() {
        if (availableConnections.size() == 0) {
            logger.info("All connections are used");
            return null;
        } else {
            Connection con =
                    availableConnections.remove(
                            availableConnections.size() - 1);
            usedConnections.add(con);
            return con;
        }

    }

    public boolean releaseConnection(Connection con) {
        if (null != con) {
            usedConnections.remove(con);
            availableConnections.add(con);
            return true;
        }
        return false;
    }


    public int getFreeConnectionCount() {
        return availableConnections.size();
    }
}
