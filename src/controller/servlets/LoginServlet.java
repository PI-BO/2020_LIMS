package controller.servlets;

import exceptions.LoginInputInvalidException;
import exceptions.ModelNotFoundException;
import exceptions.PasswordIncorrectException;
import model.Login;
import model.database.tableModels.Mitarbeiter;
import model.database.tableModels.Sessions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(LoginServlet.ROUTE)
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 244881171954270102L;
    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class.getSimpleName());

    public static final String ROUTE = "/login";
    public static final String LOGIN_PAGE = "/login.html";
    private static final String FORWARD_ROUTE = MainServlet.ROUTE;

    public static final String REQUEST_ATTRIBUTE = "login";
    public static final String REQUEST_PARAMETER_PASSWORD = "mitarbeiter_password";
    public static final String REQUEST_PARAMETER_ID = "mitarbeiter_id";

    /**
     * Checks Cookies for Session id, and request Database for Session/User validation
     * @param request Site Http Request for Cookie reference
     * @return User Object if session user is valid else null
     * @throws ModelNotFoundException if session or user can't be found in Database
     * @throws SQLException
     */
    private static Mitarbeiter getSessionUser(HttpServletRequest request) throws ModelNotFoundException, SQLException {
        Mitarbeiter m = null;
        Cookie[] cookieArray = request.getCookies();
        if (cookieArray != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().equals("session")) {
                    Sessions sessions = new Sessions(c.getValue());
                    m = new Mitarbeiter(sessions.getMitarbeiterID());
                }
            }
        }
        return m;
    }

    /**
     * returns if user is Logged in in a valid session
     * if not sends redirect
     * @param request Site HTTP request
     * @param response Site HTTP response
     * @return true if user is valid session else false
     * @throws IOException
     */
    public static boolean isLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isLogin = false;
        try {
            isLogin = getSessionUser(request) != null;
            if (!isLogin) response.sendRedirect(request.getContextPath() + LoginServlet.LOGIN_PAGE);
        } catch (ModelNotFoundException | SQLException e) {
        	e.printStackTrace();
            response.sendRedirect(request.getContextPath() + LoginServlet.LOGIN_PAGE);
        }
        return isLogin;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validateUserLogin(request);
            keepUserLoggedIn(request, response);
            forwardRequest(request, response); // Unterschied forward, redirect:	https://javabeat.net/difference-forward-sendredirect-servlet/
        } catch (SQLException e) {
            logException(e);
            e.printStackTrace();
        } catch (ModelNotFoundException e) {
        	e.printStackTrace();
        	logException(e);
            returnLoginFailedPage(response);
        } catch (PasswordIncorrectException e) {
        	e.printStackTrace();
        	logException(e);
            returnPasswordIncorrectPage(response);
        } catch (LoginInputInvalidException e) {
        	e.printStackTrace();
        	logException(e);
            returnLoginInputInvalidPage(response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.debug("doGet() called");
        try {
           Mitarbeiter m = getSessionUser(request);
            if (m != null) {
                LOGGER.debug("redirect to: " + FORWARD_ROUTE);
                response.sendRedirect(request.getContextPath() + FORWARD_ROUTE);
            } else {
                LOGGER.debug("redirect to: " + LOGIN_PAGE);
                response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
            }
        } catch (SQLException | ModelNotFoundException throwables) {
            throwables.printStackTrace();
            LOGGER.debug("redirect to: " + LOGIN_PAGE);
            response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
        }
    }

    private void validateUserLogin(HttpServletRequest request) throws SQLException, ModelNotFoundException, PasswordIncorrectException, LoginInputInvalidException {
        String mitarbeiterId = getEnteredMitarbeiterId(request);
        Login login = new Mitarbeiter(mitarbeiterId);
        String password = getEnteredPassword(request);
        login.validate(password);
    }

    private String getEnteredPassword(HttpServletRequest request) throws LoginInputInvalidException {

        String password = request.getParameter(REQUEST_PARAMETER_PASSWORD);

        if (password.isEmpty()) throw new LoginInputInvalidException();

        return password;
    }

    private String getEnteredMitarbeiterId(HttpServletRequest request) throws LoginInputInvalidException {

        String mitarbeiterId = request.getParameter(REQUEST_PARAMETER_ID);

        if (mitarbeiterId.isEmpty()) throw new LoginInputInvalidException();

        Pattern pattern = Pattern.compile("[a-z]");
        Matcher userInputMatcher = pattern.matcher(mitarbeiterId);

        boolean wrongUserInputFound = userInputMatcher.find();

        if (wrongUserInputFound) throw new LoginInputInvalidException();

        return mitarbeiterId;
    }

    private void keepUserLoggedIn(HttpServletRequest request, HttpServletResponse response) throws LoginInputInvalidException, ModelNotFoundException, SQLException {
        String mitarbeiterId = getEnteredMitarbeiterId(request);
        Mitarbeiter login = new Mitarbeiter(mitarbeiterId);
        String sessionKey = randomString(20);
        Cookie cookie = new Cookie("session", sessionKey);
        //cookie.setHttpOnly(true);
        //cookie.setSecure(true);
        if (getKeepLogin(request)) cookie.setMaxAge(60 * 60 * 24 * 30); // Set age in seconds for 30 Days
        response.addCookie(cookie);
        Sessions session = new Sessions(cookie, login);
        session.saveToDatabase();
    }

    private boolean getKeepLogin(HttpServletRequest request) {
        String[] check = request.getParameterValues("keep-login");
        return check != null;
    }

    private static String randomString(int length) {
        SecureRandom secureRnd = new SecureRandom();
        String SOURCE = "0123456789" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ" +
                "abcdefghijklmnopqrstuvwxyzäöüß" +
                "!§$%&/()=?{[]}+*~'#-";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(SOURCE.charAt(secureRnd.nextInt(SOURCE.length())));
        return sb.toString();
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

    private void returnLoginFailedPage(HttpServletResponse response) {
        //TODO
    }

    private void returnLoginInputInvalidPage(HttpServletResponse response) {
        //TODO
    }

    private void returnPasswordIncorrectPage(HttpServletResponse response) {
        //TODO
    }

    private void logException(Exception e) {
        LOGGER.debug(e.toString());
    }
}
