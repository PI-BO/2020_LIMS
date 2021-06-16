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

@WebServlet(ProbeneingangServlet.ROUTE)
@MultipartConfig
public class ProbeneingangServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ProbeneingangServlet.class.getSimpleName());

    private static final long serialVersionUID = 7322122506656092712L;
    public static final String ROUTE = "/probeneingang";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	LOGGER.debug("doPost()");
    	
    	PrintWriter out = response.getWriter();
    	
    	JSON json = new JSON();
    	
		try
		{
			Probeneingang probeneingang = createProbeneingang(request);
			probeneingang.saveToDatabasePlaceholderMethod();
			
			Probe probe = new Probe();
			probe.setPrimaryKey(probeneingang.getProbenId());
			probe.setProjektID(probeneingang.getProjektId());
			probe.saveToDatabase();
		}
		catch (IOException e) {
			e.printStackTrace();
			return;
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Projekt ID nicht vorhanden");
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

    public static Probeneingang createProbeneingang(HttpServletRequest request) throws IOException, ServletException {
        Probeneingang probeneingang = new Probeneingang();

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {
            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            probeneingang.setParameters(parameterName, parameter);
        }

        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            if (part.getSubmittedFileName() == null) continue;

            LOGGER.debug("imagefileName: " + part.getSubmittedFileName());

            probeneingang.addBild(part.getInputStream());
        }

        return probeneingang;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	LOGGER.debug("doGet()");
		
		String primaryKey = request.getParameter("id");
		JSON json;

		if (primaryKey != null && !primaryKey.isEmpty()) try {
			Probe probe = new Probe(primaryKey);

			json = probe.toJSON();

			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(json);
		}
		catch (ModelNotFoundException e) {
			 e.printStackTrace();
		}
		catch (SQLException throwables) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setContentType("text/plain");
			response.getWriter().println(throwables);
		}
    }
    
    @Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	LOGGER.debug("doPut()");

		PrintWriter out = response.getWriter();

		JSON json = new JSON();

		try {
			Probeneingang probeneingang = ProbeneingangServlet.createProbeneingang(request);
			probeneingang.saveToDatabasePlaceholderMethod();

			Probe probe = new Probe();
			probe.setPrimaryKey(probeneingang.getProbenId());
			probe.setProjektID(probeneingang.getProjektId());
			probe.updateModel();
		}
		catch (IOException e) {
			e.printStackTrace();
			return;
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Projekt ID nicht vorhanden");
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
}