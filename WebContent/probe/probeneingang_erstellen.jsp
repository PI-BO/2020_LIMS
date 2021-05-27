<%@page import="model.Probeneingang"%>
<%@page import="config.Address"%>
<%@page import="controller.servlets.probeneingang.ProbeneingangErstellenServlet"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<title>Probeneingang</title>
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
<script src="<%=Address.MAIN_PATH + Address.PROBENEINGANG_JS%>"></script>
</head>

<body>
	<form id="form_probeneingang">
		<input type="hidden" id="probeneingang_url" value=<%=ProbeneingangErstellenServlet.ROUTE%>>
		<table id="table_probeneingang">
			<tr style="background-color: #77bbff;">
				<th id="probeneingangTooltip" style="background-color: #77bbff; padding: 16px;">Probeneingang</th>
			</tr>

			<tr>
				<th style="text-align: center" id="button_probeneingang_speichern">
					<button type="submit">Speichern</button>
				</th>
			</tr>

			<tr>
				<th style="text-align: center" id="probeneingang_erstellen_save_message"></th>
			</tr>

		</table>
	</form>
	<script>
        $(document).ready(function () {
        	
			const url = "<%=Address.MAIN_PATH + Address.PROBENEINGANG_JSP%>";
        	const probeneingangContent = $.post(url);
            probeneingangContent.done(function (data) {
                $("#table_probeneingang tr").eq(0).after(data);
            });
        })

        // reset preview pictures when reset button has been pressed
        $("#input_image_reset_button").on("click", function () {
            $("#preview-container").empty();
            $("#input_image_upload").val("");
        });

        initFormHandler();

        function initFormHandler() {
            
            // send form data to url
            let form = document.querySelector('#form_probeneingang');
            
            form.addEventListener('submit', function (e) {
                e.preventDefault();

                let projektId = document.getElementsByName("<%=Probeneingang.PROJEKT_ID%>")[0];
                projektId.disabled = false;

                var formData = new FormData(form);

                let url = "<%=Address.getMainPath()%>" + document.querySelector("#probeneingang_url").value;

                json = fetch(url, {
                    method: "post",
                    body: formData
                })
                    .then(response => {

                        projektId.disabled = true;
                        let json = response.json().then(data => {

                            console.log({ data });

                            if (data["status"] === "error") $("#probeneingang_erstellen_save_message").empty().append("<h3 style=\"color:red\">" + data["message"] + "</h3>");

                            if (data["status"] === "success") {

                                let requiredFields = document.querySelectorAll("input:required");
                                for (let i = 0; i < requiredFields.length; i++) requiredFields[i].style["border-color"] = "green";
                                $("#probeneingang_erstellen_save_message").empty().append("<div style=\"color:green\">" + data["message"] + "</div>");
                                $("#button_probeneingang_speichern").empty();

                                const probenIdInputField = document.getElementsByName("<%=Probeneingang.PROBEN_ID%>")[0];
                                probenIdInputField.disabled = true;
                                MainState.setProbe(probenIdInputField.value);
                            }
                        })
                    })
                    .catch(error => {
                        replaceContent("button_probeneingang_speichern", "Fehler:" + error, "red");
                    });


            }, false);
        }

    </script>
</body>

</html>