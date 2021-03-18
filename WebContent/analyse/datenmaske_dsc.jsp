<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.servlets.AnalyseServlet" %>
<%@ page import="model.database.tableModels.ModelList" %>
<%@ page import="model.database.tableModels.Mitarbeiter" %>
<%@ page import="model.database.tableModels.Model" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="exceptions.ModelNotFoundException" %>
<%@ page import="model.database.tableModels.AnalyseTemperaturprogramme" %>
<div class="analyse_erstellen_header">Experiment ID</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseServlet.EXPERIMENT_ID%>>
</div>

<div class="analyse_erstellen_header">Datum</div>
<div class="analyse_erstellen_entry">
    <input type="date" name=<%=AnalyseServlet.DATUM%>>
</div>

<div class="analyse_erstellen_header">Einwaage [mg]</div>
<div class="analyse_erstellen_entry">
    <input type="number" name=<%=AnalyseServlet.EINWAAGE_MG%>>
</div>

<div class="analyse_erstellen_header">Auswaage [mg]</div>
<div class="analyse_erstellen_entry">
    <input type="number" name=<%=AnalyseServlet.AUSWAAGE_MG%>>
</div>

<div class="analyse_erstellen_header">Rampe [K/min]</div>
<div class="analyse_erstellen_entry">
    <input type="number" name=<%=AnalyseServlet.RAMPE_K_MIN%>>
</div>

<div class="analyse_erstellen_header">Temperaturprogramm</div>
<div class="analyse_erstellen_entry">
    <select required name=<%=AnalyseServlet.TEMPERATURPROGRAMM%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <option value="new">Neues Temperaturprogramm</option>
        <%
            try {
                ModelList modelList = new ModelList(new AnalyseTemperaturprogramme());
                for (Model model : modelList.getModelList()) {
        %>
        <option value=<%=model.getPrimaryKey()%>><%=((AnalyseTemperaturprogramme) model).getTabelle()%>
        </option>
        <%
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        %>
    </select>
</div>

<div class="analyse_erstellen_header">Tiegel</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseServlet.TIEGEL%>>
</div>

<div class="analyse_erstellen_header">Tiegelpräsentation</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseServlet.TIEGELPRAESENTATION%>>
</div>

<div class="analyse_erstellen_header">Bemerkung</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseServlet.BEMERKUNG%>>
</div>

<div class="experiment_erstellen_header">Operator</div>
<div class="experiment_erstellen_entry">
    <select required name=<%=AnalyseServlet.OPERATOR%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <%
            try {
                ModelList modelList = new ModelList(new Mitarbeiter());
                for (Model model : modelList.getModelList()) {
        %>
        <option value=<%=model.getPrimaryKey()%>><%=((Mitarbeiter) model).getNachname()%>
        </option>
        <%
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        %>
    </select>
</div>
