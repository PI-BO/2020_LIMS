package controller.servlets.projekt;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.servlets.login.LoginServlet;
import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.tableModels.Projekt;
import utility.JSON;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(ProjektServlet.ROUTE)
@MultipartConfig
public class ProjektServlet extends HttpServlet {

	private static final long serialVersionUID = 651365257358343314L;

	private static final Logger LOGGER = LogManager.getLogger(ProjektServlet.class.getSimpleName());
	
	public static final String ROUTE = "/projekt";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doPost()");
    	
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
    	
    	Projekt projekt = new Projekt();
    	projekt.setPrimaryKey(request.getParameter(Projekt.COLUMN_PRIMARY_KEY));
    	projekt.setProjektPartnerId(request.getParameter(Projekt.COLUMN_PROJEKTPARTNER));
    	
    	JSON json = new JSON();
    	
		try {
			projekt.saveToDatabase();
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Projektpartner nicht vorhanden");
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doPut()");

		PrintWriter out = response.getWriter();

		JSON json = new JSON();

		try {
			String primaryKey = request.getParameter(Projekt.COLUMN_PRIMARY_KEY);
			Projekt projekt = new Projekt(primaryKey);

			String vertragsnummer = request.getParameter(Projekt.COLUMN_VERTRAGSNUMMER);
			projekt.setVertragsnummer(vertragsnummer);

			projekt.updateModel();
			
			Projekt projektTest = new Projekt(primaryKey);
			System.out.println("projektTest = " + projektTest.toJSON().toString());
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Partner ID nicht vorhanden");
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String primaryKey = req.getParameter("id");
		JSON json;

		if (primaryKey != null && !primaryKey.isEmpty()) try {
			Projekt projekt = new Projekt(primaryKey);

			json = projekt.toJSON();

			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(json);
		}
		catch (ModelNotFoundException e) {
			 e.printStackTrace();
		}
		catch (SQLException throwables) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.setContentType("text/plain");
			resp.getWriter().println(throwables);
		}
	}
}