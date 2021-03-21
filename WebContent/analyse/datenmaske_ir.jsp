<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.servlets.AnalyseServlet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="exceptions.ModelNotFoundException" %>
<%@ page import="model.database.tableModels.*" %>
<%@ page import="model.database.tableModels.experimente.Experiment" %>

<div class="analyse_erstellen_header">Analyse ID</div>
<div class="analyse_erstellen_entry">
    <input required type="number" name=<%=AnalyseServlet.ANALYSE_ID%>>
</div>

<div class="analyse_erstellen_header">Experiment ID</div>
<div class="analyse_erstellen_entry">
    <select required name=<%=AnalyseServlet.EXPERIMENT_ID%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <%
            try {
                ModelTable modelList = new ModelTable(new Experiment());
                for (Model model : modelList.getModelList()) {
        %>
        <option value=<%=model.getPrimaryKey()%>><%=((Experiment) model).getPrimaryKey()%>
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

<div class="analyse_erstellen_header">Datum</div>
<div class="analyse_erstellen_entry">
    <input type="date" name=<%=AnalyseServlet.DATUM%>>
</div>

<div class="analyse_erstellen_header">Scans</div>
<div class="analyse_erstellen_entry">
    <input type="number" name=<%=AnalyseServlet.SCANS%>>
</div>

<div class="analyse_erstellen_header">Auflösung [1/cm]</div>
<div class="analyse_erstellen_entry">
    <input type="number" name=<%=AnalyseServlet.AUFLOESUNG%>>
</div>

<div class="analyse_erstellen_header">Geometrie</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseServlet.GEOMETRIE%>>
</div>

<div class="analyse_erstellen_header">Präparation</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseServlet.PRAEPARATION%>>
</div>

<div class="analyse_erstellen_header">Background</div>
<div class="analyse_erstellen_entry">
    <input type="date" name=<%=AnalyseServlet.BACKGROUND%>>
</div>

<div class="analyse_erstellen_header">Bemerkung</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseServlet.BEMERKUNG%>>
</div>

<div class="analyse_erstellen_header">Operator</div>
<div class="analyse_erstellen_entry">
    <select required name=<%=AnalyseServlet.OPERATOR%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <%
            try {
                ModelTable modelList = new ModelTable(new Mitarbeiter());
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
