package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import config.Config;
import exceptions.ModelNotFoundException;
import database.model.Substanz;

public class SubstanzServlet extends HttpServlet{

	private static final long serialVersionUID = -636069655154226214L;
	private static final Logger LOGGER = LogManager.getLogger(SubstanzServlet.class.getSimpleName());
	private final String REQUEST_PARAMETER = Config.getValue("html.requestParameter.ProjektPage");
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String substanzId = req.getParameter(REQUEST_PARAMETER);
		
		try 
		{
			Substanz substanz = new Substanz(substanzId);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ModelNotFoundException e) {
			LOGGER.debug(e.toString());
		}
		
	}
	
	
	
	

}
