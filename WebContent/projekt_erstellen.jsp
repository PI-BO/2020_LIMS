<%@page import="database.model.Partner"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<!-- Latest compiled and minified jQuery -->
<meta charset="UTF-8" />
<title>Solid-Chem | LIMS - Insert Projekt</title>
<link rel="stylesheet" href="projekt_erstellen.css">
</head>
<body>
	<form id="form_projekt_erstellen" action="save_project_servlet_bla" method="post">
		<table id="create_projekt_table">
			<tr>
				<th colspan=4><h1>Projekt erstellen</h1></th>
			</tr>
			<tr>
				<th>Vertrags Informationen</th>
				<th></th>
				<th>Projekt Informationen</th>
				<th></th>
			</tr>
			<tr>
				<td>Vertragsnummer</td>
				
				<td>
					<select name="vertragsnummer" id="subject" required>
					 	<option value="" selected disabled></option>
					 	<option value="1" >1</option>
					 	<option value="2" >2</option>
					</select>
				</td>
				
				<td>Projekt ID</td>
				<td><input type=text placeholder="Projekt ID"></td>
				<td></td>
			</tr>
			<tr>
				<td>Ansprechpartner</td>
				<td><input type=text placeholder="Ansprechpartner"></td>
				<td>Datum</td>
				<td><input type=text placeholder="Datum"></td>
				<td></td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td><input type=text placeholder="E-Mail"></td>
				<td>Substanz</td>
				<td><input type=text placeholder="Substanz"></td>
			</tr>

			<tr>
				<th id="th_speichern" colspan=4>
					<button type="submit">Speichern</button>
				</th>
			</tr>
		</table>
	</form>
	
	<script>
	
	$("#form_projekt_erstellen").submit(function(e){
		e.preventDefault();
		
		var url = "http://localhost:8080/2020_LIMS/save_project_servlet";
		var posting = $.post( url, {} );
		posting.done(function( data ) {
			$("#th_speichern").empty().append(data);
		});
	})
	
	</script>
	
</body>
</html>