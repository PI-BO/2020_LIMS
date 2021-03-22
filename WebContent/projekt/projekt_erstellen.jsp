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
<link rel="stylesheet" href="projekt/projekt_erstellen.css">
<style>

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #77bbff;
  border: 2px solid #77bbff;
  min-width: 10px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
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
.dropdown-content a:hover {background-color: #ddd}

/* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
.show {display:block;}

</style>
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

				<td><select name=<%=Partner.COLUMN_PRIMARY_KEY%> id="subject" required style="float: left">
						<option value="" selected disabled></option>

						<%
					 	ModelTable modelList = new ModelTable(new Partner());

					 	for(Model model : modelList.getModelList()){
					 		
					 		%>
						<option value=<%=model.getPrimaryKey()%>><%= model.getPrimaryKey()%></option>
						<%
					 	}
					 	
					 	%>

				</select></td>

				<td style="">Partner ID</td>
				<td><input onclick="dropDownFunction()" id="projekt_id_input_field" class="drop_down_field" type=text placeholder="Partner ID" name=<%=Partner.COLUMN_PRIMARY_KEY %>>
					  <div id="myDropdown" class="dropdown-content">
					    <a id="drop_down_suche" href="#">suchen</a>
					  </div>
				</td>
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
		
		var url = "http://localhost:8080/2020_LIMS/save_project_servlet";
		var posting = $.post( url, submitData );
		posting.done(function( data ) {
			$("#th_speichern").empty().append(data);
		});
	})
	
	document.getElementById("drop_down_suche").addEventListener("click", () => {
		
		hideAllExcept("#main-content-global-search");
		const template = [
			{ "partner": "name" }
		];
		GlobaleSuche.initTemplateParameters(template);
		GlobaleSuche.addSearchCallback((callbackContent)=>{
			hideAllExcept("#main-content-input-masks");
			let inputField = document.getElementById("projekt_id_input_field");
			inputField.value = callbackContent;
		})
	}); 
	
	</script>
</html>