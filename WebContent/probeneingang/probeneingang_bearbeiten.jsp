<%@page import="config.Address" %>
<%@page import="model.Probeneingang" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8" />
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
</head>

<body>
    <form id="form_probeneingang_bearbeiten">
        <table id="table_probeneingang">
            <tr style="background-color: #77bbff;">
                <th style="background-color: #77bbff; padding: 16px;">Probe</th>
            </tr>

            <tr class="table_probeneingang_bearbeiten_tr">
                <th id="probenIdTooltip">
                    Proben ID
                </th>
            </tr>
            <tr class="table_probeneingang_bearbeiten_tr">
                <td>
                    <input disabled required type="text" id="proben_id_input_field" placeholder="" style="color: red;"
                        name=<%=Probeneingang.PROBEN_ID%>>
                </td>
            </tr>

            <div id="table_probeneingang_content"></div>

            <tr>
                <th style="text-align: center" id="button_probeneingang_speichern">
                    <button disabled id="button_probeneingang_update" type="submit">Speichern</button>
                    <input required type="checkbox" id="acknowledge_probeneingang_update"
                        onclick="enableSaveButton(this)">
                    <i>Die bestehende Probe wird mit den neuen Werten ueberschrieben!</i>
                </th>
            </tr>

            <tr>
                <th style="text-align: center" id="probeneingang_erstellen_save_message"></th>
            </tr>

        </table>
    </form>
    <script>

        initFormHandler();

        function initFormHandler() {

            // send form data to url
            let form = document.querySelector('#form_probeneingang_bearbeiten');

            form.addEventListener('submit', function (e) {
                e.preventDefault();

                let projektId = document.getElementsByName("<%=Probeneingang.PROJEKT_ID%>")[0];
                projektId.disabled = false;

                let probenId = document.getElementsByName("<%=Probeneingang.PROBEN_ID%>")[0];
                let probenIdWasDisabled = false;
                if (probenId.disabled === true) {
                    probenId.disabled = false;
                    probenIdWasDisabled = true;
                }

                var formData = new FormData(form);

                let url = '<%=Address.getProbeneingangBearbeitenServlet()%>';

                json = fetch(url, {
                    method: "post",
                    body: formData
                })
                    .then(response => {

                        projektId.disabled = true;
                        if (probenIdWasDisabled) probenId.disabled = true;

                        let json = response.json().then(data => {

                            console.log({ data });

                            if (data["status"] === "error") $("#probeneingang_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] + "</h3>");

                            if (data["status"] === "success") {

                                let requiredFields = document.querySelectorAll("input:required");
                                for (let i = 0; i < requiredFields.length; i++) requiredFields[i].style["border-color"] = "green";
                                $("#probeneingang_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] + "</div>");
                                $("#button_probeneingang_speichern").empty();
                            }
                        })
                    })
                    .catch(error => {
                        replaceContent("button_probeneingang_speichern", "Fehler:" + error, "red");
                    });


            }, false);
        }

        document.getElementById('proben_id_input_field').addEventListener('change', function (e) {
            $.ajax({
                url: '<%=Address.getProbeneingangBearbeitenServlet()%>',
                type: 'get',
                data: { id: e.target.value },
                success: function (data) {
                    const trlist = $("#table_probeneingang tr")
                    if (data) {
                        const probeneingangContent = $.post("probeneingang/probeneingang.jsp");
                        probeneingangContent.done(async function (post) {
                            await MainState.setProbe(e.target.value);
                            trlist.eq(2).after(post).ready(function () {
                                for (let key in data) {
                                    const nodeList = document.getElementsByName(key)
                                    const val = data[key]
                                    for (let i = 0; i < nodeList.length; i++) {
                                        nodeList[i].value = val
                                        // nodeList[i].disabled = false
                                    }
                                }
                            });

                            // Proben ID in das originale ProbenID InputField uebertragen
                            const probenIdInputFieldNodeList = document.getElementsByName("<%=Probeneingang.PROBEN_ID%>");
                            probenIdInputFieldNodeList[1].value = probenIdInputFieldNodeList[0].value;
                            // die ersten beiden Zeilen von probeneingang_bearbeiten werden geloescht, 
                            // sonst sind die Input-Felder doppelt und probeneingang.jsp kann mehrfach doppelt 
                            // unten am Dokument angehaengt werden!
                            let rows = document.getElementsByClassName("table_probeneingang_bearbeiten_tr");
                            for (let i = rows.length - 1; i >= 0; i--) {
                                rows[i].remove();
                            }
                            GlobaleSuche.deleteSearchLink("<%=Probeneingang.PROBEN_ID%>");
                            let probenId = document.getElementsByName("<%=Probeneingang.PROBEN_ID%>")[0];
                            probenId.disabled = true;
                        });
                    } else if (trlist.length === 38) trlist.slice(3, 36).remove()


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

        function initProbeneingangBearbeiten() {
            const probenIdInput = document.getElementsByName("<%=Probeneingang.PROBEN_ID%>")[0];
            probenIdInput.value = MainState.state[Parameters.PROBE.CATEGORY][Parameters.PROBE.PK];

            if (probenIdInput.value !== "") {
                probenIdInput.disabled = false;
                probenIdInput.dispatchEvent(new Event("change"));
                probenIdInput.disabled = true;
            }

            // terrible hack solange keine vernuenftige Loesing gefunden wurde
            setTimeout(function () {
                if (probenIdInput.value === "") {
                    alert("bitte Probe auswaehlen!");
                    // $("#probe_auswaehlen").click();
                    NavigationMenu.openStateSearch(
                        [
                            new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, ""),
                            new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, () => MainState.state[Parameters.PROJEKT.CATEGORY][Parameters.PROJEKT.PK]),
                            new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, () => MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.NAME])
                        ],
                        async (callbackData) => {
                            await MainState.setProbe(callbackData[Parameters.PROBE.PK]);
                            $("#probeneingang_erstellen").click();
                        },
                        new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK),
                    )
                }
            }, 500);
        }
        initProbeneingangBearbeiten();

    </script>
</body>

</html>