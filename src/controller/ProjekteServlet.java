package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSpinnerUI;

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

public class ProjekteServlet extends HttpServlet {

	private static final long serialVersionUID = 6585003356653758862L;
	private static final Logger LOGGER = LogManager.getLogger(ProjekteServlet.class.getSimpleName());
	private final String REQUEST_PARAMETER = Config.getValue("html.requestParameter.ProjekteListPage");
	private final String FORWARD_ROUTE_PROJEKTE = Config.getValue("fowardRoute.Projekte");
	private final String FORWARD_ROUTE_PROJEKT = Config.getValue("fowardRoute.Projekt");
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	LOGGER.debug("doPost()");
    	
    	try 
    	{
    		if((request.getContextPath() + request.getServletPath()).equals(FORWARD_ROUTE_PROJEKTE)) showProjekteListPage(request, response);	//TODO Route aus Config auslesen?
    		if((request.getContextPath() + request.getServletPath()).equals(FORWARD_ROUTE_PROJEKT)) showProjektPage(request, response);
		}
    	catch (SQLException e)
    	{
			logException(e);
		}
		catch (ModelNotFoundException e) 
    	{
			logException(e);
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	LOGGER.debug("doGet()");
    	
    	try 
    	{
    		if((request.getContextPath() + request.getServletPath()).equals(FORWARD_ROUTE_PROJEKTE)) showProjekteListPage(request, response);	//TODO Route aus Config auslesen?
    		if((request.getContextPath() + request.getServletPath()).equals(FORWARD_ROUTE_PROJEKT)) showProjektPage(request, response);
		}
    	catch (SQLException e)
    	{
			logException(e);
		}
		catch (ModelNotFoundException e) 
    	{
			logException(e);
		}
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