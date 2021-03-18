<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="controller.servlets.ExperimentServlet" %>
<%@ page import="model.database.tableModels.*" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="exceptions.ModelNotFoundException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>

<div class="experiment_erstellen_header">No/ID</div>
<div class="experiment_erstellen_entry">
    <input required type="number" name=<%=ExperimentServlet.NO_ID%>>
</div>

<div class="experiment_erstellen_header">Screening No</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.SCREENING_NO%>>
</div>

<div class="experiment_erstellen_header">Planung erfolgt durch</div>
<div class="experiment_erstellen_entry">
    <select required name=<%=ExperimentServlet.PLANUNG_ERFOLGT_DURCH%>>
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
    <select required onchange="newExperimentSerie(this.value)" name=<%=ExperimentServlet.EXPERIMENT_SERIE%>>
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
           name=<%=ExperimentServlet.EXPERIMENT_SERIE_TEXT%>>
</div>

<div class="experiment_erstellen_header">Experiment No.</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.EXPERIMENT_NO%>>
</div>

<div class="experiment_erstellen_header">Durchführung</div>
<div class="experiment_erstellen_entry">
    <select onchange="newExperimentDurchfuehrungstext(this)" required name=<%=ExperimentServlet.DURCHFUEHRUNGSTEXT%>>
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
                       placeholder="neuer Titel"
                       name=<%=ExperimentServlet.DURCHFUEHRUNGSTEXT_TITEL%>>
            </th>
        </tr>
        <tr>
            <th>
                <textarea id="experiment_durchführungstext_text" rows="4" cols="50"
                          name=<%=ExperimentServlet.DURCHFUEHRUNGSTEXT_TEXT%>></textarea>
            </th>
        </tr>
    </table>
</div>

<div class="experiment_erstellen_header">Projektleiternotiz / Intention</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.PROJEKTLEITERNOTIZ_INTENTION%>>
</div>

<div class="experiment_erstellen_header">Verweis</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.VERWEIS%>>
</div>

<div class="experiment_erstellen_header">Startfreigabe (ab)</div>
<div class="experiment_erstellen_entry">
    <input required type="date" name=<%=ExperimentServlet.STARTFREIGABE%>>
</div>

<div class="experiment_erstellen_header">Erledigt bis (soll)</div>
<div class="experiment_erstellen_entry">
    <input required type="date" name=<%=ExperimentServlet.ERLEDIGT_BIS%>>
</div>

<div class="experiment_erstellen_header">Hinweis an Laborleiter</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.HINWEIS_AN_LABORLEITER%>>
</div>

<div class="experiment_erstellen_header">Experiment No.</div>
<div class="experiment_erstellen_entry">
    <input required type="text" name=<%=ExperimentServlet.EXPERIMENT_NO%>>
</div>

<div class="experiment_erstellen_header">Planung Abgeschlossen</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" name=<%=ExperimentServlet.PLANUNG_ABGESCHLOSSEN%>>
</div>

<div class="experiment_erstellen_header">Priorität Experiment</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.PRIORITAET_EXPERIMENT%>>
</div>

<div class="experiment_erstellen_header">Sicherheitshinwes</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.SICHERHEITSHINWEIS%>>
</div>

<div class="experiment_erstellen_header">Verantwortlicher Operator</div>
<div class="experiment_erstellen_entry">
    <select name=<%=ExperimentServlet.VERANTWORTLICHER_OPERATOR%>>
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
    <input type="date" name=<%=ExperimentServlet.EXPERIMENT_START%>>
</div>

<div class="experiment_erstellen_header">Vial Tara [g]</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" name=<%=ExperimentServlet.VIAL_TARA_G%>>
</div>

<div class="experiment_erstellen_header">API/Startmaterial</div>
<div class="experiment_erstellen_entry">
    <select required name=<%=ExperimentServlet.API_STARTMATERIAL%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <%
            try {
                ModelTable apiModelList = new ModelTable(new Probe());
                for (Model model : apiModelList.getModelList()) {
        %>
        <option value=<%=model.getPrimaryKey()%>><%=model.getPrimaryKey()%>
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

<div class="experiment_erstellen_header">API/Startmaterial Soll Einwaage</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" name="<%=ExperimentServlet.API_STARTMATERIAL_SOLL_EINWAAGE%>">
    <i>mg</i>
</div>

<div class="experiment_erstellen_header">API/Startmaterial Soll Einwaage [mg]</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" name="<%=ExperimentServlet.API_STARTMATERIAL_SOLL_EINWAAGE_MG%>">
</div>

<div class="experiment_erstellen_header">CoF Bezeichnung</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.COF_BEZEICHNUNG%>>
</div>

<div class="experiment_erstellen_header">CoF ref-Code</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.COF_REF_CODE%>>
</div>

<div class="experiment_erstellen_header">CoF Soll Einwaage</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" name="<%=ExperimentServlet.COF_SOLL_EINWAAGE%>">
    <i>mg</i>
</div>

<div class="experiment_erstellen_header">CoF Soll Einwaage [mg]</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" name="<%=ExperimentServlet.COF_SOLL_EINWAAGE_MG%>">
</div>

<div class="experiment_erstellen_header">Soll Temperatur</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" name="<%=ExperimentServlet.SOLL_TEMPERATUR%>">
</div>

<div class="experiment_erstellen_header">Lösungsmittel für API & CoF</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.LOESUNGSMITTEL_API_COF%>>
</div>

<div class="experiment_erstellen_header">Vorgabe/Info Volumen</div>
<div class="experiment_erstellen_entry">
    <input type="text" value="keine Info" name=<%=ExperimentServlet.VORGABE_INFO_VOLUMEN%>>
</div>

<div class="experiment_erstellen_header">Lösungsmittel Ist Volumen</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.LOESUNGSMITTEL_IN_VOLUMEN%>>
</div>

<div class="experiment_erstellen_header">Beobachtungen zum Loesungsvorgang oder Abänderung des Experiments</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.BEOBACHTUNG_LOESUNGSVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS%>>
</div>

<div class="experiment_erstellen_header">Experiment Start</div>
<div class="experiment_erstellen_entry">
    <input type="date" name=<%=ExperimentServlet.EXPERIMENT_START%>>
</div>

<div class="experiment_erstellen_header">Beobachtungen zum Experimentverlauf</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.BEOBACHTUNGEN_EXPERIMENTVERLAUF%>>
</div>

<div class="experiment_erstellen_header">Experiment Ende</div>
<div class="experiment_erstellen_entry">
    <input type="date" name=<%=ExperimentServlet.EXPERIMENT_ENDE%>>
</div>

<div class="experiment_erstellen_header">Status Experiment</div>
<div class="experiment_erstellen_entry">
    <select required name=<%=ExperimentServlet.STATUS_EXPERIMENT%>>
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
    <input type="date" name=<%=ExperimentServlet.AUFBEREITUNG_PRAESENTATION_PXRD%>>
</div>

<div class="experiment_erstellen_header">Beobachtungen zum Ende des Experiments & Aufarbeitung</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.BEOBACHTUNGEN_ENDE_EXPERIMENT_UND_AUFBEREITUNG%>>
</div>

<div class="experiment_erstellen_header">Ausbeute Vial mit Kristallat [g]</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" name="<%=ExperimentServlet.AUSBEUTE_VIAL_KRISTALLAT_G%>">
</div>

<div class="experiment_erstellen_header">Ausbeute / [mg] von Präp / analytik</div>
<div class="experiment_erstellen_entry">
    <input type="number" value="0" name="<%=ExperimentServlet.AUSBEUTE_MG_PRAEP_ANALYTIK%>">
</div>

<div class="experiment_erstellen_header">Standort/Lagerorte der finalen Probe</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.STANDORT_LAGERORT_FINALE_PROBE%>>
</div>

<div class="experiment_erstellen_header">Priorität Analystik</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.PRIORITAET_ANALYSTIK%>>
</div>

<div class="experiment_erstellen_header">Erstanalystik PXRD</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.ERSTANALYSTIK_PXRD%>>
</div>

<div class="experiment_erstellen_header">PXRD möglich?</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" name=<%=ExperimentServlet.PXRD_MOEGLICH%>>
</div>

<div class="experiment_erstellen_header">Mit Ausbeute-Rest weitere Analytik möglich?</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.AUSBEUTE_REST_WEITERE_ANALYSTIK_MOEGLICH%>>
</div>

<div class="experiment_erstellen_header">Ergebnis PXRD</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.ERGEBNIS_PXRD%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik DSC</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" name=<%=ExperimentServlet.FOLGEANALYTIK_DSC%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik TG</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" name=<%=ExperimentServlet.FOLGEANALYTIK_TG%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik PXRD II</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" name=<%=ExperimentServlet.FOLGEANALYTK_PXRD_II%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik H-NMR</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" name=<%=ExperimentServlet.FOLGEANALYTIK_H_NMR%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik IR</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" name=<%=ExperimentServlet.FOLGEANALYTIK_IR%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik Raman</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" name=<%=ExperimentServlet.FOLGEANALYTIK_RAMAN%>>
</div>

<div class="experiment_erstellen_header">Folgeanalytik OMI</div>
<div class="experiment_erstellen_entry">
    <input type="checkbox" name=<%=ExperimentServlet.FOLGEANALYTIK_OMI%>>
</div>

<div class="experiment_erstellen_header">Informationen zur Folgeanalytik (ToDo / Parameter)</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.INFORMATIONEN_FOLGEANALYSTIK%>>
</div>

<div class="experiment_erstellen_header">Ergebnis DSC</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.ERGEBNIS_DSC%>>
</div>

<div class="experiment_erstellen_header">Ergebnis TG</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.ERGEBNIS_TG%>>
</div>

<div class="experiment_erstellen_header">Ergebnis PXRD II</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.ERGEBNIS_PXRD_II%>>
</div>

<div class="experiment_erstellen_header">Ergebnis H-NMR</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.ERGEBNIS_H_NMR%>>
</div>

<div class="experiment_erstellen_header">Ergebnis IR</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.ERGEBNIS_IR%>>
</div>

<div class="experiment_erstellen_header">Ergebnis Raman</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.ERGEBNIS_RAMAN%>>
</div>

<div class="experiment_erstellen_header">Ergebnis OMI</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.ERGEBNIS_OMI%>>
</div>

<div class="experiment_erstellen_header">Status Analystik</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.STATUS_ANALYSTIK%>>
</div>

<div class="experiment_erstellen_header">Gesamt Ergebnis</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.GESAMT_ERGEBNIS%>>
</div>

<div class="experiment_erstellen_header">Einstufung Ergebnis</div>
<div class="experiment_erstellen_entry">
    <input type="text" name=<%=ExperimentServlet.EINSTUFUNG_ERGEBNIS%>>
</div>