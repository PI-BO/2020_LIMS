package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Mitarbeiter;

import java.io.IOException;
import java.io.PrintWriter;

public class ProjekteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	returnProjektePage(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    private void returnProjektePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	Mitarbeiter mitarbeiter = (Mitarbeiter) request.getAttribute("mitarbeiter");
    	
    	try (PrintWriter htmlWriter = response.getWriter()) {
    		htmlWriter.println("<div>Login erfolgreich.</div>");
    		htmlWriter.println("<br>");
    		htmlWriter.println("<div>Hallo " + mitarbeiter.getVorname() + mitarbeiter.getNachname() + ". Willkommen auf der Projekte Seite.</div>");
    	}
    }
}