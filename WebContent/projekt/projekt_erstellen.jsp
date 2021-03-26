<%@page import="config.Address"%>
<%@page import="model.database.tableModels.Projekt"%>
<%@page import="model.database.tableModels.Model"%>
<%@page import="model.database.tableModels.Partner"%>
<%@page import="model.database.tableModels.ModelTable"%>
<%@page import="controller.servlets.SaveProjectServlet"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Solid-Chem | LIMS - Insert Projekt</title>
<!-- <link rel="stylesheet" href="projekt/projekt_erstellen.css"> -->
<style>
#create_projekt_table {
	float: left;
	border: 1px solid #ddd;
	padding: 10px;
	padding-left: 30px;
	padding-right: 30px;
	display: inline-block;
	background-color: white;
}

#th_speichern {
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

.tooltip {
	position: relative;
	display: inline-block;
	/*   color: #0000EE; */
	/*   border-bottom: 1px solid #0000EE; */
}

.tooltip .tooltiptext {
	visibility: hidden;
	/*   height: 1em; */
	min-width: 20em;
	width: auto;
	background-color: black;
	color: #fff;
	text-align: center;
	border-radius: 6px;
	padding: 10px;
	position: absolute;
	z-index: 1;
	top: -5px;
	left: 110%;
}

.tooltip:hover {
	cursor: help;
}

.tooltip a:hover {
	cursor: help;
}

.tooltip .tooltiptext::after {
	content: " ";
	position: absolute;
	top: 50%;
	right: 100%; /* To the left of the tooltip */
	margin-top: -5px;
	border-width: 5px;
	border-style: solid;
	border-color: transparent black transparent transparent;
}

.tooltip:hover .tooltiptext {
	visibility: visible;
}
</style>
</head>
<body>
	<form id="form_projekt_erstellen">
		<table id="create_projekt_table">
			<tr>
				<th colspan=4>
					<h1>Projekt erstellen</h1>
				</th>
			</tr>
			<tr>
				<th>Projekt Informationen</th>
			</tr>
			<tr>
				<td>Projektpartner ID</td>
				<td>
					<input required onclick="dropDownFunction()" id="partner_id_input_field" class="drop_down_field" type=text placeholder="*" name=<%=Projekt.COLUMN_PROJEKTPARTNER%>>
				</td>
				<td>
					<div id="myDropdown" class="dropdown-content-projektpartner">
						<a id="suche_projekt_partner_id" href="#suche_projekt_partner_id">suchen</a>
					</div>
				</td>
			</tr>
			<tr>
				<td>Projekt ID</td>
				<td>
					<input required onclick="dropDownFunction()" id="projekt_id_input_field" class="drop_down_field" type=text placeholder="*" name=<%=Projekt.COLUMN_PRIMARY_KEY%>>
				</td>
				<td>
					<div id="myDropdown" class="dropdown-content-projektpartner">
						<a id="suche_projekt_id" href="#suche_projekt_id">suchen</a>
					</div>
				</td>
			</tr>
			<tr>
				<td class="tooltip">
				Vertragsnummer <a href="javascript:void(0);">?</a>
				<div class="tooltiptext">Ist die Vertragsnummer gleich der Projekt ID?</div>
				</td>
				<td>
					<input type=text placeholder="" name=<%=Projekt.COLUMN_VERTRAGSNUMMER%>>
					<div id="myDropdown" class="dropdown-content">
						<a id="drop_down_suche" href="#">suchen</a>
					</div>
				</td>
			</tr>
			<tr>
				<th id="th_speichern" colspan=4>
					<button type="submit">Speichern</button>
				</th>
			</tr>
			<tr>
				<th id="projekt_erstellen_save_message" colspan=4></th>
			</tr>
		</table>
	</form>

	<script>
	
	function dropDownFunction() {
		  document.getElementById("myDropdown").classList.toggle("show");
		}

		// Close the dropdown menu if the user clicks outside of it
		window.onclick = function(event) {
		  if (!event.target.matches('.drop_down_field')) {
		    var dropdowns = document.getElementsByClassName("dropdown-content");
		    var i;
		    for (i = 0; i < dropdowns.length; i++) {
		      var openDropdown = dropdowns[i];
		      if (openDropdown.classList.contains('show')) {
		        openDropdown.classList.remove('show');
		      }
		    }
		  }
		} 
	
	$("#form_projekt_erstellen").submit(function(e){
		e.preventDefault();
		
		var submitData = {};
		
		for(var i = 0; i < e.target.length; i++){
			
			console.log(e.target[i].name, e.target[i].value);
			submitData[e.target[i].name] = e.target[i].value;
			
		}
		
// 		var url = "http://localhost:8080/2020_LIMS/save_project_servlet";
		var url = "<%=Address.getMainPath()%>" + "<%=SaveProjectServlet.ROUTE%>";
		var posting = $.post( url, submitData );
		posting.done(function( data ) {
			
			if(data["status"] === "error") $("#projekt_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] +  "</h3>");

			if(data["status"] === "success"){
				
				let requiredFields = document.querySelectorAll("input:required");
				for(let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
				$("#projekt_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] +  "</div>");
				$("#th_speichern").empty();
			}
		});
	})
	
	// init Partner Suche
	document.getElementById("suche_projekt_partner_id").addEventListener("click", () => {
		
		NavigationMenu.hideAllExcept("#main-content-global-search");
		const template = [
			{ "partner": "id" },
			{ "partner": "name" }
		];
		GlobaleSuche.initTemplateParameters(template);
		GlobaleSuche.addSearchCallback((callbackContent)=>{
			NavigationMenu.hideAllExcept("#main-content-input-masks");
			let inputField = document.getElementById("partner_id_input_field");
			inputField.value = callbackContent;
		})
	}); 
	
	// init Projekt Suche
	document.getElementById("suche_projekt_id").addEventListener("click", () => {
		
		NavigationMenu.hideAllExcept("#main-content-global-search");
		const template = [
			{ "projekte": "id" }
		];
		GlobaleSuche.initTemplateParameters(template);
		GlobaleSuche.addSearchCallback((callbackContent)=>{
			NavigationMenu.hideAllExcept("#main-content-input-masks");
			let inputField = document.getElementById("projekt_id_input_field");
			inputField.value = callbackContent;
		})
	}); 
	
	</script>
</html>