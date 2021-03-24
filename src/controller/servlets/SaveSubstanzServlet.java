package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.tableModels.Partner;
import model.database.tableModels.Projekt;
import model.database.tableModels.Substanz;
import utility.JSON;
import utility.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(SaveSubstanzServlet.ROUTE)
public class SaveSubstanzServlet extends HttpServlet {

	private static final long serialVersionUID = 3436048584501475296L;

	private static final Logger LOGGER = LogManager.getLogger(SaveSubstanzServlet.class.getSimpleName());
	
	public static final String ROUTE = "/save_substanz_servlet";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doPost()");
    	
    	response.setHeader("Access-Control-Allow-Origin", "*"); // TODO nur fuer Testzwecke! in Produktion rausnehmen!
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
    	
    	
    	JSON json = new JSON();
    	
		try {
			Substanz substanz = new Substanz();
			substanz.setPrimaryKey(request.getParameter(Substanz.COLUMN_PRIMARY_KEY));
			substanz.setProjektID(request.getParameter(Substanz.COLUMN_PROJEKT_ID));
			substanz.setWirkstoff(request.getParameter(Substanz.COLUMN_WIRKSTOFF));
			substanz.saveToDatabase();
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Projekt ID nicht vorhanden");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		catch (DublicateModelException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Substanz ID schon vorhanden");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		catch (SQLException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "SQLException");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		
		json.addKeyValue("status", "success");
		json.addKeyValue("message", "Erfolgreich gespeichert");
		out.print(json.toString());
		
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doGet()");
    	response.sendRedirect(request.getContextPath() + LoginServlet.LOGIN_PAGE);
    }
}