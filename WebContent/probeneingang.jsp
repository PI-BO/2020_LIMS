<%@page import="controller.testServlets.FileuploadTestServlet"%>
<%@page import="controller.ProbeneingangServlet"%>
<%@page import="controller.testServlets.PostGetTestServlet"%>
<%@page import="database.model.Projekt"%>
<%@page import="database.model.Model"%>
<%@page import="database.model.ModelList"%>
<%@page import="database.model.Partner"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<title></title>
	<!-- <link rel="stylesheet" href="projekt_erstellen.css"> -->
	<script src="jquery-3.5.1.js"></script>
	<style>
        #table_probeneingang {
			border-spacing: 0;
            background-color: white;
            width:auto;
        }
        
        #table_probeneingang tr:nth-child(even) {
  			background-color: #dddddd;
		}
		
        #table_probeneingang th{
            text-align: left;
            padding : 4px;
		}
		
        #table_probeneingang td {
        	border: 1px solid #dddddd;
            text-align: left;
  			padding: 8px;
		}
		
		.table_in_table_header {
			border-right:1px solid white;
		}
		
		#table_probeneingang input{
			min-width:300px;
			max-width:auto;
		}
		
		.table_in_table{
			border-collapse: collapse;
 			max-width:auto;
		}
		
		.table_in_table td{
			min-width:auto;
		}
		
		#table_image_upload td{
			border: 0px solid #dddddd;	
		}
		
		.image-container{
/* 			border: 2px solid #dddddd;	 */
			float: left;
  			width: 25%;
  			margin: 5px;
		}
		
    </style>
</head>
<body>
	<form id="form_probeneingang">
	<input type="hidden" id="probeneingang_url" value=<%=ProbeneingangServlet.ROUTE%>>
	<input type="hidden" id="image_upload_url" value=<%=FileuploadTestServlet.ROUTE%>>
		<table id="table_probeneingang">
			<tr><th style="background-color:#77bbff; padding: 16px;">Probeneingang </th></tr>
			
			<tr><th>Interne Vergabenummer</th></tr>
			<tr><td><input type="number" name=<%=ProbeneingangServlet.INTERNE_VERGABENUMMER%>></td></tr>
			
			<tr><th>Wirkstoff</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.WIRKSTOFF%>></td></tr>
			
			<tr><th>Auftraggeber</th></tr>
			<tr>
				<td>
					<select required name=<%=ProbeneingangServlet.AUFTRAGGEBER%> >
					 	<option value="" selected disabled></option>
					 	
					 	<%
					 	ModelList modelList = new ModelList(new Partner());

					 	for(Model model : modelList.getModelList()){
					 		
					 		%>
					 		<option value=<%=model.getPrimaryKey()%> ><%= ((Partner)model).getName()%></option>
					 		<%
					 	}
					 	
					 	%>
					 	
					</select>
				</td>
			</tr>
			
			<tr><th>Proben-Nr</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.PROBEN_NR%>></td></tr>
			
			<tr><th>Projektvertragnummer</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.PROJEKTVERTRAGNUMMER%>></td></tr>
			
			<tr><th>Anlagennummer</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.ANLAGENNUMMER%>></td></tr>
			
			<tr><th>Summenformel</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.SUMMENFORMEL%>></td></tr>
			
			<tr><th>Bezeichung</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.BEZEICHNUNG%>></td></tr>
			
			<tr><th>Originator</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.ORIGINATOR%>></td></tr>
			
			<tr><th>Probeneingang</th></tr>
			<tr><td><input type="date" name=<%=ProbeneingangServlet.PROBENEINGANG%>></td></tr>
			
			<tr><th>Probenmasse</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.PROBENMASSE%>></td></tr>
			
			<tr><th>Besonderheiten</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.BESONDERHEITEN%>></td></tr>
			
			<tr><th>Infos</th></tr>
			<tr><td><textarea rows="4" cols="50" name=<%=ProbeneingangServlet.INFOS%>></textarea></td></tr>
			
			<tr>
				<td style="padding:0px">
					<table class="table_in_table">
						<tr>
							<th>Standort</th>
							<th>Messung DSC</th>
							<th>Messung Pulver</th>
							<th>Messung IR</th>
						</tr>
						<tr style="background-color:white; widht:100%">
							<td><input style="min-width:auto" type="text" name=<%=ProbeneingangServlet.STANDORT%>></td>
							<td style="text-align:center"><input style="min-width:auto" type="checkbox" value="DSC" name=<%=ProbeneingangServlet.MESSUNG_DSC%>></td>
							<td style="text-align:center"><input style="min-width:auto" type="checkbox" value="Pulver" name=<%=ProbeneingangServlet.MESSUNG_PULVER%>></td>
							<td style="text-align:center"><input style="min-width:auto" type="checkbox" value="IR" name=<%=ProbeneingangServlet.MESSUNG_IR%>></td>
						</tr>
					</table>
				</td>
			</tr>			
			
			<tr></tr>
			
			<tr><th>Bemerkungen zur Messung</th></tr>
			<tr><td><textarea rows="4" cols="50" name=<%=ProbeneingangServlet.BEMERKUNGEN_ZUR_MESSUNG%>></textarea></td></tr>
			
			<tr></tr>
			
			<tr>
				<td style="padding:0px">
					<table class="table_in_table">
						<tr style="background-color:#dddddd">
							<th class="table_in_table_header">Vertrag vorhanden</th>
							<th class="table_in_table_header">Vertrag vorhanden Datum</th>
							<th class="table_in_table_header">Vertrag unterzeichnet</th>
							<th>Vertrag unterzeichnet Datum</th>
						</tr>
						<tr style="background-color:white">
							<td style="text-align:center"><input style="min-width:auto" type="checkbox" name=<%=ProbeneingangServlet.VERTRAG_VORHANDEN%>></td>
							<td><input style="min-width:auto" type="date" name=<%=ProbeneingangServlet.VERTRAG_VORHANDEN_DATUM%>></td>
							<td style="text-align:center"><input style="min-width:auto" type="checkbox" name=<%=ProbeneingangServlet.VERTRAG_UNTERZEICHNET%>></td>
							<td><input style="min-width:auto" type="date" name=<%=ProbeneingangServlet.VERTRAG_UNTERZEICHNET_DATUM%>></td>
						</tr>
						<tr style="background-color:#dddddd">
							<th class="table_in_table_header">Vertrag verschickt</th>
							<th class="table_in_table_header">Vertrag verschickt Datum</th>
							<th class="table_in_table_header">Vertrag abgerechnet</th>
							<th>Vertrag abgerechnet Datum</th>
						</tr>
						<tr style="background-color:white">
							<td style="text-align:center"><input style="min-width:auto" type="checkbox" name=<%=ProbeneingangServlet.VERTRAG_VERSCHICKT%>></td>
							<td><input style="min-width:auto" type="date" name=<%=ProbeneingangServlet.VERTRAG_VERSCHICKT_DATUM%>></td>
							<td style="text-align:center"><input style="min-width:auto" type="checkbox" name=<%=ProbeneingangServlet.VERTRAG_ABGERECHNET%>></td>
							<td><input style="min-width:auto" type="date" name=<%=ProbeneingangServlet.VERTRAG_ABGERECHNET_DATUM%>></td>
						</tr>
						<tr style="background-color:#dddddd">
							<th class="table_in_table_header">Vertrag bezahlt</th>
							<th>Vertrag bezahlt Datum</th>
						</tr>
						<tr style="background-color:white">
							<td style="text-align:center"><input style="min-width:auto" type="checkbox" value="vertrag_bezahlt" name=<%=ProbeneingangServlet.VERTRAG_BEZAHLT%>></td>
							<td><input  style="min-width:auto" type="date" name=<%=ProbeneingangServlet.VERTRAG_BEZAHLT_DATUM%>></td>
						</tr>
					</table>
				</td>
						
			<tr><th>Bemerkungen</th></tr>
			<tr><td><textarea rows="4" cols="50" name=<%=ProbeneingangServlet.BEMERKUNGEN%>></textarea></td></tr>
			
			<tr><th>Literatur</th></tr>
			<tr><td><textarea rows="4" cols="50" name=<%=ProbeneingangServlet.LITERATUR%>></textarea></td></tr>
			
			<tr><th>Bilder hinzufuegen</th></tr>
			<tr>
				<td>
					<table class="table_in_table" id="table_image_upload">
						<tr>
							<td >
								(mehrer Bilder auswaehlen: STRG + Linksklick)
								<br>
								<button type="button" id="input_image_reset_button">Reset Images</button> 
								<input type="file" id="input_image_upload" name="probeneingang_bilder" accept="image/*" onchange="loadFile(event)" multiple> 
							</td>
							<td >
								<div id="preview-container" style="max-width:300px"></div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr><th style="text-align:center" id="button_probeneingang_speichern"><button type="submit">Speichern</button></th></tr>
			
		</table>
	</form>
	<script>
	
	function loadFile(event) {
		
		$("#preview-container").empty();
		
		for(let i = 0; i < event.target.files.length; i++){
			
			var objectURL = URL.createObjectURL(event.target.files[i]);
			$("#preview-container").append("<div class='image-container'><img class='preview-image' style='width:100%; height=100%' src=" + objectURL + "></div>")
			$(".preview-image").attr({onload : "freeMemory(this);"});
		}
	};
	
	$("#input_image_reset_button").on("click", function(){
			$("#preview-container").empty();
			$("#input_image_upload").val("");		
	});
	
	
	function freeMemory(element){
		URL.revokeObjectURL(element.src);
	}
	
	// Bilder aus Form herausnehmen und an speziellen Servlet senden
	$('#form_probeneingang').submit(function(e) {
		e.preventDefault();
		
		// extra Form erstellen
		var formWithImage = $("<form></form>")[0];
		
		// file-input element mit Bilder clonen und Form anhaengen
		$($("#input_image_upload")[0]).clone().appendTo(formWithImage);
		
		// Bilder als enctype an Bilder Servlet senden
		$.ajax({
			url : "http://localhost:8080/2020_LIMS" + $("#image_upload_url").val(),
			type : 'POST',
			data : new FormData(formWithImage),
			processData : false,
			contentType : false,
			xhrFields : {
				responseType : 'blob'
			},
			success : function(data) {

				// falls Blob als response kommt
// 				var blobData = data;
// 				var url = window.URL || window.webkitURL;
// 				var src = url.createObjectURL(blobData);
// 				$('#result-image').attr("src", src);
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});
	});
	
	
	// Form an Servlet senden
	$("#form_probeneingang").submit(function(e){
		e.preventDefault();

		var submitData = {};
		var url = "http://localhost:8080/2020_LIMS" + $("#probeneingang_url").val();

		var array = $("#form_probeneingang").serializeArray();
		array.forEach(function(item, index){
			submitData[item.name] = item.value;
		});
		
		var posting = $.post( url, submitData );
// 		posting.done(function( data ) {
			
			// Button nach Speichern entfernen und Rueckmeldung fuer User anhaengen
// 			$("#button_probeneingang_speichern").empty().append(data);
// 		});
	})
	
	</script>
	
</body>
</html>