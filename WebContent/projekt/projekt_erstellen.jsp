<%@page import="database.model.Projekt"%>
<%@page import="controller.SaveProjectServlet"%>
<%@page import="database.model.Model"%>
<%@page import="database.model.ModelList"%>
<%@page import="database.model.Partner"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Solid-Chem | LIMS - Insert Projekt</title>
<link rel="stylesheet" href="projekt/projekt_erstellen.css">
</head>
<body>
	<form id="form_projekt_erstellen">
		<table id="create_projekt_table">
			<tr>
				<th colspan=4><h1>Projekt erstellen</h1></th>
			</tr>
			<tr>
				<th style="">Vertrags Informationen</th>
				<th></th>
				<th style="">Projekt Informationen</th>
				<th></th>
			</tr>
			<tr>
				<td style="">Vertragsnummer</td>
				
				<td>
					<select name=<%=Partner.COLUMN_PRIMARY_KEY%> id="subject" required style="float:left">
					 	<option value="" selected disabled></option>
					 	
					 	<%
					 	ModelList modelList = new ModelList(new Partner());

					 	for(Model model : modelList.getModelList()){
					 		
					 		%>
					 		<option value=<%=model.getPrimaryKey()%> ><%= model.getPrimaryKey()%></option>
					 		<%
					 	}
					 	
					 	%>
					 	
					</select>
				</td>
				
				<td style="">Projekt ID</td>
				<td><input type=text placeholder="Projekt ID" name=<%=Projekt.COLUMN_PRIMARY_KEY %>></td>
				<td></td>
			</tr>
			<tr>
				<td style="">Ansprechpartner</td>
				<td><input type=text placeholder="Ansprechpartner"></td>
				<td style="">Datum</td>
				<td><input type=text placeholder="Datum"></td>
				<td></td>
			</tr>
			<tr>
				<td style="">E-Mail</td>
				<td><input type=text placeholder="E-Mail"></td>
				<td style="">Substanz</td>
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
		
		var submitData = {};
		
		for(var i = 0; i < e.target.length; i++){
			
			console.log(e.target[i].name, e.target[i].value);
			submitData[e.target[i].name] = e.target[i].value;
			
		}
		
		var url = "http://localhost:8080/2020_LIMS/save_project_servlet";
		var posting = $.post( url, submitData );
		posting.done(function( data ) {
			$("#th_speichern").empty().append(data);
		});
	})
	
	</script>
	
</body>
</html>