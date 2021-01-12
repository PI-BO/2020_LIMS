package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.HtmlUtility;
import model.Mitarbeiter;
import model.Projekt;
import model.ProjekteIdList;

public class ProjekteListHTML implements HTMLPage{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<String> projekteIdStringList;
	private BufferedReader htmlFile;
		
	public ProjekteListHTML(ProjekteIdList projekteIdList, HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) throws FileNotFoundException {

		this.request = request;
		this.response = response;
		this.projekteIdStringList = projekteIdList.getProjekteIdList();
		htmlFile = HtmlUtility.getHtmlFile("projekte.html", servlet);
	}
	
	@Override
	public void show() throws IOException {

		Mitarbeiter mitarbeiter = (Mitarbeiter) request.getAttribute("login");	//TODO "login" in Config auslagern
    	
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
         					+ "<form action=\"/2020_LIMS/Projekt\" method=\"post\">"
         					+ "<tr>"
         					+ 	"<td>"
         					+ 		"<input type=\"text\" "
         					+ 			"id=\"projektIdInput\" "
         					+ 			"name=\"projektId\" "
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
