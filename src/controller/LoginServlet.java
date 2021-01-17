package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.Config;
import exceptions.LoginInputInvalidException;
import exceptions.ModelNotFoundException;
import exceptions.PasswordIncorrectException;
import model.Login;
import model.Mitarbeiter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 244881171954270102L;
	private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class.getSimpleName());
	public static final String REQUEST_ATTRIBUTE = "login";
	public static final String REQUEST_PARAMETER_PASSWORD = Config.getValue("html.requestParameter.loginPage.password");
	public static final String REQUEST_PARAMETER_ID = Config.getValue("html.requestParameter.loginPage.id");
	private static final String FORWARD_ROUTE = "ProjekteServletRoute";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try
    	{
    		String mitarbeiterIdString = request.getParameter(REQUEST_PARAMETER_ID);
    		String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);
    		
    		System.out.println(mitarbeiterIdString + " " + password);
    		
    		validateUserLogin(request);
    		forwardRequest(request, response);
		}
    	catch (SQLException e)
    	{
    		logException(e);
		}
    	catch (ModelNotFoundException e)
    	{
    		logException(e);
			returnLoginFailedPage(response);
		}
    	catch (PasswordIncorrectException e) {
			
			logException(e);
			returnPasswordIncorrectPage(response);
		}
    	catch (LoginInputInvalidException e) {
			
    		logException(e);
			returnLoginInvalidPage(response);
		}
    }

	private void validateUserLogin(HttpServletRequest request) throws SQLException, ModelNotFoundException, PasswordIncorrectException, LoginInputInvalidException {
		
		String mitarbeiterId = getEnteredMitarbeiterId(request);
		Login login = new Mitarbeiter(mitarbeiterId);
		String password = getEnteredPassword(request);
		login.validate(password);
		addMitarbeiterToRequest(request, (Mitarbeiter)login);
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    	LOGGER.debug("doGet() called but not implemented");
    }
    
	private String getEnteredPassword(HttpServletRequest request) throws LoginInputInvalidException {
		
		String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);
		
		if(password.isEmpty()) throw new LoginInputInvalidException();

		return password;
	}

	private String getEnteredMitarbeiterId(HttpServletRequest request) throws LoginInputInvalidException {
		
		String mitarbeiterIdString = request.getParameter(REQUEST_PARAMETER_ID);
		
		if(mitarbeiterIdString.isEmpty()) throw new LoginInputInvalidException();
		
		Pattern pattern = Pattern.compile("[a-z]");
	    Matcher matcher = pattern.matcher(mitarbeiterIdString);
	    
	    boolean matchFound = matcher.find();
	    
	    if(matchFound) throw new LoginInputInvalidException();
		
	    String mitarbeiterId = request.getParameter(REQUEST_PARAMETER_ID);
	    
		return mitarbeiterId;
	}

	private void forwardRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		forwardRequestToRoute(request, response, FORWARD_ROUTE);
	}

	private void addMitarbeiterToRequest(HttpServletRequest request, Login login) {
		request.setAttribute(LoginServlet.REQUEST_ATTRIBUTE, login);
	}

    private void forwardRequestToRoute(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(route);
        requestDispatcher.forward(request, response);
    }
    
    private void returnLoginFailedPage(HttpServletResponse response){
    	
    	PrintWriter htmlWriter;
		try
		{
			htmlWriter = response.getWriter();
			htmlWriter.println("<div>Mitarbeiter nicht gefunden.</div>");
		}
		catch (IOException e)
		{
			e.printStackTrace();
			LOGGER.debug(e.toString());
		}
    }
    
    private void returnLoginInvalidPage(HttpServletResponse response){
    	
    	PrintWriter htmlWriter;
    	try
    	{
    		htmlWriter = response.getWriter();
    		htmlWriter.println("<div>Keine oder inkorrekte Zeichen eingegeben.</div>");
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    		LOGGER.debug(e.toString());
    	}
    }
    
    private void returnPasswordIncorrectPage(HttpServletResponse response) {
    	
    	PrintWriter htmlWriter;
    	
    	try
    	{    		
    		htmlWriter = response.getWriter();
    		htmlWriter.println("<div>Falsches Passwort.</div>");
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    		LOGGER.debug(e.toString());
    	}
    }
    
    private void logException(Exception e) {
    	LOGGER.debug(e.toString());
    }
}
