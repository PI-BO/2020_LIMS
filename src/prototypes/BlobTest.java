package prototypes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Queue;

public class BlobTest {
    private static final Logger logger = LogManager.getLogger(BlobTest.class.getSimpleName());
    private static Connection connection;
    private static final String LOGIN_NAME = "root";
    private static final String PASSWORT = "123";
    private static final String DB_ADRESSE = "jdbc:mariadb://localhost:3306/test";

    public static void main(String[] args) throws SQLException, IOException {
        connectToDatabase();

        //printTableContent(convertTableContent(returnTableContent("employees")));

        //insertBlobFrom("C:\\Users\\Leberwurst\\Desktop\\Vue.jpg");

        File file = new File("C:\\Users\\Leberwurst\\Desktop\\Vue2.jpg");

        TableBilder.getFile(file, connection);

        disconnectFromDatabase();
    }

    public static void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(DB_ADRESSE, LOGIN_NAME, PASSWORT);
        logger.debug("connected");
    }


    public static void insertBlobFrom(String fileLocation) throws SQLException, IOException {

        String sql = "INSERT INTO bilder (bild) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            File image = new File(fileLocation);
            FileInputStream fis = new FileInputStream(image);
            stmt.setBinaryStream(1, fis, (int) image.length());
            stmt.execute();

            connection.commit();
            fis.close();
        }
    }

    public static void disconnectFromDatabase() throws SQLException {

        connection.close();
        logger.debug("disconnected");
    }

    public static ResultSet returnTableContent(String table) throws SQLException {
        ResultSet resultSet;

        String query = "SELECT * FROM " + table + ";";

        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(query);
        }
        logger.info("table content returned");
        return resultSet;
    }

    private static Queue<String> getTableContentAsQueue(ResultSet tableContentResultSet) throws SQLException {
        Queue<String> tableContent = new LinkedList<>();

        while (tableContentResultSet.next()) {
            StringBuilder line = new StringBuilder();

            for (int i = 1; i <= tableContentResultSet.getMetaData().getColumnCount(); i++) {
                line.append(tableContentResultSet.getString(i)).append(", ");
            }

            line = new StringBuilder(line.toString().trim());

            if (line.toString().endsWith(",")) {
                line = new StringBuilder(line.substring(0, line.length() - 1));
            }
            tableContent.add(line.toString());
        }
        return tableContent;
    }

    private static void printTableContent(Queue<String> tableContent) {

        String line;

        while (!tableContent.isEmpty()) {
            line = tableContent.poll();
            logger.info(line);
        }
    }
}
