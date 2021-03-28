<%@page import="config.Address" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Solid-Chem | LIMS</title>

    <script src="jquery-3.5.1.js"></script>

    <link rel="stylesheet" href="<%=Address.getMainCSS()%>">
    <link rel="stylesheet" id="google-font-api-special-1-css"
          href="https://fonts.googleapis.com/css?family=Roboto%3A100italic%2C200italic%2C300italic%2C400italic%2C500italic%2C600italic%2C700italic%2C800italic%2C900italic%2C100%2C200%2C300%2C400%2C500%2C600%2C700%2C800%2C900&amp;ver=5.4.4"
          type="text/css" media="all">
    <link rel="stylesheet" id="google-font-api-special-2-css"
          href="https://fonts.googleapis.com/css?family=Lato%3A100italic%2C200italic%2C300italic%2C400italic%2C500italic%2C600italic%2C700italic%2C800italic%2C900italic%2C100%2C200%2C300%2C400%2C500%2C600%2C700%2C800%2C900&amp;subset=latin&amp;ver=5.4.4"
          type="text/css" media="all">
</head>

<body>
	<div id="main-container">
		<div id="main-header">
			<img src="https://solid-chem.de/wp-content/uploads/2017/11/solid_chem_logo_head-kopie.png">
		</div>
		<div id="main-menu">
			<%-- 				<%@ include file = "/navigation_menu.jsp" %> --%>
			<jsp:include page="<%=Address.getNavigationMenuRelativeJSP()%>" />
		</div>
		<div id="main-content">
			<div id="main-content-explorer" style="display: none"></div>
			<div id="main-content-input-masks" style="display: none"></div>
			<div id="main-content-global-search" style="display: none"></div>
		</div>
	</div>
</body>
<script src="Main.js"></script>
<script type="text/javascript">

Main.initSubpages(
		{		
			"<%=Address.getGlobaleSucheJsp()%>" : "#main-content-global-search",
			"<%=Address.getExplorerJSP()%>" : "#main-content-explorer"
		}
	);

</script>
</html>