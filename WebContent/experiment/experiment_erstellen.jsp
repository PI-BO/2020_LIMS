<%@ page import="model.database.tableModels.ModelList" %>
<%@ page import="model.database.tableModels.Experimenttyp" %>
<%@ page import="model.database.tableModels.Model" %>
<%@ page import="controller.servlets.ExperimentServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Solid-Chem | LIMS - Insert Experiment</title>
    <link rel="stylesheet" href="experiment_erstellen.css">
</head>
<body>
<form action="login" method="post">
    <table id="create_projekt_table">
        <tr>
            <th colspan=4><h1>Experiment erstellen</h1></th>
        </tr>
        <tr>
            <th>Experiment Informationen</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        <tr>
            <td>Experiment ID</td>
            <td><input type=text placeholder="Experiment ID"></td>
            <td></td>
        </tr>
        <tr>
            <td>Experimente</td>
            <td><input type=text placeholder="Experimente"></td>
        </tr>
        <tr>
            <td>Probe</td>
            <td><input type=text placeholder="Probe"></td>
        </tr>
        <tr>
            <td>Datum</td>
            <td><input type=text placeholder="Daturm"></td>
            <td></td>
        </tr>
        <tr>
            <td>Sonstiges</td>
            <td><textarea id="w3review" name="w3review" rows="4" cols="50">
						Sonstige Informationen
  					</textarea></td>
            <td></td>
            <td></td>
        </tr>

        <tr>
            <th colspan=4>
                <button type="submit">Speichern</button>
            </th>
        </tr>
        <tr>
            <td><select required name=<%=ExperimentServlet.TYP%>>
                <option value="" selected disabled>bitte auswaehlen</option>

                <%
                    ModelList modelList = new ModelList(new Experimenttyp());
                    for (Model model : modelList.getModelList()) {
                %>
                <option value=<%=model.getPrimaryKey()%>><%= ((Experimenttyp) model).getTyp()%>
                </option>
                <%
                    }
                %>

            </select></td>
        </tr>
    </table>
</form>
</body>
</html>