package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.Config;
import model.Projekt;
import model.Projekt_Substanz;
import model.ProjekteIdList;
import view.HTMLPage;
import view.ProjektHTML;
import view.ProjekteListHTML;
import exceptions.ModelNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(ProjekteServlet.ROUTE)
public class ProjekteServlet extends HttpServlet {

	private static final long serialVersionUID = 6585003356653758862L;
	private static final Logger LOGGER = LogManager.getLogger(ProjekteServlet.class.getSimpleName());
	
	public static final String ROUTE = "/projekte";
	public static final String PROJEKTE = "/projekte.jsp";
	private final String REQUEST_PARAMETER = Config.getValue("html.requestParameter.ProjekteListPage");
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	LOGGER.debug("doPost()");
    	
//		Cookie cookie = new Cookie(name, value);
//      // setting cookie to expiry in 60 mins
//      crunchifyCookie.setMaxAge(10);
//      response.addCookie(crunchifyCookie);
//      response.sendRedirect("CrunchifyLoginSuccessful.jsp");
    	
    	HttpSession session = request.getSession();
    	try {
			session.setAttribute("liste", new ProjekteIdList());
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	response.sendRedirect(request.getContextPath() + PROJEKTE);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	LOGGER.debug("doGet()");
    	response.sendRedirect(request.getContextPath() + "/projekte_design.html");
    }

	private boolean validateSession(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(session == null) return false;
    	if(session.getAttribute("validatedUser") == null) return false;
    	if(!session.getAttribute("validatedUser").equals("true")) return false;
    	return true;
	}
    
	private void showProjekteListPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, FileNotFoundException, IOException, ModelNotFoundException {
		
		ProjekteIdList projekteIdList = new ProjekteIdList();
		HTMLPage htmlPage = new ProjekteListHTML(projekteIdList, request, response, this);
		htmlPage.show();
	}
	
	private void showProjektPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, FileNotFoundException, IOException, ModelNotFoundException {
		
		String projektId =  request.getParameter(REQUEST_PARAMETER);
		Projekt projekt = new Projekt(projektId);
		
		Projekt_Substanz projekt_Substanz = new Projekt_Substanz(projektId);

		HTMLPage htmlPage = new ProjektHTML(projekt, projekt_Substanz, request, response, this);
		htmlPage.show();
	}


    private void logException(Exception e) {
    	LOGGER.debug(e.toString());
    }
}