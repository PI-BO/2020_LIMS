<%@page import="controller.ProbeneingangServlet"%>
<%@page import="controller.PostGetTestServlet"%>
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
            width:100%;
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
    </style>
</head>
<body>
	<form id="form_probeneingang">
	<input type="hidden" id="probeneingang_url" value=<%=PostGetTestServlet.ROUTE%>>
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

			<tr></tr>
			
			<tr>
				<td style="padding:0px">
					<table style="border-collapse: collapse">
						<tr style="background-color:#dddddd">
							<th>Standort</th>
							<th>Messung DSC</th>
							<th>Messung Pulver</th>
							<th>Messung IR</th>
						</tr>
						<tr style="background-color:white">
							<td><input type="text" name=<%=ProbeneingangServlet.STANDORT%>></td>
							<td><input type="checkbox" value="DSC" name=<%=ProbeneingangServlet.MESSUNG_DSC%>></td>
							<td><input type="checkbox" value="Pulver" name=<%=ProbeneingangServlet.MESSUNG_PULVER%>></td>
							<td><input type="checkbox" value="IR" name=<%=ProbeneingangServlet.MESSUNG_IR%>></td>
						</tr>
					</table>
				</td>
			</tr>			
			
			<tr><th>Bemerkungen zur Messung</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.BEMERKUNGEN_ZUR_MESSUNG%>></td></tr>
			
			<tr></tr>
			
			<tr>
				<td style="padding:0px">
					<table style="border-collapse: collapse">
						<tr style="background-color:#dddddd">
							<th class="table_in_table_header">Vertrag vorhanden</th>
							<th class="table_in_table_header">Vertrag vorhanden Datum</th>
							<th class="table_in_table_header">Vertrag unterzeichnet</th>
							<th>Vertrag unterzeichnet Datum</th>
						</tr>
						<tr style="background-color:white">
							<td><input type="checkbox" name=<%=ProbeneingangServlet.VERTRAG_VORHANDEN%>></td>
							<td><input type="date" name=<%=ProbeneingangServlet.VERTRAG_VORHANDEN_DATUM%>></td>
							<td><input type="checkbox" value="vertrag_unterzeichnet" name=<%=ProbeneingangServlet.VERTRAG_UNTERZEICHNET%>></td>
							<td><input type="date" name=<%=ProbeneingangServlet.VERTRAG_UNTERZEICHNET_DATUM%>></td>
						</tr>
						<tr style="background-color:#dddddd">
							<th class="table_in_table_header">Vertrag verschickt</th>
							<th class="table_in_table_header">Vertrag verschickt Datum</th>
							<th class="table_in_table_header">Vertrag abgerechnet</th>
							<th>Vertrag abgerechnet Datum</th>
						</tr>
						<tr style="background-color:white">
							<td><input type="checkbox" value="vertrag_verschickt" name=<%=ProbeneingangServlet.VERTRAG_VERSCHICKT%>></td>
							<td><input type="date" name=<%=ProbeneingangServlet.VERTRAG_VERSCHICKT_DATUM%>></td>
							<td><input type="checkbox" value="vertrag_abgerechnet" name=<%=ProbeneingangServlet.VERTRAG_ABGERECHNET%>></td>
							<td><input type="date" name=<%=ProbeneingangServlet.VERTRAG_ABGERECHNET_DATUM%>></td>
						</tr>
						<tr style="background-color:#dddddd">
							<th class="table_in_table_header">Vertrag bezahlt</th>
							<th>Vertrag bezahlt Datum</th>
						</tr>
						<tr style="background-color:white">
							<td><input type="checkbox" value="vertrag_bezahlt" name=<%=ProbeneingangServlet.VERTRAG_BEZAHLT%>></td>
							<td><input type="date" name=<%=ProbeneingangServlet.VERTRAG_BEZAHLT_DATUM%>></td>
						</tr>
					</table>
				</td>
						
			<tr><th>Bemerkungen</th></tr>
			<tr><td><input type="text" name=<%=ProbeneingangServlet.BEMERKUNGEN%>></td></tr>
			
			<tr><th id="button_probeneingang_speichern" colspan=4><button type="submit">Speichern</button></th></tr>
			
		</table>
	</form>
	
	<script>
	
	$("#form_probeneingang").submit(function(e){
		e.preventDefault();

		var submitData = {};
		var url = "http://localhost:8080/2020_LIMS" + $("#probeneingang_url").val();

		var array = $("#form_probeneingang").serializeArray();
		array.forEach(function(item, index){
// 			console.log( item.name + ": " + item.value );
			submitData[item.name] = item.value;
		});
		
		var posting = $.post( url, submitData );
		posting.done(function( data ) {
// 			$("#button_probeneingang_speichern").empty().append(data);
		});
	})
	
	</script>
	
</body>
</html>