<%@page import="model.database.tableModels.Substanz"%>
<%@page import="config.Address"%>
<%@ page import="model.database.tableModels.Probe" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" href="<%=Address.getExplorerCSS()%>">
<title>LIMS | Substanz</title>
</head>

<%
	String projekt_id = request.getParameter("projekt_id");
	Substanz substanz = new Substanz(projekt_id);
%>

<body>
	<table id="explorer_table">

		<tr class="explorer_sortfunctions_row">
			<td class="explorer_sortfunction symbol_triangle_up" onclick="sortExplorerTable(0)">Name</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">Datum</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">etc</td>
		</tr>


		<%
				for (Probe probe : substanz.getProben()) {
			%>

		<tr>
			<td class="explorer_table_data symbol_folder_closed"><%=probe.getPrimaryKey()%></td>
			<td class="explorer_table_data"></td>
			<td class="explorer_table_data"></td>
		</tr>

		<%
				}
			%>
	</table>

	<script>
			
			addSymbolToggleListenerToCssClass("explorer_sortfunction", "symbol_triangle_down");
			explorerState.setStateSubstanz("<%= projekt_id %>");
			
		</script>

</body>
</html>