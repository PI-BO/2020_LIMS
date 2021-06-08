<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="controller.servlets.experiment.ExperimentErstellenServlet"%>
<%@ page import="model.database.tableModels.*"%>
<%@ page import="config.Address"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="exceptions.ModelNotFoundException"%>
<%@ page import="model.database.tableModels.experimente.Experimenttyp"%>
<!DOCTYPE html>
<html lang="de">

<head>
<meta charset="UTF-8">
<title>Solid-Chem | LIMS - Insert Experiment</title>
<link rel="stylesheet" href="<%=Address.getExperimentErstellenCss()%>">
<script type="text/javascript" src="<%=Address.getMainPath()%>/experiment/experimenttyp.jsp"></script>
<script src="<%=Address.getExperimentErstellenJS()%>"></script>
</head>

<body>
	<form id="form_experiment_erstellen">
		<div id="experiment_erstellen_main_header"></div>

		<div class="experiment_erstellen_header">Experiment Typ:</div>
		<div class="experiment_erstellen_entry">
			<select id="select_experiment_typ" required name=<%=ExperimentErstellenServlet.TYP%>>
				<option value="" selected disabled>bitte auswaehlen</option>
				<%
                try {
                    ModelTable experimentTypModelList = new ModelTable(new Experimenttyp());
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

		<div id="experiment_erstellen_content"></div>

		<div id="experiment_erstellen_speichern" class="experiment_erstellen_entry">
			<button id="button_experiment_speichern" type="submit">Speichern</button>
		</div>
		<div id="experiment_erstellen_save_message" class="experiment_erstellen_entry"></div>
	</form>
</body>
<script type="text/javascript">

    $("#form_experiment_erstellen").submit(function (e) {
        e.preventDefault();

        var submitData = {};

        let startMaterialInputField = document.getElementsByName("<%=ExperimentErstellenServlet.API_STARTMATERIAL%>")[0];
        startMaterialInputField.disabled = false;

        for (var i = 0; i < e.target.length; i++) {
            submitData[e.target[i].name] = e.target[i].value;
        }

        var url = "<%=Address.getExperimentErstellenServlet()%>";
        var posting = $.post(url, submitData);
        posting.done(function (data) {

            startMaterialInputField.disabled = true;

            if (data["status"] === "error") $("#experiment_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] + "</h3>");

            if (data["status"] === "success") {

                let requiredFields = document.querySelectorAll("input:required");
                for (let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
                $("#experiment_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] + "</div>");
                $("#experiment_erstellen_speichern").empty();

                const experimentId = document.getElementsByName("<%=ExperimentErstellenServlet.NO_ID%>")[0];
                MainState.setExperiment(experimentId.value);
            }
        });
    })





    // $(document).ready(function () {
    //     $('#form_experiment_erstellen').submit(function () {
    //         $.ajax({
    //             url: '<%=Address.getExperimentErstellenServlet()%>',
    //             type: 'post',
    //             data: $(this).serialize(),
    //             success: function () {
    //                 replaceContent("experiment_erstellen_speichern", "Erfolgreich gespeichert", "green");
    //             },
    //             error: function (xhr, status, error) {
    //                 replaceContent("button_experiment_speichern", "Fehler: " + xhr.responseText, "red");
    //             }
    //         });
    //         return false;
    //     });
    // });
</script>

</html>