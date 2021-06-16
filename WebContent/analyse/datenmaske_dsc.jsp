<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="controller.servlets.analyse.AnalyseServlet"%>
<%@ page import="model.database.tableModels.ModelTable"%>
<%@ page import="model.database.tableModels.Mitarbeiter"%>
<%@ page import="model.database.tableModels.Model"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="exceptions.ModelNotFoundException"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="model.database.tableModels.analyse.AnalyseTemperaturprogramme"%>
<%@ page import="model.database.tableModels.experimente.Experiment"%>
<%@ page import="model.database.relations.ExperimentExperimenttyp"%>
<%@ page import="model.database.tableModels.experimente.ExperimenteModel"%>

<%!
    private ModelTable modelList;

    {
        try {
            modelList = new ModelTable(new AnalyseTemperaturprogramme());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }
%>

<div class="analyse_erstellen_header">Analyse ID</div>
<div class="analyse_erstellen_entry">
	<input required type="number" min="1" name=<%=AnalyseServlet.ANALYSE_ID%>>
</div>

<div class="analyse_erstellen_header">Experiment ID</div>
<div class="analyse_erstellen_entry">
	<input type="text" disabled required name=<%=AnalyseServlet.EXPERIMENT_ID%>>

	<!-- <select required name=<%=AnalyseServlet.EXPERIMENT_ID%>>
        <option value="" selected disabled>bitte auswaehlen</option>
        <%
            try {
                ModelTable modelList = new ModelTable(new Experiment());
                for (Model model : modelList.getModelList()) {
                    ExperimenteModel experimenteModel = new ExperimentExperimenttyp((Experiment) model).getTypModel();
        %>
        <option value=<%=model.getPrimaryKey()%>><%=experimenteModel.getExperiment_no()%>
        </option>
        <%
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        %>
    </select> -->

</div>

<div class="analyse_erstellen_header">Datum</div>
<div class="analyse_erstellen_entry">
	<input type="date" name=<%=AnalyseServlet.DATUM%>>
</div>

<div class="analyse_erstellen_header">Einwaage [mg]</div>
<div class="analyse_erstellen_entry">
	<input type="number" min="0" step="0.001" name=<%=AnalyseServlet.EINWAAGE_MG%>>
</div>

<div class="analyse_erstellen_header">Auswaage [mg]</div>
<div class="analyse_erstellen_entry">
	<input type="number" min="0" step="0.001" name=<%=AnalyseServlet.AUSWAAGE_MG%>>
</div>

<div class="analyse_erstellen_header">Rampe [K/min]</div>
<div class="analyse_erstellen_entry">
	<input type="number" min="0" name=<%=AnalyseServlet.RAMPE_K_MIN%>>
</div>

<div class="analyse_erstellen_header">Temperaturprogramm</div>
<div class="analyse_erstellen_entry">
	<select required onchange="showTemperaturprogramm(this)" name=<%=AnalyseServlet.TEMPERATURPROGRAMM%>>
		<option value="" selected disabled>bitte auswaehlen</option>
		<option value="new">Neues Temperaturprogramm</option>
		<%
            if (modelList != null) {
                Set<String> tabellen = modelList.getModelList().stream().map(model -> ((AnalyseTemperaturprogramme) model).getTabelle()).collect(Collectors.toSet());
                for (String tabelle : tabellen) {
        %>
		<option value="<%=tabelle%>">Tabelle
			<%=tabelle%>
		</option>
		<%
                }
            }
        %>
	</select>
	<input id="temperaurprogramme_table_title" style="display: none" type="number" min="1" name=<%=AnalyseServlet.TEMPERATURPROGRAMM_TITEL%>>
	<div id="temperaturprogramme_table" style="display: none">
		<div class="temperaturprogramme_methode_add_button">
			<input type="button" value=" neue Zeile " onclick="newTemperaturprogrammRow(this)">
		</div>
		<table id="template_temperaturprogramme">
			<tbody>
				<tr>
					<th>Schritt</th>
					<th>Temperatur [°C]</th>
					<th>Rampe [k/min]</th>
					<th>Zeit [min]</th>
					<th>Segmenttyp</th>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div class="analyse_erstellen_header">Tiegel</div>
<div class="analyse_erstellen_entry">
	<input type="text" name=<%=AnalyseServlet.TIEGEL%>>
</div>

<div class="analyse_erstellen_header">Tiegelpräsentation</div>
<div class="analyse_erstellen_entry">
	<input type="text" name=<%=AnalyseServlet.TIEGELPRAESENTATION%>>
</div>

<div class="analyse_erstellen_header">Bemerkung</div>
<div class="analyse_erstellen_entry">
	<input type="text" name=<%=AnalyseServlet.BEMERKUNG%>>
</div>

<div class="analyse_erstellen_header">Operator</div>
<div class="analyse_erstellen_entry">
	<select required name=<%=AnalyseServlet.OPERATOR%>>
		<option value="" selected disabled>bitte auswaehlen</option>
		<%
            try {
                ModelTable modelList = new ModelTable(new Mitarbeiter());
                for (Model model : modelList.getModelList()) {
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

<!-- TABLE ROW -->
<table style="display: none">
	<tr class="temperaturprogramm_tamplate_table_row">
		<td class="eingangsanalyse_entry">
			<input type="number" min="1" name="<%=AnalyseServlet.TEMPERATURPROGRAMM_SCHRITT%>">
		</td>

		<td class="eingangsanalyse_entry">
			<input type="number" name=<%=AnalyseServlet.TEMPERATURPROGRAMM_TEMPERATUR%>>
		</td>

		<td class="eingangsanalyse_entry">
			<input type="number" min="0" name=<%=AnalyseServlet.TEMPERATURPROGRAMM_RAMPE%>>
		</td>

		<td class="eingangsanalyse_entry">
			<input type="number" min="0" name=<%=AnalyseServlet.TEMPERATURPROGRAMM_ZEIT%>>
		</td>

		<td class="eingangsanalyse_entry">
			<input type="text" name="<%=AnalyseServlet.TEMPERATURPROGRAMM_SEGMENTTYP%>">
		</td>
	</tr>
</table>

<script type="text/javascript">
    function showTemperaturprogramm(param) {
        $(".temperaturprogramm_table_row").remove()
        $("#temperaurprogramme_table_title").prop('required', param.value === "new")
        if (param.value === "new") {
            $("#temperaurprogramme_table_title").show()
        } else {
            $("#temperaurprogramme_table_title").hide()
                <%
                if (modelList != null) {
                ModelTable modelList = new ModelTable(new AnalyseTemperaturprogramme());
                for (Model model : modelList.getModelList()) {
                    AnalyseTemperaturprogramme row = (AnalyseTemperaturprogramme) model;

            %>
            if (param.value == <%= row.getTabelle() %>) {
                        const row = $(".temperaturprogramm_tamplate_table_row").clone();
                        row.attr("class", "temperaturprogramm_table_row")
                        row.children("td.eingangsanalyse_entry").children('input[name="<%=AnalyseServlet.TEMPERATURPROGRAMM_SCHRITT%>"]').prop('required', true)
                        row.children("td.eingangsanalyse_entry").children('input[name="<%=AnalyseServlet.TEMPERATURPROGRAMM_SCHRITT%>"]').val(<%=row.getSchritt() %>)
                        row.children("td.eingangsanalyse_entry").children('input[name="<%=AnalyseServlet.TEMPERATURPROGRAMM_TEMPERATUR%>"]').val(<%=row.getTemperatur() %>)
                        row.children("td.eingangsanalyse_entry").children('input[name="<%=AnalyseServlet.TEMPERATURPROGRAMM_RAMPE%>"]').val(<%=row.getRampe() %>)
                        row.children("td.eingangsanalyse_entry").children('input[name="<%=AnalyseServlet.TEMPERATURPROGRAMM_ZEIT%>"]').val(<%=row.getZeit() %>)
                        row.children("td.eingangsanalyse_entry").children('input[name="<%=AnalyseServlet.TEMPERATURPROGRAMM_SEGMENTTYP%>"]').val("<%=row.getSegmenttyp()%>")
                        $("#template_temperaturprogramme tr:last").after(row)
                    }
            <%
                    }
            }
            %>
        }
        $("#temperaturprogramme_table").show()
    }

    analyseErstellenInit();

</script>