package controller.servlets.partner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.servlets.login.LoginServlet;
import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyDB;
import model.database.manager.DatabaseManager;
import model.database.tableModels.Model;
import model.database.tableModels.Partner;
import utility.JSON;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(PartnerErstellenServlet.ROUTE)
public class PartnerErstellenServlet extends HttpServlet {

	private static final long serialVersionUID = 8965190467865649574L;

	private static final Logger LOGGER = LogManager.getLogger(PartnerErstellenServlet.class.getSimpleName());
	
	public static final String ROUTE = "/partner_erstellen_servlet";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doPost()");
    	
    	response.setHeader("Access-Control-Allow-Origin", "*"); // TODO nur fuer Testzwecke! in Produktion rausnehmen!
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		if(hasDublicatedName(request, out)) return;	//TODO nur fuer DummyDB, bei MariaDB umstellen
		
    	Partner partner = new Partner();
    	partner.setPrimaryKey(request.getParameter(Partner.COLUMN_PRIMARY_KEY));
    	partner.setName(request.getParameter(Partner.COLUMN_NAME));
    	partner.setEmail(request.getParameter(Partner.COLUMN_EMAIL));
    	
    	JSON json = new JSON();
    	
		try {
			partner.saveToDatabase();
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "ModelNotFoundException - Problem liegt bei der Datenbank.");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		catch (DublicateModelException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Partner ID schon vorhanden");
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

	private boolean hasDublicatedName(HttpServletRequest request, PrintWriter out) {
		DummyDB dummyDb = (DummyDB)(DatabaseManager.getDatabaseInstance());
		
		for(Model model : dummyDb.getModelList()){
			if(model.getClass() != Partner.class) continue;
			Partner partner = (Partner)model;
			if(!partner.getName().equals(request.getParameter(Partner.COLUMN_NAME))) continue;
			
			JSON json = new JSON();
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Name schon vorhanden");
			out.print(json.toString());
			
			System.out.println("doppel");
			
			return true;
		}
		
		return false;
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doGet()");
    	response.sendRedirect(request.getContextPath() + LoginServlet.LOGIN_PAGE);
    }
}