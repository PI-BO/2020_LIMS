<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.servlets.analyse.AnalyseErstellenServlet" %>
<%@ page import="model.database.tableModels.ModelTable" %>
<%@ page import="model.database.tableModels.Mitarbeiter" %>
<%@ page import="model.database.tableModels.Model" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="exceptions.ModelNotFoundException" %>
<%@ page import="model.database.tableModels.experimente.Experiment" %>
<%@ page import="model.database.relations.ExperimentExperimenttyp" %>
<%@ page import="model.database.tableModels.experimente.ExperimenteModel" %>

<div class="analyse_erstellen_header">Analyse ID</div>
<div class="analyse_erstellen_entry">
    <input required type="number" min="1" name=<%=AnalyseErstellenServlet.ANALYSE_ID%>>
</div>

<div class="analyse_erstellen_header">Experiment ID</div>
<div class="analyse_erstellen_entry">
    <input type="text" required name=<%=AnalyseErstellenServlet.EXPERIMENT_ID%>>

    <!-- <select required name=<%=AnalyseErstellenServlet.EXPERIMENT_ID%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <%
        try {
            ModelTable modelList = new ModelTable(new Experiment());
            for (Model model : modelList.getModelList()) {
                ExperimenteModel experimenteModel = new ExperimentExperimenttyp((Experiment) model).getTypModel();
                %>
                <option value=<%=model.getPrimaryKey()%>><%=experimenteModel.getExperiment_no()%>
                </option>
                <%
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
        %>
    </select> -->

</div>

<div class="analyse_erstellen_header">Datum</div>
<div class="analyse_erstellen_entry">
    <input type="date" name=<%=AnalyseErstellenServlet.DATUM%>>
</div>

<div class="analyse_erstellen_header">Gerät</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseErstellenServlet.GERAET%>>
</div>

<div class="analyse_erstellen_header">Probenpräparation</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseErstellenServlet.PROBENPRAEPARATION%>>
</div>

<div class="analyse_erstellen_header">Position</div>
<div class="analyse_erstellen_entry">
    <input type="number" min="0" name=<%=AnalyseErstellenServlet.POSITION%>>
</div>

<div class="analyse_erstellen_header">Programm</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseErstellenServlet.PROGRAMM%>>
</div>

<div class="analyse_erstellen_header">Messzeit</div>
<div class="analyse_erstellen_entry">
    <input type="number" min="0" name=<%=AnalyseErstellenServlet.MESSZEIT%>>
    <i>min</i>
</div>

<div class="analyse_erstellen_header">Bemerkung</div>
<div class="analyse_erstellen_entry">
    <input type="text" name=<%=AnalyseErstellenServlet.BEMERKUNG%>>
</div>

<div class="analyse_erstellen_header">Operator</div>
<div class="analyse_erstellen_entry">
    <select required name=<%=AnalyseErstellenServlet.OPERATOR%>>
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

<script>

    // Such-Links
    GlobaleSuche.addSearchLinkToInputWithName("<%=AnalyseErstellenServlet.EXPERIMENT_ID%>",
        [
            {
                "category": "experiment",
                "parameter": "id",
                "value": ""
            },
            {
                "category": "experiment",
                "parameter": "typ",
                "value": ""
            }
        ]
    );

</script>