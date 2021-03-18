<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="config.Address" %>
<%@ page import="controller.servlets.AnalyseServlet" %>
<%@ page import="model.database.tableModels.ModelTable" %>
<%@ page import="model.database.tableModels.Analysetyp" %>
<%@ page import="model.database.tableModels.Model" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="exceptions.ModelNotFoundException" %>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Solid-Chem | LIMS - Insert Analyse</title>
    <link rel="stylesheet" href="<%=Address.getAnalyseErstellenCss()%>">
    <script src="<%=Address.getAnalyseErstellenJS()%>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#form_analyse_erstellen').submit(function () {
                $.ajax({
                    url: '<%=Address.getAnalyseErstellenServlet()%>',
                    type: 'post',
                    data: $(this).serialize(),
                    success: function () {
                        replaceContent("button_analyse_speichern", "Erfolgreich gespeichert", "green");
                    },
                    error: function (e) {
                        replaceContent("button_analyse_speichern", "Fehler: " + e, "red");
                    }
                });
                return false;
            });
        });
    </script>
</head>
<body>
<form id="form_analyse_erstellen">
    <div class="analyse_erstellen_main_header">Analyse erstellen</div>

    <div class="analyse_erstellen_header">Datenmaske:</div>
    <div class="analyse_erstellen_entry">
        <select required onchange="showAnalysetypFieldsMethode(this.value)" name=<%=AnalyseServlet.TYP%>>
            <option value="" selected disabled>bitte auswaehlen</option>
            <%
                try {
                    ModelTable analyseTypModelList = new ModelTable(new Analysetyp());
                    for (Model model : analyseTypModelList.getModelList()) {
            %>
            <option value="<%=model.getPrimaryKey()%>"><%=((Analysetyp) model).getTyp()%>
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

    <div id="analyse_erstellen_content">
    </div>

    <div class="analyse_erstellen_entry">
        <button id="button_analyse_speichern" type="submit">Speichern</button>
    </div>
</form>
</body>
</html>
