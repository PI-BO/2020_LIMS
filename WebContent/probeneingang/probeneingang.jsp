<%@page import="model.Probeneingang" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<tr>
    <th class="tooltip">
        Wirkstoff-Name <a href="javascript:void(0);">?</a>
        <div class="tooltiptext">Wirkstoff ist Substanz?</div>
    </th>
</tr>
<tr>
    <td>
        <input type="text" name=<%=Probeneingang.WIRKSTOFF%>>
    </td>
</tr>

<tr>
    <th>Auftraggeber</th>
</tr>
<tr>
    <td>
        <input type="text" id="partner_name_input_field" name=<%=Probeneingang.AUFTRAGGEBER%>
                placeholder="">
    </td>
</tr>

<tr>
    <th class="tooltip">
        Proben-Nr <a href="javascript:void(0);">?</a>
        <div class="tooltiptext">Proben-Nr und Proben ID sind dasselbe?</div>
    </th>
</tr>
<tr>
    <td>
        <input required type="text" id="proben_id_input_field" name="<%=Probeneingang.PROBEN_NR%>"
               placeholder="* neue Proben ID">
    </td>
</tr>

<tr>
    <th>Summenformel</th>
</tr>
<tr>
    <td>
        <input type="text" name=<%=Probeneingang.SUMMENFORMEL%>>
    </td>
</tr>

<tr>
    <th>Bezeichung</th>
</tr>
<tr>
    <td>
        <input type="text" name=<%=Probeneingang.BEZEICHNUNG%>>
    </td>
</tr>

<tr>
    <th>Originator</th>
</tr>
<tr>
    <td>
        <input type="text" name=<%=Probeneingang.ORIGINATOR%>>
    </td>
</tr>

<tr>
    <th>Probeneingang</th>
</tr>
<tr>
    <td>
        <input type="date" name=<%=Probeneingang.PROBENEINGANG%>>
    </td>
</tr>

<tr>
    <th>Probenmasse</th>
</tr>
<tr>
    <td>
        <input type="text" name=<%=Probeneingang.PROBENMASSE%>>
    </td>
</tr>

<tr>
    <th>Besonderheiten</th>
</tr>
<tr>
    <td>
        <input type="text" name=<%=Probeneingang.BESONDERHEITEN%>>
    </td>
</tr>

<tr>
    <th>Infos</th>
</tr>
<tr>
    <td>
        <textarea rows="4" cols="50" name=<%=Probeneingang.INFOS%>></textarea>
    </td>
</tr>

<tr></tr>

<tr>
    <th>Bemerkungen zur Messung</th>
</tr>
<tr>
    <td>
        <textarea rows="4" cols="50" name=<%=Probeneingang.BEMERKUNGEN_ZUR_MESSUNG%>></textarea>
    </td>
</tr>

<tr></tr>

<tr>
    <th>Bemerkungen</th>
</tr>
<tr>
    <td>
        <textarea rows="4" cols="50" name=<%=Probeneingang.BEMERKUNGEN%>></textarea>
    </td>
</tr>

<tr>
    <th>Literatur</th>
</tr>
<tr>
    <td>
        <textarea rows="4" cols="50" name=<%=Probeneingang.LITERATUR%>></textarea>
    </td>
</tr>

<tr>
    <th>Bilder hinzufuegen</th>
</tr>
<tr>
    <td>
        <table class="table_in_table" id="table_image_upload">
            <tr>
                <td>
                    (mehrere Bilder auswaehlen: STRG + Linksklick)
                    <br>
                    <button type="button" id="input_image_reset_button">Bilderauswahl leeren</button>
                    <input type="file" id="input_image_upload" name="probeneingang_bilder" accept="image/*"
                           onchange="loadFile(this)" multiple>
                </td>
                <td>
                    <div id="preview-container" style="max-width: 300px"></div>
                </td>
            </tr>
        </table>
    </td>
</tr>
<script>
    // reset preview pictures when reset button has been pressed
    $("#input_image_reset_button").on("click", function () {
        $("#preview-container").empty();
        $("#input_image_upload").val("");
    });

    GlobaleSuche.addSearchLinkToInputWithName("<%=Probeneingang.WIRKSTOFF_ID%>",
        [
            {
                "category": "substanz",
                "parameter": "id",
                "value": ""
            },
            {
                "category": "projekte",
                "parameter": "id",
                "value": ""
            },
            {
                "category": "partner",
                "parameter": "name",
                "value": ""
            }
        ]
    );

    GlobaleSuche.addSearchLinkToInputWithName("<%=Probeneingang.AUFTRAGGEBER%>",
        [
            {
                "category": "partner",
                "parameter": "id",
                "value": ""
            },
            {
                "category": "partner",
                "parameter": "name",
                "value": ""
            },
            {
                "category": "partner",
                "parameter": "email",
                "value": ""
            },
            {
                "category": "substanz",
                "parameter": "id",
                "value": () => document.getElementsByName("<%=Probeneingang.WIRKSTOFF_ID%>")[0].value
            }

        ]
    );

    GlobaleSuche.addSearchLinkToInputWithName("<%=Probeneingang.PROBEN_NR%>",
        [
            {
                "category": "probe",
                "parameter": "id",
                "value": ""
            },
            {
                "category": "projekte",
                "parameter": "id",
                "value": ""
            },
            {
                "category": "partner",
                "parameter": "name",
                "value": () => document.getElementsByName("<%=Probeneingang.AUFTRAGGEBER%>")[0].value
            },
            {
                "category": "substanz",
                "parameter": "id",
                "value": () => document.getElementsByName("<%=Probeneingang.WIRKSTOFF_ID%>")[0].value
            }

        ]
    );
</script>
