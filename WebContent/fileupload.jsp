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
		<input type="hidden" id="probeneingang_url" value=<%=FileuploadTestServlet.ROUTE%>>
		<input type="file" name="probeneingang_bilder" accept="image/*" onchange="loadFile(event)" multiple> 
		<br />
		<input type="submit" value="Speichern" />
	</form>
	<div id="preview-container"></div>
</body>

<script>
	
	function loadFile(event) {
		
		$("#preview-container").empty();
		
		for(let i = 0; i < event.target.files.length; i++){
			
			var objectURL = URL.createObjectURL(event.target.files[i]);
			$("#preview-container").append("<img class='preview-image' style='width:10%' src=" + objectURL + ">")
			$(".preview-image").attr({onload : "freeMemory(this);"});
		}
	};
	
	function freeMemory(element){
		URL.revokeObjectURL(element.src);
	}
	
	$('#form_probeneingang').submit(function(e) {
		e.preventDefault();
		
		$.ajax({
			url : "http://localhost:8080/2020_LIMS" + $("#probeneingang_url").val(),
			type : 'POST',
			data : new FormData(this),
			processData : false,
			contentType : false,
			xhrFields : {
				responseType : 'blob'
			},
			success : function(data) {

				var blobData = data;
				var url = window.URL || window.webkitURL;
				var src = url.createObjectURL(blobData);
				$('#result-image').attr("src", src);
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});
	});
</script>
</html>