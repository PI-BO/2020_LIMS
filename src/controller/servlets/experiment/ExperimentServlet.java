package controller.servlets.experiment;

import config.Config;
import config.Const;
import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.relations.ExperimentExperimenttyp;
import model.database.tableModels.experimente.*;
import utility.JSON;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet(ExperimentServlet.ROUTE)
@MultipartConfig
public class ExperimentServlet extends HttpServlet {

	private static final long serialVersionUID = -6868802655204403664L;
	private static final Logger LOGGER = LogManager.getLogger(Config.class.getName());
	public static final String ROUTE = "/experiment";

	// Basisfelder
	public static final String NO_ID = "EXPERIMENT_" + ExperimenteModel.COLUMN_PRIMARY_KEY;
	public static final String API_STARTMATERIAL = "EXPERIMENT_" + Experiment.COLUMN_PROBEN_NR;
	public static final String SCREENING_NO = "EXPERIMENT_" + ExperimenteModel.COLUMN_SCREENING_NO_KEY;
	public static final String PLANUNG_ERFOLGT_DURCH = "EXPERIMENT_" + ExperimenteModel.COLUMN_PLANUNG_ERFOLGT_DURCH_KEY;
	public static final String EXPERIMENT_SERIE = "EXPERIMENT_" + ExperimenteModel.COLUMN_EXPERIMENT_SERIE_KEY;
	public static final String EXPERIMENT_SERIE_TEXT = "EXPERIMENT_" + ExperimenteModel.COLUMN_EXPERIMENT_SERIE_KEY + "_TEXT";
	public static final String EXPERIMENT_NO = "EXPERIMENT_" + ExperimenteModel.COLUMN_EXPERIMENT_NO_KEY; // Ist
																											// zwei
																											// mal
																											// da
	public static final String DURCHFUEHRUNGSTEXT = "EXPERIMENT_" + ExperimenteModel.COLUMN_DURCHFUEHRUNG_KEY;
	public static final Object DURCHFUEHRUNGSTEXT_TITEL = "EXPERIMENT_" + ExperimenteModel.COLUMN_DURCHFUEHRUNG_KEY + "_TITEL";
	public static final String DURCHFUEHRUNGSTEXT_TEXT = "EXPERIMENT_" + ExperimenteModel.COLUMN_DURCHFUEHRUNG_KEY + "_TEXT";
	public static final String PROJEKTLEITERNOTIZ_INTENTION = "EXPERIMENT_" + ExperimenteModel.COLUMN_PROJEKTLEITERNOTIZ_INTENTION_KEY;
	public static final String VERWEIS = "EXPERIMENT_" + ExperimenteModel.COLUMN_VERWEIS_KEY;
	public static final String STARTFREIGABE = "EXPERIMENT_" + ExperimenteModel.COLUMN_STARTFREIGABE_KEY;
	public static final String ERLEDIGT_BIS = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERLEDIGT_BIS_KEY;
	public static final String HINWEIS_AN_LABORLEITER = "EXPERIMENT_" + ExperimenteModel.COLUMN_HINWEIS_LABORLEITER_KEY;
	public static final String PLANUNG_ABGESCHLOSSEN = "EXPERIMENT_" + ExperimenteModel.COLUMN_PLANUNG_ABGESCHLOSSEN_KEY;
	public static final String PRIORITAET_EXPERIMENT = "EXPERIMENT_" + ExperimenteModel.COLUMN_PRIORITAET_EXPERIMENT_KEY;
	public static final String SICHERHEITSHINWEIS = "EXPERIMENT_" + ExperimenteModel.COLUMN_SICHERHEITSHINWEIS_KEY;
	public static final String VERANTWORTLICHER_OPERATOR = "EXPERIMENT_" + ExperimenteModel.COLUMN_VERANTWORTLICHER_OPERATOR;
	public static final String EXPERIMENT_START = "EXPERIMENT_" + ExperimenteModel.COLUMN_EXPERIMENT_START_KEY; // Ist
																												// zwei
																												// mal
																												// da
	public static final String API_STARTMATERIAL_SOLL_EINWAAGE = "EXPERIMENT_" + ExperimenteModel.COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE;
	public static final String API_STARTMATERIAL_SOLL_EINWAAGE_MG = "EXPERIMENT_" + ExperimenteModel.COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE_MG;
	public static final String COF_BEZEICHNUNG = "EXPERIMENT_" + ExperimenteModel.COLUMN_COF_BEZEICHNUNG_KEY;
	public static final String COF_REF_CODE = "EXPERIMENT_" + ExperimenteModel.COLUMN_COF_REF_CODE_KEY;
	public static final String COF_SOLL_EINWAAGE = "EXPERIMENT_" + ExperimenteModel.COLUMN_COF_SOLL_EINWAAGE_KEY;
	public static final String COF_SOLL_EINWAAGE_MG = "EXPERIMENT_" + ExperimenteModel.COLUMN_COF_SOLL_EINWAAGE_MG_KEY;
	public static final String SOLL_TEMPERATUR = "EXPERIMENT_" + ExperimenteModel.COLUMN_SOLL_TEMPERATUR_KEY;
	public static final String LOESUNGSMITTEL_API_COF = "EXPERIMENT_" + ExperimenteModel.COLUMN_LOESUNGSMITTEL_FUER_API_COF_KEY;
	public static final String LOESUNGSMITTEL_IN_VOLUMEN = "EXPERIMENT_" + ExperimenteModel.COLUMN_LOESUNGSMITTEL_IST_VOLUMEN_KEY;
	public static final String BEOBACHTUNGEN_EXPERIMENTVERLAUF = "EXPERIMENT_" + ExperimenteModel.COLUMN_BEOBACHTUNGEN_ZUM_EXPERIMENTVERLAUF_KEY;
	public static final String EXPERIMENT_ENDE = "EXPERIMENT_" + ExperimenteModel.COLUMN_EXPERIMENT_ENDE;
	public static final String STATUS_EXPERIMENT = "EXPERIMENT_" + ExperimenteModel.COLUMN_STATUS_EXPERIMENT_KEY;
	public static final String AUFBEREITUNG_PRAESENTATION_PXRD = "EXPERIMENT_" + ExperimenteModel.COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY;
	public static final String BEOBACHTUNGEN_ENDE_EXPERIMENT_UND_AUFBEREITUNG = "EXPERIMENT_"
			+ ExperimenteModel.COLUMN_BEOBACHTUNG_ZUM_ENDE_DES_EXPERIMENTS_AUFBEREITUNG_KEY;
	public static final String STANDORT_LAGERORT_FINALE_PROBE = "EXPERIMENT_" + ExperimenteModel.COLUMN_STANDORT_LAGERORTE_DER_FINALEN_PROBE;
	public static final String PRIORITAET_ANALYTIK = "EXPERIMENT_" + ExperimenteModel.COLUMN_PRIORITAET_ANALYTIK_KEY;
	public static final String ERSTANALYTIK_PXRD = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERSTANALYTIK_PXRD_KEY;
	public static final String PXRD_MOEGLICH = "EXPERIMENT_" + ExperimenteModel.COLUMN_PXRD_MOEGLICH_KEY;
	public static final String AUSBEUTE_REST_WEITERE_ANALYTIK_MOEGLICH = "EXPERIMENT_" + ExperimenteModel.COLUMN_MIT_AUSBEUTE_REST_WEITERE_ANALYTIK_MOEGLICH_KEY;
	public static final String ERGEBNIS_PXRD = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERGEBNIS_PXRD_KEY;
	public static final String FOLGEANALYTIK_DSC = "EXPERIMENT_" + ExperimenteModel.COLUMN_FOLGEANALYTIK_DSC_KEY;
	public static final String FOLGEANALYTIK_TG = "EXPERIMENT_" + ExperimenteModel.COLUMN_FOLGEANALYTIK_TG_KEY;
	public static final String FOLGEANALYTIK_PXRD_II = "EXPERINENT_" + ExperimenteModel.COLUMN_FOLGEANALYTIK_PXRD_II_KEY;
	public static final String FOLGEANALYTIK_H_NMR = "EXPERIMENT_" + ExperimenteModel.COLUMN_FOLGEANALYTIK_H_NMR_KEY;
	public static final String FOLGEANALYTIK_IR = "EXPERIMENT_" + ExperimenteModel.COLUMN_FOLGEANALYTIK_IR_KEY;
	public static final String FOLGEANALYTIK_RAMAN = "EXPERIMENT_" + ExperimenteModel.COLUMN_FOLGEANALYTIK_RAMAN_KEY;
	public static final String FOLGEANALYTIK_OMI = "EXPERIMENT_" + ExperimenteModel.COLUMN_FOLGEANALYTIK_OMI_KEY;
	public static final String INFORMATIONEN_FOLGEANALYTIK = "EXPERIMENT_" + ExperimenteModel.COLUMN_INFORMATIONEN_ZUR_FOLGEANALYTIK_KEY;
	public static final String ERGEBNIS_DSC = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERGEBNIS_DSC_KEY;
	public static final String ERGEBNIS_TG = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERGEBNIS_TG_KEY;
	public static final String ERGEBNIS_PXRD_II = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERGEBNIS_PXRD_II_KEY;
	public static final String ERGEBNIS_H_NMR = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERGEBNIS_H_NMR_KEY;
	public static final String ERGEBNIS_IR = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERGEBNIS_IR_KEY;
	public static final String ERGEBNIS_RAMAN = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERGEBNIS_RAMAN_KEY;
	public static final String ERGEBNIS_OMI = "EXPERIMENT_" + ExperimenteModel.COLUMN_ERGEBNIS_OMI_KEY;
	public static final String STATUS_ANALYTIK = "EXPERIMENT_" + ExperimenteModel.COLUMN_STATUS_ANALYTIK_KEY;
	public static final String GESAMT_ERGEBNIS = "EXPERIMENT_" + ExperimenteModel.COLUMN_GESAMT_ERGEBNIS_KEY;
	public static final String EINSTUFUNG_ERGEBNIS = "EXPERIMENT_" + ExperimenteModel.COLUMN_EINSTUFUNG_ERGEBNIS_KEY;
	public static final String TYP = "EXPERIMENT_" + Experiment.COLUMN_TYP;
	public static final String VORGABE_INFO_VOLUMEN = "EXPERIMENT_" + ExperimenteModel.COLUMN_VORGABE_INFO_VOLUMEN_KEY;

	// Slurry
	public static final String BEOBACHTUNG_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS = "EXPERIMENT_"
			+ ExperimenttypSlurry.COLUMN_BEOBACHTUNGEN_ZUR_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY;

	// Verdampfung
	public static final String VIAL_TARA_G = "EXPERIMENT_" + ExperimenttypVerdampfung.COLUMN_VIAL_TARA_KEY;
	public static final String AUSBEUTE_VIAL_KRISTALLAT_G = "EXPERIMENT_" + ExperimenttypVerdampfung.COLUMN_AUSWAAGE_VIAL_KRISTALLISAT_KEY;
	public static final String BEOBACHTUNG_LOESUNGSVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS = "EXPERIMENT_"
			+ ExperimenttypVerdampfung.COLUMN_BEOBACHTUNGEN_ZUM_LOESEVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY;
	public static final String AUSBEUTE_MG_PRAEP_ANALYTIK = "EXPERIMENT_" + ExperimenttypVerdampfung.COLUMN_AUSBEUTE_VON_PRAEP_ANALYSTIK_KEY;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOGGER.debug("doPost()");	
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		JSON json = new JSON();

		String primaryKey = request.getParameter(NO_ID);
		String api = request.getParameter(API_STARTMATERIAL);
		String typId = request.getParameter(TYP);
		
		try {
			Experiment experiment = createExperiment(primaryKey, api, typId);
			experiment.saveToDatabase();
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Probe nicht vorhanden");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		catch (SQLException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "SQLException");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		catch (DublicateModelException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Experiment ID oder Experimenttyp schon vorhanden");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		
		Experimenttyp typ;
		try {
			typ = new Experimenttyp(typId);
			ExperimenteModel verdampfung = createExperiment(request, typ.getTyp());
			verdampfung.saveToDatabase();
		}
		catch (SQLException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "SQLException");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		catch (ModelNotFoundException e) {
			json.addKeyValue("status", "error");
			json.addKeyValue("message", "Experimenttyp nicht vorhanden");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}
		catch (DublicateModelException e) {
			// json.addKeyValue("status", "error");
			json.addKeyValue("message", "Experiment schon vorhanden");
			out.print(json.toString());
			e.printStackTrace();
			return;
		}

		json.addKeyValue("status", "success");
		json.addKeyValue("message", "Erfolgreich gespeichert");
		out.print(json.toString());
	}

	private Experiment createExperiment(String primaryKey, String api, String typ) {
		Experiment experiment = new Experiment();
		experiment.setPrimaryKey(primaryKey);
		experiment.setProbenNr(api);
		experiment.setTyp(typ);
		return experiment;
	}

	private ExperimenteModel createExperiment(HttpServletRequest request, String typ) throws ModelNotFoundException, SQLException, DublicateModelException {
		ExperimenteModel experiment;
		switch (typ) {
			case Const.EXPERIMENT_TYP_SLURRY:
				experiment = new ExperimenttypSlurry();
				break;
			case Const.EXPERIMENT_TYP_VERDAMPFUNG:
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
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					experiment.setExperiment_serie(request.getParameter(EXPERIMENT_SERIE_TEXT));
					break;
				case EXPERIMENT_NO:
					experiment.setExperiment_no(parameter);
					break;
				case DURCHFUEHRUNGSTEXT:
					try {
						if (parameter.equals("new")) createNewDurchfuehrungstext(parameter, request);
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					experiment.setDurchfuehrung(parameter);
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
					}
					catch (IllegalArgumentException e) {
						experiment.setStartfreigabe(null);
					}
					break;
				case ERLEDIGT_BIS:
					try {
						experiment.setErledigt_bis(Date.valueOf(parameter));
					}
					catch (IllegalArgumentException e) {
						experiment.setErledigt_bis(null);
					}
					break;
				case HINWEIS_AN_LABORLEITER:
					experiment.setHinweis_an_laborleiter(parameter);
					break;
				case PLANUNG_ABGESCHLOSSEN:
					experiment.setPlanung_abgeschlossen(Boolean.parseBoolean(parameter));
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
					}
					catch (IllegalArgumentException e) {
						experiment.setExperiment_start(null);
					}
					break;
				case VIAL_TARA_G:
					if (experiment instanceof ExperimenttypVerdampfung) ((ExperimenttypVerdampfung) experiment).setVial_tara(Double.parseDouble(parameter));
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
					}
					catch (IllegalArgumentException e) {
						experiment.setExperiment_ende(null);
					}
					break;
				case STATUS_EXPERIMENT:
					experiment.setStatus_experiment(parameter);
					break;
				case AUFBEREITUNG_PRAESENTATION_PXRD:
					try {
						experiment.setAufarbeitung_praesentation_pxrd(Date.valueOf(parameter));
					}
					catch (IllegalArgumentException e) {
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
						((ExperimenttypVerdampfung) experiment).setAusbeute_von_praep_analytik(Double.parseDouble(parameter));
					break;
				case STANDORT_LAGERORT_FINALE_PROBE:
					experiment.setStandort_lagerorte_der_finalen_probe(parameter);
					break;
				case PRIORITAET_ANALYTIK:
					experiment.setPrioritaet_analytik(parameter);
					break;
				case ERSTANALYTIK_PXRD:
					experiment.setErstanalytik_pxrd(parameter);
					break;
				case PXRD_MOEGLICH:
					experiment.setPxrd_moeglich(Boolean.parseBoolean(parameter));
					break;
				case AUSBEUTE_REST_WEITERE_ANALYTIK_MOEGLICH:
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
				case FOLGEANALYTIK_PXRD_II:
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
				case INFORMATIONEN_FOLGEANALYTIK:
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
				case STATUS_ANALYTIK:
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
	
   @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
	   LOGGER.debug("doGet()");
	   
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
   protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   LOGGER.debug("doPut()");
	   
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

}
