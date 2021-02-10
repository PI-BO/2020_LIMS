<!DOCTYPE html>
<html>
<head>

<script src="explorerFunctions.js"></script>

<style>
#explorer-grid-container {
	display: grid;
	grid-template-areas: 
		'arrow-navi header' 
		'tree-navi content';
	grid-template-rows: 60px auto;
	grid-template-columns: 25% auto;
	grid-gap: 2px;
	background-color: #f2f2f2;
	height: 100%;
}

#explorer-grid-container>div {
	background-color: rgba(255, 255, 255, 0.8);
	text-align: center;
	/* 	align-self: start; */
}

#explorer-header {
	grid-area: header;
	padding: 20px;
}

#explorer-navigation-arrows {
	grid-area: arrow-navi;
	padding: 20px;
}

#explorer-navigation-tree {
	grid-area: tree-navi;
}

#explorer-content {
	grid-area: content;
	height: 560px;
}
</style>
</head>
<body>
	<div id="explorer-grid-container">
		<div id="explorer-header">Pfad</div>
		<div id="explorer-navigation-arrows">Nav-Pfeile</div>
		<div id="explorer-navigation-tree">
			<%@ include file="navigation_menu_full_tree.html"%>
		</div>
		<div id="explorer-content"></div>
	</div>
</body>

<script type="text/javascript">

	

</script>
</html>
