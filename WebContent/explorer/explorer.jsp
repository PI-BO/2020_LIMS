<!DOCTYPE html>
<%@page import="config.Address" %>
<html>
<head>
    <link rel="stylesheet" href="<%=Address.getExplorerCSS()%>"/>
</head>
<body>
<div id="explorer-grid-container">
    <div id="explorer-header"></div>
    <div id="explorer-navigation-arrows"></div>
    <div id="explorer-navigation-tree">
    </div>
    <div id="explorer-content"></div>
</div>

<ul id="contextMenu" hidden
    style='position: absolute;top: 0;left: 0;z-index: 100;background-color: white;border: 1px solid black;border-radius: 10px; padding: 10px;list-style-type: none;'>
    <li>
        <button id="editProject">Edit</button>
    </li>
</ul>
</body>

</html>
