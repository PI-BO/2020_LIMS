<%@page import="database.model.ProjekteIdList"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
		<title>LIMS | Projekt</title>
		<script src="jquery-3.5.1.js"></script>
		<link rel="stylesheet" href="projekte.css">
	</head>

	<% ProjekteIdList projekte = new ProjekteIdList(); %>

	<body>
		<div id="container_content">
			<table id="projekt_list">
			<% 
			for(String id : projekte.getProjekteIdList()){
				
				%><tr><td class="projekt_list_element"><%=id %></td></tr><%
			}
			%>
			</table>			
		</div>
	</body>
</html>