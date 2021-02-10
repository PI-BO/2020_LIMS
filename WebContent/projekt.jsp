<%@page import="database.model.Substanz"%>
<%@page import="database.model.Projekt"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<script src="explorerFunctions.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" href="explorer.css">
<title>LIMS | Projekt</title>
</head>

<%
	String projekt_id = (String) request.getParameter("projekt_id");
	Projekt projekt = new Projekt(projekt_id);
%>

<body>
		<table id="explorer_table">

			<tr class="explorer_sortfunctions_row">
				<td class="explorer_sortfunction symbol_triangle_up" onclick="sortExplorerTable(0)">Name</td>
				<td class="explorer_sortfunction symbol_triangle_up" onclick="">Datum</td>
				<td class="explorer_sortfunction symbol_triangle_up" onclick="">etc</td>
			</tr>
			
			
			<%
				for (Substanz substanz : projekt.getSubstanzen()) {
			%>

			<tr>
				<td class="explorer_table_data symbol_folder_closed"><%=substanz.getPrimaryKey()%></td>
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