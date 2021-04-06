package controller.servlets;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.manager.DatabaseManager;
import model.database.tableModels.Partner;
import model.database.tableModels.Probe;
import model.database.tableModels.Projekt;
import model.database.tableModels.analyse.Analyse;
import model.database.tableModels.experimente.Experiment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/jstree/search")
public class JSTreeSearchServlet extends HttpServlet {
    private Database database = DatabaseManager.getDatabaseInstance();
    private Set<String> keys;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("str");
        keys = new HashSet<>();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        String[] partnerparam = request.getParameterValues("Partner[]");
        if (partnerparam != null) getPartner(str, partnerparam);

        String[] projektparam = request.getParameterValues("Projekt[]");
        if (projektparam != null) getProjects(str, projektparam);

        String[] probenparam = request.getParameterValues("Probe[]");
        if (probenparam != null) getProbe(str, probenparam);

        String[] experimentparam = request.getParameterValues("Experiment[]");
        if (experimentparam != null) getExperiment(str, experimentparam);

        String[] analyseparam = request.getParameterValues("Analyse[]");
        if (analyseparam != null) getAnalyse(str, analyseparam);

        StringBuilder res = new StringBuilder("[");
        if (!keys.isEmpty()) {
            for (String s : keys)
                res.append("\"").append(s).append("\",");
            res.deleteCharAt(res.length() - 1);
        }
        res.append("]");

        response.getWriter().write(
                res.toString()
        );
    }

    private void getPartner(String str, String[] partnerparam) {
        try {
            ResultSet set = database.findSubstring(Partner.class, str, partnerparam);
            String columnPrimaryKey = Partner.COLUMN_PRIMARY_KEY;
            while (set.next()) {
                keys.add(Partner.TABLE + ":" + set.getString(columnPrimaryKey));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void getProjects(String str, String[] projektparam) {
        try {
            ResultSet set = database.findSubstring(Projekt.class, str, projektparam);
            String columnPrimaryKey = Projekt.COLUMN_PRIMARY_KEY;
            String columnParent = Projekt.COLUMN_VERTRAGSNUMMER;
            while (set.next()) {
                keys.add(Projekt.TABLE + ":" + set.getString(columnPrimaryKey));
                keys.add(Partner.TABLE + ':' + set.getString(columnParent));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void getProbe(String str, String[] probenparam) {
        try {
            ResultSet set = database.findSubstring(Probe.class, str, probenparam);
            String columnPrimaryKey = Probe.COLUMN_PRIMARY_KEY;
            String columnParent = Probe.COLUMN_PROJEKT_ID;
            while (set.next()) {
                keys.add(Probe.TABLE + ":" + set.getString(columnPrimaryKey));
                keys.add(Projekt.TABLE + ":" + set.getString(columnParent));
                Projekt projekt = new Projekt(set.getString(columnParent));
                keys.add(Partner.TABLE + ':' + projekt.getVertragsnummer());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getExperiment(String str, String[] experimentparam) {
        try {
            ResultSet set = database.findSubstring(Experiment.class, str, experimentparam);
            String columnPrimaryKey = Experiment.COLUMN_PRIMARY_KEY;
            String columnParent = Experiment.COLUMN_PROBEN_NR;
            while (set.next()) {
                keys.add(Experiment.TABLE + ":" + set.getString(columnPrimaryKey));
                keys.add(Probe.TABLE + ":" + set.getString(columnParent));
                Probe probe = new Probe(set.getString(columnParent));
                keys.add(Projekt.TABLE + ":" + probe.getSubstanzID());
                Projekt projekt = new Projekt(probe.getSubstanzID());
                keys.add(Partner.TABLE + ':' + projekt.getVertragsnummer());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getAnalyse(String str, String[] analyseparam) {
        try {
            ResultSet set = database.findSubstring(Analyse.class, str, analyseparam);
            String columnPrimaryKey = Analyse.COLUMN_PRIMARY_KEY;
            String columnParent = Analyse.COLUMN_EXPERIMENT_ID;
            while (set.next()) {
                keys.add(Analyse.TABLE + ":" + set.getString(columnPrimaryKey));
                keys.add(Experiment.TABLE + ":" + set.getString(columnParent));
                Experiment experiment = new Experiment(set.getString(columnParent));
                keys.add(Probe.TABLE + ":" + experiment.getProbenNr());
                Probe probe = new Probe(experiment.getProbenNr());
                keys.add(Projekt.TABLE + ":" + probe.getSubstanzID());
                Projekt projekt = new Projekt(probe.getSubstanzID());
                keys.add(Partner.TABLE + ':' + projekt.getVertragsnummer());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }
}
