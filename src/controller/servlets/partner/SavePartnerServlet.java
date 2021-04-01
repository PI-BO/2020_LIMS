package controller.servlets.partner;

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
import model.database.tableModels.Partner;
import utility.JSON;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(SavePartnerServlet.ROUTE)
public class SavePartnerServlet extends HttpServlet {

	private static final long serialVersionUID = 8965190467865649574L;

	private static final Logger LOGGER = LogManager.getLogger(SavePartnerServlet.class.getSimpleName());
	
	public static final String ROUTE = "/save_partner_servlet";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doPost()");
    	
    	response.setHeader("Access-Control-Allow-Origin", "*"); // TODO nur fuer Testzwecke! in Produktion rausnehmen!
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
    	
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
			json.addKeyValue("message", "Projektpartner ID nicht vorhanden");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doGet()");
    	response.sendRedirect(request.getContextPath() + LoginServlet.LOGIN_PAGE);
    }
}