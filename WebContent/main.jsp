<%@page import="config.Address"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<title>Solid-Chem | LIMS</title>
		
		<script src="jquery-3.5.1.js"></script>
		
		<link rel="stylesheet" href="<%=Address.getMainCSS()%>">
	</head>

	<body>
	
		<div id="main-container">
			<div id="main-header" ><img src="https://solid-chem.de/wp-content/uploads/2017/11/solid_chem_logo_head-kopie.png"></div>
			<div id="main-menu">
<%-- 				<%@ include file = "/navigation_menu.jsp" %> --%>
				<jsp:include page="<%=Address.getNavigationMenuRelativeJSP()%>"/>
			</div>
			<div id="main-content-explorer" style="display:none"></div>  
			<div id="main-content-input-masks"></div>  
		</div>
	</body>
</html>