<%@page import="model.database.tableModels.Partner"%>
<%@page import="config.Address" %>
<%@page import="model.database.tableModels.Projekt" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
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
                    <input disabled required id="projekt_id_input_field" class="drop_down_field" type=text
                        placeholder="Projekt auswaehlen!" name=<%=Projekt.COLUMN_PRIMARY_KEY%>>
                </td>
            </tr>
            <tr>
                <td>Projektpartner ID</td>
                <td>
                    <input disabled required id="partner_id_input_field" class="drop_down_field" type=text
                        placeholder="*" name=<%=Projekt.COLUMN_PROJEKTPARTNER%>>
                </td>
            </tr>
            <tr>
                <td>Vertragsnummer</td>
                <td>
                    <input id="vertragsnummer_input_field" type=text 
                        name=<%=Projekt.COLUMN_VERTRAGSNUMMER%>>
                </td>
            </tr>
            <tr>
                <th id="th_speichern" colspan=4>
                    <button disabled id="button_projekt_update" type="submit">Speichern</button>
                    <input required type="checkbox" id="acknowledge_projekt_update" onclick="enableSaveButton(this)">
                    <i>Das bestehende Projekt wird mit den neuen Werten ueberschrieben!</i>
                </th>
            </tr>
            <tr>
                <th id="projekt_erstellen_save_message" colspan=4></th>
            </tr>
        </table>
    </form>
</body>

<script>



    $("#form_projekt_bearbeiten").submit(function (e) {
        e.preventDefault();

        var submitData = {};


        let projektIdInputField = document.getElementsByName("<%=Projekt.COLUMN_PRIMARY_KEY%>")[0];
        let partnerIdInputField = document.getElementsByName("<%=Projekt.COLUMN_PROJEKTPARTNER%>")[0];
        projektIdInputField.disabled = false;
        partnerIdInputField.disabled = false;

        for (var i = 0; i < e.target.length; i++) {

            console.log(e.target[i].name, e.target[i].value);
            submitData[e.target[i].name] = e.target[i].value;
        }

        let partnerId = MainState.state[Parameters.PROJEKT.CATEGORY][Parameters.PROJEKT.FK];
        submitData[Parameters.PROJEKT.FK] = partnerId;

        console.log({ partnerId })

        var url = "<%=Address.getProjektBearbeitenServlet()%>";
        var posting = $.post(url, submitData);
        posting.done(function (data) {

            projektIdInputField.disabled = true;
            partnerIdInputField.disabled = true;

            if (data["status"] === "error") $("#projekt_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] + "</h3>");

            if (data["status"] === "success") {

                let requiredFields = document.querySelectorAll("input:required");
                for (let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
                $("#projekt_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] + "</div>");
                $("#th_speichern").empty();

                MainState.setProjekt(projektIdInputField.value);
            }
        });
    })

    function enableSaveButton(param) {
        $("#button_projekt_update").prop("disabled", !param.checked)
    }

    function projektBearbeitenInit() {

        let projektInput = document.getElementsByName("<%=Projekt.COLUMN_PRIMARY_KEY%>")[0];
        projektInput.value = MainState.state[Parameters.PROJEKT.CATEGORY][Parameters.PROJEKT.PK];

        let partnerInput = document.getElementsByName("<%=Projekt.COLUMN_PROJEKTPARTNER%>")[0];
        partnerInput.value = MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.NAME];

        let vertragsnummer = document.getElementsByName("<%=Projekt.COLUMN_VERTRAGSNUMMER%>")[0];
        vertragsnummer.value = MainState.state[Parameters.PROJEKT.CATEGORY][Parameters.PROJEKT.VERTRAGSNUMMER];

        let projektId = document.getElementById("projekt_id_input_field").value;
        console.log({ projektId })


        // terrible hack solange keine vernuenftige Loesing gefunden wurde
        setTimeout(function () {
            if (projektInput.value === "") {
                alert("bitte Projekt auswaehlen!");
                $("#projekt_auswaehlen").click();
            }
        }, 500);
    }

    projektBearbeitenInit();
</script>

</html>