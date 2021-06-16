<%@page import="model.Probeneingang" %>
	<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<%@page import="config.Address" %>
			<%@page import="controller.servlets.probeneingang.ProbeneingangServlet" %>

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
								<th id="probeneingangTooltip" style="background-color: #77bbff; padding: 16px;">
									Probeneingang</th>
							</tr>

							<tr>
								<th>Auftraggeber</th>
							</tr>
							<tr>
								<td>
									<input disabled type="text" id="partner_name_input_field"
										name=<%=Probeneingang.AUFTRAGGEBER%>>
								</td>
							</tr>

							<tr>
								<th>Projekt ID</th>
							</tr>
							<tr>
								<td>
									<input disabled type="text" id="projekt_id_input_field"
										name=<%=Probeneingang.PROJEKT_ID%>>
								</td>
							</tr>
							<tr>
								<th id="probenIdTooltip">Proben ID</th>
							</tr>
							<tr>
								<td>
									<input required type="text" id="probe_id_input_field"
										name=<%=Probeneingang.PROBEN_ID%>>
								</td>
							</tr>
							<tr>
								<th id="wirkstoffTooltip">Wirkstoff</th>
							</tr>
							<tr>
								<td>
									<input placeholder="in Demo noch nicht vorhanden!" type="text"
										name=<%=Probeneingang.WIRKSTOFF%>>
								</td>
							</tr>

							<tr>
								<th>Summenformel</th>
							</tr>
							<tr>
								<td>
									<input placeholder="in Demo noch nicht vorhanden!" type="text"
										name=<%=Probeneingang.SUMMENFORMEL%>>
								</td>
							</tr>

							<tr>
								<th>Bezeichung</th>
							</tr>
							<tr>
								<td>
									<input placeholder="in Demo noch nicht vorhanden!" type="text"
										name=<%=Probeneingang.BEZEICHNUNG%>>
								</td>
							</tr>

							<tr>
								<th>Originator</th>
							</tr>
							<tr>
								<td>
									<input placeholder="in Demo noch nicht vorhanden!" type="text"
										name=<%=Probeneingang.ORIGINATOR%>>
								</td>
							</tr>

							<tr>
								<th>Probeneingang</th>
							</tr>
							<tr>
								<td>
									<input disabled type="date" name=<%=Probeneingang.PROBENEINGANG%>>
								</td>
							</tr>

							<tr>
								<th>Probenmasse</th>
							</tr>
							<tr>
								<td>
									<input placeholder="in Demo noch nicht vorhanden!" type="text"
										name=<%=Probeneingang.PROBENMASSE%>>
								</td>
							</tr>

							<tr>
								<th>Besonderheiten</th>
							</tr>
							<tr>
								<td>
									<input placeholder="in Demo noch nicht vorhanden!" type="text"
										name=<%=Probeneingang.BESONDERHEITEN%>>
								</td>
							</tr>

							<tr>
								<th>Infos</th>
							</tr>
							<tr>
								<td>
									<textarea placeholder="in Demo noch nicht vorhanden!" rows="4" cols="50"
										name=<%=Probeneingang.INFOS%>></textarea>
								</td>
							</tr>

							<tr>
								<th>Bemerkungen zur Messung</th>
							</tr>
							<tr>
								<td>
									<textarea placeholder="in Demo noch nicht vorhanden!" rows="4" cols="50"
										name=<%=Probeneingang.BEMERKUNGEN_ZUR_MESSUNG%>></textarea>
								</td>
							</tr>


							<tr>
								<th>Bemerkungen</th>
							</tr>
							<tr>
								<td>
									<textarea placeholder="in Demo noch nicht vorhanden!" rows="4" cols="50"
										name=<%=Probeneingang.BEMERKUNGEN%>></textarea>
								</td>
							</tr>

							<tr>
								<th>Literatur</th>
							</tr>
							<tr>
								<td>
									<textarea placeholder="in Demo noch nicht vorhanden!" rows="4" cols="50"
										name=<%=Probeneingang.LITERATUR%>></textarea>
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
												<button type="button" id="input_image_reset_button">Bilderauswahl
													leeren</button>
												<input type="file" id="input_image_upload" name="probeneingang_bilder"
													accept="image/*"multiple>
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
				</body>

				</html>