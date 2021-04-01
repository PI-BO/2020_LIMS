<%@ page import="config.Address" %>
<%@ page import="model.database.tableModels.experimente.ExperimenttypVerdampfung" %>
<%@ page import="model.database.tableModels.experimente.ExperimenttypSlurry" %>
<%@ page import="model.database.tableModels.experimente.ExperimenteModel" %>
<%@ page import="controller.servlets.experiment.ExperimentErstellenServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Solid-Chem | LIMS - Insert Experiment</title>
    <link rel="stylesheet" href="<%=Address.getExperimentErstellenCss()%>">
    <link rel="stylesheet" href="<%=Address.getExperimentBearbeitenCss()%>">
    <script src="<%=Address.getExperimentErstellenJS()%>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#form_experiment_bearbeiten').submit(function () {
                $.ajax({
                    url: '<%=Address.getExperimentBearbeitenServlet()%>',
                    type: 'post',
                    data: $(this).serialize(),
                    success: function () {
                        replaceContent("button_experiment_update", "Erfolgreich gespeichert", "green");
                    },
                    error: function (xhr, status, error) {
                        replaceContent("button_experiment_update", "Fehler: " + xhr.responseText, "red");
                    }
                });
                return false;
            });
        });
    </script>
</head>
<body>
<form id="form_experiment_bearbeiten">
    <div class="experiment_bearbeiten_main_header">Experiment bearbeiten</div>

    <div class="experiment_bearbeiten_header">Experiment ID:</div>
    <div class="experiment_bearbeiten_entry">
        <input required type="number" min="1" id="experiment_id_input_field" name="<%=ExperimentErstellenServlet.NO_ID%>">
    </div>

    <div id="experiment_bearbeiten_content">
    </div>

    <div id="experiment_bearbeiten_speichern" class="experiment_bearbeiten_entry">
        <button disabled id="button_experiment_update" type="submit">Speichern</button>
        <input required type="checkbox" id="acknowledge_experiment_update" onclick="enableSaveButton(this)">
        <i>Das bestehende Experiment wird mit den neuen werten überschrieben!</i>
    </div>
</form>
</body>

<script>
    document.getElementById('experiment_id_input_field').addEventListener('change', function (e) {
        $.ajax({
            url: '<%=Address.getExperimentBearbeitenServlet()%>',
            type: 'get',
            data: {id: e.target.value},
            success: function (data) {
                const content = $("#experiment_bearbeiten_content");

                const table = data['table']
                let experimentBearbeitenPosting
                if (table === '<%=ExperimenttypVerdampfung.TABLE%>') {
                    experimentBearbeitenPosting = $.post('<%=Address.getExperimentTypVerdampfungJsp()%>', {});
                } else if (table === '<%=ExperimenttypSlurry.TABLE%>') {
                    experimentBearbeitenPosting = $.post('<%=Address.getExperimentTypSlurryJsp()%>', {});
                } else {
                    content.empty()
                    return;
                }
                experimentBearbeitenPosting.done(function (post) {
                    content.empty().append(post).ready(function () {
                        content.children().slice(0,2).remove();// remove first 2 elements
                        for (let key in data) {
                            const modifiedkey = "EXPERIMENT_" + key
                            const nodeList = document.getElementsByName(modifiedkey)
                            const val = data[key]
                            for (let i = 0; i < nodeList.length; i++) {
                                if (nodeList[i].type === 'checkbox')
                                    nodeList[i].checked = $.parseJSON(val)
                                else {
                                    nodeList[i].value = val
                                    if (key === '<%=ExperimenteModel.COLUMN_DURCHFUEHRUNG_KEY%>') {
                                        const t = $('select[name=<%=ExperimentErstellenServlet.DURCHFUEHRUNGSTEXT%>]')[0]
                                        t.onchange(t);
                                    }
                                }
                            }
                        }
                    });
                });
            },
            error: function (xhr, status, error) {
                alert("Fehler: " + xhr.responseText);
            }
        });
    });

    function enableSaveButton(param) {
        $("#button_experiment_update").prop("disabled", !param.checked)
    }
</script>

</html>
