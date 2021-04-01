<%@page import="config.Address" %>
<%@page import="model.database.tableModels.Partner" %>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Solid-Chem | LIMS - Update Partner</title>
    <!-- <link rel="stylesheet" href="projekt/projekt_erstellen.css"> -->
    <style>
        #create_partner_table {
            float: left;
            border: 1px solid #ddd;
            padding: 10px;
            padding-left: 30px;
            padding-right: 30px;
            display: inline-block;
            background-color: white;
        }

        #partner_speicher_th {
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
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#form_partner_bearbeiten').submit(function () {
                $.ajax({
                    url: '<%=Address.getPartnerBearbeitenServlet()%>',
                    type: 'post',
                    data: $(this).serialize(),
                    success: function () {
                        replaceContent("button_analyse_update", "Erfolgreich gespeichert", "green");
                    },
                    error: function (xhr, status, error) {
                        replaceContent("button_analyse_update", "Fehler: " + xhr.responseText, "red");
                    }
                });
                return false;
            });
        });
    </script>
</head>
<body>
<form id="form_partner_bearbeiten">
    <table id="create_partner_table">
        <tr>
            <th colspan=4>
                <h1>Projektpartner bearbeiten</h1>
            </th>
        </tr>
        <tr>
            <th>Partner Informationen</th>
        </tr>
        <tr>
            <td>Projektpartner ID</td>
            <td>
                <input required id="partner_id_input_field" type="number" placeholder="*"
                       name=<%=Partner.COLUMN_PRIMARY_KEY%>>
            </td>
        </tr>
        <tr>
            <td>Name</td>
            <td>
                <input disabled id="partner_name_input_field" type="text" placeholder="" name=<%=Partner.COLUMN_NAME%>>
            </td>
        </tr>
        <tr>
            <td>E-Mail</td>
            <td>
                <input disabled id="partner_email_input_field" type=text placeholder="" name=<%=Partner.COLUMN_EMAIL%>>
            </td>
        </tr>
        <tr>
            <th id="partner_speicher_th" colspan=4>
                <button disabled id="button_partner_update" type="submit">Speichern</button>
                <input required type="checkbox" id="acknowledge_parner_update" onclick="enableSaveButton(this)">
                <i>Der bestehende Parner wird mit den neuen werten berschrieben!</i>
            </th>
        </tr>
        <tr>
            <th id="partner_erstellen_save_message" colspan=4></th>
        </tr>
    </table>
</form>
</body>

<script>
    document.getElementById('partner_id_input_field').addEventListener('change', function (e) {
        $.ajax({
            url: '<%=Address.getPartnerBearbeitenServlet()%>',
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
                    const name = document.getElementById('partner_name_input_field')
                    name.value = ''
                    name.disabled = true
                    const email = document.getElementById('partner_email_input_field')
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
        $("#button_partner_update").prop("disabled", !param.checked)
    }

    // Such-Links
    GlobaleSuche.addSearchLinkToInputWithName("<%=Partner.COLUMN_PRIMARY_KEY%>",
        [
            {
                "category": "partner",
                "parameter": "id",
                "value": ""
            },
            {
                "category": GlobaleSuche.parameter.PARTNER.CATEGORY,
                "parameter": GlobaleSuche.parameter.PARTNER.NAME,
                "value": () => document.getElementsByName("<%=Partner.COLUMN_NAME%>")[0].value
            },
            {
                "category": GlobaleSuche.parameter.PARTNER.CATEGORY,
                "parameter": GlobaleSuche.parameter.PARTNER.EMAIL,
                "value": () => document.getElementsByName("<%=Partner.COLUMN_EMAIL%>")[0].value
            },
        ]);

</script>
</html>