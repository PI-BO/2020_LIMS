<<<<<<< HEAD
<%@page import="model.database.tableModels.Projekt"%>
<%@page import="config.Address"%>
<%@ page import="model.database.tableModels.Probe"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
=======
<%@page import="model.database.tableModels.Projekt" %>
<%@page import="config.Address" %>
<%@ page import="model.database.tableModels.Probe" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
>>>>>>> 518ae08dd2f3ffd4906397a5a8e077c18a299c15

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <link rel="stylesheet" href="<%=Address.getExplorerCSS()%>">
    <title>LIMS | Projekt</title>
</head>

<%
    String projekt_id = request.getParameter("projekt_id");
    Projekt projekt = new Projekt(projekt_id);
%>

<body>
<<<<<<< HEAD
	<table id="explorer_table">

		<tr class="explorer_sortfunctions_row">
			<td class="explorer_sortfunction symbol_triangle_up" onclick="sortExplorerTable(0)">Name</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">Datum</td>
			<td class="explorer_sortfunction symbol_triangle_up" onclick="">etc</td>
		</tr>


		<%
				for (Probe probe : projekt.getProben()) {
			%>

		<tr>
			<td class="explorer_table_data symbol_folder_closed"
				onclick="(
					function() {
					let data = {projekt_id: '<%=probe.getPrimaryKey()%>'};
					loadPage('<%=Address.getProbeJSP()%>', data);
					explorerState.pushToState({
					table: '<%=probe.getTable()%>',
					id: '<%=probe.getPrimaryKey()%>',
					text: '<%=probe.getPrimaryKey()%>'
					});
					}
					)()"><%=probe.getPrimaryKey()%></td>
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
=======
<table id="explorer_table">

    <tr class="explorer_sortfunctions_row">
        <td class="explorer_sortfunction symbol_triangle_up" onclick="sortExplorerTable(0)">Name</td>
        <td class="explorer_sortfunction symbol_triangle_up" onclick="">Datum</td>
        <td class="explorer_sortfunction symbol_triangle_up" onclick="">etc</td>
    </tr>


    <%
        for (Probe probe : projekt.getProben()) {
    %>

    <tr class="explorer_table_row <%=probe.getPrimaryKey()%>">
        <td class="explorer_table_data symbol_folder_closed" onclick="(
                function() {
                let data = {projekt_id: '<%=probe.getPrimaryKey()%>'};
                loadPage('<%=Address.getProbeJSP()%>', data);
                explorerState.pushToState({
                table: '<%=probe.getTable()%>',
                id: '<%=probe.getPrimaryKey()%>',
                text: '<%=probe.getPrimaryKey()%>'
                });
                }
                )()"><%=probe.getPrimaryKey()%>
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

    contextMenu.initProbe();

</script>
>>>>>>> 518ae08dd2f3ffd4906397a5a8e077c18a299c15

</body>
</html>