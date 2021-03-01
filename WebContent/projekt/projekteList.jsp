<%@page import="model.database.tableModels.Projekt"%>
<%@page import="model.database.tableModels.Model"%>
<%@page import="model.database.tableModels.ModelList"%>
<%@page import="config.Address"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>LIMS | Projekt</title>
<link rel="stylesheet" href="<%=Address.getExplorerCSS()%>">
</head>

<%
	ModelList projekte = new ModelList(new Projekt());
%>

<body>
	<table id="explorer_table">

		<tr class="explorer_sortfunctions_row">
			<td class="explorer_sortfunction symbol_triangle_up" onclick="sortExplorerTable(0)">Name</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">Datum</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">etc</td>
		</tr>

		<%
				for (Model projekt : projekte.getModelList()) {
			%>

		<tr>
			<td class="explorer_table_data symbol_folder_closed"><%=projekt.getPrimaryKey()%></td>
			<td class="explorer_table_data"></td>
			<td class="explorer_table_data"></td>
		</tr>

		<%
				}
			%>

	</table>

	<script>

			addSymbolToggleListenerToCssClass("explorer_sortfunction", "symbol_triangle_down");
			
			$(".explorer_table_data").click(function(){
				let data = {projekt_id : $(this).text()};
				loadPage("<%=Address.getProjektJSP()%>", data);
			});
			
			explorerState.setStateProjekte("Projekte");
			
		</script>

</body>
</html>