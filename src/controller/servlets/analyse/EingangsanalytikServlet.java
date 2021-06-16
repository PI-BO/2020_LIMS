package controller.servlets.analyse;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Eingangsanalyse;
import model.EingangsanalyseSetterEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@WebServlet(EingangsanalytikServlet.ROUTE)
@MultipartConfig
public class EingangsanalytikServlet extends HttpServlet {

	private static final long serialVersionUID = -56418996427501117L;

	private static final Logger LOGGER = LogManager.getLogger(EingangsanalytikServlet.class.getSimpleName());

	public static final String ROUTE = "/eingangsanalytik";
	
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
    	
    	LOGGER.debug("doPost()");
    	
    	//TODO JSON Daten in key value Paaren aufteilen und in Eingangsanalyse Objekt einfuegen
    	
    	try {
			BufferedReader br = request.getReader();
			String bla;
			
			while((bla = br.readLine()) != null){
				
				String[]stringArray = bla.split(",");
				
				for(String value : stringArray){
					System.out.println(value.replace("\"", ""));
				}
			}
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    private Eingangsanalyse createEingangsanalyse(HttpServletRequest request) throws IOException, ServletException {
    	
    	Eingangsanalyse eingangsanalyse = new Eingangsanalyse();
    	
    	Enumeration<String> parameterNames = request.getParameterNames();
    	
    	String parameterName;
    	if(parameterNames.hasMoreElements()) parameterName = parameterNames.nextElement();
    	
    	while(parameterNames.hasMoreElements()){
    		
    		parameterName = parameterNames.nextElement();
    		
    		if(parameterName == null) continue;
    		
    		String parameter = (String)request.getParameter(parameterName);
    		LOGGER.debug(parameterName + ": " + parameter);
    		
    		for(EingangsanalyseSetterEnum setter : EingangsanalyseSetterEnum.values()){
    			
    			if(parameterName.equalsIgnoreCase(setter.name())){
    				setter.set(eingangsanalyse, parameter);
    				System.out.println("SET: " + parameter);
    			}
    		}
    		
    	}
    	
		return eingangsanalyse;
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.debug("doPost()");
    }

	private void printTestLog(HttpServletRequest request) throws IOException, ServletException {
    	
    	Enumeration<String> parameterNames = request.getParameterNames();
    	
    	System.out.println("\n----------PARAMETER----------\n");
    	
    	String parameterName;
    	if(parameterNames.hasMoreElements()) parameterName = parameterNames.nextElement();
    	
    	while(parameterNames.hasMoreElements()){
    		
    		parameterName = parameterNames.nextElement();
    		if(parameterName == null) continue;
    		String parameter = (String)request.getParameter(parameterName);
    		System.out.println(parameterName + " : " + parameter);
    	}
    	
    	System.out.println("\n----------PARTS----------\n");
    	
    	Collection<Part> parts = request.getParts();
		
		for(Part p : parts){
			System.out.println("fileName: " + p.getSubmittedFileName());
		}
	}
}