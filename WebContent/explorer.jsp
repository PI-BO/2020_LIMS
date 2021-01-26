<!DOCTYPE html>
<html>
<head>

<style>


#explorer-grid-container {
  display: grid;
  grid-template-areas:
    'arrow-navi header header'
    'tree-navi content content'
    'tree-navi content content';
  grid-template-rows: 1fr 6fr;
  grid-template-columns: 1fr 5fr;
  grid-gap: 2px;
  background-color: #f2f2f2;
}

#explorer-grid-container > div {
  background-color: rgba(255, 255, 255, 0.8);
  text-align: center;
/* 	align-self: start; */
}

#explorer-header {
	grid-area: header;
	padding : 20px;
}

#explorer-navigation-arrows{
	grid-area: arrow-navi;
	padding : 20px;
}

#explorer-navigation-tree {
	grid-area: tree-navi;
}

#explorer-content {
	grid-area: content;
}

</style>
</head>
<body>
<div id="explorer-grid-container">
	<div id="explorer-header">Pfad</div>
	<div id="explorer-navigation-arrows"> Nav-Pfeile</div>
	<div id="explorer-navigation-tree">
		Baum
		<%@ include file = "navigation_menu_full_tree.html" %>
	</div>
	<div id="explorer-content">Content</div>  
</div>

</body>
</html>
