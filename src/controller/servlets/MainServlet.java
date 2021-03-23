package controller.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.Address;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(MainServlet.ROUTE)
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 6585003356653758862L;
	private static final Logger LOGGER = LogManager.getLogger(MainServlet.class.getSimpleName());

	public static final String ROUTE = "/main";
	public static final String MAIN_PAGE = Address.getMainRelativeJSP();
	public static final String SESSION_ATTRIBUTE_NAVIGATION = "projekt_id_lise";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.debug("doPost()");
		redirectToWelcomePage(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.debug("doGet()");
		redirectToWelcomePage(request, response);
		// if (LoginServlet.isLogin(request, response)) doPost(request,
		// response);

	}

	private void redirectToWelcomePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + MAIN_PAGE);
	}

	private void forwardToWelcomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(MAIN_PAGE);
		requestDispatcher.forward(request, response);
	}
}