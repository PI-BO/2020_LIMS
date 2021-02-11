<!DOCTYPE html>
<html>
<head>

<script src="explorerFunctions.js"></script>
<link rel="stylesheet" href="explorer.css">

</head>
<body>
	<div id="explorer-grid-container">
		<div id="explorer-header"></div>
		<div id="explorer-navigation-arrows"></div>
		<div id="explorer-navigation-tree">
			<%@ include file="navigation_menu_full_tree.html"%>
		</div>
		<div id="explorer-content"></div>
	</div>
</body>

<script type="text/javascript">

	let explorerState = new ExplorerState();

</script>
</html>
