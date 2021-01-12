package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utility.HtmlUtility;
import config.Config;
import controller.LoginServlet;
import model.Mitarbeiter;
import model.ProjekteIdList;

public class ProjekteListHTML implements HTMLPage{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<String> projekteIdStringList;
	private BufferedReader htmlFile;
	private final String REQUEST_PARAMETER = Config.getValue("html.requestParameter.ProjekteListPage");
	private final String HTML_FILE_NAME = Config.getValue("html.file.ProjekteListPage");
	private final String FORWARD_ROUTE = "/2020_LIMS/Projekt";
		
	public ProjekteListHTML(ProjekteIdList projekteIdList, HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) throws FileNotFoundException {

		this.request = request;
		this.response = response;
		this.projekteIdStringList = projekteIdList.getProjekteIdList();
		htmlFile = HtmlUtility.getHtmlFile(HTML_FILE_NAME, servlet);
	}
	
	@Override
	public void show() throws IOException {

		Mitarbeiter mitarbeiter = (Mitarbeiter) request.getAttribute(LoginServlet.REQUEST_ATTRIBUTE);
    	
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
         		
         		for(String projektId : projekteIdStringList){
         			
         			htmlWriter.println(""
         					+ "<form action=\"" + FORWARD_ROUTE + "\" method=\"post\">"
         					+ "<tr>"
         					+ 	"<td>"
         					+ 		"<input type=\"text\" "
         					+ 			"id=\"projektIdInput\" "
         					+ 			"name=\"" + REQUEST_PARAMETER + "\" "
         					+ 			"value=\"" + projektId + "\" "
 							+	 		"readonly "
 							+ 			"style=\"border:0;\""
 							+ 			">"
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
}
