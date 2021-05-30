<%@page import="controller.servlets.analyse.EingangsanalytikServlet"%>
<%@page import="config.Address"%>
<%@page import="model.EingangsanalyseSetterEnum"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=Address.getEingangsAnalytikCSS()%>">
<script src="<%=Address.getEingangsAnalytikJS()%>"></script>
</head>
<body>
	<div id="eingangsanalyse_container">
		<form id="form_eingangsanalyse">
			<input type="hidden" id="eingangsanalyse_url" value=<%=EingangsanalytikServlet.ROUTE%>>
			<div class="eingangsanalyse_main_header">Eingangsanalytik</div>
			<div class="eingangsanalyse_header">No/ID</div>
			<div class="eingangsanalyse_entry">
				<input type="text" name=<%=EingangsanalyseSetterEnum.NO_ID%>>
			</div>
			<div class="eingangsanalyse_header">Screening No</div>
			<div class="eingangsanalyse_entry">
				<input type="text" name=<%=EingangsanalyseSetterEnum.SCREENING_NO%>>
			</div>
			<div class="eingangsanalyse_header">Planung erfolgt durch</div>
			<div class="eingangsanalyse_entry">
				<input type="text" name=<%=EingangsanalyseSetterEnum.PLANUNG_ERFOLGT_DURCH%>>
			</div>
			<div class="eingangsanalyse_header">Substanz</div>
			<div class="eingangsanalyse_entry">
				<select <%--required --%> name=<%=EingangsanalyseSetterEnum.SUBSTANZ%>>
					<option value="" selected disabled>bitte auswaehlen</option>
					<option value="test">test</option>
				</select>
			</div>
			<div class="eingangsanalyse_header">API/Startmaterial ref-Code</div>
			<div class="eingangsanalyse_entry">
				<input type="text" name=<%=EingangsanalyseSetterEnum.API_STARTMATERIAL_REF_CODE%>>
			</div>
			<div class="eingangsanalyse_header">Projektleiternotiz</div>
			<div class="eingangsanalyse_entry">
				<textarea rows="4" cols="50" name=<%=EingangsanalyseSetterEnum.PROJEKTLEITERNOTIZ%>></textarea>
			</div>
			<div class="eingangsanalyse_header">Verweis</div>
			<div class="eingangsanalyse_entry">
				<select <%--required --%> name=<%=EingangsanalyseSetterEnum.VERWEIS%>>
					<option value="" selected disabled>bitte auswaehlen</option>
					<option value="test">test</option>
				</select>
			</div>
			<div class="eingangsanalyse_header">Startfreigabe (ab)</div>
			<div class="eingangsanalyse_entry">
				<input type="date" name=<%=EingangsanalyseSetterEnum.STARTFREIGABE%>>
			</div>
			<div class="eingangsanalyse_header">erledigt bis (soll)</div>
			<div class="eingangsanalyse_entry">
				<input type="date" name=<%=EingangsanalyseSetterEnum.ERLEDIGT_BIS_SOLL%>>
			</div>
			<div class="eingangsanalyse_header">Hinweis an den Laborleiter</div>
			<div class="eingangsanalyse_entry">
				<textarea rows="4" cols="50" name=<%=EingangsanalyseSetterEnum.HINWEIS%>></textarea>
			</div>
			<div class="eingangsanalyse_header">Planung abgeschlossen</div>
			<div class="eingangsanalyse_entry">
				<select name=<%=EingangsanalyseSetterEnum.PLANUNG_ABGESCHLOSSEN%>>
					<option value="nein" selected>nein</option>
					<option value="ja">ja</option>
				</select>
			</div>
			<div class="eingangsanalyse_header">Sicherheitshinweis</div>
			<div class="eingangsanalyse_entry">
				<input type="text" name=<%=EingangsanalyseSetterEnum.SICHERHEITSHINWEIS%>>
			</div>

			<div class="eingangsanalyse_header">
				Methoden:
				<input type="button" value="+ Methode hinzufuegen" onclick="addEingangsanalytikMethode()" id="eingangsanalyse_methode_add_button">
				<div id="eingangsanalyse_methoden_list"></div>
			</div>

			<div class="eingangsanalyse_entry">
				<button type="submit">Speichern</button>
			</div>
		</form>

		<!-- HIDDEN TEMPLATE FOR addEingangsanalytikMethode() -->

		<div id="template_methode" style="display: none;">
			<div>
				<div class="eingangsanalyse_methode_row eingangsanalyse_methode_main_row">

					<div class="eingangsanalyse_methode_column">
						<div class="eingangsanalyse_main_header">
							<select name=<%=EingangsanalyseSetterEnum.METHODE%>>
								<option value="" selected disabled>bitte auswaehlen</option>
								<option value="PXRD D2">PXRD D2</option>
								<option value="PXRD D8">PXRD D8</option>
								<option value="DSC">DSC</option>
								<option value="TG">TG</option>
								<option value="IT">IT</option>
								<option value="1H-NMR">1H-NMR</option>
							</select>
						</div>
					</div>
					<div class="eingangsanalyse_methode_delete_button">
						<input type="button" value=" entfernen " onclick="deleteEingangsanalytikMethode(this)">
					</div>
				</div>

				<div class="eingangsanalyse_methode_row">
					<div class="eingangsanalyse_methode_column">
						<div class="eingangsanalyse_header">Auswahl</div>
						<div class="eingangsanalyse_entry">
							<select name=<%=EingangsanalyseSetterEnum.AUSWAHL%>>
								<option value="nein" selected>nein</option>
								<option value="ja">ja</option>
							</select>
						</div>
					</div>

					<div class="eingangsanalyse_methode_column">
						<div class="eingangsanalyse_header">Operator</div>
						<div class="eingangsanalyse_entry">
							<input type="text" name=<%=EingangsanalyseSetterEnum.OPERATOR%>>
						</div>
					</div>

					<div class="eingangsanalyse_methode_column">
						<div class="eingangsanalyse_header">Parameter</div>
						<div class="eingangsanalyse_entry">
							<input type="text" value="Standard" name=<%=EingangsanalyseSetterEnum.PARAMETER%>>
						</div>
					</div>

					<div class="eingangsanalyse_methode_column">
						<div class="eingangsanalyse_header">Messfile</div>
						<div class="eingangsanalyse_entry">
							<input type="text" name=<%=EingangsanalyseSetterEnum.MESSFILE%>>
						</div>
					</div>

					<div class="eingangsanalyse_methode_column">
						<div class="eingangsanalyse_header">Status</div>
						<div class="eingangsanalyse_entry">
							<select name=<%=EingangsanalyseSetterEnum.STATUS%>>
								<option value="offen" selected>offen</option>
								<option value="abgeschlossen">abgeschlossen</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END TEMPLATE -->
	</div>

	<script>
		
	  	$("#eingangsanalyse_methode_add_button").click();

	  	initFormHandler();
	  	
	  	function initFormHandler(){
	  		
	  		let form = document.querySelector('#form_eingangsanalyse');
			form.addEventListener('submit', function(e) {
				e.preventDefault();
	
				let url = "<%=Address.getMainPath()%>" + document.querySelector("#eingangsanalyse_url").value;
	
				let submitData = {
						Methoden : []
				};
				
				let name;
				let value;
				const parameterMethode = "<%=EingangsanalyseSetterEnum.METHODE%>";
	
				// JSON mit FormData fuellen bis die Methoden kommen
				let i = 0;
				for (i; i < e.target.length; i++) {
	
					name = e.target[i].name;
					value = e.target[i].value;
	
					console.log(name, value);
	
					// break wenn erste Methode kommt
					if (name == parameterMethode) break;
	
					submitData[name] = value;
				}
	
				// erste Methode als JSON erstellen
				let methode = {};
				methode[name] = value;
				i++;
	
				// restlichen Daten der Methode in methode-JSON fuellen
				for (i; i < e.target.length; i++) {
	
					name = e.target[i].name;
					value = e.target[i].value;
	
					console.log(name, value);
	
					// naechste Methode erreicht, also aktuelle Methode ins Array pushen und methode-JSON neu instaziieren
					if (name == parameterMethode) {
						submitData["Methoden"].push(methode);
						methode = {};
						console.log("methode nach push:", methode);
						methode[name] = value;
	
					} else {
						methode[name] = value;
					}
				}
	
				// letzes methode-JSON in Array pushen
				submitData["Methoden"].push(methode);
	
				console.log(submitData);
	
				fetch(url, {
					method : "post",
					headers : {
						'Content-Type' : 'application/json'
					},
					body : JSON.stringify(submitData)
				})
	
			}, false);
	  	}
	  	

	</script>
</body>
</html>
