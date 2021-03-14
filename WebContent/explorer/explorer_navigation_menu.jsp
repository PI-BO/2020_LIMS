<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="config.Address" %>
<%@page import="model.database.tableModels.Partner" %>
<%@page import="model.database.tableModels.Projekt" %>
<%@page import="model.database.tableModels.Substanz" %>
<%@page import="model.database.tableModels.Probe" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
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

        /* Dropdown Button */
        .dropbtn {
            color: black;
            font-size: 20px;
            border: none;
        }

        /* The container <div> - needed to position the dropdown content */
        .dropdown {
            position: relative;
            display: inline-block;
        }

        /* Dropdown Content (Hidden by Default) */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            width: max-content;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        /* Links inside the dropdown */
        .dropdown-content label {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        /* Show the dropdown menu on hover */
        .dropdown:hover .dropdown-content {
            display: block;
        }
    </style>
    <link rel="stylesheet" href="jstree/style.min.css"/>
    <script src="jstree/jstree.min.js"></script>
</head>

<body>
<div style="display: table; height: 100%; width: 100%">
    <!--     <button id="evts_button" style="margin:0em auto 1em auto; display:block; padding:4px; border-radius:4px;"> -->
    <!--         select node Projekt A -->
    <!--     </button> -->

    <input type="text" id="search" value="" class="input"
           style="margin:1em auto 1em auto; padding:4px; border-radius:4px; border:1px solid silver;">
    <div class="dropdown">
        <button class="dropbtn">
            &#9881
        </button>
        <div class="dropdown-content">
            <table>
                <tr>
                    <th>Partner</th>
                    <td>
                        <label><input type="checkbox" id="Partner:${Partner.COLUMN_PRIMARY_KEY}" class="checkbox"
                                      checked="true">Vertragsnummer</label>
                    </td>
                </tr>
                <tr>
                    <th>Projekte</th>
                    <td>
                        <label><input type="checkbox" id="Projekt:${Projekt.COLUMN_PRIMARY_KEY}" class="checkbox"
                                      checked="true">id</label>
                    </td>
                    <td>
                        <label><input type="checkbox" id="Projekt:${Projekt.COLUMN_VERTRAGSNUMMER}" class="checkbox">Vertragsnummer</label>
                    </td>
                </tr>
                <tr>
                    <th>Substanzen</th>
                    <td>
                        <label><input type="checkbox" id="Substanz:${Substanz.COLUMN_PRIMARY_KEY}" class="checkbox"
                                      checked="true">id</label>
                    </td>
                    <td>
                        <label><input type="checkbox" id="Substanz:${Substanz.COLUMN_PROJEKT_ID}" class="checkbox">Projekt
                            id</label>
                    </td>
                </tr>
                <tr>
                    <th>Proben</th>
                    <td>
                        <label><input type="checkbox" id="Probe:${Probe.COLUMN_PRIMARY_KEY}" class="checkbox"
                                      checked="true">id</label>
                    </td>
                    <td>
                        <label><input type="checkbox" id="Probe:${Probe.COLUMN_SUBSTANZ_ID}" class="checkbox">Substanz
                            id</label>
                    </td>
                </tr>
            </table>
        </div>
    </div>

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
                url: "jstree/search",
                data: {
                    "Partner": ["${Partner.COLUMN_PRIMARY_KEY}"],
                    "Projekt": ["${Projekt.COLUMN_PRIMARY_KEY}"],
                    "Substanz": ["${Substanz.COLUMN_PRIMARY_KEY}"],
                    "Probe": ["${Probe.COLUMN_PRIMARY_KEY}"]
                }
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

    let to = false;
    $('#search').keyup(function () {
        if (to) {
            clearTimeout(to);
        }
        to = setTimeout(function () {
            let v = $('#search').val();
            $('#lazy').jstree(true).search(v);
        }, 250);
    });

    // Get checkbox click
    $(".checkbox").change(function () {
        const json = $('#lazy').jstree(true).settings.search.ajax.data;
        const type = this.id.split(':')[0]
        const id = this.id.split(':')[1]
        if (!json.hasOwnProperty(type)) {
            json[type] = []
        }
        if (this.checked) {
            json[type].push(id);
        } else {
            json[type].splice(id, 1);
        }
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

    function createPath(data) {

        let i = 0;
        let path = data.node.text;

        for (i = 0; i < data.node.parents.length; i++) {

            id = data.node.parents[i];
            anchorElement = document.getElementById(id + "_anchor");
            if (anchorElement == null) break;
            path = anchorElement.textContent + " / " + path;

        }

        return path;
    }

</script>
</body>
</html>
