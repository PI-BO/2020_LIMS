package controller.servlets.probeneingang;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.Probeneingang;
import model.database.tableModels.Probe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;

@WebServlet("/probeneingang/bearbeiten")
@MultipartConfig
public class ProbeneingangBearbeitenServlet extends HttpServlet {
	private static final Logger LOGGER = LogManager.getLogger(ProbeneingangBearbeitenServlet.class.getSimpleName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String primaryKey = request.getParameter("id");
		JSON json;

		if (primaryKey != null && !primaryKey.isEmpty())
			try {
				Probe probe = new Probe(primaryKey);

				json = probe.toJSON();

				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().print(json);
			} catch (ModelNotFoundException e) {
				//e.printStackTrace();
			} catch (SQLException throwables) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.setContentType("text/plain");
				response.getWriter().println(throwables);
			}
	}
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LOGGER.debug("doPost()");
    	
    	response.setHeader("Access-Control-Allow-Origin", "*"); // TODO nur fuer Testzwecke! in Produktion rausnehmen!
    	response.setContentType("application/json");
    	response.setCharacterEncoding("utf-8");
    	PrintWriter out = response.getWriter();
    	
    	JSON json = new JSON();
    	
		try {
			Probeneingang probeneingang = createProbeneingang(request);
			probeneingang.saveToDatabasePlaceholderMethod();
			
			Probe probe = new Probe();
			probe.setPrimaryKey(probeneingang.getProbenNr());
			probe.setProjektID(probeneingang.getWirkstoffId());
			probe.saveToDatabase();
		}
		catch (IOException e) {
			e.printStackTrace();
			return;
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Substanz ID nicht vorhanden");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		catch (DublicateModelException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Proben ID schon vorhanden");
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
    
    private Probeneingang createProbeneingang(HttpServletRequest request) throws IOException, ServletException {
    	Probeneingang probeneingang = new Probeneingang();
    	
    	Enumeration<String> parameterNames = request.getParameterNames();
    	
    	String parameterName;
    	if(parameterNames.hasMoreElements()) parameterNames.nextElement();
    	
    	while(parameterNames.hasMoreElements()){
    		parameterName = parameterNames.nextElement();
    		
    		if(parameterName == null) continue;
    		
    		String parameter = request.getParameter(parameterName);
    		
    		probeneingang.setParameters(parameterName, parameter);
    	}
    	
    	Collection<Part> parts = request.getParts();
		
		for(Part part : parts){
			if(part.getSubmittedFileName() == null) continue;
			
			LOGGER.debug("imagefileName: " + part.getSubmittedFileName());
			
			probeneingang.addBild(part.getInputStream());
		}
		
		return probeneingang;
	}
}