<%@page import="config.Address"%>
<%@page import="model.database.tableModels.Projekt"%>
<%@page import="controller.servlets.projekt.SaveProjectServlet"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<title>Solid-Chem | LIMS - Insert Projekt</title>
	<!-- <link rel="stylesheet" href="projekt/projekt_erstellen.css"> -->
	<style>
		#create_projekt_table {
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
					<input required id="partner_id_input_field" class="drop_down_field" type=text placeholder="*"
						name=<%=Projekt.COLUMN_PROJEKTPARTNER%>>
				</td>
			</tr>
			<tr>
				<td>Projekt ID</td>
				<td>
					<input required id="projekt_id_input_field" class="drop_down_field" type=text placeholder="*"
						name=<%=Projekt.COLUMN_PRIMARY_KEY%>>
				</td>
			</tr>
			<!-- <tr>
				<td class="tooltip">
					Vertragsnummer <a href="javascript:void(0);">?</a>
					<div class="tooltiptext">Ist die Vertragsnummer gleich der Projekt ID?</div>
				</td>
			</tr> -->
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
	<script src="projekt/projekt_erstellen.js"></script>
	<script>

		$("#form_projekt_erstellen").submit(function (e) {
			e.preventDefault();

			var submitData = {};

			for (var i = 0; i < e.target.length; i++) {

				console.log(e.target[i].name, e.target[i].value);
				submitData[e.target[i].name] = e.target[i].value;

			}

			var url = "<%=Address.getMainPath()%>" + "<%=SaveProjectServlet.ROUTE%>";
			var posting = $.post(url, submitData);
			posting.done(function (data) {

				if (data["status"] === "error") $("#projekt_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] + "</h3>");

				if (data["status"] === "success") {

					let requiredFields = document.querySelectorAll("input:required");
					for (let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
					$("#projekt_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] + "</div>");
					$("#th_speichern").empty();
				}
			});
		})

		// Such-Links
		GlobaleSuche.addSearchLinkToInputWithName("<%=Projekt.COLUMN_PROJEKTPARTNER%>",
			[
				new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, ""),
				new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, "")
			]
		);

		// Such-Links
		GlobaleSuche.addSearchLinkToInputWithName("<%=Projekt.COLUMN_PRIMARY_KEY%>",
			[
				new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, ""),
				new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, () => document.getElementsByName("<%=Projekt.COLUMN_PROJEKTPARTNER%>")[0].value)
			]
		);
	</script>

</html>