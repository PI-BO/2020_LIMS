<%@page import="controller.servlets.SucheServlet"%>
<%@page import="config.Address" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Suche</title>
	<style>
		#global_search_main_container {
			background-color: #f2f2f2;
			/* font-family: Arial, Helvetica, sans-serif; */
		}

		#global_search_main_header {
			background-color: #77bbff;
			padding: 16px;
			font-weight: bold;
		}

		.global_search_select_main_category{
			width: 100%;
		}

		.global_search_select_parameter {
			width: 100%;
		}
		
		#global_search_button{
			float: left;
			width: 100%;
		}

		#global_search_result_table {
			font-family: Arial, Helvetica, sans-serif;
			border-collapse: collapse;
		}
		
		#global_search_result_table td {
			border: 1px solid #ddd;
			padding: 8px;
			cursor: default;
		}

		#global_search_result_table tr:nth-child(n+2) td {
			cursor: cell;
		}
		
		#global_search_result_table tr:nth-child(n+3) td {
			cursor: pointer;
		}

		#global_search_result_table tr:nth-of-type(even) {
			background-color: #ffffff;
		}

		#global_search_result_table tr:nth-of-type(odd) {
			background-color: #f2f2f2;
		}

		.global_search_result_table_first_header td{
			background-color: #77bbff;
			font-weight: bold;
			text-align: center;
		}		
		.global_search_result_table_second_header td{
			background-color: #3c9eff;
			font-weight: bold;
			text-align: center;
		}		
		
		.global_search_result_table_result_row td:hover{
			background-color: #ddd;
		}
	</style>
</head>

<body>

	<div id="global_search_main_container">
		<div id="global_search_main_header">Suche</div>
		<table id="global_search_parameter_table">
			<tr>
				<th><input type="button" value="suchen" id="global_search_button"></th>
				<th><input type="button" value="Filter hinzufuegen" id="global_search_add_parameter_button"></th>
				<th></th>
				<th style="font-weight: normal; font-size:x-small">*leeres Feld = alles durchsuchen</th>
				<th></th>
				<th></th>
			</tr>
		</table>

		<table id="global_search_result_table"></table>

		<!-- HIDDEN PARAMETER TEMPLATE  -->

		<div id="global_search_parameter_row_template" style="display: none;">
			<select class="global_search_select_main_category"></select>
			<select class="global_search_select_parameter"></select> 
			<select class="global_search_select_parameter_filter"></select>
			<input class="global_search_parameter_input" type="text" />
			<input type="button" value="X" class="global_search_delete_parameter_button" />
		</div>

		<!-- END TEMPLATE -->
	</div>
	<!-- <script src="globale_suche2.js"></script> -->
<!-- 	<script src="http://localhost:8080/2020_LIMS/suche/globale_suche2.js"></script> -->
	<script src="<%=Address.getGlobaleSucheJs()%>"></script>
	<script type="text/javascript">
		GlobaleSuche.init("<%=Address.getMainPath()%>" + "<%=SucheServlet.ROUTE%>");
	</script>
</body>

</html>