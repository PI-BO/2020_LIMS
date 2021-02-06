package controller;

import database.model.Model;
import database.model.ModelList;
import database.model.Probe;
import database.model.Projekt;
import database.model.Substanz;
import database.relations.ProjekteSubstanz;
import database.relations.SubstanzenProbe;
import exceptions.ModelNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Servlet implementation class Services
 */
@WebServlet("/jstree/nodes")
public class JSTreeNodesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String table = "";
        String parentId = "";
        try {
            table = request.getParameter("id").split(":")[0];
            parentId = request.getParameter("id").split(":")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //e.printStackTrace();
        } catch (NullPointerException e) {

        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        try {
            if (table.equals(Projekt.TABLE)) {
                getSubstances(response, new Projekt(parentId));
            } else if (table.equals(Substanz.TABLE)) {
                getProben(response, new Substanz(parentId));
            } else {
                getProjects(response);
            }
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * Create JSTree JSONObject
     *
     * @param model    Model from database to create JSONObject with
     * @param parent   Parent Node of JSTree, if null takes root
     * @param children Boolean to determinate if Node has Children
     * @return JSONObject String
     */
    private String jsonObject(Model model, String parent, boolean children) {
        StringBuilder json = new StringBuilder();
        json.append("{\"id\":\"").append(model.getTable()).append(":").append(model.getPrimaryKey()).append("\"");
        if (parent != null && !parent.isEmpty()) json.append(",\"parent\":\"").append(parent).append("\"");
        json.append(",\"text\":\"").append(model.getPrimaryKey()).append("\"");
        json.append(",\"icon\":\"symbol_folder_closed\"");
        if (children) json.append(",\"children\":true");
        json.append("}");

        return json.toString();
    }

    /**
     * Creates a JSONArray from a list of JSONObject Strings
     *
     * @param objects List of JSONObjects
     * @return JSONArray String
     */
    private String jsonArray(List<String> objects) {
        StringBuilder array = new StringBuilder();
        array.append("[");

        if (!objects.isEmpty()) {
            for (String object : objects) {
                array.append(object).append(",");
            }
            array.deleteCharAt(array.length() - 1);
        }

        array.append("]");

        return array.toString();
    }

    private void getProjects(HttpServletResponse response) throws IOException, ModelNotFoundException, SQLException {

        ModelList projektList = new ModelList(new Projekt());

        List<String> ids = new LinkedList<>();
        for (Model projektModel : projektList.getModelList()) {
            ids.add(projektModel.getPrimaryKey());
        }

        List<String> jsons = new ArrayList<>();
        for (String id : ids)
            jsons.add(jsonObject(new Projekt(id), null, true));

        response.getWriter().write(
                "{\"parent\":\"#\",\"text\":\"Projekte\",\"icon\":\"symbol_folder_closed\",\"children\":" + jsonArray(jsons) + "}"
        );
    }

    private void getSubstances(HttpServletResponse response, Projekt p) throws ModelNotFoundException, SQLException, IOException {
        ProjekteSubstanz projekteSubstanz = new ProjekteSubstanz(p);
        List<Substanz> substanzen = projekteSubstanz.getSubstanzen();
        List<String> jsons = new ArrayList<>();
        for (Substanz substanz : substanzen)
            jsons.add(jsonObject(substanz, p.getTable() + ':' + p.getPrimaryKey(), true));

        response.getWriter().write(
                jsonArray(jsons)
        );
    }

    private void getProben(HttpServletResponse response, Substanz substanz) throws ModelNotFoundException, SQLException, IOException {
        SubstanzenProbe substanzenProbe = new SubstanzenProbe(substanz);
        List<Probe> proben = substanzenProbe.getProben();
        List<String> jsons = new ArrayList<>();
        for (Probe probe : proben)
            jsons.add(jsonObject(probe, substanz.getTable() + ':' + substanz.getPrimaryKey(), false));

        response.getWriter().write(
                jsonArray(jsons)
        );
    }
}
