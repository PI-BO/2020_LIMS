package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// wird nicht ausgeführt da weiter unten der Request direkt an AfterIndexServlet weitergeleitet wurde
		PrintWriter htmlWriter = response.getWriter();
		htmlWriter.println("<h1>Willkommen</>");
		htmlWriter.println("<h2>zum Index Servlet!</>");
		
		forwardRequestToRoute(request, response, "AfterIndexServletRoute");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void forwardRequestToRoute(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException{
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(route);
		requestDispatcher.forward(request, response);
	}

}
