<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="config.Address"%>
<%@page import="model.database.tableModels.Partner"%>
<%@page import="model.database.tableModels.Projekt"%>
<%@page import="model.database.tableModels.Probe"%>
<%@page import="model.database.tableModels.experimente.Experiment"%>
<%@page import="model.database.tableModels.analyse.Analyse"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>JSTree</title>
<style>
.demo {
	overflow: scroll;
	scrollbar-width: thin;
	border: 1px solid silver;
	height: 100%;
	min-height: 100px;
	text-align: initial;
}

/* Dropdown Button */
.dropbtn {
	color: black;
	font-size: 20px;
	border: none;
}

/* The container <div> - needed to position the dropdown content */
.dropdown {
	position: relative;
	display: inline-block;
}

/* Dropdown Content (Hidden by Default) */
.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	width: max-content;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

/* Links inside the dropdown */
.dropdown-content label {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

/* Show the dropdown menu on hover */
.dropdown:hover .dropdown-content {
	display: block;
}
</style>
<link rel="stylesheet" href="jstree/style.min.css" />
</head>
<script>
</script>
<body>
	<div style="display: flex; flex-direction: column; justify-content: space-around; height: 100%; width: 100%">
		<!--     <button id="evts_button" style="margin:0em auto 1em auto; display:block; padding:4px; border-radius:4px;"> -->
		<!--         select node Projekt A -->
		<!--     </button> -->

		<div>
			<input type="text" id="search" value="" class="input" style="margin: 1em auto 1em auto; padding: 4px; border-radius: 4px; border: 1px solid silver;">
			<div class="dropdown">
				<button class="dropbtn">&#9881</button>
				<div class="dropdown-content">
					<table>
						<tr>
							<th>Partner</th>
							<td>
								<label><input type="checkbox" id="Partner:${Partner.COLUMN_PRIMARY_KEY}" class="checkbox" checked="true">Vertragsnummer</label>
							</td>
						</tr>
						<tr>
							<th>Projekte</th>
							<td>
								<label><input type="checkbox" id="Projekt:${Projekt.COLUMN_PRIMARY_KEY}" class="checkbox" checked="true">id</label>
							</td>
							<td>
								<label><input disabled type="checkbox" id="Projekt:${Projekt.COLUMN_VERTRAGSNUMMER}" class="checkbox">Vertragsnummer</label>
							</td>
						</tr>
						<tr>
							<th>Proben</th>
							<td>
								<label><input type="checkbox" id="Probe:${Probe.COLUMN_PRIMARY_KEY}" class="checkbox" checked="true">id</label>
							</td>
							<td>
								<label><input disabled type="checkbox" id="Probe:${Probe.COLUMN_PROJEKT_ID}" class="checkbox">Projekt id</label>
							</td>
						</tr>
						<tr>
							<th>Experimente</th>
							<td>
								<label><input type="checkbox" id="Experiment:${Experiment.COLUMN_PRIMARY_KEY}" class="checkbox" checked="true">id</label>
							</td>
							<td>
								<label><input disabled type="checkbox" id="Experiment:${Experiment.COLUMN_PROBEN_NR}" class="checkbox">Proben Nr</label>
							</td>
							<td>
								<label><input disabled type="checkbox" id="Experiment:${Experiment.COLUMN_TYP}" class="checkbox">Experiment Typ</label>
							</td>
						<tr>
							<th>Analysen</th>
							<td>
								<label><input type="checkbox" id="Analyse:${Analyse.COLUMN_PRIMARY_KEY}" class="checkbox" checked="true">id</label>
							</td>
							<td>
								<label><input disabled type="checkbox" id="Analyse:${Analyse.COLUMN_EXPERIMENT_ID}" class="checkbox">Experiment ID</label>
							</td>
							<td>
								<label><input disabled type="checkbox" id="Analyse:${Analyse.COLUMN_TYP}" class="checkbox">Analyse Typ</label>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div id="lazy" class="demo"></div>
	</div>

</body>
</html>
