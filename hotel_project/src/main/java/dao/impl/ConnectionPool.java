package dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance = null;

    private ConnectionPool() {
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/hotel");
            connection = dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            logger.warn("Connection could not be created: {}", e.getMessage());
        }
        return connection;
    }


}
