<%@page import="model.Probeneingang" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<tr>
    <th>Auftraggeber</th>
</tr>
<tr>
    <td>
        <input disabled type="text" id="partner_name_input_field" name=<%=Probeneingang.AUFTRAGGEBER%>>
    </td>
</tr>

<tr>
    <th>Projekt ID</th>
</tr>
<tr>
    <td>
        <input required readonly type="text" id="projekt_id_input_field" name=<%=Probeneingang.PROJEKT_ID%>>
    </td>
</tr>
<tr>
    <th id="wirkstoffTooltip">
        Wirkstoff
    </th>
</tr>
<tr>
    <td>
        <input type="text" name=<%=Probeneingang.WIRKSTOFF%>>
    </td>
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

    GlobaleSuche.addSearchLinkToInputWithName("<%=Probeneingang.PROJEKT_ID%>",
        [
            new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, () => MainState.state[Parameters.PROJEKT.CATEGORY][Parameters.PROJEKT.PK]),
            new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, "")
        ],
        returnParameter = new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK),
        linkText = "auswaehlen"
    );

    GlobaleSuche.addSearchLinkToInputWithName("<%=Probeneingang.PROBEN_ID%>",
        [
            new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, ""),
            new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, () => document.getElementsByName("<%=Probeneingang.PROJEKT_ID%>")[0].value),
            new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, "")
        ],
        returnParameter = new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK),
    );

    Tooltip.setTooltip("probenIdTooltip", "automatisch generieren lassen?");
    Tooltip.setTooltip("wirkstoffTooltip", "Wirkstoff schon vorhanden und raussuchen, oder neuen erstellen?");
    Tooltip.setTooltip("probeneingangTooltip", "Der Probeneingang dient zum Anlegen der ersten Probe? Von dieser Probe werden dann Unterproben fuer Experimente etc. genommen?");

    function probenEingangInit(){
        let auftraggeberInput = document.getElementsByName("<%=Probeneingang.AUFTRAGGEBER%>")[0];
        auftraggeberInput.value = MainState.state[Parameters.PARTNER.CATEGORY][Parameters.PARTNER.NAME];
    
        let projektInput = document.getElementsByName("<%=Probeneingang.PROJEKT_ID%>")[0];
        projektInput.value = MainState.state[Parameters.PROJEKT.CATEGORY][Parameters.PROJEKT.PK];
    }

    probenEingangInit();
</script>