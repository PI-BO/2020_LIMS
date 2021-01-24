package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.LoginInputInvalidException;
import exceptions.ModelNotFoundException;
import exceptions.PasswordIncorrectException;
import database.inerfaces.Login;
import database.model.Mitarbeiter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(LoginServlet.ROUTE)
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 244881171954270102L;
	private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class.getSimpleName());
	
	public static final String ROUTE = "/login";
	public static final String LOGIN_PAGE = "/login.html";
	private static final String FORWARD_ROUTE = WelcomeServlet.ROUTE;
	
	public static final String REQUEST_ATTRIBUTE = "login";
	public static final String REQUEST_PARAMETER_PASSWORD = "mitarbeiter_password";
	public static final String REQUEST_PARAMETER_ID = "mitarbeiter_id";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		validateUserLogin(request);
    		forwardRequest(request, response); // Unterschied forward, redirect:	https://javabeat.net/difference-forward-sendredirect-servlet/
		}
    	catch (SQLException e) {
    		logException(e);
		}
    	catch (ModelNotFoundException e) {
    		logException(e);
			returnLoginFailedPage(response);
		}
    	catch (PasswordIncorrectException e) {
			logException(e);
			returnPasswordIncorrectPage(response);
		}
    	catch (LoginInputInvalidException e) {
    		logException(e);
			returnLoginInputInvalidPage(response);
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	LOGGER.debug("doGet() called");
    	LOGGER.debug("redirect to: " + LOGIN_PAGE);
    	response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
    }
    
	private void validateUserLogin(HttpServletRequest request) throws SQLException, ModelNotFoundException, PasswordIncorrectException, LoginInputInvalidException {
		String mitarbeiterId = getEnteredMitarbeiterId(request);
		Login login = new Mitarbeiter(mitarbeiterId);
		String password = getEnteredPassword(request);
		login.validate(password);
	}
    
	private String getEnteredPassword(HttpServletRequest request) throws LoginInputInvalidException {
		
		String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);
		
		if(password.isEmpty()) throw new LoginInputInvalidException();

		return password;
	}

	private String getEnteredMitarbeiterId(HttpServletRequest request) throws LoginInputInvalidException {
		
		String mitarbeiterId = request.getParameter(REQUEST_PARAMETER_ID);
		
		if(mitarbeiterId.isEmpty()) throw new LoginInputInvalidException();
		
		Pattern pattern = Pattern.compile("[a-z]");
	    Matcher userInputMatcher = pattern.matcher(mitarbeiterId);
	    
	    boolean wrongUserInputFound = userInputMatcher.find();
	    
	    if(wrongUserInputFound) throw new LoginInputInvalidException();
		
		return mitarbeiterId;
	}

	private void forwardRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardRequestToRoute(request, response, FORWARD_ROUTE);
	}

    private void forwardRequestToRoute(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(route);
        requestDispatcher.forward(request, response);
    }

	private void redirectRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + FORWARD_ROUTE);
	}
    
    private void returnLoginFailedPage(HttpServletResponse response){
    	//TODO
    }
    
    private void returnLoginInputInvalidPage(HttpServletResponse response){
    	//TODO
    }
    
    private void returnPasswordIncorrectPage(HttpServletResponse response) {
    	//TODO
    }
    
    private void logException(Exception e) {
    	LOGGER.debug(e.toString());
    }
}
