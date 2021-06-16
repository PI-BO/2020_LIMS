<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="config.Address"%>
<%@ page import="controller.servlets.analyse.AnalyseServlet"%>
<%@ page import="model.database.tableModels.analyse.Analysetyp"%>
<%@ page import="model.database.tableModels.ModelTable"%>
<%@ page import="model.database.tableModels.Model"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="exceptions.ModelNotFoundException"%>
<html lang="de">

<head>
<meta charset="UTF-8">
<title>Solid-Chem | LIMS - Insert Analyse</title>
<link rel="stylesheet" href="./analyse/analyse.css">
<script src="./Analyse.js"></script>
</head>

<body>
	<form id="form_analyse_erstellen">
		<div id="analyse_erstellen_main_header">Analyse erstellen</div>

		<div class="analyse_erstellen_header">Datenmaske:</div>
		<div class="analyse_erstellen_entry">
			<select required id="select_analyse_typ" name=<%=AnalyseServlet.TYP%>>
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

		<div id="analyse_erstellen_content"></div>

		<div id="analyse_erstellen_speichern" class="analyse_erstellen_entry">
			<button id="button_analyse_speichern" type="submit">Speichern</button>
		</div>
	</form>
</body>
<script type="text/javascript">
    // $(document).ready(function () {
    //     $('#form_analyse_erstellen').submit(function () {

    //         let experimentIdInputField = document.getElementsByName("<%=AnalyseServlet.EXPERIMENT_ID%>")[0];
    //         experimentIdInputField.disabled = false;

    //         $.ajax({
    //             url: '<%=AnalyseServlet.ROUTE%>',
    //             type: 'post',
    //             data: $(this).serialize(),
    //             success: function () {
    //                 experimentIdInputField.disabled = true;
    //                 replaceContent("analyse_erstellen_speichern", "Erfolgreich gespeichert", "green");
    //                 const analyseIdInputField = document.getElementsByName("<%=AnalyseServlet.ANALYSE_ID%>")[0];
    //                 MainState.setAnalyse(analyseIdInputField.value);
    //             },
    //             error: function (xhr, status, error) {
    //                 experimentIdInputField.disabled = true;
    //                 replaceContent("button_analyse_speichern", "Fehler: " + xhr.responseText, "red");
    //             }
    //         });
    //         return false;
    //     });
    // });

    // function analyseErstellenInit() {

    //     console.log("analyseErstellenInit")

    //     let experimentIdInputField = document.getElementsByName("<%=AnalyseServlet.EXPERIMENT_ID%>")[0];
    //     experimentIdInputField.value = MainState.state[Parameters.EXPERIMENT.CATEGORY][Parameters.EXPERIMENT.PK];

    //     // terrible hack solange keine vernuenftige Loesing gefunden wurde
    //     setTimeout(function () {
    //         if (experimentIdInputField.value === "") {
    //             alert("bitte Experiment auswaehlen!");
    //             // $("#experiment_auswaehlen").click();
    //             NavigationMenu.openStateSearch(
    //                 [
    //                     new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK, ""),
    //                     new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, () => MainState.state[Parameters.PROBE.CATEGORY][Parameters.PROBE.PK]),
    //                     new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, () => MainState.state[Parameters.PROJEKT.CATEGORY][Parameters.PROJEKT.PK]),
    //                     new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, () => MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.NAME])
    //                 ],
    //                 async (callbackData) => {
    //                     await MainState.setExperiment(callbackData[Parameters.EXPERIMENT.PK]);
    //                     $("#analyse_erstellen").click();
    //                 },
    //                 new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK),
    //             )
    //         }
    //     }, 500);
    // }
</script>

</html>