<%@page import="config.Address"%>
<%@page import="model.database.tableModels.Partner"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>Solid-Chem | LIMS - Update Partner</title>
<!-- <link rel="stylesheet" href="projekt/projekt_erstellen.css"> -->
<style>
#create_partner_table {
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
</head>

<body>
	<form id="form_partner_bearbeiten">
		<table id="create_partner_table">
			<tr>
				<th colspan=4>
					<h1>Partner bearbeiten</h1>
				</th>
			</tr>
			<tr>
				<th style="float: left">Partner Informationen</th>
			</tr>
			<tr>
				<td>Partner ID</td>
				<td>
					<input disabled required id="partner_id_input_field" type="text" placeholder="" name=<%=Partner.COLUMN_PRIMARY_KEY%>>
				</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>
					<input id="partner_name_input_field" type="text" placeholder="" name=<%=Partner.COLUMN_NAME%>>
				</td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td>
					<input id="partner_email_input_field" type=text placeholder="" name=<%=Partner.COLUMN_EMAIL%>>
				</td>
			</tr>
			<tr>
				<th id="partner_speicher_th" colspan=4>
					<button disabled id="button_partner_update" type="submit">Speichern</button>
					<input required type="checkbox" id="acknowledge_parner_update" onclick="enableSaveButton(this)">
					<i>Der bestehende Partner wird mit den neuen Werten ueberschrieben!</i>
				</th>
			</tr>
			<tr>
				<th id="partner_erstellen_save_message" colspan=4></th>
			</tr>
		</table>
	</form>
</body>

<script>

    // $(document).ready(function () {
    //     $('#form_partner_bearbeiten').submit(function () {
    //         $.ajax({
    //             url: '<%=Address.getPartnerBearbeitenServlet()%>',
    //             type: 'post',
    //             data: $(this).serialize(),
    //             success: function () {
    //                 replaceContent("button_partner_update", "Erfolgreich gespeichert", "green");
    //             },
    //             error: function (xhr, status, error) {
    //                 replaceContent("button_partner_update", "Fehler: " + xhr.responseText, "red");
    //             }
    //         });
    //         return false;
    //     });
    // });

    $("#form_partner_bearbeiten").submit(function (e) {
        e.preventDefault();

        var submitData = {};

        let partnerInput = document.getElementsByName("<%=Partner.COLUMN_PRIMARY_KEY%>")[0];
        partnerInput.disabled = false;

        for (var i = 0; i < e.target.length; i++) {
            submitData[e.target[i].name] = e.target[i].value;
        }

        var url = "<%=Address.getPartnerBearbeitenServlet()%>";
        var posting = $.post(url, submitData);
        posting.done(function (data) {

            partnerInput.disabled = true;

            if (data["status"] === "error") $("#partner_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] + "</h3>");

            if (data["status"] === "success") {

                let requiredFields = document.querySelectorAll("input:required");
                for (let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
                $("#partner_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] + "</div>");
                $("#partner_speicher_th").empty();

                GlobaleSuche.deleteSearchLink("<%=Partner.COLUMN_PRIMARY_KEY%>");

                MainState.setProjektPartner(partnerInput.value)
            }
        });
    })

    document.getElementById('partner_id_input_field').addEventListener('change', function (e) {
        $.ajax({
            url: '<%=Address.getPartnerBearbeitenServlet()%>',
            type: 'get',
            data: { id: e.target.value },
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

    function replaceContent(id, text, color) {
        $(`#${id}`).empty().append(`<div style="color: ${color}">${text}</div>`)
    }

    function partnerBearbeitenInit() {

        let partnerName = document.getElementsByName("<%=Partner.COLUMN_NAME%>")[0];
        partnerName.value = MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.NAME];

        let partnerId = document.getElementsByName("<%=Partner.COLUMN_PRIMARY_KEY%>")[0];
        partnerId.value = MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.PK];

        let partnerEmail = document.getElementsByName("<%=Partner.COLUMN_EMAIL%>")[0];
        partnerEmail.value = MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.EMAIL];

        // terrible hack solange keine vernuenftige Loesing gefunden wurde
        setTimeout(function () {
            if (partnerId.value === "") {
                alert("bitte Partner auswaehlen!");
                // $("#partner_auswaehlen").click();
                NavigationMenu.openStateSearch(
                    [
                        new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, ""),
                        new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, "")
                    ],
                    async (callbackData) => {
                        await MainState.setProjektPartner(callbackData[Parameters.PARTNER.PK]);
                        $("#projekt_partner_bearbeiten").click();
                    },
                    new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK),
                )
            }
        }, 500);

    };

    partnerBearbeitenInit();

</script>

</html>