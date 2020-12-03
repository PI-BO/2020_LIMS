package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private Connection connection;
    private final String loginName = "root";
    private String passwort = "123";
    private String dbAdresse = "jdbc:mariadb://localhost:3306/test";

    public void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(dbAdresse, loginName, passwort);
        System.out.println(this.getClass().getSimpleName() + ": connected");
    }

    public void disconnectFromDatabase() throws SQLException {
        connection.close();
        System.out.println(this.getClass().getSimpleName() + ": disconnected");
    }

    public ResultSet returnTableContent(String table) throws SQLException {
        Statement statement;
        ResultSet resultSet;

        String query = "SELECT * FROM " + table + ";";

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        System.out.println(this.getClass().getSimpleName() + ": table content returned");
        return resultSet;
    }
}
