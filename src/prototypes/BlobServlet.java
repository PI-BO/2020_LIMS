package prototypes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import servlets.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

@MultipartConfig
public class BlobServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(BlobServlet.class.getSimpleName());
    private static Connection connection;
    private static final String LOGIN_NAME = "root";
    private static final String PASSWORT = "123";
    private static final String DB_ADRESSE = "jdbc:mariadb://localhost:3306/test";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            connectToDatabase();

            //printTableContent(convertTableContent(returnTableContent("employees")));

            //insertBlobFrom("C:\\Users\\Leberwurst\\Desktop\\Vue.jpg");

            // Retrieves <input type="file" name="file" multiple="true">
            List<Part> fileParts = request.getParts().stream().filter( part ->
                    "file".equals(part.getName()) && part.getSize() > 0
            ).collect(Collectors.toList());

            for (Part filePart : fileParts) {
                String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                InputStream fileContent = filePart.getInputStream();
                insertBlobFrom(fileContent);
            }

            //String path = Objects.requireNonNull(Config.getApplicationProperties()).getProperty("path.picture", "C:\\Users\\Leberwurst\\Desktop\\Vue2.jpg");
            File file = new File("C:\\Users\\Narwutsch Dominic\\Desktop\\Vue2.jpg");

            TableBilder.getFile(file, connection);

            disconnectFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connectToDatabase() throws SQLException {
        connection = DriverManager.getConnection(DB_ADRESSE, LOGIN_NAME, PASSWORT);
        logger.debug("connected");
    }


    public static void insertBlobFrom(InputStream fis) throws SQLException, IOException {

        String sql = "INSERT INTO bilder (bild) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            //File image = new File(fileLocation);
            //FileInputStream fis = new FileInputStream(image);
            stmt.setBinaryStream(1, fis);
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

        String query = "SELECT * FROM %s;";
        query = String.format(query, table);

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
