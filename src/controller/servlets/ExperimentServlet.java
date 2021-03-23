package controller.servlets;

import config.Config;
import exceptions.ModelNotFoundException;
import model.database.tableModels.experimente.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet("/experiment/erstellen")
public class ExperimentServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Config.class.getName());

    // Basisfelder
    public static final String NO_ID = "EXPERIMENT_NO_ID";
    public static final String API_STARTMATERIAL = "EXPERIMENT_API_STARTMATERIAL";
    public static final String SCREENING_NO = "EXPERIMENT_SCREENING_NO";
    public static final String PLANUNG_ERFOLGT_DURCH = "EXPERIMENT_PLANUNG_ERFOLGT_DURCH";
    public static final String EXPERIMENT_SERIE = "EXPERIMENT_EXPERIMENT_SERIE";
    public static final String EXPERIMENT_SERIE_TEXT = "EXPERIMENT_EXPERIMENT_SERIE_TEXT";
    public static final String EXPERIMENT_NO = "EXPERIMENT_EXPERIMENT_NO"; // Ist zwei mal da
    public static final String DURCHFUEHRUNGSTEXT = "EXPERIMENT_DURCHFUEHRUNGSTEXT";
    public static final Object DURCHFUEHRUNGSTEXT_TITEL = "EXPERIMENT_DURCHFUEHRUNGSTEXT_TITEL";
    public static final String DURCHFUEHRUNGSTEXT_TEXT = "EXPERIMENT_DURCHFUEHRUNGSTEXT_TEXT";
    public static final String PROJEKTLEITERNOTIZ_INTENTION = "EXPERIMENT_PROJEKTLEITERNOTIZ_INTENTION";
    public static final String VERWEIS = "EXPERIMENT_VERWEIS";
    public static final String STARTFREIGABE = "EXPERIMENT_STARTFREIGABE";
    public static final String ERLEDIGT_BIS = "EXPERIMENT_ERLEDIGT_BIS";
    public static final String HINWEIS_AN_LABORLEITER = "EXPERIMENT_KINWEIS_AN_LABORLEITER";
    public static final String PLANUNG_ABGESCHLOSSEN = "EXPERIMENT_PLANUNG_ABGESCHLOSSEN";
    public static final String PRIORITAET_EXPERIMENT = "EXPERIMENT_PRIORITAET_EXPERIMENT";
    public static final String SICHERHEITSHINWEIS = "EXPERIMENT_SICHERHEITSHINWEIS";
    public static final String VERANTWORTLICHER_OPERATOR = "EXPERIMENT_VERANTWORTLICHER_OPERATOR";
    public static final String EXPERIMENT_START = "EXPERIMENT_EXPERIMENT_START"; // Ist zwei mal da
    public static final String API_STARTMATERIAL_SOLL_EINWAAGE = "EXPERIMENT_API_STARTMATERIAL_SOLL_EINWAAGE";
    public static final String API_STARTMATERIAL_SOLL_EINWAAGE_MG = "EXPERIMENT_API_STARTMATERIAL_SOLL_EINWAAGE_MG";
    public static final String COF_BEZEICHNUNG = "EXPERIMENT_COF_BEZEICHNUNG";
    public static final String COF_REF_CODE = "EXPERIMENT_COF_REF_CODE";
    public static final String COF_SOLL_EINWAAGE = "EXPERIMENT_COF_SOLL_EINWAAGE";
    public static final String COF_SOLL_EINWAAGE_MG = "EXPERIMENT_COF_SOLL_EINWAAGE_MG";
    public static final String SOLL_TEMPERATUR = "EXPERIMENT_SOLL_TEMPERATUR";
    public static final String LOESUNGSMITTEL_API_COF = "EXPERIMENT_LOESUNGSMITTEL_API_COF";
    public static final String LOESUNGSMITTEL_IN_VOLUMEN = "EXPERIMENT_LOESUNGSMITTEL_IN_VOLUMEN";
    public static final String BEOBACHTUNGEN_EXPERIMENTVERLAUF = "EXPERIMENT_BEOBACHTUNGEN_EXPERIMENTVERLAUF";
    public static final String EXPERIMENT_ENDE = "EXPERIMENT_EXPERIMENT_ENDE";
    public static final String STATUS_EXPERIMENT = "EXPERIMENT_STATUS_EXPERIMENT";
    public static final String AUFBEREITUNG_PRAESENTATION_PXRD = "EXPERIMENT_AUFBEREITUNG_PRAESENTATION_PXRD";
    public static final String BEOBACHTUNGEN_ENDE_EXPERIMENT_UND_AUFBEREITUNG = "EXPERIMENT_BEOBACHTUNGEN_ENDE_EXPERIMENT_UND_AUFBEREITUNG";
    public static final String STANDORT_LAGERORT_FINALE_PROBE = "EXPERIMENT_STANDORT_LAGERORT_FINALE_PROBE";
    public static final String PRIORITAET_ANALYSTIK = "EXPERIMENT_PRIORITAET_ANALYSTIK";
    public static final String ERSTANALYSTIK_PXRD = "EXPERIMENT_ERSTANALYSTIK_PXRD";
    public static final String PXRD_MOEGLICH = "EXPERIMENT_PXRD_MOEGLICH";
    public static final String AUSBEUTE_REST_WEITERE_ANALYSTIK_MOEGLICH = "EXPERIMENT_AUSBEUTE_REST_WEITERE_ANALYSTIK_MOEGLICH";
    public static final String ERGEBNIS_PXRD = "EXPERIMENT_ERGEBNIS_PXRD";
    public static final String FOLGEANALYTIK_DSC = "EXPERIMENT_FOLGEANALYTIK_DSC";
    public static final String FOLGEANALYTIK_TG = "EXPERIMENT_FOLGEANALYTIK_TG";
    public static final String FOLGEANALYTK_PXRD_II = "EXPERINENT_FOLGEANALYTK_PXRD_II";
    public static final String FOLGEANALYTIK_H_NMR = "EXPERIMENT_FOLGEANALYTIK_H_NMR";
    public static final String FOLGEANALYTIK_IR = "EXPERIMENT_FOLGEANALYTIK_IR";
    public static final String FOLGEANALYTIK_RAMAN = "EXPERIMENT_FOLGEANALYTIK_RAMAN";
    public static final String FOLGEANALYTIK_OMI = "EXPERIMENT_FOLGEANALYTIK_OMI";
    public static final String INFORMATIONEN_FOLGEANALYSTIK = "EXPERIMENT_INFORMATIONEN_FOLGEANALYSTIK";
    public static final String ERGEBNIS_DSC = "EXPERIMENT_ERGEBNIS_DSC";
    public static final String ERGEBNIS_TG = "EXPERIMENT_ERGEBNIS_TG";
    public static final String ERGEBNIS_PXRD_II = "EXPERIMENT_ERGEBNIS_PXRD_II";
    public static final String ERGEBNIS_H_NMR = "EXPERIMENT_ERGEBNIS_H_NMR";
    public static final String ERGEBNIS_IR = "EXPERIMENT_ERGEBNIS_IR";
    public static final String ERGEBNIS_RAMAN = "EXPERIMENT_ERGEBNIS_RAMAN";
    public static final String ERGEBNIS_OMI = "EXPERIMENT_ERGEBNIS_OMI";
    public static final String STATUS_ANALYSTIK = "EXPERIMENT_STATUS_ANALYSTIK";
    public static final String GESAMT_ERGEBNIS = "EXPERIMENT_GESAMT_ERGEBNIS";
    public static final String EINSTUFUNG_ERGEBNIS = "EXPERIMENT_EINSTUFUNG_ERGEBNIS";
    public static final String TYP = "EXPERIMENT_TYP";
    public static final String VORGABE_INFO_VOLUMEN = "EXPERIMENT_VORGABE_INFO_VOLUMEN";

    // Slurry
    public static final String BEOBACHTUNG_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS = "EXPERIMENT_BEOBACHTUNG_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS";

    // Verdampfung
    public static final String VIAL_TARA_G = "EXPERIMENT_VIAL_TARA_G";
    public static final String AUSBEUTE_VIAL_KRISTALLAT_G = "EXPERIMENT_AUSBEUTE_VIAL_KRISTALLAT_G";
    public static final String BEOBACHTUNG_LOESUNGSVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS = "EXPERIMENT_BEOBACHTUNG_LOESUNGSVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS";
    public static final String AUSBEUTE_MG_PRAEP_ANALYTIK = "EXPERIMENT_AUSBEUTE_MG_PRAEP_ANALYTIK";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(this.getClass() + ": doPost()");

        try {
            String typId = request.getParameter(TYP);
            String api = request.getParameter(API_STARTMATERIAL);
            Experimenttyp typ = new Experimenttyp(typId);
            ExperimenteModel verdampfung = createExperiment(request, typ.getTyp());
            verdampfung.saveToDatabase();
            createExperiment(verdampfung.getPrimaryKey(), api, typ.getPrimaryKey());
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            response.getWriter().println(throwables);
        }
    }

    private void createExperiment(String primaryKey, String api, String typ) throws SQLException, ModelNotFoundException {
        Experiment experiment = new Experiment();
        experiment.setPrimaryKey(primaryKey);
        experiment.setProbenNr(api);
        experiment.setTyp(typ);
        experiment.saveToDatabase();
    }

    private ExperimenteModel createExperiment(HttpServletRequest request, String typ) throws ModelNotFoundException, SQLException {
        ExperimenteModel experiment = null;
        switch (typ) {
            case "Slurry":
                experiment = new ExperimenttypSlurry();
                break;
            case "Verdampfung":
                experiment = new ExperimenttypVerdampfung();
                break;
            default:
                throw new ModelNotFoundException("No Applyeable Model found for Create Experiment");
        }

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);
            LOGGER.debug(parameterName + ": " + parameter);

            switch (parameterName) {
                case NO_ID:
                    experiment.setPrimaryKey(parameter);
                    break;
                case SCREENING_NO:
                    experiment.setScreening_no(parameter);
                    break;
                case PLANUNG_ERFOLGT_DURCH:
                    experiment.setPlanung_erfolgt_durch(Integer.parseInt(parameter));
                    break;
                case EXPERIMENT_SERIE:
                    try {
                        if (parameter.equals("new")) createNewExperimentSerie(request);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    experiment.setExperiment_serie(parameter);
                    break;
                case EXPERIMENT_NO:
                    experiment.setExperiment_no(parameter);
                    break;
                case DURCHFUEHRUNGSTEXT:
                    try {
                        if (parameter.equals("new")) createNewDurchfuehrungstext(parameter, request);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    experiment.setScreening_no(parameter);
                    break;
                case PROJEKTLEITERNOTIZ_INTENTION:
                    experiment.setProjektleiternotiz_intention(parameter);
                    break;
                case VERWEIS:
                    experiment.setVerweis(parameter);
                    break;
                case STARTFREIGABE:
                    try {
                        experiment.setStartfreigabe(Date.valueOf(parameter));
                    } catch (IllegalArgumentException e) {
                        experiment.setStartfreigabe(null);
                    }
                    break;
                case ERLEDIGT_BIS:
                    try {
                        experiment.setErledigt_bis(Date.valueOf(parameter));
                    } catch (IllegalArgumentException e) {
                        experiment.setErledigt_bis(null);
                    }
                    break;
                case HINWEIS_AN_LABORLEITER:
                    experiment.setHinweis_an_laborleiter(parameter);
                    break;
                case PLANUNG_ABGESCHLOSSEN:
                    experiment.setPlanung_abgeschlossen(Boolean.getBoolean(parameter));
                    break;
                case PRIORITAET_EXPERIMENT:
                    experiment.setPrioritaet_experiment(parameter);
                    break;
                case SICHERHEITSHINWEIS:
                    experiment.setSicherheitshinweis(parameter);
                    break;
                case VERANTWORTLICHER_OPERATOR:
                    experiment.setVerantwortlicher_operator(Integer.parseInt(parameter));
                    break;
                case EXPERIMENT_START:
                    try {
                        experiment.setExperiment_start(Date.valueOf(parameter));
                    } catch (IllegalArgumentException e) {
                        experiment.setExperiment_start(null);
                    }
                    break;
                case VIAL_TARA_G:
                    if (experiment instanceof ExperimenttypVerdampfung)
                        ((ExperimenttypVerdampfung) experiment).setVial_tara(Double.parseDouble(parameter));
                    break;
                case API_STARTMATERIAL_SOLL_EINWAAGE:
                    experiment.setApi_startmaterial_soll_einwaage(Double.parseDouble(parameter));
                    break;
                case API_STARTMATERIAL_SOLL_EINWAAGE_MG:
                    experiment.setApi_startmaterial_soll_einwaage_mg(Double.parseDouble(parameter));
                    break;
                case COF_BEZEICHNUNG:
                    experiment.setCof_bezeichnung(parameter);
                    break;
                case COF_REF_CODE:
                    experiment.setCof_ref_code(parameter);
                    break;
                case COF_SOLL_EINWAAGE:
                    experiment.setCof_soll_einwaaage(Double.parseDouble(parameter));
                    break;
                case COF_SOLL_EINWAAGE_MG:
                    experiment.setCof_soll_einwaage_mg(Double.parseDouble(parameter));
                    break;
                case SOLL_TEMPERATUR:
                    experiment.setSoll_temperatur(Double.parseDouble(parameter));
                    break;
                case LOESUNGSMITTEL_API_COF:
                    experiment.setLoesungsmittel_fuer_api_cof(parameter);
                    break;
                case VORGABE_INFO_VOLUMEN:
                    experiment.setVorgabe_info_volumen(parameter);
                    break;
                case LOESUNGSMITTEL_IN_VOLUMEN:
                    experiment.setLoesungsmittel_ist_volumen(parameter);
                    break;
                case BEOBACHTUNG_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS:
                    if (experiment instanceof ExperimenttypSlurry)
                        ((ExperimenttypSlurry) experiment).setBeobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments(parameter);
                    break;
                case BEOBACHTUNG_LOESUNGSVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS:
                    if (experiment instanceof ExperimenttypVerdampfung)
                        ((ExperimenttypVerdampfung) experiment).setBeobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments(parameter);
                    break;
                case BEOBACHTUNGEN_EXPERIMENTVERLAUF:
                    experiment.setBeobachtungen_zum_experimentverlauf(parameter);
                    break;
                case EXPERIMENT_ENDE:
                    try {
                        experiment.setExperiment_ende(Date.valueOf(parameter));
                    } catch (IllegalArgumentException e) {
                        experiment.setExperiment_ende(null);
                    }
                    break;
                case STATUS_EXPERIMENT:
                    experiment.setStatus_experiment(parameter);
                    break;
                case AUFBEREITUNG_PRAESENTATION_PXRD:
                    try {
                        experiment.setAufarbeitung_praesentation_pxrd(Date.valueOf(parameter));
                    } catch (IllegalArgumentException e) {
                        experiment.setAufarbeitung_praesentation_pxrd(null);
                    }
                    break;
                case BEOBACHTUNGEN_ENDE_EXPERIMENT_UND_AUFBEREITUNG:
                    experiment.setBeobachtung_zum_ende_des_experiments_aufarbeitung(parameter);
                    break;
                case AUSBEUTE_VIAL_KRISTALLAT_G:
                    if (experiment instanceof ExperimenttypVerdampfung)
                        ((ExperimenttypVerdampfung) experiment).setAuswaage_vial_kristallisat(Double.parseDouble(parameter));
                    break;
                case AUSBEUTE_MG_PRAEP_ANALYTIK:
                    if (experiment instanceof ExperimenttypVerdampfung)
                        ((ExperimenttypVerdampfung) experiment).setAusbeute_von_praep_analystik(Double.parseDouble(parameter));
                    break;
                case STANDORT_LAGERORT_FINALE_PROBE:
                    experiment.setStandort_lagerorte_der_finalen_probe(parameter);
                    break;
                case PRIORITAET_ANALYSTIK:
                    experiment.setPrioritaet_analytik(parameter);
                    break;
                case ERSTANALYSTIK_PXRD:
                    experiment.setErstanalytik_pxrd(parameter);
                    break;
                case PXRD_MOEGLICH:
                    experiment.setPxrd_moeglich(Boolean.parseBoolean(parameter));
                    break;
                case AUSBEUTE_REST_WEITERE_ANALYSTIK_MOEGLICH:
                    experiment.setMit_ausbeute_rest_weitere_analytik_moeglich(parameter);
                    break;
                case ERGEBNIS_PXRD:
                    experiment.setErgebnis_pxrd(parameter);
                    break;
                case FOLGEANALYTIK_DSC:
                    experiment.setFolgeanalytik_dsc(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_TG:
                    experiment.setFolgeanalytik_tg(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTK_PXRD_II:
                    experiment.setFolgeanalytik_pxrd_ii(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_H_NMR:
                    experiment.setFolgeanalytik_h_nmr(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_IR:
                    experiment.setFolgeanalytik_ir(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_RAMAN:
                    experiment.setFolgeanalytik_raman(Boolean.parseBoolean(parameter));
                    break;
                case FOLGEANALYTIK_OMI:
                    experiment.setFolgeanalytik_omi(Boolean.parseBoolean(parameter));
                    break;
                case INFORMATIONEN_FOLGEANALYSTIK:
                    experiment.setInformationen_zur_folgeanalytik(parameter);
                    break;
                case ERGEBNIS_DSC:
                    experiment.setErgebnis_dsc(parameter);
                    break;
                case ERGEBNIS_TG:
                    experiment.setErgebnis_tg(parameter);
                    break;
                case ERGEBNIS_PXRD_II:
                    experiment.setErgebnis_pxrd_ii(parameter);
                    break;
                case ERGEBNIS_H_NMR:
                    experiment.setErgebnis_h_nmr(parameter);
                    break;
                case ERGEBNIS_IR:
                    experiment.setErgebnis_ir(parameter);
                    break;
                case ERGEBNIS_RAMAN:
                    experiment.setErgebnis_raman(parameter);
                    break;
                case ERGEBNIS_OMI:
                    experiment.setErgebnis_omi(parameter);
                    break;
                case STATUS_ANALYSTIK:
                    experiment.setStatus_analytik(parameter);
                    break;
                case GESAMT_ERGEBNIS:
                    experiment.setGesamt_ergebnis(parameter);
                    break;
                case EINSTUFUNG_ERGEBNIS:
                    experiment.setEinstufung_ergebnis(parameter);
                    break;
            }


        }
        return experiment;
    }

    private void createNewDurchfuehrungstext(String parameter, HttpServletRequest request) throws SQLException {
        ExperimentDurchfuehrungstext durchfuehrungstext = new ExperimentDurchfuehrungstext();
        durchfuehrungstext.setPrimaryKey(parameter);
        durchfuehrungstext.setText(request.getParameter(DURCHFUEHRUNGSTEXT_TEXT));
        durchfuehrungstext.saveToDatabase();
    }

    private void createNewExperimentSerie(HttpServletRequest parameter) throws SQLException {
        ExperimentSerie serie = new ExperimentSerie();
        serie.setPrimaryKey(parameter.getParameter(EXPERIMENT_SERIE_TEXT));
        serie.saveToDatabase();
    }
}
