package controller.servlets.projekt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.servlets.LoginServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.tableModels.Projekt;
import utility.JSON;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(SaveProjectServlet.ROUTE)
public class SaveProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 651365257358343314L;

	private static final Logger LOGGER = LogManager.getLogger(SaveProjectServlet.class.getSimpleName());
	
	public static final String ROUTE = "/save_project_servlet";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doPost()");
    	
    	response.setHeader("Access-Control-Allow-Origin", "*"); // TODO nur fuer Testzwecke! in Produktion rausnehmen!
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
    	
    	Projekt projekt = new Projekt();
    	projekt.setPrimaryKey(request.getParameter(Projekt.COLUMN_PRIMARY_KEY));
    	projekt.setVertragsnummer(request.getParameter(Projekt.COLUMN_VERTRAGSNUMMER));
    	projekt.setProjektPartnerId(request.getParameter(Projekt.COLUMN_PROJEKTPARTNER));
    	
    	JSON json = new JSON();
    	
		try {
			projekt.saveToDatabase();
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Projektpartner ID nicht vorhanden");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		catch (DublicateModelException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Projekt ID schon vorhanden");
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