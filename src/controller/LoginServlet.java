package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.exceptions.MitarbeiterNotFoundException;
import controller.exceptions.PasswordIncorrectException;
import model.Mitarbeiter;
import prototypes.DatabaseServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(DatabaseServlet.class.getSimpleName());
	
	Database database = new MariaDBController();
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try
    	{
    		int mitarbeiterId = getEnteredMitarbeiterId(request);
    		String mitarbeiterPassword = getEnteredPassword(request);

    		Mitarbeiter mitarbeiter = database.getMitarbeiter(mitarbeiterId);
    		database.validateMitarbeiter(mitarbeiter, mitarbeiterPassword);
    		addMitarbeiterToRequest(request, mitarbeiter);
    		forwardRequest(request, response);
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
    	catch (PasswordIncorrectException e) {
			
			logException(e);
			returnPasswordIncorrectPage(response);
		}
    	catch (LoginInvalidException e) {
			
    		logException(e);
			returnLoginInvalidPage(response);
		}
    }

	private String getEnteredPassword(HttpServletRequest request) throws LoginInvalidException {
		
		String password = request.getParameter("mitarbeiterPasswort");
		
		if(password.isEmpty()) throw new LoginInvalidException();

		return password;
	}

	private int getEnteredMitarbeiterId(HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("mitarbeiterID"));
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	logger.debug("doGet() called but not implemented");
    }    
    
	private void forwardRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		forwardRequestToRoute(request, response, "ProjekteServletRoute");
	}

	private void addMitarbeiterToRequest(HttpServletRequest request, Mitarbeiter mitarbeiter) {
		request.setAttribute("mitarbeiter", mitarbeiter);
	}

    private void forwardRequestToRoute(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(route);
        requestDispatcher.forward(request, response);
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
    
    private void returnLoginInvalidPage(HttpServletResponse response){
    	
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

	public LoginInvalidException(){
		super();
	}
	
	public LoginInvalidException(String message){
		super(message);
	}
}
