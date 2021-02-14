<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title></title>
  <link rel="stylesheet" href="eingangsanalytik.css">

</head>
<body>
<div id="eingangsanalyse_container">
	<form>
    <div class="eingangsanalyse_main_header">Eingangsanalytik</div>
    <div class="eingangsanalyse_header">No/ID</div>
    <div class="eingangsanalyse_entry"><input type="text"></div>
    <div class="eingangsanalyse_header">Screening No</div>
    <div class="eingangsanalyse_entry"><input type="text"></div>
    <div class="eingangsanalyse_header">Planung erfolgt durch</div>
    <div class="eingangsanalyse_entry"><input type="text"></div>
    <div class="eingangsanalyse_header">Substanz</div>
    <div class="eingangsanalyse_entry">
      <select required>
        <option value="" selected disabled>bitte auswaehlen</option>
        <option value="">test</option>
      </select>
    </div>
    <div class="eingangsanalyse_header">API/Startmaterial ref-Code</div>
    <div class="eingangsanalyse_entry"><input type="text"></div>
    <div class="eingangsanalyse_header">Projektleiternotiz</div>
    <div class="eingangsanalyse_entry"><textarea rows="4" cols="50"></textarea></div>
    <div class="eingangsanalyse_header">Verweis</div>
    <div class="eingangsanalyse_entry">
      <select required>
        <option value="" selected disabled>bitte auswaehlen</option>
        <option value="">test</option>
      </select>
    </div>
    <div class="eingangsanalyse_header">Startfreigabe (ab)</div>
    <div class="eingangsanalyse_entry"><input type="date"></div>
    <div class="eingangsanalyse_header">erledigt bis (soll)</div>
    <div class="eingangsanalyse_entry"><input type="date"></div>
    <div class="eingangsanalyse_header">Hinweis an den Laborleiter</div>
    <div class="eingangsanalyse_entry"><textarea rows="4" cols="50"></textarea></div>
    <div class="eingangsanalyse_header">Planung abgeschlossen</div>
    <div class="eingangsanalyse_entry">
      <select required>
        <option value="" selected>nein</option>
        <option value="">ja</option>
      </select>
    </div>
    <div class="eingangsanalyse_header">Sicherheitshinweis</div>
    <div class="eingangsanalyse_entry"><input type="text"></div>

    <div class="eingangsanalyse_header">Methoden:
      <input type="button" value="+ Methode hinzufuegen" onclick="addEingangsanalytikMethode()" id="eingangsanalyse_methode_add_button">
      <div id="eingangsanalyse_methoden_list"></div>
    </div>
	</form>
</div>

  <script  src="eingangsanalytik.js"></script>
  <script>
  	$("#eingangsanalyse_methode_add_button").click();  
  </script>

</body>
</html>
