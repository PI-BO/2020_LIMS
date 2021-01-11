package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Projekt;
import view.ProjektHTML;
import view.ProjekteListHTML;
import controller.Database;
import controller.exceptions.MitarbeiterNotFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProjekteServlet extends HttpServlet {

	private static final long serialVersionUID = 6585003356653758862L;
	private static final Logger logger = LogManager.getLogger(ProjekteServlet.class.getSimpleName());
	private Database database = new MariaDBController();
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try 
    	{
    		System.out.println(request.getServletPath());
    		
    		if(request.getServletPath().equals("/ProjekteServletRoute")) showProjekteListPage(request, response);
    		if(request.getServletPath().equals("/Projekt")) showProjektPage(request, response);
		}
    	catch (SQLException e) {
			logException(e);
		}
    }

	private void showProjekteListPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, FileNotFoundException, IOException {
		
		List<Projekt> projekte = database.getProjekte();
		HTMLPage htmlPage = new ProjekteListHTML(projekte, request, response, this);
		htmlPage.show();
	}
	
	private void showProjektPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, FileNotFoundException, IOException {
		
		String projektId =  request.getParameter("projektId");
		Projekt projekt = database.getProjekt(projektId);

		HTMLPage htmlPage = new ProjektHTML(projekt, request, response, this);
		htmlPage.show();
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	logger.debug("doGet() called but not implemented");
    }

    private void logException(Exception e) {
    	logger.debug(e.toString());
    }
}