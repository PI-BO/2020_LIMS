<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>


<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Projekte</title>
<style type="text/css">
body {
/* 	background-image: url('https://cdn.crunchify.com/bg.png'); */
}
</style>
<link rel="stylesheet" href="projekte.css">
</head>

<body>
	<div align="left">
		<br>
		<br>

		<table>
			<tr>
				<th style="width: 50%">Projekte</th>
			</tr>

		<%@ page import="model.ProjekteIdList"%>
		
		<%
// 			ProjekteIdList projekte = (ProjekteIdList) request.getAttribute("projekte");
			ProjekteIdList projekte = new ProjekteIdList();
		
			ProjekteIdList sessionList = (ProjekteIdList) request.getSession().getAttribute("liste");
			
			if(sessionList != null){
				
				for(String projektId : sessionList.getProjekteIdList()){
					
					System.out.println(projektId);
				}
			}
			
			
			
			if (projekte != null){
				
				for(String projektId : projekte.getProjekteIdList()){
					
					%>
					<tr>
						<td><%=projektId %></td>
					</tr>
					<%
				}
			}
		%>
		</table>

		<ul id="myUL">
			<li><span class="caret">Beverages</span>
				<ul class="nested">
					<li>Water</li>
					<li>Coffee</li>
					<li><span class="caret">Tea</span>
						<ul class="nested">
							<li>Black Tea</li>
							<li>White Tea</li>
							<li><span class="caret">Green Tea</span>
								<ul class="nested">
									<li>Sencha</li>
									<li>Gyokuro</li>
									<li>Matcha</li>
									<li>Pi Lo Chun</li>
								</ul></li>
						</ul></li>
				</ul></li>
		</ul>


	</div>
</body>

<script>
	var toggler = document.getElementsByClassName("caret");
	var i;

	for (i = 0; i < toggler.length; i++) {
		toggler[i].addEventListener("click", function() {
			this.parentElement.querySelector(".nested").classList
					.toggle("active");
			this.classList.toggle("caret-down");
		});
	}
</script>

</html>