<%@page import="controller.testServlets.FileuploadTestServlet"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title></title>
<!-- <link rel="stylesheet" href="projekt_erstellen.css"> -->
<script src="jquery-3.5.1.js"></script>
<style>
</style>
</head>
<body>
	<h3>File Upload:</h3>
	Select a file to upload:
	<br />
	<form id="form_probeneingang">
		<input type="file" name="probeneingang_bilder" accept="image/*" onchange="loadFile(event)" multiple>
		<br />
		<input type="text" name="filelabel" size="12" maxlength="32" />
		<br />
		<input type="submit" value="Speichern" />
	</form>
	<div id="preview-container"></div>
</body>

<script>
	const form = document.querySelector('#form_probeneingang');
	form.addEventListener('submit', function(e) {
		e.preventDefault();
		var formData = new FormData(form);

		fetch("http://localhost:8080/2020_LIMS/fileupload-test", {
			method: "post",
			body: formData
		})
		.then( response => {
			console.log("Success");
		})
		.catch(error => {
			console.log("Error", error);
		})

	}, false);

	function loadFile(event) {

		$("#preview-container").empty();

		for (let i = 0; i < event.target.files.length; i++) {

			var objectURL = URL.createObjectURL(event.target.files[i]);
			$("#preview-container").append("<img class='preview-image' style='width:10%' src=" + objectURL + ">")
			$(".preview-image").attr({
				onload : "freeMemory(this);"
			});
		}
	};

	function freeMemory(element) {
		URL.revokeObjectURL(element.src);
	}
</script>
</html>