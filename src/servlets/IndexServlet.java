package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//    	 darf nicht ausgeführt werden da sonst "forwardRequestToRoute nicht ausgeführt wird
//        try (PrintWriter htmlWriter = response.getWriter()) {
//            htmlWriter.println("<h1>Willkommen</>");
//            htmlWriter.println("<h2>zum Index Servlet!</>");
//        }

        forwardRequestToRoute(request, response, "AfterIndexServletRoute");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void forwardRequestToRoute(HttpServletRequest request, HttpServletResponse response, String route) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(route);
        requestDispatcher.forward(request, response);
    }
}
