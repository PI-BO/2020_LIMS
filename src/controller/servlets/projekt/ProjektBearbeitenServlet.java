package controller.servlets.projekt;

import controller.servlets.analyse.AnalyseBearbeitenServlet;
import exceptions.ModelNotFoundException;
import model.database.tableModels.Partner;
import model.database.tableModels.Projekt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(ProjektBearbeitenServlet.ROUTE)
public class ProjektBearbeitenServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(AnalyseBearbeitenServlet.class.getName());
    public static final String ROUTE = "/projekt/bearbeiten";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String primaryKey = req.getParameter("id");
        JSON json;

        if (primaryKey != null && !primaryKey.isEmpty())
            try {
                Projekt projekt = new Projekt(primaryKey);

                json = projekt.toJSON();

                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/json;charset=utf-8");
                resp.getWriter().print(json);
            } catch (ModelNotFoundException e) {
                //e.printStackTrace();
            } catch (SQLException throwables) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.setContentType("text/plain");
                resp.getWriter().println(throwables);
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            String primaryKey = req.getParameter(Projekt.COLUMN_PRIMARY_KEY);
            Projekt projekt = new Projekt(primaryKey);

            String projektpartner = req.getParameter(Projekt.COLUMN_PROJEKTPARTNER);
            projekt.setProjektPartnerId(projektpartner);

            String vertragsnummer = req.getParameter(Projekt.COLUMN_VERTRAGSNUMMER);
            projekt.setVertragsnummer(vertragsnummer);

            projekt.updateModel();
        } catch (SQLException throwables) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.setContentType("text/plain");
            res.getWriter().println(throwables);
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

}
