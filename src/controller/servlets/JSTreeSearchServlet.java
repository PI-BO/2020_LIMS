package controller.servlets;

import exceptions.ModelNotFoundException;
import model.database.mariaDB.MariaDB;
import model.database.tableModels.Probe;
import model.database.tableModels.Projekt;
import model.database.tableModels.Substanz;

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
    private MariaDB database = new MariaDB();
    private Set<String> keys;


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("str");
        keys = new HashSet<>();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        String[] projektparam = request.getParameterValues("Projekt[]");
        if (projektparam != null) getProjects(str, projektparam);

        String[] substanceparam = request.getParameterValues("Substanz[]");
        if (substanceparam != null) getSubstaces(str, substanceparam);

        String[] probenparam = request.getParameterValues("Probe[]");
        if (probenparam != null) getProbe(str, probenparam);

        StringBuilder res = new StringBuilder();
        if (!keys.isEmpty()) {
            res.append("[");
            for (String s : keys)
                res.append("\"").append(s).append("\",");
            res.deleteCharAt(res.length() - 1);
            res.append("]");
        }

        response.getWriter().write(
                res.toString()
        );
        //response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    private void getProjects(String str, String[] projektparam) {
        try {
            ResultSet set = database.findSubstring(Projekt.class, str, projektparam);
            int col = set.findColumn(Projekt.COLUMN_PRIMARY_KEY);
            while (set.next()) {
                keys.add(Projekt.TABLE + ":" + set.getString(col));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void getSubstaces(String str, String[] substanceparam) {
        try {
            ResultSet set = database.findSubstring(Substanz.class, str, substanceparam);
            int col = set.findColumn(Substanz.COLUMN_PRIMARY_KEY);
            int par = set.findColumn(Substanz.COLUMN_PROJEKT_ID);
            while (set.next()) {
                keys.add(Substanz.TABLE + ":" + set.getString(col));
                keys.add(Projekt.TABLE + ":" + set.getString(par));
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
            int col = set.findColumn(Probe.COLUMN_PRIMARY_KEY);
            int par = set.findColumn(Probe.COLUMN_SUBSTANZ_ID);
            while (set.next()) {
                keys.add(Probe.TABLE + ":" + set.getString(col));
                keys.add(Substanz.TABLE + ":" + set.getString(par));
                Substanz substanz = new Substanz(set.getString(par));
                keys.add(Projekt.TABLE + ":" + substanz.getProjektID());
            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }
}