<%@page import="controller.servlets.partner.SavePartnerServlet"%>
<%@page import="config.Address"%>
<%@page import="model.database.tableModels.Projekt"%>
<%@page import="model.database.tableModels.Model"%>
<%@page import="model.database.tableModels.Partner"%>
<%@page import="model.database.tableModels.ModelTable"%>
<%@page import="controller.servlets.projekt.SaveProjectServlet"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<title>Solid-Chem | LIMS - Insert Projekt</title>
	<!-- <link rel="stylesheet" href="projekt/projekt_erstellen.css"> -->
	<style>
		#create_partner_table {
			border: 1px solid #ddd;
			padding: 10px;
			padding-left: 30px;
			padding-right: 30px;
			display: inline-block;
			background-color: white;
		}

		#partner_speicher_th {
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
	<form id="form_partner_erstellen">
		<table id="create_partner_table">
			<tr>
				<th colspan=4>
					<h1>Projektpartner erstellen</h1>
				</th>
			</tr>
			<tr>
				<th>Partner Informationen</th>
			</tr>
			<tr>
				<td id="ProjektpartnerTooltip">
					Projektpartner ID
				</td>
				<td>
					<input required id="partner_id_input_field" type=text placeholder=""
						name=<%=Partner.COLUMN_PRIMARY_KEY%>>
				</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>
					<input id="partner_name_input_field" type=text placeholder="" name=<%=Partner.COLUMN_NAME%>>
				</td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td>
					<input type=text placeholder="" name=<%=Partner.COLUMN_EMAIL%>>
				</td>
			</tr>
			<tr>
				<th id="partner_speicher_th" colspan=4>
					<button type="submit">Speichern</button>
				</th>
			</tr>
			<tr>
				<th id="partner_erstellen_save_message" colspan=4></th>
			</tr>
		</table>
	</form>

	<script>

		$("#form_partner_erstellen").submit(function (e) {
			e.preventDefault();

			var submitData = {};

			for (var i = 0; i < e.target.length; i++) {

				console.log(e.target[i].name, e.target[i].value);
				submitData[e.target[i].name] = e.target[i].value;

			}

			var url = "<%=Address.getMainPath()%>" + "<%=SavePartnerServlet.ROUTE%>";
			var posting = $.post(url, submitData);
			posting.done(function (data) {

				if (data["status"] === "error") $("#partner_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] + "</h3>");

				if (data["status"] === "success") {

					let requiredFields = document.querySelectorAll("input:required");
					for (let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
					$("#partner_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] + "</div>");
					$("#partner_speicher_th").empty();

					MainState.setProjektPartner(submitData["<%=Partner.COLUMN_PRIMARY_KEY%>"])
				}
			});
		})

		// Such-Links
		// GlobaleSuche.addSearchLinkToInputWithName("<%=Partner.COLUMN_PRIMARY_KEY%>",
		// 	[
		// 		new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, ""),
		// 		new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, "")
		// 	], 
		// 	returnParameter = new Parameter("", ""),
		// 	linkText = "auflisten"
		// );

		// Such-Links
		GlobaleSuche.addGenerierenLinkToInputWithName("<%=Partner.COLUMN_PRIMARY_KEY%>",
			[
				new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
			],
			returnParameter = new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
		);

		Tooltip.setTooltip("ProjektpartnerTooltip", "ID automatisch generieren lassen?");

	</script>

</html>