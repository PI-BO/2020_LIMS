package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.model.ProjekteIdList;
import exceptions.ModelNotFoundException;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(WelcomeServlet.ROUTE)
public class WelcomeServlet extends HttpServlet {

	private static final long serialVersionUID = 6585003356653758862L;
	private static final Logger LOGGER = LogManager.getLogger(WelcomeServlet.class.getSimpleName());
	
	public static final String ROUTE = "/welcome";
	public static final String WELCOME_PAGE = "/welcome.jsp";
	public static final String SESSION_ATTRIBUTE_NAVIGATION = "projekt_id_lise";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doPost()");
    	
    	try {
    		redirectToWelcomePage(request, response);
		}
		catch (SQLException e) {
			LOGGER.debug(e);
		}
		catch (ModelNotFoundException e) {
			LOGGER.debug(e);
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doGet()");
    	response.sendRedirect(request.getContextPath() + "/projekte_design.html");
    }
    
    private void redirectToWelcomePage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ModelNotFoundException{
    	
    	HttpSession session = request.getSession();
		session.setAttribute(SESSION_ATTRIBUTE_NAVIGATION, new ProjekteIdList());
		response.sendRedirect(request.getContextPath() + WELCOME_PAGE);
    }
}