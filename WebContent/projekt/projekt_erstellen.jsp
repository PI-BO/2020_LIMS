<%@page import="model.database.tableModels.Partner" %>
<%@page import="config.Address" %>
<%@page import="model.database.tableModels.Projekt" %>
<%@page import="controller.servlets.projekt.SaveProjectServlet" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

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
				<td>Projektpartner Name</td>
				<td>
					<input disabled required type=text placeholder="" name=<%=Partner.COLUMN_NAME%>>
				</td>
			</tr>
			<tr>
				<td id="projektIdTooltip">Projekt ID</td>
				<td>
					<input required id="projekt_id_input_field" class="drop_down_field" type=text placeholder="" name=<%=Projekt.COLUMN_PRIMARY_KEY%>>
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

				// console.log(e.target[i].name, e.target[i].value);
				if (e.target[i].name === "") continue;

				submitData[e.target[i].name] = e.target[i].value;
			}

			let partnerName = document.getElementsByName("<%=Partner.COLUMN_NAME%>")[0].value;

			GlobaleSuche.backgroundSearch(
				[
					new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, partnerName)
				],
				requestedData => {

					let partnerId = requestedData[0][0][Parameters.PARTNER.PK];

					submitData[Parameters.PROJEKT.FK] = partnerId;

					var url = "<%=Address.getMainPath()%>" + "<%=SaveProjectServlet.ROUTE%>";
					var posting = $.post(url, submitData);
					posting.done(async function (data) {

						if (data["status"] === "error") $("#projekt_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] + "</h3>");

						if (data["status"] === "success") {

							let requiredFields = document.querySelectorAll("input:required");
							for (let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
							$("#projekt_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] + "</div>");
							$("#th_speichern").empty();

							MainState.setProjekt(submitData["<%=Projekt.COLUMN_PRIMARY_KEY%>"])
						}
					});
				}
			)
		})

		GlobaleSuche.addGenerierenLinkToInputWithName("<%=Projekt.COLUMN_PRIMARY_KEY%>",
			[
				new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, "")
			],
			returnParameter = new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, "")
		);

		Tooltip.setTooltip("projektIdTooltip", "Projekt ID automatisch generieren lassen?");

		function projektErstellenInit() {

			let projektPartnerInput = document.getElementsByName("<%=Partner.COLUMN_NAME%>")[0];
			projektPartnerInput.value = MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.NAME];

			// terrible hack solange keine vernuenftige Loesing gefunden wurde
			setTimeout(function () {
				if (projektPartnerInput.value === "") {
					alert("bitte Partner auswaehlen!");
					// $("#partner_auswaehlen").click();

					NavigationMenu.openStateSearch(
						[
							new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, () => MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.NAME]),
							new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, () => MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.PK])
						],
						async (callbackData) => {
							await MainState.setProjekt(callbackData[Parameters.PROJEKT.PK]);
							$("#projekt_erstellen").click();
						},
						new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK),
					)
				}
			}, 500);
		}

		projektErstellenInit();

	</script>
</html>