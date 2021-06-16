<%@ page import="config.Address"%>
<%@ page import="model.database.tableModels.analyse.*"%>
<%@ page import="controller.servlets.analyse.AnalyseServlet"%>
<%@ page import="controller.servlets.analyse.AnalyseBearbeitenServlet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="de">
<head>
<meta charset="UTF-8">
<title>Solid-Chem | LIMS - Update Analyse</title>
<link rel="stylesheet" href="<%=Address.getAnalyseErstellenCss()%>">
<link rel="stylesheet" href="<%=Address.getAnalyseBearbeitenCss()%>">
<script src="<%=Address.getAnalyseErstellenJS()%>"></script>
<script type="text/javascript">
        $(document).ready(function () {
            $('#form_analyse_bearbeiten').submit(function () {
                $.ajax({
                    url: '<%=Address.getAnalyseBearbeitenServlet()%>',
                    type: 'post',
                    data: $(this).serialize(),
                    success: function () {
                        replaceContent("button_analyse_update", "Erfolgreich gespeichert", "green");
                    },
                    error: function (xhr, status, error) {
                        replaceContent("button_analyse_update", "Fehler: " + xhr.responseText, "red");
                    }
                });
                return false;
            });
        });
    </script>
</head>
<body>
	<form id="form_analyse_bearbeiten">
		<div class="analyse_bearbeiten_main_header">Analyse bearbeiten</div>

		<div class="analyse_bearbeiten_header">Analyse ID:</div>
		<div class="analyse_bearbeiten_entry">
			<input required type="number" min="1" id="analyse_id_input_field" name="<%=AnalyseServlet.ANALYSE_ID%>">
		</div>

		<div id="analyse_bearbeiten_content"></div>

		<div id="analyse_bearbeiten_speichern" class="experiment_bearbeiten_entry">
			<button disabled id="button_analyse_update" type="submit">Speichern</button>
			<input required type="checkbox" id="acknowledge_analyse_update" onclick="enableSaveButton(this)">
			<i>Die bestehende Analyse wird mit den neuen werten überschrieben!</i>
		</div>
	</form>
</body>

<script>
    document.getElementById('analyse_id_input_field').addEventListener('change', function (e) {
        $.ajax({
            url: '<%=Address.getAnalyseBearbeitenServlet()%>',
            type: 'get',
            data: {id: e.target.value},
            success: function (data) {
                const content = $("#analyse_bearbeiten_content");

                const table = data['table']
                let analyseBearbeitenPosting
                if (table === '<%=AnalyseDatenmaskePXRD.TABLE%>') {
                    analyseBearbeitenPosting = $.post('<%=Address.getAnalyseDatenmaskePxrdJsp()%>', {});
                } else if (table === '<%=AnalyseDatenmaskeIR.TABLE%>') {
                    analyseBearbeitenPosting = $.post('<%=Address.getAnalyseDatenmaskeIrJsp()%>', {});
                } else if (table === '<%=AnalyseDatenmaskeDSC.TABLE%>') {
                    analyseBearbeitenPosting = $.post('<%=Address.getAnalyseDatenmaskeDscJsp()%>', {});
                } else if (table === '<%=AnalyseDatenmaskeTGA.TABLE%>') {
                    analyseBearbeitenPosting = $.post('<%=Address.getAnalyseDatenmaskeTgaJsp()%>', {});
                } else {
                    content.empty()
                    return;
                }
                analyseBearbeitenPosting.done(function (post) {
                    content.empty().append(post).ready(function () {
                        content.children().slice(0,2).remove();// remove first 2 elements
                        for (let key in data) {
                            const modifiedkey = "ANALYSE_" + key
                            const nodeList = document.getElementsByName(modifiedkey)
                            const val = data[key]
                            for (let i = 0; i < nodeList.length; i++) {
                                nodeList[i].value = val
                                if (key === '<%=AnalyseDatenmaskeDscTgaConst.COLUMN_TEMPERATURPROGRAMM%>') {
                                    const t = $('select[name=<%=AnalyseServlet.TEMPERATURPROGRAMM%>]')[0]
                                    t.onchange(t);
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
        $("#button_analyse_update").prop("disabled", !param.checked)
    }
</script>

</html>
