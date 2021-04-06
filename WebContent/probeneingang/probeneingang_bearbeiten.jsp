<%@page import="config.Address" %>
<%@page import="model.Probeneingang" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Probeneingang bearbeiten</title>
    <!-- <link rel="stylesheet" href="projekt_erstellen.css"> -->
    <style>
        input:required {
            border-style: solid;
            border-color: red;
            border-width: 2px;
        }

        #table_probeneingang {
            border-spacing: 0;
            background-color: white;
            width: auto;
        }

        #table_probeneingang tr:nth-child(even) {
            background-color: #dddddd;
        }

        #table_probeneingang th {
            text-align: left;
            padding: 4px;
        }

        #table_probeneingang td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        .table_in_table_header {
            border-right: 1px solid white;
        }

        #table_probeneingang input {
            min-width: 300px;
            max-width: auto;
        }

        .table_in_table {
            border-collapse: collapse;
            max-width: auto;
        }

        .table_in_table td {
            min-width: auto;
        }

        #table_image_upload td {
            border: 0px solid #dddddd;
        }

        .image-container {
            /* 			border: 2px solid #dddddd;	 */
            float: left;
            width: 25%;
            margin: 5px;
        }

        .tooltip {
            position: relative;
            display: inline-block;
            /*   color: #0000EE; */
            /*   border-bottom: 1px solid #0000EE; */
        }

        .tooltip .tooltiptext {
            visibility: hidden;
            /*   height: 1em; */
            min-width: 20em;
            width: auto;
            background-color: black;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 10px;
            position: absolute;
            z-index: 1;
            top: -5px;
            left: 110%;
        }

        .tooltip:hover {
            cursor: help;
        }

        .tooltip a:hover {
            cursor: help;
        }

        .tooltip .tooltiptext::after {
            content: " ";
            position: absolute;
            top: 50%;
            right: 100%;
            /* To the left of the tooltip */
            margin-top: -5px;
            border-width: 5px;
            border-style: solid;
            border-color: transparent black transparent transparent;
        }

        .tooltip:hover .tooltiptext {
            visibility: visible;
        }
    </style>
    <script src="<%=Address.getProbeneingangJS()%>"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#form_probeneingang_bearbeiten').submit(function () {
                $.ajax({
                    url: '<%=Address.getProbeneingangBearbeitenServlet()%>',
                    type: 'post',
                    data: $(this).serialize(),
                    success: function () {
                        replaceContent("button_probeneingang_update", "Erfolgreich gespeichert", "green");
                    },
                    error: function (xhr, status, error) {
                        replaceContent("button_probeneingang_update", "Fehler: " + xhr.responseText, "red");
                    }
                });
                return false;
            });
        });
    </script>
</head>

<body>
<form id="form_probeneingang_bearbeiten">
    <table id="table_probeneingang">
        <tr style="background-color: #77bbff;">
            <th style="background-color: #77bbff; padding: 16px;">Probeneingang</th>
        </tr>

        <tr>
            <th id="probenIdTooltip">
                Proben ID
            </th>
        </tr>
        <tr>
            <td>
                <input required type="text" id="proben_id_input_field" name=<%=Probeneingang.PROBEN_ID%>>
            </td>
        </tr>

        <tr>
            <th style="text-align: center" id="button_probeneingang_speichern">
                <button disabled id="button_probeneingang_update" type="submit">Speichern</button>
                <input required type="checkbox" id="acknowledge_probeneingang_update" onclick="enableSaveButton(this)">
                <i>Die bestehende Probe wird mit den neuen werten berschrieben!</i>
            </th>
        </tr>

        <tr>
            <th style="text-align: center" id="probeneingang_erstellen_save_message"></th>
        </tr>

    </table>
</form>
<script>
    document.getElementById('proben_id_input_field').addEventListener('change', function (e) {
        $.ajax({
            url: '<%=Address.getProbeneingangBearbeitenServlet()%>',
            type: 'get',
            data: {id: e.target.value},
            success: function (data) {
                const probeneingangContent = $.post("probeneingang/probeneingang.jsp");
                probeneingangContent.done(function (post) {
                    $("#table_probeneingang tr").eq(2).after(post).ready(function () {
                        $(this).children().slice(0,2).remove();
                        for (let key in data) {
                            const nodeList = document.getElementsByName(key)
                            const val = data[key]
                            for (let i = 0; i < nodeList.length; i++) {
                                nodeList[i].value = val
                                nodeList[i].disabled = false
                            }
                        }
                    });
                });
            },
            error: function (xhr, status, error) {
                alert("Fehler: " + xhr.responseText);
            }
        });
    });

    function enableSaveButton(param) {
        $("#button_probeneingang_update").prop("disabled", !param.checked)
    }

    // reset preview pictures when reset button has been pressed
    $("#input_image_reset_button").on("click", function () {
        $("#preview-container").empty();
        $("#input_image_upload").val("");
    });
</script>
</body>
</html>