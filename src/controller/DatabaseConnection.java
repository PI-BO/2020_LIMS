package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DatabaseConnection {
	
    private static final Logger logger = LogManager.getLogger(DatabaseConnection.class.getSimpleName());
    private static final String LOGIN_NAME = "root";
    private static final String PASSWORD = "123";
    private static final String DB_ADDRESS = "jdbc:mariadb://localhost:3306/demo";
    private Connection connection;

    public void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(DB_ADDRESS, LOGIN_NAME, PASSWORD);
        logger.debug("connected to database");
    }

    public void disconnectFromDatabase() throws SQLException {
        connection.close();
        logger.debug("disconnected from database");
    }

    public ResultSet returnTableContent(String table) throws SQLException {
        ResultSet resultSet;

        String query = "SELECT * FROM %s;";
        query = String.format(query, table);

        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(query);
        }

        logger.debug("table content returned");
        return resultSet;
    }
    
    public ResultSet executeSQLStatementAndReturnResults(String sqlStatement) throws SQLException{
    	
    	ResultSet resultSet;

        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlStatement);

        logger.debug("executeSQLStatementAndReturnResults, SQL Statement: " + sqlStatement);
        return resultSet;
    }
}
