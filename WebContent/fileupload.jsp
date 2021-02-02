<%@page import="controller.PostGetTestServlet"%>
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
	<form id="form_probeneingang" action="fileupload-test" method="post" enctype="multipart/form-data">
		<input type="file" name="file" size="1" /> <br /> <input type="submit" value="Upload File" />
	</form>
	<img id="result-image" src =""></img>
</body>

<script>

	$('#form_probeneingang').submit(function(e) {
		e.preventDefault();
		$.ajax({
			url : 'fileupload-test',
			type : 'POST',
			data : new FormData(this),
			processData : false,
			contentType : false,
			xhrFields:{
	            responseType: 'blob'
	        },
			success : function(data) {

				console.log("SUCCESS : ");

				var blobData = data;
	            var url = window.URL || window.webkitURL;
	            var src = url.createObjectURL(blobData);
	            $('#result-image').attr("src", src);
	            
	            //TODO: img Element erstellen
	            
			},
			error : function(e) {
				console.log("ERROR : ", e);
			}
		});
	});

</script>
</html>