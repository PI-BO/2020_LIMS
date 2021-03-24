<%@page import="model.database.tableModels.Substanz"%>
<%@page import="model.database.tableModels.Model"%>
<%@page import="model.database.tableModels.Partner"%>
<%@page import="model.database.tableModels.ModelTable"%>
<%@page import="controller.servlets.SaveProjectServlet"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Solid-Chem | LIMS - Substanz erstellen</title>
<!-- <link rel="stylesheet" href="projekt/projekt_erstellen.css"> -->
<style>
#create_substanz_table {
	float: left;
	border: 1px solid #ddd;
	padding: 10px;
	padding-left: 30px;
	padding-right: 30px;
	display: inline-block;
	background-color: white;
}

#substanz_speichern_button_th {
	padding-top: 10px;
}

input:required {
	border-style: solid;
	border-color: red;
	border-width: 2px;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #77bbff;
	border: 2px solid #77bbff;
	min-width: 10px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}
/* Links inside the dropdown */
.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

/* Change color of dropdown links on hover */
.dropdown-content a:hover {
	background-color: #ddd
}

/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
.show {
	display: block;
}
</style>
</head>
<body>
	<form id="form_substanz_erstellen">
		<table id="create_substanz_table">
			<tr>
				<th colspan=4>
					<h1>Substanz erstellen</h1>
				</th>
			</tr>
			<tr>
				<th>Substanz Informationen</th>
			</tr>
			<tr>
				<td>Projekt ID</td>
				<td>
					<input required id="projekt_id_input_field" class="drop_down_field" type=text placeholder="*" name=<%=Substanz.COLUMN_PROJEKT_ID%>>
				</td>
				<td>
					<a id="suche_projekt_id" href="#suche_projekt_id">suchen</a>
				</td>
			</tr>
			<tr>
				<td>Substanz ID</td>
				<td>
					<input required id="substanz_id_input_field" class="drop_down_field" type=text placeholder="*" name=<%=Substanz.COLUMN_PRIMARY_KEY%>>
				</td>
				<td>
					<a id="suche_substanz_id" href="#suche_substanz_id">suchen</a>
				</td>
			</tr>
			<tr>
				<td>Wirkstoff</td>
				<td>
					<input type=text placeholder="" name=<%=Substanz.COLUMN_WIRKSTOFF%>>
				</td>
			</tr>
			<tr>
				<th id="substanz_speichern_button_th" colspan=4>
					<button type="submit">Speichern</button>
				</th>
			</tr>
			<tr>
				<th id="substanz_erstellen_save_message" colspan=4></th>
			</tr>
		</table>
	</form>

	<script>
	
	$("#form_substanz_erstellen").submit(function(e){
		e.preventDefault();
		
		var submitData = {};
		
		for(var i = 0; i < e.target.length; i++){
			
			console.log(e.target[i].name, e.target[i].value);
			submitData[e.target[i].name] = e.target[i].value;
			
		}
		
		var url = "http://localhost:8080/2020_LIMS/save_substanz_servlet";
		var posting = $.post( url, submitData );
		posting.done(function( data ) {
			
			if(data["status"] === "error") $("#substanz_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] +  "</h3>");

			if(data["status"] === "success"){
				
				let requiredFields = document.querySelectorAll("input:required");
				for(let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
				$("#substanz_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] +  "</div>");
				$("#substanz_speichern_button_th").empty();
			}
		});
	})
	
	// init Projekt Suche
	document.getElementById("suche_projekt_id").addEventListener("click", () => {
		
		hideAllExcept("#main-content-global-search");
		const template = [
			{ "projekte": "id" },
			{ "projekte": "projektpartner" }
		];
		GlobaleSuche.initTemplateParameters(template);
		GlobaleSuche.addSearchCallback((callbackContent)=>{
			hideAllExcept("#main-content-input-masks");
			let inputField = document.getElementById("projekt_id_input_field");
			inputField.value = callbackContent;
		})
	}); 
	
	// init Substanz Suche
	document.getElementById("suche_substanz_id").addEventListener("click", () => {
		
		hideAllExcept("#main-content-global-search");
		const template = [
			{ "substanz": "id" },
			{ "substanz": "projekt_id" }
		];
		GlobaleSuche.initTemplateParameters(template);
		GlobaleSuche.addSearchCallback((callbackContent)=>{
			hideAllExcept("#main-content-input-masks");
			let inputField = document.getElementById("substanz_id_input_field");
			inputField.value = callbackContent;
		})
	}); 
	
	</script>
</html>