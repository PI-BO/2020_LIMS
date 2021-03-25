<!DOCTYPE html>
<%@page import="config.Address"%>
<html>
<head>

<link rel="stylesheet" href="<%=Address.getExplorerCSS()%>">
</head>
<body>
	<div id="explorer-grid-container">
		<div id="explorer-header"></div>
		<div id="explorer-navigation-arrows"></div>
		<div id="explorer-navigation-tree">
			<%@ include file="explorer_navigation_menu.jsp"%>
			<%-- 			<jsp:include page="<%=Address.getExplorerNavigationMenuJSP()%>"/> --%>
		</div>
		<div id="explorer-content"></div>
	</div>
</body>

<script src="<%=Address.getExplorerJS()%>"></script>
<script type="text/javascript">

	// init ExplorerState
	const explorerState = new ExplorerState();
	explorerState.setPartnerListAddress("<%=Address.getPartnerListJSP()%>");
	explorerState.setPartnerAddress("<%=Address.getPartnerJSP()%>");
	explorerState.setProjektAddress("<%=Address.getProjektJSP()%>");
	explorerState.setSubstanzAddress("<%=Address.getSubstanzJSP()%>");
	explorerState.setProbeAddress("<%=Address.getProbeJSP()%>");
	explorerState.setExperimentAddress("<%=Address.getExperimentJSP()%>")
	
</script>
</html>
