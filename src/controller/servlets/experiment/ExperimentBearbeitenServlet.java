package controller.servlets.experiment;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.relations.ExperimentExperimenttyp;
import model.database.tableModels.experimente.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;

import static controller.servlets.experiment.ExperimentErstellenServlet.*;

@WebServlet(ExperimentBearbeitenServlet.ROUTE)
public class ExperimentBearbeitenServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(ExperimentBearbeitenServlet.class.getName());
    public static final String ROUTE = "/experiment/bearbeiten";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String primaryKey = req.getParameter("id");
        JSON json;

        if (primaryKey != null && !primaryKey.isEmpty())
            try {
                Experiment experiment = new Experiment(primaryKey);
                ExperimenteModel experimenttyp = new ExperimentExperimenttyp(experiment).getTypModel();

                if (experimenttyp instanceof ExperimenttypVerdampfung) {
                    ExperimenttypVerdampfung verdampfung = (ExperimenttypVerdampfung) experimenttyp;
                    json = verdampfung.toJSON();
                } else if (experimenttyp instanceof ExperimenttypSlurry) {
                    ExperimenttypSlurry slurry = (ExperimenttypSlurry) experimenttyp;
                    json = slurry.toJSON();
                } else {
                    throw new ModelNotFoundException("");
                }

                json.addKeyValue(Experiment.COLUMN_PROBEN_NR, experiment.getProbenNr());

                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/json;charset=utf-8");
                resp.getWriter().print(json);

            } catch (ModelNotFoundException e) {
                //e.printStackTrace();
            } catch (SQLException throwables) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.setContentType("text/plain");
                resp.getWriter().println(throwables);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ExperimenteModel verdampfung = updateExperimentTyp(request);
            verdampfung.updateModel();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            response.getWriter().println(throwables);
        }
        catch (DublicateModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Experiment updateExperiment(HttpServletRequest request) throws SQLException, ModelNotFoundException {
        String primaryKey = request.getParameter(NO_ID);
        String api = request.getParameter(API_STARTMATERIAL);
        Experiment experiment = new Experiment(primaryKey);
        experiment.setProbenNr(api);
        experiment.updateModel();
        return experiment;
    }

    private ExperimenteModel updateExperimentTyp(HttpServletRequest request) throws ModelNotFoundException, SQLException, DublicateModelException {
        Experiment experiment = updateExperiment(request);
        ExperimenteModel experimenteModel = new ExperimentExperimenttyp(experiment).getTypModel();

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);
            LOGGER.debug(parameterName + ": " + parameter);

            switch (parameterName) {
                case SCREENING_NO:
                    experimenteModel.setScreening_no(parameter);
                    break;
                case PLANUNG_ERFOLGT_DURCH:
                    experimenteModel.setPlanung_erfolgt_durch(Integer.parseInt(parameter));
                    break;
                case EXPERIMENT_SERIE:
                    try {
                        if (parameter.equals("new")) createNewExperimentSerie(request);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    experimenteModel.setExperiment_serie(request.getParameter(EXPERIMENT_SERIE_TEXT));
                    break;
                case EXPERIMENT_NO:
                    experimenteModel.setExperiment_no(parameter);
                    break;
                case DURCHFUEHRUNGSTEXT:
                    try {
                        if (parameter.equals("new")) createNewDurchfuehrungstext(parameter, request);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    experimenteModel.setDurchfuehrung(parameter);
                    break;
                case PROJEKTLEITERNOTIZ_INTENTION:
                    experimenteModel.setProjektleiternotiz_intention(parameter);
                    break;
                case VERWEIS:
                    experimenteModel.setVerweis(parameter);
                    break;
                case STARTFREIGABE:
                    try {
                        experimenteModel.setStartfreigabe(Date.valueOf(parameter));
                    } catch (IllegalArgumentException ignored) {
                        // keep old value
                    }
                    break;
                case ERLEDIGT_BIS:
                    try {
                        experimenteModel.setErledigt_bis(Date.valueOf(parameter));
                    } catch (IllegalArgumentException ignored) {
                        // keep old value
                    }
                    break;
                case HINWEIS_AN_LABORLEITER:
                    experimenteModel.setHinweis_an_laborleiter(parameter);
                    break;
                case PLANUNG_ABGESCHLOSSEN:
                    experimenteModel.setPlanung_abgeschlossen(Boolean.parseBoolean(parameter));
                    break;
                case PRIORITAET_EXPERIMENT:
                    experimenteModel.setPrioritaet_experiment(parameter);
                    break;
                case SICHERHEITSHINWEIS:
                    experimenteModel.setSicherheitshinweis(parameter);
                    break;
                case VERANTWORTLICHER_OPERATOR:
                    experimenteModel.setVerantwortlicher_operator(Integer.parseInt(parameter));
                    break;
                case EXPERIMENT_START:
                    try {
                        experimenteModel.setExperiment_start(Date.valueOf(parameter));
                    } catch (IllegalArgumentException ignored) {
                        // keep old value
                    }
                    break;
                case VIAL_TARA_G:
                    if (experimenteModel instanceof ExperimenttypVerdampfung)
                        ((ExperimenttypVerdampfung) experimenteModel).setVial_tara(Double.parseDouble(parameter));
                    break;
                case API_STARTMATERIAL_SOLL_EINWAAGE:
                    experimenteModel.setApi_startmaterial_soll_einwaage(Double.parseDouble(parameter));
                    break;
                case API_STARTMATERIAL_SOLL_EINWAAGE_MG:
                    experimenteModel.setApi_startmaterial_soll_einwaage_mg(Double.parseDouble(parameter));
                    break;
                case COF_BEZEICHNUNG:
                    experimenteModel.setCof_bezeichnung(parameter);
                    break;
                case COF_REF_CODE:
                    experimenteModel.setCof_ref_code(parameter);
                    break;
                case COF_SOLL_EINWAAGE:
                    experimenteModel.setCof_soll_einwaaage(Double.parseDouble(parameter));
                    break;
                case COF_SOLL_EINWAAGE_MG:
                    experimenteModel.setCof_soll_einwaage_mg(Double.parseDouble(parameter));
                    break;
                case SOLL_TEMPERATUR:
                    experimenteModel.setSoll_temperatur(Double.parseDouble(parameter));
                    break;
                case LOESUNGSMITTEL_API_COF:
                    experimenteModel.setLoesungsmittel_fuer_api_cof(parameter);
                    break;
                case VORGABE_INFO_VOLUMEN:
                    experimenteModel.setVorgabe_info_volumen(parameter);
                    break;
                case LOESUNGSMITTEL_IN_VOLUMEN:
                    experimenteModel.setLoesungsmittel_ist_volumen(parameter);
                    break;
                case BEOBACHTUNG_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS:
                    if (experimenteModel instanceof ExperimenttypSlurry)
                        ((ExperimenttypSlurry) experimenteModel).setBeobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments(parameter);
                    break;
                case BEOBACHTUNG_LOESUNGSVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS:
                    if (experimenteModel instanceof ExperimenttypVerdampfung)
                        ((ExperimenttypVerdampfung) experimenteModel).setBeobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments(parameter);
                    break;
                case BEOBACHTUNGEN_EXPERIMENTVERLAUF:
                    experimenteModel.setBeobachtungen_zum_experimentverlauf(parameter);
                    break;
                case EXPERIMENT_ENDE:
                    try {
                        experimenteModel.setExperiment_ende(Date.valueOf(parameter));
                    } catch (IllegalArgumentException ignored) {
                        // keep old value
                    }
                    break;
                case STATUS_EXPERIMENT:
                    experimenteModel.setStatus_experiment(parameter);
                    break;
                case AUFBEREITUNG_PRAESENTATION_PXRD:
                    try {
                        experimenteModel.setAufarbeitung_praesentation_pxrd(Date.valueOf(parameter));
                    } catch (IllegalArgumentException ignored) {
                        // keep old value
                    }
                    break;
                case BEOBACHTUNGEN_ENDE_EXPERIMENT_UND_AUFBEREITUNG:
                    experimenteModel.setBeobachtung_zum_ende_des_experiments_aufarbeitung(parameter);
                    break;
                case AUSBEUTE_VIAL_KRISTALLAT_G:
                    if (experimenteModel instanceof ExperimenttypVerdampfung)
                        ((ExperimenttypVerdampfung) experimenteModel).setAuswaage_vial_kristallisat(Double.parseDouble(parameter));
                    break;
                case AUSBEUTE_MG_PRAEP_ANALYTIK:
                    if (experimenteModel instanceof ExperimenttypVerdampfung)
                        ((ExperimenttypVerdampfung) experimenteModel).setAusbeute_von_praep_analytik(Double.parseDouble(parameter));
                    break;
                case STANDORT_LAGERORT_FINALE_PROBE:
                    experimenteModel.setStandort_lagerorte_der_finalen_probe(parameter);
                    break;
                case PRIORITAET_ANALYTIK:
                    experimenteModel.setPrioritaet_analytik(parameter);
                    break;
                case ERSTANALYTIK_PXRD:
                    experimenteModel.setErstanalytik_pxrd(parameter);
                    break;
                case PXRD_MOEGLICH:
                    experimenteModel.setPxrd_moeglich(Boolean.parseBoolean(parameter));
                    break;
                case AUSBEUTE_REST_WEITERE_ANALYTIK_MOEGLICH:
                    experimenteModel.setMit_ausbeute_rest_weitere_analytik_moeglich(parameter);
                    break;
                case ERGEBNIS_PXRD:
                    experimenteModel.setErgebnis_pxrd(parameter);
                    break;
                case FOLGEANALYTIK_DSC:
                    experimenteModel.setFolgeanalytik_dsc(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_TG:
                    experimenteModel.setFolgeanalytik_tg(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_PXRD_II:
                    experimenteModel.setFolgeanalytik_pxrd_ii(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_H_NMR:
                    experimenteModel.setFolgeanalytik_h_nmr(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_IR:
                    experimenteModel.setFolgeanalytik_ir(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_RAMAN:
                    experimenteModel.setFolgeanalytik_raman(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_OMI:
                    experimenteModel.setFolgeanalytik_omi(Boolean.parseBoolean(parameter));
                    break;
                case INFORMATIONEN_FOLGEANALYTIK:
                    experimenteModel.setInformationen_zur_folgeanalytik(parameter);
                    break;
                case ERGEBNIS_DSC:
                    experimenteModel.setErgebnis_dsc(parameter);
                    break;
                case ERGEBNIS_TG:
                    experimenteModel.setErgebnis_tg(parameter);
                    break;
                case ERGEBNIS_PXRD_II:
                    experimenteModel.setErgebnis_pxrd_ii(parameter);
                    break;
                case ERGEBNIS_H_NMR:
                    experimenteModel.setErgebnis_h_nmr(parameter);
                    break;
                case ERGEBNIS_IR:
                    experimenteModel.setErgebnis_ir(parameter);
                    break;
                case ERGEBNIS_RAMAN:
                    experimenteModel.setErgebnis_raman(parameter);
                    break;
                case ERGEBNIS_OMI:
                    experimenteModel.setErgebnis_omi(parameter);
                    break;
                case STATUS_ANALYTIK:
                    experimenteModel.setStatus_analytik(parameter);
                    break;
                case GESAMT_ERGEBNIS:
                    experimenteModel.setGesamt_ergebnis(parameter);
                    break;
                case EINSTUFUNG_ERGEBNIS:
                    experimenteModel.setEinstufung_ergebnis(parameter);
                    break;
            }


        }
        return experimenteModel;
    }

    private void createNewDurchfuehrungstext(String parameter, HttpServletRequest request) throws SQLException, DublicateModelException, ModelNotFoundException {
        ExperimentDurchfuehrungstext durchfuehrungstext = new ExperimentDurchfuehrungstext();
        durchfuehrungstext.setPrimaryKey(parameter);
        durchfuehrungstext.setText(request.getParameter(DURCHFUEHRUNGSTEXT_TEXT));
        durchfuehrungstext.saveToDatabase();
    }

    private void createNewExperimentSerie(HttpServletRequest parameter) throws SQLException, DublicateModelException, ModelNotFoundException {
        ExperimentSerie serie = new ExperimentSerie();
        serie.setPrimaryKey(parameter.getParameter(EXPERIMENT_SERIE_TEXT));
        serie.saveToDatabase();
    }
}
