package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;
import utility.HtmlUtility;
import database.model.Projekt;
import database.model.Substanz;
import database.relations.ProjekteSubstanz;

public class ProjektHTML implements HTMLPage{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Projekt projekt;
	private ProjekteSubstanz substanzen;
	private BufferedReader htmlFile;
	private final String HTML_FILE_NAME = Config.getValue("html.file.ProjektPage");
	private final String FORWARD_ROUTE = Config.getValue("fowardRoute.Projekt");
	private final String REQUEST_PARAMETER = Config.getValue("html.requestParameter.ProjektPage");
		
	public ProjektHTML(Projekt projekt, ProjekteSubstanz substanzen, HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) throws FileNotFoundException {
	
		this.request = request;
		this.response = response;
		this.projekt = projekt;
		this.substanzen = substanzen;
		htmlFile = HtmlUtility.getHtmlFile(HTML_FILE_NAME, servlet);
	}

	@Override
	public void show() throws IOException {

    	PrintWriter htmlWriter = response.getWriter();
    	
    	String line;
    	
    	while((line = htmlFile.readLine()) != null){
         	
    		htmlWriter.println(line);
    		
         	if(line.contains("class=\"container\"")){
         		
         		htmlWriter.println("<h1>Projekt: " + projekt.getPrimaryKey() + "</h1>");
         		
         		htmlWriter.println("<form action=\"" + FORWARD_ROUTE + "\" method=\"post\">");
         		
         		for(Substanz substanz : substanzen.getSubstanzen()){
         			
         			htmlWriter.println(""
         					+ "<form action=\"" + FORWARD_ROUTE + "\" method=\"post\">"
         					+ "<input type=\"submit\" "
         					+ "id=\"projektIdInput\" "
         					+ "name=\"" + REQUEST_PARAMETER + "\" "
         					+ "value=\"" + substanz.getPrimaryKey() + "\" "
 							+ ">"
         					);
         		}
         		
         		htmlWriter.println("</form>");
         	}
    	}
	}
}
