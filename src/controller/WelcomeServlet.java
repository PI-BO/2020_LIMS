package controller;

import exceptions.ModelNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        forwardToWelcomePage(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("doGet()");
        if (LoginServlet.isLogin(request, response)) doPost(request, response);

    }

    private void redirectToWelcomePage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ModelNotFoundException {
        response.sendRedirect(request.getContextPath() + WELCOME_PAGE);
    }

    private void forwardToWelcomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(WELCOME_PAGE);
        requestDispatcher.forward(request, response);
    }
}