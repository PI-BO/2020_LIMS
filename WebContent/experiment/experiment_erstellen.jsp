<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.servlets.ExperimentServlet" %>
<%@ page import="model.database.tableModels.*" %>
<%@ page import="config.Address" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="exceptions.ModelNotFoundException" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Solid-Chem | LIMS - Insert Experiment</title>
    <link rel="stylesheet" href="<%=Address.getExperimentErstellenCss()%>">
    <script src="<%=Address.getExperimentErstellenJS()%>"></script>
</head>
<body>
<form id="form_experiment_erstellen">
    <div class="experiment_erstellen_main_header">Experiment erstellen
        <select required onchange="showExperimenttypFieldsMethode(this.value)" name=<%=ExperimentServlet.TYP%>>
            <option value="" selected disabled>bitte auswaehlen</option>
            <%
                try {
                    ModelList experimentTypModelList = new ModelList(new Experimenttyp());
                    for (Model model : experimentTypModelList.getModelList()) {
            %>
            <option value="<%=model.getPrimaryKey()%>"><%=((Experimenttyp) model).getTyp()%>
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

    <div id="experiment_erstellen_content">
    </div>

    <div class="experiment_erstellen_entry">
        <button type="submit">Speichern</button>
    </div>
</form>
</body>
</html>