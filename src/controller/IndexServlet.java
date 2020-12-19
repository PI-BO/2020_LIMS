package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Mitarbeiter;
import model.MitarbeiterNotFoundException;
import prototypes.DatabaseServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class IndexServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(DatabaseServlet.class.getSimpleName());
	
	DatabaseConnection databaseConnection = new DatabaseConnection();
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	int mitarbeiterID = getRequestedMitarbeiterID(request);
    	
    	try
    	{
    		Mitarbeiter mitarbeiter = new Mitarbeiter(mitarbeiterID);
			forwardRequestToRoute(request, response, "AfterIndexServletRoute");
		}
    	catch (SQLException e)
    	{
			logger.debug(e.toString());
		}
    	catch (MitarbeiterNotFoundException e)
    	{
			logger.debug(e.toString());
			returnLoginFailedPage(response);
		}
    }

	private int getRequestedMitarbeiterID(HttpServletRequest request) {
		int mitarbeiterID = Integer.parseInt(request.getParameter("mitarbeiterID"));
		return mitarbeiterID;
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void forwardRequestToRoute(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(route);
        requestDispatcher.forward(request, response);
    }
    
    private void connectToDatabase() throws SQLException {
        databaseConnection.connectToDatabase();
    }

    private void disconnectFromDatabase() throws SQLException {
        databaseConnection.disconnectFromDatabase();
    }
    
    private void returnLoginFailedPage(HttpServletResponse response){
    	
    	PrintWriter htmlWriter;
		try {
			htmlWriter = response.getWriter();
			htmlWriter.println("<div>Mitarbeiter nicht gefunden.</div>");
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug(e.toString());
		}
    }
}
