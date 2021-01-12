package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utility.HtmlUtility;
import config.Config;
import controller.ProjekteServlet;
import model.Projekt;
import model.Substanz;

public class ProjektHTML implements HTMLPage{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Projekt projekt;
	private BufferedReader htmlFile;
	private final String HTML_FILE_NAME = Config.getValue("html.file.ProjektPage");
	private final String FORWARD_ROUTE = "/2020_LIMS/Projekt";
	private final String REQUEST_PARAMETER = Config.getValue("html.requestParameter.ProjektPage");
		
	public ProjektHTML(Projekt projekt, HttpServletRequest request, HttpServletResponse response, ProjekteServlet servlet) throws FileNotFoundException {
	
		this.request = request;
		this.response = response;
		this.projekt = projekt;
		htmlFile = HtmlUtility.getHtmlFile(HTML_FILE_NAME, servlet);
	}

	@Override
	public void show() throws IOException {

    	PrintWriter htmlWriter = response.getWriter();
    	
    	String line;
    	
    	while((line = htmlFile.readLine()) != null){
         	
    		htmlWriter.println(line);
    		
    		if(line.contains("<body>")){
    			
    			htmlWriter.println("<div>Projekt: <b>" + projekt.getId());
    			htmlWriter.println("<br>");
    		}
    		
         	if(line.contains("id=\"table\"")){
         		
         		htmlWriter.println("<tr><th>Substanzen</th></tr>");
         		
         		for(Substanz substanz : projekt.getSubstanzen()){
         			
         			htmlWriter.println(""
         					+ "<form action=\"" + FORWARD_ROUTE + "\" method=\"post\">"
         					+ "<tr>"
         					+ 	"<td>"
         					+ 		"<input type=\"text\" "
         					+ 			"id=\"substanzIdInput\" "
         					+ 			"name=\"" + REQUEST_PARAMETER + "\" "
         					+ 			"value=\"" + substanz.getId() + "\" "
 							+	 		"readonly "
 							+ 			"style=\"border:0;\""
 							+ 			">"
         					+ 	"</td>"
         					+ 	"<td>"
         					+ 		"<button disabled>-></button>"
         					+ 	"</td>"
         					+ "</tr>"
         					+ "</form>"
         					);
         		}
         	}
    	}
	}

}
