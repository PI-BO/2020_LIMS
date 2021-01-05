package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Projekt;
import prototypes.DatabaseServlet;
import view.ProjekteHTML;
import controller.Database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProjekteServlet extends HttpServlet {

	private static final long serialVersionUID = 6585003356653758862L;
	private static final Logger logger = LogManager.getLogger(DatabaseServlet.class.getSimpleName());
	private Database database = new MariaDBController();
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	try 
    	{
    		showProjektePage(request, response);
		}
    	catch (SQLException e) {
			logException(e);
		}
    }

	private void showProjektePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, FileNotFoundException, IOException {
		
		List<Projekt> projekte = database.getProjekte();
		HTMLPage htmlPage = new ProjekteHTML(projekte, request, response, this);
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