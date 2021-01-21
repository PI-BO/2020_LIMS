package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import config.Config;
import database.model.Probe;
import database.model.Substanz;
import database.relations.ProjekteSubstanz;
import database.relations.SubstanzenProbe;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubstanzHTML implements HTMLPage{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Substanz substanz;
	private SubstanzenProbe proben;
	private BufferedReader htmlFile;
	private final String HTML_FILE_NAME = Config.getValue("html.file.SubstanzPage");
	private final String FORWARD_ROUTE = Config.getValue("fowardRoute.Substanz");
	private final String REQUEST_PARAMETER = Config.getValue("html.requestParameter.SubstanzPage");

	public SubstanzHTML(Substanz substanz, SubstanzenProbe proben, HttpServletRequest request, HttpServletResponse response, HttpServlet servlet) throws FileNotFoundException {
		this.request = request;
		this.response = response;
		this.substanz = substanz;
		this.proben = proben;
		htmlFile = utility.HtmlUtility.getHtmlFile(HTML_FILE_NAME, servlet);
	}

	@Override
	public void show() throws IOException {

		PrintWriter htmlWriter = response.getWriter();

		String line;

		while((line = htmlFile.readLine()) != null){

			htmlWriter.println(line);

			if(line.contains("class=\"container\"")){

				htmlWriter.println("<h1>Projekt: " + substanz.getPrimaryKey() + "</h1>");

				htmlWriter.println("<form action=\"" + FORWARD_ROUTE + "\" method=\"post\">");

				for(Probe probe : proben.getProben()){
					htmlWriter.println(""
							+ "<form action=\"" + FORWARD_ROUTE + "\" method=\"post\">"
							+ "<input type=\"submit\" "
							+ "id=\"projektIdInput\" "
							+ "name=\"" + REQUEST_PARAMETER + "\" "
							+ "value=\"" + probe.getPrimaryKey() + "\" "
							+ ">"
					);
				}

				htmlWriter.println("</form>");
			}
		}
	}

}
