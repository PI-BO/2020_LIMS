package prototypes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class DatabaseServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(DatabaseServlet.class.getSimpleName());
    
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();

    public DatabaseServlet() {
        logger.debug("created");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            connectToDatabase();
            returnTableContentAsHTML("employees", response);
            disconnectFromDatabase();
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void connectToDatabase() throws SQLException {
        databaseConnection.connectToDatabase();
    }

    private void disconnectFromDatabase() throws SQLException {
        databaseConnection.disconnectFromDatabase();
    }

    private void returnTableContentAsHTML(String table, HttpServletResponse response) throws SQLException, IOException, InterruptedException {
        Queue<String> tableContent = getTableContent(table);
        PrintWriter htmlWriter = response.getWriter();
        writeTableContentToHtmlWriter(tableContent, htmlWriter);
    }

    private Queue<String> getTableContent(String table) throws SQLException {
        Queue<String> tableContent = new LinkedList<>();

        ResultSet tableContentResultSet = databaseConnection.returnTableContent(table);

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

    private void writeTableContentToHtmlWriter(Queue<String> tableContent, PrintWriter htmlWriter) throws InterruptedException {
        String line;

        while (!tableContent.isEmpty()) {
            line = tableContent.poll();
            htmlWriter.println(line);
        }
    }
}
