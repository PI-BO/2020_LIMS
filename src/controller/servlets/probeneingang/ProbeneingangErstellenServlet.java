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

@WebServlet(ProbeneingangErstellenServlet.ROUTE)
@MultipartConfig
public class ProbeneingangErstellenServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ProbeneingangErstellenServlet.class.getSimpleName());

    private static final long serialVersionUID = 7322122506656092712L;
    public static final String ROUTE = "/probeneingang";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	
    	System.out.println(this.getClass() + ": doPost()");
    	LOGGER.debug("doPost()");
    	
    	response.setHeader("Access-Control-Allow-Origin", "*"); // TODO nur fuer Testzwecke! in Produktion rausnehmen!
    	response.setContentType("application/json");
    	response.setCharacterEncoding("utf-8");
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

    private Probeneingang createProbeneingang(HttpServletRequest request) throws IOException, ServletException {
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
        LOGGER.debug("doPost()");
    }

    private void printTestLog(HttpServletRequest request) throws IOException, ServletException {
        Enumeration<String> parameterNames = request.getParameterNames();

        System.out.println("\n----------PARAMETER----------\n");

        String parameterName;
        if (parameterNames.hasMoreElements()) parameterName = parameterNames.nextElement();

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();
            if (parameterName == null) continue;
            String parameter = request.getParameter(parameterName);
            System.out.println(parameterName + " : " + parameter);
        }

        System.out.println("\n----------PARTS----------\n");

        Collection<Part> parts = request.getParts();

        for (Part p : parts) {
            System.out.println("fileName: " + p.getSubmittedFileName());
        }
    }
}