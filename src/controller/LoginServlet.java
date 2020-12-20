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
import model.PasswordIncorrectException;
import prototypes.DatabaseServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(DatabaseServlet.class.getSimpleName());
	
	DatabaseConnection databaseConnection = new DatabaseConnection();
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try
    	{
    		validateMitarbeiterLoginAndForwardRequest(request, response);
		}
    	catch (SQLException e)
    	{
    		logException(e);
		}
    	catch (MitarbeiterNotFoundException e)
    	{
    		logException(e);
			returnLoginFailedPage(response);
		}
    	catch (LoginInvalidException e)
    	{
    		logException(e);
			returnLoginIsInvalidPage(response);
		} catch (PasswordIncorrectException e) {
			
			logException(e);
			returnPasswordIncorrectPage(response);
		}
    }

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	logger.debug("doGet() called but not implemented");
    }    
    
	private void validateMitarbeiterLoginAndForwardRequest(HttpServletRequest request, HttpServletResponse response) throws LoginInvalidException, SQLException, MitarbeiterNotFoundException, ServletException, IOException, PasswordIncorrectException {
		
		int mitarbeiterID = getRequestedMitarbeiterID(request);
		String mitarbeiterPassword = getRequestedMitarbeiterPassword(request);
		Mitarbeiter mitarbeiter = new Mitarbeiter(mitarbeiterID, mitarbeiterPassword);		// muss noch weitergeleitet werden
		
		request.setAttribute("mitarbeiter", mitarbeiter);
		
		forwardRequestToRoute(request, response, "ProjekteServletRoute");
	}

	private String getRequestedMitarbeiterPassword(HttpServletRequest request) {

		String password = request.getParameter("mitarbeiterPasswort");
		return password;
	}

	private int getRequestedMitarbeiterID(HttpServletRequest request) throws LoginInvalidException{
		
		try
		{
			int mitarbeiterID = Integer.parseInt(request.getParameter("mitarbeiterID"));
			return mitarbeiterID;
		}
		catch (Exception e){
			throw new LoginInvalidException();
		}
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
    
    private void returnLoginIsInvalidPage(HttpServletResponse response){
    	
    	PrintWriter htmlWriter;
    	try {
    		htmlWriter = response.getWriter();
    		htmlWriter.println("<div>Keine oder inkorrekte Zeichen eingegeben (nur Zahlen erlaubt).</div>");
    	} catch (IOException e) {
    		e.printStackTrace();
    		logger.debug(e.toString());
    	}
    }
    
    private void returnPasswordIncorrectPage(HttpServletResponse response) {
    	
    	PrintWriter htmlWriter;
    	try {
    		htmlWriter = response.getWriter();
    		htmlWriter.println("<div>Falsches Passwort.</div>");
    	} catch (IOException e) {
    		e.printStackTrace();
    		logger.debug(e.toString());
    	}
    }
    
    private void logException(Exception e) {
    	logger.debug(e.toString());
    }
}

class LoginInvalidException extends Exception{
	
}
