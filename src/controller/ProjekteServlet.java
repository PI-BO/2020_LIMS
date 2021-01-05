package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Mitarbeiter;
import model.Projekt;
import prototypes.DatabaseServlet;
import controller.Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class ProjekteServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(DatabaseServlet.class.getSimpleName());
	private Database database = new MariaDBController();
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	List<Projekt> projekte;
    	
    	try 
    	{
			projekte = database.getProjekte();
			returnProjektePage(request, response, projekte);
		}
    	catch (SQLException e) {
			logException(e);
		}
    	
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	logger.debug("doGet() called but not implemented");
    }
    
    private void returnProjektePage(HttpServletRequest request, HttpServletResponse response, List<Projekt> projekte) throws IOException {
    	
    	BufferedReader htmlFile = HtmlUtility.getHtmlFile("projekte.html", this);
    	
    	Mitarbeiter mitarbeiter = (Mitarbeiter) request.getAttribute("mitarbeiter");
    	
    	PrintWriter htmlWriter = response.getWriter();
    	
    	String line;
    	
    	while((line = htmlFile.readLine()) != null){
         	
    		htmlWriter.println(line);
    		
    		if(line.contains("<body>")){
    			
    			htmlWriter.println("<div>Login erfolgreich.</div>");
    			htmlWriter.println("<br>");
    			htmlWriter.println("<div>Hallo <b>" + mitarbeiter.getVorname() + " " + mitarbeiter.getNachname() + "</b>. Willkommen auf der Projekte Seite.</div>");
    			htmlWriter.println("<br>");
    		}
    		
         	if(line.contains("id=\"table\"")){
         		
         		htmlWriter.println("<tr><th> Projektname </th></tr>");
         		
         		for(Projekt projekt : projekte){
         			
         			htmlWriter.println(""
         					+ "<form action=\"/2020_LIMS\" method=\"post\">"
         					+ "<tr>"
         					+ 	"<td>"
         					+ 		"<input type=\"text\" id=\"projektIdInput\" name=\"projektID\" value=\"" + projekt.getProjektName() + "\" disabled style=\"color:black; background-color:white;\">"
         					+ 	"</td>"
         					+ 	"<td>"
         					+ 		"<button>-></button>"
         					+ 	"</td>"
         					+ "</tr>"
         					+ "</form>"
         					);
         		}
         	}
    	}
    }

    private void logException(Exception e) {
    	logger.debug(e.toString());
    }
}