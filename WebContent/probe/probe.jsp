<%@page import="config.Address"%>
<%@ page import="model.database.tableModels.Probe"%>
<%@ page import="model.database.tableModels.experimente.Experiment"%>
<%@ page import="model.database.relations.ExperimentExperimenttyp"%>
<%@ page import="model.database.tableModels.experimente.ExperimenteModel"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" href="<%=Address.getExplorerCSS()%>">
<title>LIMS | Probe</title>
</head>

<%
    String projekt_id = request.getParameter("projekt_id");
    Probe probe = new Probe(projekt_id);
%>

<body>
	<table id="explorer_table">

		<tr class="explorer_sortfunctions_row">
			<td class="explorer_sortfunction symbol_triangle_up" onclick="sortExplorerTable(0)">Name</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">Datum</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">etc</td>
		</tr>


		<%
        for (Experiment experiment : probe.getExperimente()) {
            ExperimenteModel experimenteModel = new ExperimentExperimenttyp(experiment).getTypModel();
    %>

    <tr class="explorer_table_row <%=experimenteModel.getPrimaryKey()%>">
        <td class="explorer_table_data symbol_folder_closed" onclick="(
                function() {
                let data = {projekt_id: '<%=experimenteModel.getPrimaryKey()%>'};
                loadPage('<%=Address.getExperimentJSP()%>', data);
                explorerState.pushToState({
                table: '<%=experimenteModel.getTable()%>',
                id: '<%=experimenteModel.getPrimaryKey()%>',
                text: '<%=experimenteModel.getExperiment_no()%>'
                });
                }
                )()"><%=experimenteModel.getExperiment_no()%>
			</td>
			<td class="explorer_table_data"></td>
			<td class="explorer_table_data"></td>
		</tr>

		<%
        }
    %>
	</table>

	<script>

    addSymbolToggleListenerToCssClass("explorer_sortfunction", "symbol_triangle_down");

    contextMenu.initExperiment()

</script>

</body>
</html>