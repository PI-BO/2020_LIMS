package controller;

import database.model.Model;
import database.model.Projekt;
import database.model.ProjekteIdList;
import database.model.Substanz;
import database.relations.ProjekteSubstanz;
import exceptions.ModelNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Services
 */
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
     * @param model
     * Model from database to create JSONObject with
     * @param parent
     * Parent Node of JSTree, if null takes root
     * @param children
     * Boolean to determinate if Node has Children
     * @return
     * JSONObject String
     */
    private String jsonObject(Model model, String parent, boolean children) {
        if (parent == null || parent.isEmpty()) parent = "#";

        StringBuilder json = new StringBuilder();
        json.append("{\"id\":\"").append(model.getTable()).append(":").append(model.getPrimaryKey()).append("\",");
        json.append("\"parent\":\"").append(parent).append("\",");
        json.append("\"text\":\"").append(model.getPrimaryKey()).append("\"");
        if (children) json.append(",\"children\":true");
        json.append("}");

        return json.toString();
    }

    /**
     * Creates a JSONArray from a list of JSONObject Strings
     * @param objects
     * List of JSONObjects
     * @return
     * JSONArray String
     */
    private String jsonArray(List<String> objects) {
        StringBuilder array = new StringBuilder();
        array.append("[");

        for (String object : objects) {
            array.append(object).append(",");
        }
        array.deleteCharAt(array.length() - 1);
        array.append("]");

        return array.toString();
    }

    private void getProjects(HttpServletResponse response) throws IOException, ModelNotFoundException, SQLException {
        ProjekteIdList projekteIdList = new ProjekteIdList();
        List<String> ids = projekteIdList.getProjekteIdList();
        List<String> jsons = new ArrayList<>();
        for (String id : ids)
            jsons.add(jsonObject(new Projekt(id), null, true));

        response.getWriter().write(
                jsonArray(jsons)
        );
    }

    private void getSubstances(HttpServletResponse response, Projekt p) throws ModelNotFoundException, SQLException, IOException {
        ProjekteSubstanz projekteSubstanz = new ProjekteSubstanz(p);
        List<Substanz> substanzen = projekteSubstanz.getSubstanzen();
        List<String> jsons = new ArrayList<>();
        for (Substanz substanz : substanzen)
            jsons.add(jsonObject(substanz, p.getTable() + ':' + p.getPrimaryKey(), false));

        response.getWriter().write(
                jsonArray(jsons)
        );
    }
}
