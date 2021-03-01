<!DOCTYPE html>
<%@page import="config.Address"%>
<html>
<head>
<meta charset="UTF-8" />
<title>JSTree</title>
<style>
.demo {
	overflow: scroll;
	scrollbar-width: thin;
	border: 1px solid silver;
	height: 100%;
	min-height: 100px;
	text-align: initial;
}
</style>
<link rel="stylesheet" href="jstree/style.min.css" />
<script src="jstree/jstree.min.js"></script>
</head>

<body>
	<div style="display: table; height: 100%; width: 100%">
		<!--     <button id="evts_button" style="margin:0em auto 1em auto; display:block; padding:4px; border-radius:4px;"> -->
		<!--         select node Projekt A -->
		<!--     </button> -->

		<input type="text" id="search" value="" class="input" style="margin: 1em auto 1em auto; display: block; padding: 4px; border-radius: 4px; border: 1px solid silver;">
		<div style="display: table-row; height: 100%">
			<div id="lazy" class="demo"></div>
		</div>
	</div>

	<script>
    // interaction and events
    $('#evts_button').on("click", function () {
        var instance = $('#lazy').jstree(true);
        instance.deselect_all();
        instance.select_node('projekte:A');
    });

    $('#lazy').jstree({
        core: {
            data: {
                url: function (node) {
                    console.log("call: " + node.id)
                    return 'jstree/nodes?id=' + node.id;
                }
            }
        },
        search: {
            // search config
            show_only_matches: true,
            ajax: {
                url: "jstree/search"
            }
        },
        plugins: ['search'],
    }).on("select_node.jstree", function (e, data) {
        loadNode(data);
    }).on("open_node.jstree", function (e, data) {
        data.instance.set_icon(data.node, "symbol_folder_open");
    }).on("close_node.jstree", function (e, data) {
        data.instance.set_icon(data.node, "symbol_folder_closed");
    });

    var to = false;
    $('#search').keyup(function () {
        if (to) {
            clearTimeout(to);
        }
        to = setTimeout(function () {
            let v = $('#search').val();
            $('#lazy').jstree(true).search(v);
        }, 250);
    });

    function loadNode(data) {
        let url;
        const nodeCategory = data.node.id.split(":")[0];
		let path = createPath(data);
		
        if (nodeCategory == "j1_1") {	//Projekte Node
             url = "<%=Address.getProjekteListJSP()%>";
        } else if (nodeCategory == "projekte") {
        	url = "<%=Address.getProjektJSP()%>";
        } else {
            return;
        }

        var posting = $.post(url, {projekt_id: data.node.text});
        
        posting.done(function (returnedData) {
            $("#explorer-content").empty().append(returnedData);
        });
    }
    
    function createPath(data){
    	
		let i = 0;
		let path = data.node.text;
		for(i = 0; i < data.node.parents.length;  i++){
			
			id = data.node.parents[i];
			anchorElement = document.getElementById(id + "_anchor");
			if(anchorElement == null ) break;
			path = anchorElement.textContent + " / " + path;
			
		};
		return path;
    }

</script>
</body>
</html>
