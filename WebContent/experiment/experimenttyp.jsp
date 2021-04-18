<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.servlets.experiment.ExperimentErstellenServlet" %>
<%@ page import="model.database.tableModels.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="exceptions.ModelNotFoundException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="model.database.tableModels.experimente.ExperimentDurchfuehrungstext" %>
<%@ page import="model.database.tableModels.experimente.ExperimentSerie" %>

<%
    String experiment_typ = request.getParameter("typ");
%>

<div class="experiment_erstellen_header">Experiment ID</div>
<div class="experiment_erstellen_entry">

    <%--     <input required type="number" min="1" name=<%=ExperimentServlet.NO_ID%>> --%>
    <input required type="number" min="1" name=<%=ExperimentErstellenServlet.NO_ID%>>
</div>

<div class="experiment_erstellen_header">Screening No</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.SCREENING_NO%>>
</div>

<div class="experiment_erstellen_header">Planung erfolgt durch</div>
<div class="experiment_erstellen_entry">
    <select name=<%=ExperimentErstellenServlet.PLANUNG_ERFOLGT_DURCH%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <%
            try {
                ModelTable modelList = new ModelTable(new Mitarbeiter());
                List<Model> projektplanung = modelList.getModelList().stream().filter(m -> ((Mitarbeiter) m).getRolle() == 1).collect(Collectors.toList());
                for (Model model : projektplanung) {
        %>
        <option value="<%=model.getPrimaryKey()%>"><%=((Mitarbeiter) model).getNachname()%>
        </option>
        <%
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        %>
    </select>
</div>

<div class="experiment_erstellen_header">Experiment Serie</div>
<div class="experiment_erstellen_entry">
    <select onchange="newExperimentSerie(this.value)" name=<%=ExperimentErstellenServlet.EXPERIMENT_SERIE%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <option value="new">neue Serie</option>
        <%
            try {
                ModelTable experimentSerien = new ModelTable(new ExperimentSerie());
                for (Model model : experimentSerien.getModelList()) {
        %>
        <option value="<%=model.getPrimaryKey()%>"><%=model.getPrimaryKey()%>
        </option>
        <%
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        %>
    </select>
    <input id="experiment_serie_text" style="display: none" type="text"
           name=<%=ExperimentErstellenServlet.EXPERIMENT_SERIE_TEXT%>>
</div>

<div class="experiment_erstellen_header">Experiment No.</div>
<div class="experiment_erstellen_entry">
    <input required type="text" name=<%=ExperimentErstellenServlet.EXPERIMENT_NO%>>
</div>

<div class="experiment_erstellen_header">Durchführung</div>
<div class="experiment_erstellen_entry">
    <select id="durchfuehrung_select" onchange="newExperimentDurchfuehrungstext(this)" required
            name=<%=ExperimentErstellenServlet.DURCHFUEHRUNGSTEXT%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <option value="new">neuer Durchführungstext</option>
        <%
            try {
                ModelTable experimentDurchfuehrungtext = new ModelTable(new ExperimentDurchfuehrungstext());
                for (Model model : experimentDurchfuehrungtext.getModelList()) {
        %>
        <option label="<%=model.getPrimaryKey()%>" value="<%=model.getPrimaryKey()%>">
            <%=((ExperimentDurchfuehrungstext) model).getText()%>
        </option>
        <%
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        %>
    </select>
    <table>
        <tr>
            <th>
                <input id="experiment_durchfuehrungstext_titel" style="display: none" type="text"
                       placeholder="neuer Titel" name=<%=ExperimentErstellenServlet.DURCHFUEHRUNGSTEXT_TITEL%>>
            </th>
        </tr>
        <tr>
            <th>
                <textarea required id="experiment_durchführungstext_text" rows="4" cols="50"
                          name=<%=ExperimentErstellenServlet.DURCHFUEHRUNGSTEXT_TEXT%>></textarea>
            </th>
        </tr>
    </table>
</div>

<div class="experiment_erstellen_header">Projektleiternotiz / Intention</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.PROJEKTLEITERNOTIZ_INTENTION%>>
</div>

<div class="experiment_erstellen_header">Verweis</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.VERWEIS%>>
</div>

<div class="experiment_erstellen_header">Startfreigabe (ab)</div>
<div class="experiment_erstellen_entry">
    <input type="date" name=<%=ExperimentErstellenServlet.STARTFREIGABE%>>
</div>

<div class="experiment_erstellen_header">Erledigt bis (soll)</div>
<div class="experiment_erstellen_entry">
    <input type="date" name=<%=ExperimentErstellenServlet.ERLEDIGT_BIS%>>
</div>

<div class="experiment_erstellen_header">Hinweis an Laborleiter</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.HINWEIS_AN_LABORLEITER%>>
</div>

<div class="experiment_erstellen_header">Experiment No.</div>
<div class="experiment_erstellen_entry">
    <input required type="text" name=<%=ExperimentErstellenServlet.EXPERIMENT_NO%>>
</div>

<div class="experiment_erstellen_header">Planung Abgeschlossen</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" value="true" name=<%=ExperimentErstellenServlet.PLANUNG_ABGESCHLOSSEN%>>
</div>

<div class="experiment_erstellen_header">Priorität Experiment</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.PRIORITAET_EXPERIMENT%>>
</div>

<div class="experiment_erstellen_header">Sicherheitshinwes</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.SICHERHEITSHINWEIS%>>
</div>

<div class="experiment_erstellen_header">Verantwortlicher Operator</div>
<div class="experiment_erstellen_entry">
    <select name=<%=ExperimentErstellenServlet.VERANTWORTLICHER_OPERATOR%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <%
            try {
                ModelTable modelList = new ModelTable(new Mitarbeiter());
                List<Model> laborbetreuung = modelList.getModelList().stream().filter(m -> ((Mitarbeiter) m).getRolle() == 2).collect(Collectors.toList());
                for (Model model : laborbetreuung) {
        %>
        <option value=<%=model.getPrimaryKey()%>><%=((Mitarbeiter) model).getNachname()%>
        </option>
        <%
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        %>
    </select>
</div>

<div class="experiment_erstellen_header">Experiment Start</div>
<div class="experiment_erstellen_entry">
    <input type="date" name=<%=ExperimentErstellenServlet.EXPERIMENT_START%>>
</div>

<div class="experiment_erstellen_header typ_Verdampfung">Vial Tara [g]</div>
<div class="experiment_erstellen_entry typ_Verdampfung">
    <input type="number" value="0" step="0.0001" min="0" name=<%=ExperimentErstellenServlet.VIAL_TARA_G%>>
</div>

<div class="experiment_erstellen_header">API/Startmaterial</div>
<div class="experiment_erstellen_entry">


    <%--     <select required name=<%=ExperimentServlet.API_STARTMATERIAL%>> --%>
    <!--         <option value="" selected disabled>bitte auswaehlen</option> -->
    <%--         <% --%>

    <%--


//             try {
//                 ModelTable apiModelList = new ModelTable(new Probe());
//                 for (Model model : apiModelList.getModelList()) {
<%--         %> --%>
    <%--         <option value=<%=model.getPrimaryKey()%>><%=model.getPrimaryKey()%> --%>
    <!--         </option> -->
    <%--         <% --%>

    <%--
//                 }
//             } catch (SQLException throwables) {
//                 throwables.printStackTrace();
//             } catch (ModelNotFoundException e) {
//                 e.printStackTrace();
//             }
--%>

    <%--         %> --%>
    <!--     </select> -->


    <input required type="text" name=<%=ExperimentErstellenServlet.API_STARTMATERIAL%>>
</div>

<div class="experiment_erstellen_header">API/Startmaterial Soll Einwaage</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" min="0" name="<%=ExperimentErstellenServlet.API_STARTMATERIAL_SOLL_EINWAAGE%>">
    <i>mg</i>
</div>

<div class="experiment_erstellen_header">API/Startmaterial Soll Einwaage [mg]</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" min="0" step="0.001"
           name="<%=ExperimentErstellenServlet.API_STARTMATERIAL_SOLL_EINWAAGE_MG%>">
</div>

<div class="experiment_erstellen_header">CoF Bezeichnung</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.COF_BEZEICHNUNG%>>
</div>

<div class="experiment_erstellen_header">CoF ref-Code</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.COF_REF_CODE%>>
</div>

<div class="experiment_erstellen_header">CoF Soll Einwaage</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" min="0" name="<%=ExperimentErstellenServlet.COF_SOLL_EINWAAGE%>">
    <i>mg</i>
</div>

<div class="experiment_erstellen_header">CoF Soll Einwaage [mg]</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" min="0" step="0.001" name="<%=ExperimentErstellenServlet.COF_SOLL_EINWAAGE_MG%>">
</div>

<div class="experiment_erstellen_header">Soll Temperatur</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" name="<%=ExperimentErstellenServlet.SOLL_TEMPERATUR%>">
</div>

<div class="experiment_erstellen_header">Lösungsmittel für API & CoF</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.LOESUNGSMITTEL_API_COF%>>
</div>

<div class="experiment_erstellen_header">Vorgabe/Info Volumen</div>
<div class="experiment_erstellen_entry">
    <input type="text" value="keine Info" name=<%=ExperimentErstellenServlet.VORGABE_INFO_VOLUMEN%>>
</div>

<div class="experiment_erstellen_header">Lösungsmittel Ist Volumen</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.LOESUNGSMITTEL_IN_VOLUMEN%>>
</div>

<div class="experiment_erstellen_header typ_Slurry">Beobachtungen zur Slurryerstellung oder Abänderung des Experiments
</div>
<div class="experiment_erstellen_entry typ_Slurry">
    <input type="text"
           name=<%=ExperimentErstellenServlet.BEOBACHTUNG_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS%>>
</div>

<div class="experiment_erstellen_header typ_Verdampfung">Beobachtungen zum Loesungsvorgang oder Abänderung des
    Experiments
</div>
<div class="experiment_erstellen_entry typ_Verdampfung">
    <input type="text"
           name=<%=ExperimentErstellenServlet.BEOBACHTUNG_LOESUNGSVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS%>>
</div>

<div class="experiment_erstellen_header">Experiment Start</div>
<div class="experiment_erstellen_entry">
    <input type="date" name=<%=ExperimentErstellenServlet.EXPERIMENT_START%>>
</div>

<div class="experiment_erstellen_header">Beobachtungen zum Experimentverlauf</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.BEOBACHTUNGEN_EXPERIMENTVERLAUF%>>
</div>

<div class="experiment_erstellen_header">Experiment Ende</div>
<div class="experiment_erstellen_entry">
    <input type="date" name=<%=ExperimentErstellenServlet.EXPERIMENT_ENDE%>>
</div>

<div class="experiment_erstellen_header">Status Experiment</div>
<div class="experiment_erstellen_entry">
    <select name=<%=ExperimentErstellenServlet.STATUS_EXPERIMENT%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <option value="angesetzt">angesetzt</option>
        <option value="abgebrochen">abgebrochen</option>
        <option value="abgeschlossen">abgeschlossen</option>
        <option value="Probe aufgebraucht">Probe aufgebraucht</option>
        <option value="rücksprache">rücksprache</option>
    </select>
</div>

<div class="experiment_erstellen_header">Aufbereitung & Präsentation PXRD</div>
<div class="experiment_erstellen_entry">
    <input type="date" name=<%=ExperimentErstellenServlet.AUFBEREITUNG_PRAESENTATION_PXRD%>>
</div>

<div class="experiment_erstellen_header">Beobachtungen zum Ende des Experiments & Aufarbeitung</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.BEOBACHTUNGEN_ENDE_EXPERIMENT_UND_AUFBEREITUNG%>>
</div>

<div class="experiment_erstellen_header typ_Verdampfung">Auswaage Vial mit Kristallisat [g]</div>
<div class="experiment_erstellen_entry typ_Verdampfung">
    <input type="number" value="0" min="0" step="0.0001"
           name="<%=ExperimentErstellenServlet.AUSBEUTE_VIAL_KRISTALLAT_G%>">
</div>

<div class="experiment_erstellen_header typ_Verdampfung">Ausbeute / [mg] von Präp / analytik</div>
<div class="experiment_erstellen_entry typ_Verdampfung">
    <input type="number" value="0" min="0" step="0.1" name="<%=ExperimentErstellenServlet.AUSBEUTE_MG_PRAEP_ANALYTIK%>">
</div>

<div class="experiment_erstellen_header">Standort/Lagerorte der finalen Probe</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.STANDORT_LAGERORT_FINALE_PROBE%>>
</div>

<div class="experiment_erstellen_header">Priorität Analystik</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.PRIORITAET_ANALYTIK%>>
</div>

<div class="experiment_erstellen_header">Erstanalystik PXRD</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.ERSTANALYTIK_PXRD%>>
</div>

<div class="experiment_erstellen_header">PXRD möglich?</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" value="true" name=<%=ExperimentErstellenServlet.PXRD_MOEGLICH%>>
</div>

<div class="experiment_erstellen_header">Mit Ausbeute-Rest weitere Analytik möglich?</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.AUSBEUTE_REST_WEITERE_ANALYTIK_MOEGLICH%>>
</div>

<div class="experiment_erstellen_header">Ergebnis PXRD</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.ERGEBNIS_PXRD%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik DSC</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" value="true" name=<%=ExperimentErstellenServlet.FOLGEANALYTIK_DSC%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik TG</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" value="true" name=<%=ExperimentErstellenServlet.FOLGEANALYTIK_TG%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik PXRD II</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" value="true" name=<%=ExperimentErstellenServlet.FOLGEANALYTIK_PXRD_II%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik H-NMR</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" value="true" name=<%=ExperimentErstellenServlet.FOLGEANALYTIK_H_NMR%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik IR</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" value="true" name=<%=ExperimentErstellenServlet.FOLGEANALYTIK_IR%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik Raman</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" value="true" name=<%=ExperimentErstellenServlet.FOLGEANALYTIK_RAMAN%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik OMI</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" value="true" name=<%=ExperimentErstellenServlet.FOLGEANALYTIK_OMI%>>
</div>

<div class="experiment_erstellen_header">Informationen zur Folgeanalytik (ToDo / Parameter)</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.INFORMATIONEN_FOLGEANALYTIK%>>
</div>

<div class="experiment_erstellen_header">Ergebnis DSC</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.ERGEBNIS_DSC%>>
</div>

<div class="experiment_erstellen_header">Ergebnis TG</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.ERGEBNIS_TG%>>
</div>

<div class="experiment_erstellen_header">Ergebnis PXRD II</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.ERGEBNIS_PXRD_II%>>
</div>

<div class="experiment_erstellen_header">Ergebnis H-NMR</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.ERGEBNIS_H_NMR%>>
</div>

<div class="experiment_erstellen_header">Ergebnis IR</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.ERGEBNIS_IR%>>
</div>

<div class="experiment_erstellen_header">Ergebnis Raman</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.ERGEBNIS_RAMAN%>>
</div>

<div class="experiment_erstellen_header">Ergebnis OMI</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.ERGEBNIS_OMI%>>
</div>

<div class="experiment_erstellen_header">Status Analystik</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.STATUS_ANALYTIK%>>
</div>

<div class="experiment_erstellen_header">Gesamt Ergebnis</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.GESAMT_ERGEBNIS%>>
</div>

<div class="experiment_erstellen_header">Einstufung Ergebnis</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentErstellenServlet.EINSTUFUNG_ERGEBNIS%>>
</div>

<script>
    switch ('<%=experiment_typ%>') {
        case 'slurry':
            $('.typ_Verdampfung').remove();
            break;
        case 'verdampfung':
            $('.typ_Slurry').remove();
            break;
    }

    // Such-Links
    GlobaleSuche.addSearchLinkToInputWithName("<%=ExperimentErstellenServlet.NO_ID%>",
        [
            new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK),
            new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.TYP)
        ],
        new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK)
    );

    // Such-Links
    GlobaleSuche.addSearchLinkToInputWithName("<%=ExperimentErstellenServlet.API_STARTMATERIAL%>",
        [
            new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK)
        ],
        new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK)
    );
</script>
