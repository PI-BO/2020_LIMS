<%@page import="config.Address"%>
<%@page import="model.database.tableModels.Model"%>
<%@page import="model.database.tableModels.Partner"%>
<%@page import="model.database.tableModels.ModelTable"%>
<%@page import="model.Probeneingang"%>
<%@page import="controller.testServlets.FileuploadTestServlet"%>
<%@page import="controller.testServlets.PostGetTestServlet"%>
<%@page import="controller.servlets.ProbeneingangServlet"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8" />
	<title>Probeneingang</title>
	<!-- <link rel="stylesheet" href="projekt_erstellen.css"> -->
	<style>
		input:required {
			border-style: solid;
			border-color: red;
			border-width: 2px;
		}

		#table_probeneingang {
			border-spacing: 0;
			background-color: white;
			width: auto;
		}

		#table_probeneingang tr:nth-child(even) {
			background-color: #dddddd;
		}

		#table_probeneingang th {
			text-align: left;
			padding: 4px;
		}

		#table_probeneingang td {
			border: 1px solid #dddddd;
			text-align: left;
			padding: 8px;
		}

		.table_in_table_header {
			border-right: 1px solid white;
		}

		#table_probeneingang input {
			min-width: 300px;
			max-width: auto;
		}

		.table_in_table {
			border-collapse: collapse;
			max-width: auto;
		}

		.table_in_table td {
			min-width: auto;
		}

		#table_image_upload td {
			border: 0px solid #dddddd;
		}

		.image-container {
			/* 			border: 2px solid #dddddd;	 */
			float: left;
			width: 25%;
			margin: 5px;
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
			right: 100%;
			/* To the left of the tooltip */
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
	<form id="form_probeneingang">
		<input type="hidden" id="probeneingang_url" value=<%=ProbeneingangServlet.ROUTE%>>
		<table id="table_probeneingang">
			<tr style="background-color: #77bbff;">
				<th class="tooltip" style="background-color: #77bbff; padding: 16px;">
					Probeneingang <a href="javascript:void(0);">?</a>
					<div class="tooltiptext">Die erst Probe ist die Hauptprobe von der spaeter Unterproben fuer
						Experimente genommen werden koennen?</div>
				</th>
			</tr>

			<tr>
				<th>Interne Vergabenummer</th>
			</tr>
			<tr>
				<td>
					<input type="number" name=<%=Probeneingang.INTERNE_VERGABENUMMER%>>
				</td>
			</tr>

			<tr>
				<th class="tooltip">
					Wirkstoff-Name <a href="javascript:void(0);">?</a>
					<div class="tooltiptext">Wirkstoff ist Substanz?</div>
				</th>
			</tr>
			<tr>
				<td>
					<input type="text" name=<%=Probeneingang.WIRKSTOFF%>>
				</td>
			</tr>

			<tr>
				<th class="tooltip">
					Wirkstoff-ID <a href="javascript:void(0);">?</a>
					<div class="tooltiptext">Wirkstoff ist Substanz?</div>
				</th>
			</tr>
			<tr>
				<td>
					<input required type="text" id="wirkstoff_id_input_field" name=<%=Probeneingang.WIRKSTOFF_ID%>
						placeholder="*Substanz ID">
				</td>
			</tr>

			<tr>
				<th>Auftraggeber</th>
			</tr>
			<tr>
				<td>
					<input type="text" id="partner_name_input_field" name=<%=Probeneingang.AUFTRAGGEBER%>
						placeholder="">
				</td>
			</tr>

			<tr>
				<th class="tooltip">
					Proben-Nr <a href="javascript:void(0);">?</a>
					<div class="tooltiptext">Proben-Nr und Proben ID sind dasselbe?</div>
				</th>
			</tr>
			<tr>
				<td>
					<input required type="text" id="proben_id_input_field" name=<%=Probeneingang.PROBEN_NR%>
						placeholder="* neue Proben ID">
				</td>
			</tr>

			<!-- Relikt laut Haferkamp -->
			<!-- <tr>
				<th class="tooltip">
					Projektvertragnummer <a href="javascript:void(0);">?</a>
					<div class="tooltiptext">sind Projektvertragnummer und Projekt ID dasselbe?</div>
				</th>
			</tr> -->
			<!-- <tr>
				<td>
					<input type="text" id="projekt_id_input_field" name=<%=Probeneingang.PROJEKTVERTRAGNUMMER%>
						placeholder="*Projekt ID">
				</td>
			</tr> -->

			<tr>
				<th>Anlagennummer</th>
			</tr>
			<tr>
				<td>
					<input type="text" name=<%=Probeneingang.ANLAGENNUMMER%>>
				</td>
			</tr>

			<tr>
				<th>Summenformel</th>
			</tr>
			<tr>
				<td>
					<input type="text" name=<%=Probeneingang.SUMMENFORMEL%>>
				</td>
			</tr>

			<tr>
				<th>Bezeichung</th>
			</tr>
			<tr>
				<td>
					<input type="text" name=<%=Probeneingang.BEZEICHNUNG%>>
				</td>
			</tr>

			<tr>
				<th>Originator</th>
			</tr>
			<tr>
				<td>
					<input type="text" name=<%=Probeneingang.ORIGINATOR%>>
				</td>
			</tr>

			<tr>
				<th>Probeneingang</th>
			</tr>
			<tr>
				<td>
					<input type="date" name=<%=Probeneingang.PROBENEINGANG%>>
				</td>
			</tr>

			<tr>
				<th>Probenmasse</th>
			</tr>
			<tr>
				<td>
					<input type="text" name=<%=Probeneingang.PROBENMASSE%>>
				</td>
			</tr>

			<tr>
				<th>Besonderheiten</th>
			</tr>
			<tr>
				<td>
					<input type="text" name=<%=Probeneingang.BESONDERHEITEN%>>
				</td>
			</tr>

			<tr>
				<th>Infos</th>
			</tr>
			<tr>
				<td>
					<textarea rows="4" cols="50" name=<%=Probeneingang.INFOS%>></textarea>
				</td>
			</tr>

			<tr>
				<td style="padding: 0px">
					<table class="table_in_table">
						<tr>
							<th>Standort</th>
							<th>Messung DSC</th>
							<th>Messung Pulver</th>
							<th>Messung IR</th>
						</tr>
						<tr style="background-color: white; width: 100%">
							<td>
								<input style="min-width: auto" type="text" name=<%=Probeneingang.STANDORT%>>
							</td>
							<td style="text-align: center">
								<input style="min-width: auto" type="checkbox" value="DSC"
									name=<%=Probeneingang.MESSUNG_DSC%>>
							</td>
							<td style="text-align: center">
								<input style="min-width: auto" type="checkbox" value="Pulver"
									name=<%=Probeneingang.MESSUNG_PULVER%>>
							</td>
							<td style="text-align: center">
								<input style="min-width: auto" type="checkbox" value="IR"
									name=<%=Probeneingang.MESSUNG_IR%>>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr></tr>

			<tr>
				<th>Bemerkungen zur Messung</th>
			</tr>
			<tr>
				<td>
					<textarea rows="4" cols="50" name=<%=Probeneingang.BEMERKUNGEN_ZUR_MESSUNG%>></textarea>
				</td>
			</tr>

			<tr></tr>

			<tr>
				<td style="padding: 0px">
					<table class="table_in_table">
						<tr style="background-color: #dddddd">
							<th class="table_in_table_header">Vertrag vorhanden</th>
							<th class="table_in_table_header">Vertrag vorhanden Datum</th>
							<th class="table_in_table_header">Vertrag unterzeichnet</th>
							<th>Vertrag unterzeichnet Datum</th>
						</tr>
						<tr style="background-color: white">
							<td style="text-align: center">
								<input style="min-width: auto" type="checkbox"
									name=<%=Probeneingang.VERTRAG_VORHANDEN%>>
							</td>
							<td>
								<input style="min-width: auto" type="date"
									name=<%=Probeneingang.VERTRAG_VORHANDEN_DATUM%>>
							</td>
							<td style="text-align: center">
								<input style="min-width: auto" type="checkbox"
									name=<%=Probeneingang.VERTRAG_UNTERZEICHNET%>>
							</td>
							<td>
								<input style="min-width: auto" type="date"
									name=<%=Probeneingang.VERTRAG_UNTERZEICHNET_DATUM%>>
							</td>
						</tr>
						<tr style="background-color: #dddddd">
							<th class="table_in_table_header">Vertrag verschickt</th>
							<th class="table_in_table_header">Vertrag verschickt Datum</th>
							<th class="table_in_table_header">Vertrag abgerechnet</th>
							<th>Vertrag abgerechnet Datum</th>
						</tr>
						<tr style="background-color: white">
							<td style="text-align: center">
								<input style="min-width: auto" type="checkbox"
									name=<%=Probeneingang.VERTRAG_VERSCHICKT%>>
							</td>
							<td>
								<input style="min-width: auto" type="date"
									name=<%=Probeneingang.VERTRAG_VERSCHICKT_DATUM%>>
							</td>
							<td style="text-align: center">
								<input style="min-width: auto" type="checkbox"
									name=<%=Probeneingang.VERTRAG_ABGERECHNET%>>
							</td>
							<td>
								<input style="min-width: auto" type="date"
									name=<%=Probeneingang.VERTRAG_ABGERECHNET_DATUM%>>
							</td>
						</tr>
						<tr style="background-color: #dddddd">
							<th class="table_in_table_header">Vertrag bezahlt</th>
							<th>Vertrag bezahlt Datum</th>
						</tr>
						<tr style="background-color: white">
							<td style="text-align: center">
								<input style="min-width: auto" type="checkbox" value="vertrag_bezahlt"
									name=<%=Probeneingang.VERTRAG_BEZAHLT%>>
							</td>
							<td>
								<input style="min-width: auto" type="date"
									name=<%=Probeneingang.VERTRAG_BEZAHLT_DATUM%>>
							</td>
						</tr>
					</table>
				</td>
			<tr>
				<th>Bemerkungen</th>
			</tr>
			<tr>
				<td>
					<textarea rows="4" cols="50" name=<%=Probeneingang.BEMERKUNGEN%>></textarea>
				</td>
			</tr>

			<tr>
				<th>Literatur</th>
			</tr>
			<tr>
				<td>
					<textarea rows="4" cols="50" name=<%=Probeneingang.LITERATUR%>></textarea>
				</td>
			</tr>

			<tr>
				<th>Bilder hinzufuegen</th>
			</tr>
			<tr>
				<td>
					<table class="table_in_table" id="table_image_upload">
						<tr>
							<td>
								(mehrere Bilder auswaehlen: STRG + Linksklick)
								<br>
								<button type="button" id="input_image_reset_button">Bilderauswahl leeren</button>
								<input type="file" id="input_image_upload" name="probeneingang_bilder" accept="image/*"
									onchange="loadFile(event)" multiple>
							</td>
							<td>
								<div id="preview-container" style="max-width: 300px"></div>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<th style="text-align: center" id="button_probeneingang_speichern">
					<button type="submit">Speichern</button>
				</th>
			</tr>

			<tr>
				<th style="text-align: center" id="probeneingang_erstellen_save_message"></th>
			</tr>

		</table>
	</form>
	<script>

		// load small preview pictures when files have been selected 
		function loadFile(event) {

			$("#preview-container").empty();

			for (let i = 0; i < event.target.files.length; i++) {

				var objectURL = URL.createObjectURL(event.target.files[i]);
				$("#preview-container").append("<div class='image-container'><img class='preview-image' style='width:100%; height=100%' src=" + objectURL + "></div>")
				$(".preview-image").attr({ onload: "freeMemory(this);" });
			}
		};

		// reset preview pictures when reset button has been pressed 
		$("#input_image_reset_button").on("click", function () {
			$("#preview-container").empty();
			$("#input_image_upload").val("");
		});

		function freeMemory(element) {
			URL.revokeObjectURL(element.src);
		}

		initFormHandler();

		function initFormHandler() {

			// send form data to url
			let form = document.querySelector('#form_probeneingang');

			form.addEventListener('submit', function (e) {
				e.preventDefault();
				var formData = new FormData(form);

				// 			let url = "http://localhost:8080/2020_LIMS" + document.querySelector("#probeneingang_url").value;
				let url = "<%=Address.getMainPath()%>" + document.querySelector("#probeneingang_url").value;

				json = fetch(url, {
					method: "post",
					body: formData
				})
					.then(response => {
						// 				replaceContent("button_probeneingang_speichern", "Erfolgreich gespeichert", "green");

						let json = response.json().then(data => {

							console.log({ data });

							if (data["status"] === "error") $("#probeneingang_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] + "</h3>");

							if (data["status"] === "success") {

								let requiredFields = document.querySelectorAll("input:required");
								for (let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
								$("#probeneingang_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] + "</div>");
								$("#button_probeneingang_speichern").empty();
							}
						})
					})
					.catch(error => {
						replaceContent("button_probeneingang_speichern", "Fehler:" + error, "red");
					});


			}, false);
		}


		function replaceContent(id, text, color) {

			var element = document.getElementById(id);
			element.style.color = color;
			while (element.firstChild) {
				element.removeChild(element.firstChild);
			}
			element.append(text);
		}

		GlobaleSuche.addSearchLinkToInputWithName("<%=Probeneingang.WIRKSTOFF_ID%>",
			[
				{
					"category": "substanz",
					"parameter": "id",
					"value": ""
				},
				{
					"category": "projekte",
					"parameter": "id",
					"value": ""
				},
				{
					"category": "partner",
					"parameter": "name",
					"value": ""
				}
			]
		);

		GlobaleSuche.addSearchLinkToInputWithName("<%=Probeneingang.AUFTRAGGEBER%>",
			[
				{
					"category": "partner",
					"parameter": "id",
					"value": ""
				},
				{
					"category": "partner",
					"parameter": "name",
					"value": ""
				},
				{
					"category": "partner",
					"parameter": "email",
					"value": ""
				},
				{
					"category": "substanz",
					"parameter": "id",
					"value": () => document.getElementsByName("<%=Probeneingang.WIRKSTOFF_ID%>")[0].value
				}

			]
		);

		GlobaleSuche.addSearchLinkToInputWithName("<%=Probeneingang.PROBEN_NR%>",
			[
				{
					"category": "probe",
					"parameter": "id",
					"value": ""
				},
				{
					"category": "projekte",
					"parameter": "id",
					"value": ""
				},
				{
					"category": "partner",
					"parameter": "name",
					"value": () => document.getElementsByName("<%=Probeneingang.AUFTRAGGEBER%>")[0].value
				},
				{
					"category": "substanz",
					"parameter": "id",
					"value": () => document.getElementsByName("<%=Probeneingang.WIRKSTOFF_ID%>")[0].value
				}

			]
		);

		// Relikt laut Haferkamp
		// GlobaleSuche.addSearchLinkToInputWithName("<%=Probeneingang.PROJEKTVERTRAGNUMMER%>",
		// 	[
		// 		{
		// 			"category": "projekte",
		// 			"parameter": "id",
		// 			"value": ""
		// 		},
		// 		{
		// 			"category": "partner",
		// 			"parameter": "name",
		// 			"value": ""
		// 		}
		// 	]
		// );



	</script>

</body>

</html>