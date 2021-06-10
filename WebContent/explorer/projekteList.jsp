<%@page import="model.database.tableModels.Projekt"%>
<%@page import="model.database.tableModels.Model"%>
<%@page import="model.database.tableModels.ModelTable"%>
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
	ModelTable projekte = new ModelTable(new Projekt());
%>

<script src="jquery-3.5.1.js"></script>
<script>
	console.log("projekteList")
    function loadPage(pageAddress, data) {
    const posting = $.post(pageAddress, data);
    posting.done(function (data) {
        $("#explorer-content").empty().append(data);
    });
}
</script>

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

			
			$(".explorer_table_data").click(function(){
				let data = {projekt_id : $(this).text()};
				console.log("projekteList.jsp")
				loadPage("<%=Address.getProjektJSP()%>", data);
			});
			
			explorerState.setStateProjekte("Projekte");
			
		</script>

</body>
</html>