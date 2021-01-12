package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.Config;

import java.sql.*;

public class DatabaseConnection {
	
    private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class.getSimpleName());
    private static final String LOGIN_NAME = Config.getValue("database.LOGIN_NAME");
    private static final String PASSWORD = Config.getValue("database.PASSWORD");
    private static final String DB_ADDRESS = Config.getValue("database.ADDRESS");
    private Connection connection;
    private static DatabaseConnection databaseConnection = null;
    
    private DatabaseConnection() throws SQLException{
    	
    	connectToDatabase();
    }
    
    public static DatabaseConnection getInstance() {
    	
    	if(databaseConnection == null){
    		
			try
			{
				databaseConnection = new DatabaseConnection();
			}
    		catch (SQLException e)
    		{    			
				e.printStackTrace();
				LOGGER.debug(e);
				LOGGER.debug("FAILED TO CONNECT TO DATABASE");
			}
    	}
    	
    	return databaseConnection;
    }
    
    private void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(DB_ADDRESS, LOGIN_NAME, PASSWORD);
        LOGGER.debug("connected to database");
    }

    private void disconnectFromDatabase() throws SQLException {
        connection.close();
        LOGGER.debug("disconnected from database");
    }

    public ResultSet executeSQLStatementAndReturnResults(String sqlStatement) throws SQLException{
    	
    	ResultSet resultSet;

        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlStatement);

        LOGGER.debug("executeSQLStatementAndReturnResults, SQL Statement: " + sqlStatement);
        return resultSet;
    }
}
