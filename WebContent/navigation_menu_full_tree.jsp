<%@ page import="database.model.ProjekteIdList" %>
<%@ page import="database.relations.ProjekteSubstanz" %>
<%@ page import="database.model.Projekt" %>
<%@ page import="database.model.Substanz" %>
<%@ page import="java.util.stream.Collectors" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        html { margin:0; padding:0; font-size:62.5%; }
        body { max-width:800px; min-width:300px; margin:0 auto; padding:20px 10px; font-size:14px; font-size:1.4em; }
        h1 { font-size:1.8em; }
        .demo { overflow:auto; border:1px solid silver; min-height:100px; }
	</style>
	<link rel="stylesheet" href="jstree/style.min.css" />
	<script src="jquery-3.5.1.js"></script>
    <script src="jstree/jstree.min.js"></script>
</head>

<%
	ProjekteIdList projekte = new ProjekteIdList();
%>

<body>
	<h1>Interaction and events demo</h1>
	<button id="evts_button">select node with id 1</button> <em>either click the button or a node in the tree</em>
	<div id="evts" class="demo"></div>

	<h1>Lazy loading demo</h1>
	<div id="lazy" class="demo"></div>

	<h1>Callback</h1>
	<div id="container" class="demo"></div>

	<script>
		// interaction and events
		$('#evts_button').on("click", function () {
			var instance = $('#evts').jstree(true);
			instance.deselect_all();
			instance.select_node('hi');
		});

		$('#evts').on("changed.jstree", function (e, data) {
			if(data.selected.length) {
				alert('The selected node is: ' + data.instance.get_node(data.selected[0]).text);
			}
		}).jstree({
			'core' : {
				'multiple' : false,
				'data' : [
					{ "text" : "Root node", "children" : [
							{ "text" : "Child node hi", "id" : "hi" },
							{ "text" : "Child node 2" }
					]}
				]
			}
		});

	// lazy demo
//		$('#lazy').jstree({
//			'core' : {
//				'data' : {
//					'url' : function (node) {
//						console.log("call" + node.id)
//						return 'services?id=' + node.id
//					},
//					'data' : function (node) {
//						return {'id' : node.id}
//					}
//				}
//			}
//		})

		$('#lazy').jstree({
    		core: {
    			data: {
        			url: function (node) {
						console.log("call: " + node.id)
						return 'services?id=' + node.id
					}
        		}
   			},
		}).bind("select_node.jstree", function (e, data) {
   			console.log("select " + data.node.id)
		});

		$('#container').jstree({
    		'core' : {
        		'data' : function (node, cb) {
          			if(node.id === "#") {
              			cb({
              				"text" : "Projekte",
              				"id" : "root_node",
              				"children" : true,
              				"data" : {
                  				"uri" : "services?id=" + node.id
                			}
              			});
          			} else {
			          	cb({
    						'url' : "services?id=" + node.id,
    						'data' : function (node) {
      							return { 'id' : node.id };
    						}
  						})
		          }
      			}
    		}
    	});
	</script>
</body>
</html>
