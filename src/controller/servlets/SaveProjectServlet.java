package controller.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.database.tableModels.Partner;
import model.database.tableModels.Projekt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(SaveProjectServlet.ROUTE)
public class SaveProjectServlet extends HttpServlet {

	private static final long serialVersionUID = 651365257358343314L;

	private static final Logger LOGGER = LogManager.getLogger(SaveProjectServlet.class.getSimpleName());
	
	public static final String ROUTE = "/save_project_servlet";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doPost()");
    	
    	Projekt projekt = new Projekt();
		projekt.setPrimaryKey(request.getParameter(Projekt.COLUMN_PRIMARY_KEY));
		projekt.setVertragsnummer(request.getParameter(Partner.COLUMN_PRIMARY_KEY));
		
		try {
			projekt.save();
			response.getWriter().println("<div style=\"color:green\">Erfolgreich gespeichert</div>");
		}
		catch (SQLException e) {
			e.printStackTrace();
			response.getWriter().println("<h3 style=\"color:red\">FEHLER!</h3>");
			response.getWriter().println("<div style=\"color:red\">" + e + "</div>");
		}
	}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LOGGER.debug("doGet()");
    	response.sendRedirect(request.getContextPath() + LoginServlet.LOGIN_PAGE);
    }
}