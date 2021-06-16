<%@page import="model.database.tableModels.Projekt" %>
<%@page import="model.database.tableModels.Model" %>
<%@page import="model.database.tableModels.ModelTable" %>
<%@page import="config.Address" %>
<%@ page import="model.database.tableModels.Partner" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>LIMS | Projekt</title>
    <link rel="stylesheet" href="./explorer.css">
</head>

<script src="jquery-3.5.1.js"></script>


<%
    String projekt_id = request.getParameter("projekt_id");
    ModelTable partnerList = new ModelTable(new Partner());
%>

<body>
<table id="explorer_table">

    <tr class="explorer_sortfunctions_row">
        <td class="explorer_sortfunction symbol_triangle_up" onclick="sortExplorerTable(0)">Name</td>
        <td class="explorer_sortfunction symbol_triangle_up" onclick="">Datum</td>
        <td class="explorer_sortfunction symbol_triangle_up" onclick="">etc</td>
    </tr>

    <%
        for (Model partner : partnerList.getModelList()) {
    %>

    <tr class="explorer_table_row <%=partner.getPrimaryKey()%>">
            <td class="explorer_table_data <%=partner.getPrimaryKey()%> symbol_folder_closed">
                    
                    <%=((Partner) partner).getName()%>
                    
                    <id style="display: none;"><%=partner.getPrimaryKey()%></id>
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