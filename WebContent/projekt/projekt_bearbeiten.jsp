<%@page import="model.database.tableModels.Partner"%>
<%@page import="config.Address" %>
<%@page import="model.database.tableModels.Projekt" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <title>Solid-Chem | LIMS - Insert Projekt</title>
    <!-- <link rel="stylesheet" href="projekt/projekt_erstellen.css"> -->
    <style>
        #create_projekt_table {
            border: 1px solid #ddd;
            padding: 10px;
            padding-left: 30px;
            padding-right: 30px;
            display: inline-block;
            background-color: white;
        }

        #th_speichern {
            padding-top: 10px;
        }

        input:required {
            border-style: solid;
            border-color: red;
            border-width: 2px;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #77bbff;
            border: 2px solid #77bbff;
            min-width: 10px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        /* Links inside the dropdown */
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        /* Change color of dropdown links on hover */
        .dropdown-content a:hover {
            background-color: #ddd
        }

        /* Show the dropdown menu (use JS to add this class to the .dropdown-content container when the user clicks on the dropdown button) */
        .show {
            display: block;
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
    <script type="text/javascript">
        $(document).ready(function () {
            $('#form_projekt_bearbeiten').submit(function () {
                $.ajax({
                    url: '<%=Address.getProjektBearbeitenServlet()%>',
                    type: 'post',
                    data: $(this).serialize(),
                    success: function () {
                        replaceContent("button_projekt_update", "Erfolgreich gespeichert", "green");
                    },
                    error: function (xhr, status, error) {
                        replaceContent("button_projekt_update", "Fehler: " + xhr.responseText, "red");
                    }
                });
                return false;
            });
        });
    </script>
</head>

<body>
<form id="form_projekt_bearbeiten">
    <table id="create_projekt_table">
        <tr>
            <th colspan=4>
                <h1>Projekt bearbeiten</h1>
            </th>
        </tr>
        <tr>
            <th>Projekt Informationen</th>
        </tr>
        <tr>
            <td>Projekt ID</td>
            <td>
                <input required id="projekt_id_input_field" class="drop_down_field" type=text placeholder="*"
                       name=<%=Projekt.COLUMN_PRIMARY_KEY%>>
            </td>
        </tr>
        <tr>
            <td>Projektpartner ID</td>
            <td>
                <input disabled required id="partner_id_input_field" class="drop_down_field" type=text placeholder="*"
                       name=<%=Projekt.COLUMN_PROJEKTPARTNER%>>
            </td>
        </tr>
        <tr>
            <td>Vertragsnummer</td>
            <td>
                <input disabled id="vertragsnummer_input_field" type=text placeholder="" name=<%=Projekt.COLUMN_VERTRAGSNUMMER%>>
            </td>
        </tr>
        <tr>
            <th id="th_speichern" colspan=4>
                <button disabled id="button_projekt_update" type="submit">Speichern</button>
                <input required type="checkbox" id="acknowledge_projekt_update" onclick="enableSaveButton(this)">
                <i>Das bestehende Projekt wird mit den neuen werten berschrieben!</i>
            </th>
        </tr>
        <tr>
            <th id="projekt_erstellen_save_message" colspan=4></th>
        </tr>
    </table>
</form>
</body>

<script>
    document.getElementById('projekt_id_input_field').addEventListener('change', function (e) {
        $.ajax({
            url: '<%=Address.getProjektBearbeitenServlet()%>',
            type: 'get',
            data: {id: e.target.value},
            success: function (data) {
                if (data)
                    for (let key in data) {
                        const nodeList = document.getElementsByName(key)
                        const val = data[key]
                        for (let i = 0; i < nodeList.length; i++) {
                            nodeList[i].value = val
                            nodeList[i].disabled = false
                        }
                    }
                else {
                    const name = document.getElementById('partner_id_input_field')
                    name.value = ''
                    name.disabled = true
                    const email = document.getElementById('vertragsnummer_input_field')
                    email.value = ''
                    email.disabled = true
                }
            },
            error: function (xhr, status, error) {
                alert("Fehler: " + xhr.responseText);
            }
        });
    });

    function enableSaveButton(param) {
        $("#button_projekt_update").prop("disabled", !param.checked)
    }

    // Such-Links
		// GlobaleSuche.addSearchLinkToInputWithName("<%=Partner.COLUMN_NAME%>",
		// 	[
		// 		new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, ""),
		// 		new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, "")
		// 		// new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, () => document.getElementsByName("<%=Partner.COLUMN_NAME%>")[0].value)
		// 	],
		// 	returnParameter = new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME)
		// );

		GlobaleSuche.addSearchLinkToInputWithName("<%=Projekt.COLUMN_PRIMARY_KEY%>",
			[
				new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, "")
			], 
			returnParameter = new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, "")
		);

		function projektErstellenInit(){

			let projektPartnerInput = document.getElementsByName("<%=Partner.COLUMN_NAME%>")[0];
			projektPartnerInput.value = MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.NAME];
		}

		projektErstellenInit();
</script>

</html>