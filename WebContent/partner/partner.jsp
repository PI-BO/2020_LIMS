<%@page import="model.database.tableModels.Projekt"%>
<%@page import="config.Address"%>
<%@ page import="model.database.tableModels.Partner"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" href="<%=Address.getExplorerCSS()%>">
<title>LIMS | Partner</title>
</head>

<%
    String projekt_id = request.getParameter("projekt_id");
    Partner partner = new Partner(projekt_id);
%>

<body>
	<table id="explorer_table">

		<tr class="explorer_sortfunctions_row">
			<td class="explorer_sortfunction symbol_triangle_up" onclick="sortExplorerTable(0)">Name</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">Datum</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">etc</td>
		</tr>


		<%
        for (Projekt projekt : partner.getProjekte()) {
    %>

		<tr>
			<td class="explorer_table_data symbol_folder_closed"
				onclick="(
                function() {
                let data = {projekt_id: '<%=projekt.getPrimaryKey()%>'};
                loadPage('<%=Address.getProjektJSP()%>', data);
                explorerState.pushToState({
                table: '<%=projekt.getTable()%>',
                id: '<%=projekt.getPrimaryKey()%>',
                text: '<%=projekt.getPrimaryKey()%>'
                });
                }
                )()"><%=projekt.getPrimaryKey()%>
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

</script>

</body>
</html>