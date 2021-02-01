package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(PostGetTestServlet.ROUTE)
public class PostGetTestServlet extends HttpServlet {

	private static final long serialVersionUID = -6604640998962371527L;

	private static final Logger LOGGER = LogManager.getLogger(PostGetTestServlet.class.getSimpleName());
	
	public static final String ROUTE = "/post-get-test";
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("TEST SERVLET: doPost()");
    	
    	Enumeration<String> parameterNames = request.getParameterNames();
    	
    	System.out.println("START - parameter names:");
    	
    	String parameterName;
    	
    	while(parameterNames.hasMoreElements()){
    		
    		parameterName = parameterNames.nextElement();
    		String value = (String)request.getParameter(parameterName);
    		System.out.println(parameterName + " : " + value);
    	}
    	
    	System.out.println("END - parameter names:");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("TEST SERVLET: doGet()");
    	System.out.println("TEST SERVLET: doGet() nothing implemented yet");
    }
}