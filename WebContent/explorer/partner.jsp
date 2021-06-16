<%@page import="model.database.tableModels.Projekt" %>
<%@page import="config.Address" %>
<%@ page import="model.database.tableModels.Partner" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <link rel="stylesheet" href="./explorer.css">
    <title>LIMS | Partner</title>
</head>

<%
    String projekt_id = request.getParameter("projekt_id");
    Partner partner = new Partner(projekt_id);
%>

<script src="jquery-3.5.1.js"></script>
<script>
    console.log("partner")
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
        for (Projekt projekt : partner.getProjekte()) {
    %>

    <tr class="explorer_table_row <%=projekt.getPrimaryKey()%>">
        <td class="explorer_table_data symbol_folder_closed" onclick="(
                function() {
                function loadPage(pageAddress, data) {
                const posting = $.post(pageAddress, data);
                posting.done(function (data) {
                    $('#explorer-content').empty().append(data);
                });
                }
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

</body>
</html>