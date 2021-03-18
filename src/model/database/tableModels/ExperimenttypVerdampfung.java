package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimenttypVerdampfung extends Model {
    private String primaryKey;
    private String screening_no;
    private int planung_erfolgt_durch;
    private String experiment_serie;
    private String experiment_no;
    private String durchfuehrung;
    private String projektleiternotiz_intention;
    private String verweis;
    private Date startfreigabe;
    private Date erledigt_bis;
    private String hinweis_an_laborleiter;
    private boolean planung_abgeschlossen;
    private String prioritaet_experiment;
    private String sicherheitshinweis;
    private int verantwortlicher_operator;
    private Date experiment_start;
    private double vial_tara;
    private double api_startmaterial_soll_einwaage;
    private double api_startmaterial_soll_einwaage_mg;
    private String cof_bezeichnung;
    private String cof_ref_code;
    private double cof_soll_einwaaage;
    private double cof_soll_einwaage_mg;
    private double soll_temperatur;
    private String loesungsmittel_fuer_api_cof;
    private String vorgabe_info_volumen;
    private String loesungsmittel_ist_volumen;
    private String beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments;
    private String beobachtungen_zum_experimentverlauf;
    private Date experiment_ende;
    private String status_experiment;
    private Date aufarbeitung_präsentation_pxrd;
    private String beobachtung_zum_ende_des_experiments_aufarbeitung;
    private double auswaage_vial_kristallisat;
    private double ausbeute_von_praep_analystik;
    private String standort_lagerorte_der_finalen_probe;
    private String prioritaet_analytik;
    private String erstanalytik_pxrd;
    private boolean pxrd_moeglich;
    private String mit_ausbeute_rest_weitere_analytik_moeglich;
    private String ergebnis_pxrd;
    private boolean folgeanalytik_dsc;
    private boolean folgeanalytik_tg;
    private boolean folgeanalytik_pxrd_ii;
    private boolean folgeanalytik_h_nmr;
    private boolean folgeanalytik_ir;
    private boolean folgeanalytik_raman;
    private boolean folgeanalytik_omi;
    private String informationen_zur_folgeanalytik;
    private String ergebnis_dsc;
    private String ergebnis_tg;
    private String ergebnis_pxrd_ii;
    private String ergebnis_h_nmr;
    private String ergebnis_ir;
    private String ergebnis_raman;
    private String ergebnis_omi;
    private String status_analytik;
    private String gesamt_ergebnis;
    private String einstufung_ergebnis;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_SCREENING_NO_KEY = "screening_no";
    public static final String COLUMN_PLANUNG_ERFOLGT_DURCH_KEY = "planung_erfolgt_durch";
    public static final String COLUMN_EXPERIMENT_SERIE_KEY = "experiment_serie";
    public static final String COLUMN_EXPERIMENT_NO_KEY = "experimnt_no";
    public static final String COLUMN_DURCHFUEHRUNG_KEY = "durchfuehrung";
    public static final String COLUMN_PROJEKTLEITERNOTIZ_INTENTION_KEY = "projektleiternotiz_intention";
    public static final String COLUMN_VERWEIS_KEY = "verweis";
    public static final String COLUMN_STARTFREIGABE_KEY = "startfreigabe";
    public static final String COLUMN_ERLEDIGT_BIS_KEY = "erledigt_bis";
    public static final String COLUMN_HINWEIS_LABORLEITER_KEY = "hinweis_an_laborleiter";
    public static final String COLUMN_PLANUNG_ABGESCHLOSSEN_KEY = "planung_abgeschlossen";
    public static final String COLUMN_PRIORITAET_EXPERIMENT_KEY = "prioritaet_experiment";
    public static final String COLUMN_SICHERHEITSHINWEIS_KEY = "sicherheitshinweis";
    public static final String COLUMN_VERANTWORTLICHER_OPERATOR = "verantwortlicher_operator";
    public static final String COLUMN_EXPERIMENT_START_KEY = "experiment_start";
    public static final String COLUMN_VIAL_TARA_KEY = "vial_tara";
    public static final String COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE = "api_startmaterial_soll_einwaage";
    public static final String COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE_MG = "api_startmaterial_soll_einwaage_mg";
    public static final String COLUMN_COF_BEZEICHNUNG_KEY = "cof_bezeichnung";
    public static final String COLUMN_COF_REF_CODE_KEY = "cof_ref_code";
    public static final String COLUMN_COF_SOLL_EINWAAGE_KEY = "cof_soll_einwaaage";
    public static final String COLUMN_COF_SOLL_EINWAAGE_MG_KEY = "cof_soll_einwaage_mg";
    public static final String COLUMN_SOLL_TEMPERATUR_KEY = "soll_temperatur";
    public static final String COLUMN_LOESUNGSMITTEL_FUER_API_COF_KEY = "loesungsmittel_fuer_api_cof";
    public static final String COLUMN_VORGABE_INFO_VOLUMEN_KEY = "vorgabe_info_volumen";
    public static final String COLUMN_LOESUNGSMITTEL_IST_VOLUMEN_KEY = "loesungsmittel_ist_volumen";
    public static final String COLUMN_BEOBACHTUNGEN_ZUM_LOESEVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY = "beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments";
    public static final String COLUMN_BEOBACHTUNGEN_ZUM_EXPERIMENTVERLAUF_KEY = "beobachtungen_zum_experimentverlauf";
    public static final String COLUMN_EXPERIMENT_ENDE = "experiment_ende";
    public static final String COLUMN_STATUS_EXPERIMENT_KEY = "status_experiment";
    public static final String COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY = "aufarbeitung_präsentation_pxrd";
    public static final String COLUMN_BEOBACHTUNG_ZUM_ENDE_DES_EXPERIMENTS_AUFBEREITUNG_KEY = "beobachtung_zum_ende_des_experiments_aufarbeitung";
    public static final String COLUMN_AUSWAAGE_VIAL_KRISTALLISAT_KEY = "ausbeute_vial_kristallisat";
    public static final String COLUMN_AUSBEUTE_VON_PRAEP_ANALYSTIK_KEY = "ausbeute_von_praep_analystik";
    public static final String COLUMN_STANDORT_LAGERORTE_DER_FINALEN_PROBE = "standort_lagerorte_der_finalen_probe";
    public static final String COLUMN_PRIORITAET_ANALYTIK_KEY = "prioritaet_analytik";
    public static final String COLUMN_ERSTANALYTIK_PXRD_KEY = "erstanalytik_pxrd";
    public static final String COLUMN_PXRD_MOEGLICH_KEY = "pxrd_moeglich";
    public static final String COLUMN_MIT_AUSBEUTE_REST_WEITERE_ANALYTIK_MOEGLICH_KEY = "mit_ausbeute_rest_weitere_analytik_moeglich";
    public static final String COLUMN_ERGEBNIS_PXRD_KEY = "ergebnis_pxrd";
    public static final String COLUMN_FOLGEANALYTIK_DSC_KEY = "folgeanalytik_dsc";
    public static final String COLUMN_FOLGEANALYTIK_TG_KEY = "folgeanalytik_tg";
    public static final String COLUMN_FOLGEANALYTIK_PXRD_II_KEY = "folgeanalytik_pxrd_ii";
    public static final String COLUMN_FOLGEANALYTIK_H_NMR_KEY = "folgeanalytik_h_nmr";
    public static final String COLUMN_FOLGEANALYTIK_IR_KEY = "folgeanalytik_ir";
    public static final String COLUMN_FOLGEANALYTIK_RAMAN_KEY = "folgeanalytik_raman";
    public static final String COLUMN_FOLGEANALYTIK_OMI_KEY = "folgeanalytik_omi";
    public static final String COLUMN_INFORMATIONEN_ZUR_FOLGEANALYTIK_KEY = "informationen_zur_folgeanalytik";
    public static final String COLUMN_ERGEBNIS_DSC_KEY = "ergebnis_dsc";
    public static final String COLUMN_ERGEBNIS_TG_KEY = "ergebnis_tg";
    public static final String COLUMN_ERGEBNIS_PXRD_II_KEY = "ergebnis_pxrd_ii";
    public static final String COLUMN_ERGEBNIS_H_NMR_KEY = "ergebnis_h_nmr";
    public static final String COLUMN_ERGEBNIS_IR_KEY = "ergebnis_ir";
    public static final String COLUMN_ERGEBNIS_RAMAN_KEY = "ergebnis_raman";
    public static final String COLUMN_ERGEBNIS_OMI_KEY = "ergebnis_omi";
    public static final String COLUMN_STATUS_ANALYTIK_KEY = "status_analytik";
    public static final String COLUMN_GESAMT_ERGEBNIS_KEY = "gesamt_ergebnis";
    public static final String COLUMN_EINSTUFUNG_ERGEBNIS_KEY = "einstufung_ergebnis";
    public static final String TABLE = "experimenttyp_verdampfung";

    public ExperimenttypVerdampfung() {

    }

    public ExperimenttypVerdampfung(String primaryKey) throws ModelNotFoundException, SQLException {
        this.primaryKey = primaryKey;
        database.getModel(this);
    }

    @Override
    public String getForeignKey() {
        return primaryKey;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(COLUMN_PRIMARY_KEY);
            screening_no = resultSet.getString(COLUMN_SCREENING_NO_KEY);
            planung_erfolgt_durch = resultSet.getInt(COLUMN_PLANUNG_ERFOLGT_DURCH_KEY);
            experiment_serie = resultSet.getString(COLUMN_EXPERIMENT_SERIE_KEY);
            experiment_no = resultSet.getString(COLUMN_EXPERIMENT_NO_KEY);
            durchfuehrung = resultSet.getString(COLUMN_DURCHFUEHRUNG_KEY);
            projektleiternotiz_intention = resultSet.getString(COLUMN_PROJEKTLEITERNOTIZ_INTENTION_KEY);
            verweis = resultSet.getString(COLUMN_VERWEIS_KEY);
            startfreigabe = resultSet.getDate(COLUMN_STARTFREIGABE_KEY);
            erledigt_bis = resultSet.getDate(COLUMN_ERLEDIGT_BIS_KEY);
            hinweis_an_laborleiter = resultSet.getString(COLUMN_HINWEIS_LABORLEITER_KEY);
            planung_abgeschlossen = resultSet.getBoolean(COLUMN_PLANUNG_ABGESCHLOSSEN_KEY);
            prioritaet_experiment = resultSet.getString(COLUMN_PRIORITAET_EXPERIMENT_KEY);
            sicherheitshinweis = resultSet.getString(COLUMN_SICHERHEITSHINWEIS_KEY);
            verantwortlicher_operator = resultSet.getInt(COLUMN_VERANTWORTLICHER_OPERATOR);
            experiment_start = resultSet.getDate(COLUMN_EXPERIMENT_START_KEY);
            vial_tara = resultSet.getDouble(COLUMN_VIAL_TARA_KEY);
            api_startmaterial_soll_einwaage = resultSet.getDouble(COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE);
            api_startmaterial_soll_einwaage_mg = resultSet.getDouble(COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE_MG);
            cof_bezeichnung = resultSet.getString(COLUMN_COF_BEZEICHNUNG_KEY);
            cof_ref_code = resultSet.getString(COLUMN_COF_REF_CODE_KEY);
            cof_soll_einwaaage = resultSet.getDouble(COLUMN_COF_SOLL_EINWAAGE_KEY);
            cof_soll_einwaage_mg = resultSet.getDouble(COLUMN_COF_SOLL_EINWAAGE_MG_KEY);
            soll_temperatur = resultSet.getDouble(COLUMN_SOLL_TEMPERATUR_KEY);
            loesungsmittel_fuer_api_cof = resultSet.getString(COLUMN_LOESUNGSMITTEL_FUER_API_COF_KEY);
            vorgabe_info_volumen = resultSet.getString(COLUMN_VORGABE_INFO_VOLUMEN_KEY);
            loesungsmittel_ist_volumen = resultSet.getString(COLUMN_LOESUNGSMITTEL_IST_VOLUMEN_KEY);
            beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments = resultSet.getString(COLUMN_BEOBACHTUNGEN_ZUM_LOESEVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY);
            beobachtungen_zum_experimentverlauf = resultSet.getString(COLUMN_BEOBACHTUNGEN_ZUM_EXPERIMENTVERLAUF_KEY);
            experiment_ende = resultSet.getDate(COLUMN_EXPERIMENT_ENDE);
            status_experiment = resultSet.getString(COLUMN_STATUS_EXPERIMENT_KEY);
            aufarbeitung_präsentation_pxrd = resultSet.getDate(COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY);
            beobachtung_zum_ende_des_experiments_aufarbeitung = resultSet.getString(COLUMN_BEOBACHTUNG_ZUM_ENDE_DES_EXPERIMENTS_AUFBEREITUNG_KEY);
            auswaage_vial_kristallisat = resultSet.getDouble(COLUMN_AUSWAAGE_VIAL_KRISTALLISAT_KEY);
            ausbeute_von_praep_analystik = resultSet.getDouble(COLUMN_AUSBEUTE_VON_PRAEP_ANALYSTIK_KEY);
            standort_lagerorte_der_finalen_probe = resultSet.getString(COLUMN_STANDORT_LAGERORTE_DER_FINALEN_PROBE);
            prioritaet_analytik = resultSet.getString(COLUMN_PRIORITAET_ANALYTIK_KEY);
            erstanalytik_pxrd = resultSet.getString(COLUMN_ERSTANALYTIK_PXRD_KEY);
            pxrd_moeglich = resultSet.getBoolean(COLUMN_PXRD_MOEGLICH_KEY);
            mit_ausbeute_rest_weitere_analytik_moeglich = resultSet.getString(COLUMN_MIT_AUSBEUTE_REST_WEITERE_ANALYTIK_MOEGLICH_KEY);
            ergebnis_pxrd = resultSet.getString(COLUMN_ERGEBNIS_PXRD_KEY);
            folgeanalytik_dsc = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_DSC_KEY);
            folgeanalytik_tg = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_TG_KEY);
            folgeanalytik_pxrd_ii = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_PXRD_II_KEY);
            folgeanalytik_h_nmr = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_H_NMR_KEY);
            folgeanalytik_ir = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_IR_KEY);
            folgeanalytik_raman = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_RAMAN_KEY);
            folgeanalytik_omi = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_OMI_KEY);
            informationen_zur_folgeanalytik = resultSet.getString(COLUMN_INFORMATIONEN_ZUR_FOLGEANALYTIK_KEY);
            ergebnis_dsc = resultSet.getString(COLUMN_ERGEBNIS_DSC_KEY);
            ergebnis_tg = resultSet.getString(COLUMN_ERGEBNIS_TG_KEY);
            ergebnis_pxrd_ii = resultSet.getString(COLUMN_ERGEBNIS_PXRD_II_KEY);
            ergebnis_h_nmr = resultSet.getString(COLUMN_ERGEBNIS_H_NMR_KEY);
            ergebnis_ir = resultSet.getString(COLUMN_ERGEBNIS_IR_KEY);
            ergebnis_raman = resultSet.getString(COLUMN_ERGEBNIS_RAMAN_KEY);
            ergebnis_omi = resultSet.getString(COLUMN_ERGEBNIS_OMI_KEY);
            status_analytik = resultSet.getString(COLUMN_STATUS_ANALYTIK_KEY);
            gesamt_ergebnis = resultSet.getString(COLUMN_GESAMT_ERGEBNIS_KEY);
            einstufung_ergebnis = resultSet.getString(COLUMN_EINSTUFUNG_ERGEBNIS_KEY);
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {

        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry entry = new DummyResultSetEntry();
        entry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
        entry.addKeyValuePair(COLUMN_SCREENING_NO_KEY, screening_no);
        entry.addKeyValuePair(COLUMN_PLANUNG_ERFOLGT_DURCH_KEY, Integer.toString(planung_erfolgt_durch));
        entry.addKeyValuePair(COLUMN_EXPERIMENT_SERIE_KEY, experiment_serie);
        entry.addKeyValuePair(COLUMN_EXPERIMENT_NO_KEY, experiment_no);
        entry.addKeyValuePair(COLUMN_DURCHFUEHRUNG_KEY, durchfuehrung);
        entry.addKeyValuePair(COLUMN_PROJEKTLEITERNOTIZ_INTENTION_KEY, projektleiternotiz_intention);
        entry.addKeyValuePair(COLUMN_VERWEIS_KEY, verweis);
        entry.addKeyValuePair(COLUMN_STARTFREIGABE_KEY, startfreigabe.toString());
        entry.addKeyValuePair(COLUMN_ERLEDIGT_BIS_KEY, erledigt_bis.toString());
        entry.addKeyValuePair(COLUMN_HINWEIS_LABORLEITER_KEY, hinweis_an_laborleiter);
        entry.addKeyValuePair(COLUMN_PLANUNG_ABGESCHLOSSEN_KEY, Boolean.toString(planung_abgeschlossen));
        entry.addKeyValuePair(COLUMN_PRIORITAET_EXPERIMENT_KEY, prioritaet_experiment);
        entry.addKeyValuePair(COLUMN_SICHERHEITSHINWEIS_KEY, sicherheitshinweis);
        entry.addKeyValuePair(COLUMN_VERANTWORTLICHER_OPERATOR, Integer.toString(verantwortlicher_operator));
        entry.addKeyValuePair(COLUMN_EXPERIMENT_START_KEY, experiment_start.toString());
        entry.addKeyValuePair(COLUMN_VIAL_TARA_KEY, Double.toString(vial_tara));
        entry.addKeyValuePair(COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE, Double.toString(api_startmaterial_soll_einwaage));
        entry.addKeyValuePair(COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE_MG, Double.toString(api_startmaterial_soll_einwaage_mg));
        entry.addKeyValuePair(COLUMN_COF_BEZEICHNUNG_KEY, cof_bezeichnung);
        entry.addKeyValuePair(COLUMN_COF_REF_CODE_KEY, cof_ref_code);
        entry.addKeyValuePair(COLUMN_COF_SOLL_EINWAAGE_KEY, Double.toString(cof_soll_einwaaage));
        entry.addKeyValuePair(COLUMN_COF_SOLL_EINWAAGE_MG_KEY, Double.toString(cof_soll_einwaage_mg));
        entry.addKeyValuePair(COLUMN_SOLL_TEMPERATUR_KEY, Double.toString(soll_temperatur));
        entry.addKeyValuePair(COLUMN_LOESUNGSMITTEL_FUER_API_COF_KEY, loesungsmittel_fuer_api_cof);
        entry.addKeyValuePair(COLUMN_VORGABE_INFO_VOLUMEN_KEY, vorgabe_info_volumen);
        entry.addKeyValuePair(COLUMN_LOESUNGSMITTEL_IST_VOLUMEN_KEY, loesungsmittel_ist_volumen);
        entry.addKeyValuePair(COLUMN_BEOBACHTUNGEN_ZUM_LOESEVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY, beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments);
        entry.addKeyValuePair(COLUMN_BEOBACHTUNGEN_ZUM_EXPERIMENTVERLAUF_KEY, beobachtungen_zum_experimentverlauf);
        entry.addKeyValuePair(COLUMN_EXPERIMENT_ENDE, experiment_ende.toString());
        entry.addKeyValuePair(COLUMN_STATUS_EXPERIMENT_KEY, status_experiment);
        entry.addKeyValuePair(COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY, aufarbeitung_präsentation_pxrd.toString());
        entry.addKeyValuePair(COLUMN_BEOBACHTUNG_ZUM_ENDE_DES_EXPERIMENTS_AUFBEREITUNG_KEY, beobachtung_zum_ende_des_experiments_aufarbeitung);
        entry.addKeyValuePair(COLUMN_AUSWAAGE_VIAL_KRISTALLISAT_KEY, Double.toString(auswaage_vial_kristallisat));
        entry.addKeyValuePair(COLUMN_AUSBEUTE_VON_PRAEP_ANALYSTIK_KEY, Double.toString(ausbeute_von_praep_analystik));
        entry.addKeyValuePair(COLUMN_STANDORT_LAGERORTE_DER_FINALEN_PROBE, standort_lagerorte_der_finalen_probe);
        entry.addKeyValuePair(COLUMN_PRIORITAET_ANALYTIK_KEY, prioritaet_analytik);
        entry.addKeyValuePair(COLUMN_ERSTANALYTIK_PXRD_KEY, erstanalytik_pxrd);
        entry.addKeyValuePair(COLUMN_PXRD_MOEGLICH_KEY, Boolean.toString(pxrd_moeglich));
        entry.addKeyValuePair(COLUMN_MIT_AUSBEUTE_REST_WEITERE_ANALYTIK_MOEGLICH_KEY, mit_ausbeute_rest_weitere_analytik_moeglich);
        entry.addKeyValuePair(COLUMN_ERGEBNIS_PXRD_KEY, ergebnis_pxrd);
        entry.addKeyValuePair(COLUMN_FOLGEANALYTIK_DSC_KEY, Boolean.toString(folgeanalytik_dsc));
        entry.addKeyValuePair(COLUMN_FOLGEANALYTIK_TG_KEY, Boolean.toString(folgeanalytik_tg));
        entry.addKeyValuePair(COLUMN_FOLGEANALYTIK_PXRD_II_KEY, Boolean.toString(folgeanalytik_pxrd_ii));
        entry.addKeyValuePair(COLUMN_FOLGEANALYTIK_H_NMR_KEY, Boolean.toString(folgeanalytik_h_nmr));
        entry.addKeyValuePair(COLUMN_FOLGEANALYTIK_IR_KEY, Boolean.toString(folgeanalytik_ir));
        entry.addKeyValuePair(COLUMN_FOLGEANALYTIK_RAMAN_KEY, Boolean.toString(folgeanalytik_raman));
        entry.addKeyValuePair(COLUMN_FOLGEANALYTIK_OMI_KEY, Boolean.toString(folgeanalytik_omi));
        entry.addKeyValuePair(COLUMN_INFORMATIONEN_ZUR_FOLGEANALYTIK_KEY, informationen_zur_folgeanalytik);
        entry.addKeyValuePair(COLUMN_ERGEBNIS_DSC_KEY, ergebnis_dsc);
        entry.addKeyValuePair(COLUMN_ERGEBNIS_TG_KEY, ergebnis_tg);
        entry.addKeyValuePair(COLUMN_ERGEBNIS_PXRD_II_KEY, ergebnis_pxrd_ii);
        entry.addKeyValuePair(COLUMN_ERGEBNIS_H_NMR_KEY, ergebnis_h_nmr);
        entry.addKeyValuePair(COLUMN_ERGEBNIS_IR_KEY, ergebnis_ir);
        entry.addKeyValuePair(COLUMN_ERGEBNIS_RAMAN_KEY, ergebnis_raman);
        entry.addKeyValuePair(COLUMN_ERGEBNIS_OMI_KEY, ergebnis_omi);
        entry.addKeyValuePair(COLUMN_STATUS_ANALYTIK_KEY, status_analytik);
        entry.addKeyValuePair(COLUMN_GESAMT_ERGEBNIS_KEY, gesamt_ergebnis);
        entry.addKeyValuePair(COLUMN_EINSTUFUNG_ERGEBNIS_KEY, einstufung_ergebnis);

        dummyResultSet.addEntry(entry);

        return dummyResultSet;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public String getScreening_no() {
        return screening_no;
    }

    public void setScreening_no(String screening_no) {
        this.screening_no = screening_no;
    }

    public int getPlanung_erfolgt_durch() {
        return planung_erfolgt_durch;
    }

    public void setPlanung_erfolgt_durch(int planung_erfolgt_durch) {
        this.planung_erfolgt_durch = planung_erfolgt_durch;
    }

    public String getExperiment_serie() {
        return experiment_serie;
    }

    public void setExperiment_serie(String experiment_serie) {
        this.experiment_serie = experiment_serie;
    }

    public String getExperiment_no() {
        return experiment_no;
    }

    public void setExperiment_no(String experiment_no) {
        this.experiment_no = experiment_no;
    }

    public String getDurchfuehrung() {
        return durchfuehrung;
    }

    public void setDurchfuehrung(String durchfuehrung) {
        this.durchfuehrung = durchfuehrung;
    }

    public String getProjektleiternotiz_intention() {
        return projektleiternotiz_intention;
    }

    public void setProjektleiternotiz_intention(String projektleiternotiz_intention) {
        this.projektleiternotiz_intention = projektleiternotiz_intention;
    }

    public String getVerweis() {
        return verweis;
    }

    public void setVerweis(String verweis) {
        this.verweis = verweis;
    }

    public Date getStartfreigabe() {
        return startfreigabe;
    }

    public void setStartfreigabe(Date startfreigabe) {
        this.startfreigabe = startfreigabe;
    }

    public Date getErledigt_bis() {
        return erledigt_bis;
    }

    public void setErledigt_bis(Date erledigt_bis) {
        this.erledigt_bis = erledigt_bis;
    }

    public String getHinweis_an_laborleiter() {
        return hinweis_an_laborleiter;
    }

    public void setHinweis_an_laborleiter(String hinweis_an_laborleiter) {
        this.hinweis_an_laborleiter = hinweis_an_laborleiter;
    }

    public boolean isPlanung_abgeschlossen() {
        return planung_abgeschlossen;
    }

    public void setPlanung_abgeschlossen(boolean planung_abgeschlossen) {
        this.planung_abgeschlossen = planung_abgeschlossen;
    }

    public String getPrioritaet_experiment() {
        return prioritaet_experiment;
    }

    public void setPrioritaet_experiment(String prioritaet_experiment) {
        this.prioritaet_experiment = prioritaet_experiment;
    }

    public String getSicherheitshinweis() {
        return sicherheitshinweis;
    }

    public void setSicherheitshinweis(String sicherheitshinweis) {
        this.sicherheitshinweis = sicherheitshinweis;
    }

    public int getVerantwortlicher_operator() {
        return verantwortlicher_operator;
    }

    public void setVerantwortlicher_operator(int verantwortlicher_operator) {
        this.verantwortlicher_operator = verantwortlicher_operator;
    }

    public Date getExperiment_start() {
        return experiment_start;
    }

    public void setExperiment_start(Date experiment_start) {
        this.experiment_start = experiment_start;
    }

    public double getVial_tara() {
        return vial_tara;
    }

    public void setVial_tara(double vial_tara) {
        this.vial_tara = vial_tara;
    }

    public double getApi_startmaterial_soll_einwaage() {
        return api_startmaterial_soll_einwaage;
    }

    public void setApi_startmaterial_soll_einwaage(double api_startmaterial_soll_einwaage) {
        this.api_startmaterial_soll_einwaage = api_startmaterial_soll_einwaage;
    }

    public double getApi_startmaterial_soll_einwaage_mg() {
        return api_startmaterial_soll_einwaage_mg;
    }

    public void setApi_startmaterial_soll_einwaage_mg(double api_startmaterial_soll_einwaage_mg) {
        this.api_startmaterial_soll_einwaage_mg = api_startmaterial_soll_einwaage_mg;
    }

    public String getCof_bezeichnung() {
        return cof_bezeichnung;
    }

    public void setCof_bezeichnung(String cof_bezeichnung) {
        this.cof_bezeichnung = cof_bezeichnung;
    }

    public String getCof_ref_code() {
        return cof_ref_code;
    }

    public void setCof_ref_code(String cof_ref_code) {
        this.cof_ref_code = cof_ref_code;
    }

    public double getCof_soll_einwaaage() {
        return cof_soll_einwaaage;
    }

    public void setCof_soll_einwaaage(double cof_soll_einwaaage) {
        this.cof_soll_einwaaage = cof_soll_einwaaage;
    }

    public double getCof_soll_einwaage_mg() {
        return cof_soll_einwaage_mg;
    }

    public void setCof_soll_einwaage_mg(double cof_soll_einwaage_mg) {
        this.cof_soll_einwaage_mg = cof_soll_einwaage_mg;
    }

    public double getSoll_temperatur() {
        return soll_temperatur;
    }

    public void setSoll_temperatur(double soll_temperatur) {
        this.soll_temperatur = soll_temperatur;
    }

    public String getLoesungsmittel_fuer_api_cof() {
        return loesungsmittel_fuer_api_cof;
    }

    public void setLoesungsmittel_fuer_api_cof(String loesungsmittel_fuer_api_cof) {
        this.loesungsmittel_fuer_api_cof = loesungsmittel_fuer_api_cof;
    }

    public String getVorgabe_info_volumen() {
        return vorgabe_info_volumen;
    }

    public void setVorgabe_info_volumen(String vorgabe_info_volumen) {
        this.vorgabe_info_volumen = vorgabe_info_volumen;
    }

    public String getLoesungsmittel_ist_volumen() {
        return loesungsmittel_ist_volumen;
    }

    public void setLoesungsmittel_ist_volumen(String loesungsmittel_ist_volumen) {
        this.loesungsmittel_ist_volumen = loesungsmittel_ist_volumen;
    }

    public String getBeobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments() {
        return beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments;
    }

    public void setBeobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments(String beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments) {
        this.beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments = beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments;
    }

    public String getBeobachtungen_zum_experimentverlauf() {
        return beobachtungen_zum_experimentverlauf;
    }

    public void setBeobachtungen_zum_experimentverlauf(String beobachtungen_zum_experimentverlauf) {
        this.beobachtungen_zum_experimentverlauf = beobachtungen_zum_experimentverlauf;
    }

    public Date getExperiment_ende() {
        return experiment_ende;
    }

    public void setExperiment_ende(Date experiment_ende) {
        this.experiment_ende = experiment_ende;
    }

    public String getStatus_experiment() {
        return status_experiment;
    }

    public void setStatus_experiment(String status_experiment) {
        this.status_experiment = status_experiment;
    }

    public Date getAufarbeitung_präsentation_pxrd() {
        return aufarbeitung_präsentation_pxrd;
    }

    public void setAufarbeitung_praesentation_pxrd(Date aufarbeitung_präsentation_pxrd) {
        this.aufarbeitung_präsentation_pxrd = aufarbeitung_präsentation_pxrd;
    }

    public String getBeobachtung_zum_ende_des_experiments_aufarbeitung() {
        return beobachtung_zum_ende_des_experiments_aufarbeitung;
    }

    public void setBeobachtung_zum_ende_des_experiments_aufarbeitung(String beobachtung_zum_ende_des_experiments_aufarbeitung) {
        this.beobachtung_zum_ende_des_experiments_aufarbeitung = beobachtung_zum_ende_des_experiments_aufarbeitung;
    }

    public double getAuswaage_vial_kristallisat() {
        return auswaage_vial_kristallisat;
    }

    public void setAuswaage_vial_kristallisat(double auswaage_vial_kristallisat) {
        this.auswaage_vial_kristallisat = auswaage_vial_kristallisat;
    }

    public double getAusbeute_von_praep_analystik() {
        return ausbeute_von_praep_analystik;
    }

    public void setAusbeute_von_praep_analystik(double ausbeute_von_praep_analystik) {
        this.ausbeute_von_praep_analystik = ausbeute_von_praep_analystik;
    }

    public String getStandort_lagerorte_der_finalen_probe() {
        return standort_lagerorte_der_finalen_probe;
    }

    public void setStandort_lagerorte_der_finalen_probe(String standort_lagerorte_der_finalen_probe) {
        this.standort_lagerorte_der_finalen_probe = standort_lagerorte_der_finalen_probe;
    }

    public String getPrioritaet_analytik() {
        return prioritaet_analytik;
    }

    public void setPrioritaet_analytik(String prioritaet_analytik) {
        this.prioritaet_analytik = prioritaet_analytik;
    }

    public String getErstanalytik_pxrd() {
        return erstanalytik_pxrd;
    }

    public void setErstanalytik_pxrd(String erstanalytik_pxrd) {
        this.erstanalytik_pxrd = erstanalytik_pxrd;
    }

    public boolean isPxrd_moeglich() {
        return pxrd_moeglich;
    }

    public void setPxrd_moeglich(boolean pxrd_moeglich) {
        this.pxrd_moeglich = pxrd_moeglich;
    }

    public String getMit_ausbeute_rest_weitere_analytik_moeglich() {
        return mit_ausbeute_rest_weitere_analytik_moeglich;
    }

    public void setMit_ausbeute_rest_weitere_analytik_moeglich(String mit_ausbeute_rest_weitere_analytik_moeglich) {
        this.mit_ausbeute_rest_weitere_analytik_moeglich = mit_ausbeute_rest_weitere_analytik_moeglich;
    }

    public String getErgebnis_pxrd() {
        return ergebnis_pxrd;
    }

    public void setErgebnis_pxrd(String  ergebnis_pxrd) {
        this.ergebnis_pxrd = ergebnis_pxrd;
    }

    public boolean isFolgeanalytik_dsc() {
        return folgeanalytik_dsc;
    }

    public void setFolgeanalytik_dsc(boolean folgeanalytik_dsc) {
        this.folgeanalytik_dsc = folgeanalytik_dsc;
    }

    public boolean isFolgeanalytik_tg() {
        return folgeanalytik_tg;
    }

    public void setFolgeanalytik_tg(boolean folgeanalytik_tg) {
        this.folgeanalytik_tg = folgeanalytik_tg;
    }

    public boolean isFolgeanalytik_pxrd_ii() {
        return folgeanalytik_pxrd_ii;
    }

    public void setFolgeanalytik_pxrd_ii(boolean folgeanalytik_pxrd_ii) {
        this.folgeanalytik_pxrd_ii = folgeanalytik_pxrd_ii;
    }

    public boolean isFolgeanalytik_h_nmr() {
        return folgeanalytik_h_nmr;
    }

    public void setFolgeanalytik_h_nmr(boolean folgeanalytik_h_nmr) {
        this.folgeanalytik_h_nmr = folgeanalytik_h_nmr;
    }

    public boolean isFolgeanalytik_ir() {
        return folgeanalytik_ir;
    }

    public void setFolgeanalytik_ir(boolean folgeanalytik_ir) {
        this.folgeanalytik_ir = folgeanalytik_ir;
    }

    public boolean isFolgeanalytik_raman() {
        return folgeanalytik_raman;
    }

    public void setFolgeanalytik_raman(boolean folgeanalytik_raman) {
        this.folgeanalytik_raman = folgeanalytik_raman;
    }

    public boolean isFolgeanalytik_omi() {
        return folgeanalytik_omi;
    }

    public void setFolgeanalytik_omi(boolean folgeanalytik_omi) {
        this.folgeanalytik_omi = folgeanalytik_omi;
    }

    public String getInformationen_zur_folgeanalytik() {
        return informationen_zur_folgeanalytik;
    }

    public void setInformationen_zur_folgeanalytik(String informationen_zur_folgeanalytik) {
        this.informationen_zur_folgeanalytik = informationen_zur_folgeanalytik;
    }

    public String getErgebnis_dsc() {
        return ergebnis_dsc;
    }

    public void setErgebnis_dsc(String ergebnis_dsc) {
        this.ergebnis_dsc = ergebnis_dsc;
    }

    public String getErgebnis_tg() {
        return ergebnis_tg;
    }

    public void setErgebnis_tg(String ergebnis_tg) {
        this.ergebnis_tg = ergebnis_tg;
    }

    public String getErgebnis_pxrd_ii() {
        return ergebnis_pxrd_ii;
    }

    public void setErgebnis_pxrd_ii(String ergebnis_pxrd_ii) {
        this.ergebnis_pxrd_ii = ergebnis_pxrd_ii;
    }

    public String getErgebnis_h_nmr() {
        return ergebnis_h_nmr;
    }

    public void setErgebnis_h_nmr(String ergebnis_h_nmr) {
        this.ergebnis_h_nmr = ergebnis_h_nmr;
    }

    public String getErgebnis_ir() {
        return ergebnis_ir;
    }

    public void setErgebnis_ir(String ergebnis_ir) {
        this.ergebnis_ir = ergebnis_ir;
    }

    public String getErgebnis_raman() {
        return ergebnis_raman;
    }

    public void setErgebnis_raman(String ergebnis_raman) {
        this.ergebnis_raman = ergebnis_raman;
    }

    public String getErgebnis_omi() {
        return ergebnis_omi;
    }

    public void setErgebnis_omi(String ergebnis_omi) {
        this.ergebnis_omi = ergebnis_omi;
    }

    public String getStatus_analytik() {
        return status_analytik;
    }

    public void setStatus_analytik(String status_analytik) {
        this.status_analytik = status_analytik;
    }

    public String getGesamt_ergebnis() {
        return gesamt_ergebnis;
    }

    public void setGesamt_ergebnis(String gesamt_ergebnis) {
        this.gesamt_ergebnis = gesamt_ergebnis;
    }

    public String getEinstufung_ergebnis() {
        return einstufung_ergebnis;
    }

    public void setEinstufung_ergebnis(String einstufung_ergebnis) {
        this.einstufung_ergebnis = einstufung_ergebnis;
    }

    public static String getColumnPrimaryKey() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnScreeningNoKey() {
        return COLUMN_SCREENING_NO_KEY;
    }

    public static String getColumnPlanungErfolgtDurchKey() {
        return COLUMN_PLANUNG_ERFOLGT_DURCH_KEY;
    }

    public static String getColumnExperimentSerieKey() {
        return COLUMN_EXPERIMENT_SERIE_KEY;
    }

    public static String getColumnExperimentNoKey() {
        return COLUMN_EXPERIMENT_NO_KEY;
    }

    public static String getColumnDurchfuehrungKey() {
        return COLUMN_DURCHFUEHRUNG_KEY;
    }

    public static String getColumnProjektleiternotizIntentionKey() {
        return COLUMN_PROJEKTLEITERNOTIZ_INTENTION_KEY;
    }

    public static String getColumnVerweisKey() {
        return COLUMN_VERWEIS_KEY;
    }

    public static String getColumnStartfreigabeKey() {
        return COLUMN_STARTFREIGABE_KEY;
    }

    public static String getColumnErledigtBisKey() {
        return COLUMN_ERLEDIGT_BIS_KEY;
    }

    public static String getColumnHinweisLaborleiterKey() {
        return COLUMN_HINWEIS_LABORLEITER_KEY;
    }

    public static String getColumnPlanungAbgeschlossenKey() {
        return COLUMN_PLANUNG_ABGESCHLOSSEN_KEY;
    }

    public static String getColumnPrioritaetExperimentKey() {
        return COLUMN_PRIORITAET_EXPERIMENT_KEY;
    }

    public static String getColumnSicherheitshinweisKey() {
        return COLUMN_SICHERHEITSHINWEIS_KEY;
    }

    public static String getColumnVerantwortlicherOperator() {
        return COLUMN_VERANTWORTLICHER_OPERATOR;
    }

    public static String getColumnExperimentStartKey() {
        return COLUMN_EXPERIMENT_START_KEY;
    }

    public static String getColumnVialTaraKey() {
        return COLUMN_VIAL_TARA_KEY;
    }

    public static String getColumnApiStartmaterialSollEinwaage() {
        return COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE;
    }

    public static String getColumnApiStartmaterialSollEinwaageMg() {
        return COLUMN_API_STARTMATERIAL_SOLL_EINWAAGE_MG;
    }

    public static String getColumnCofBezeichnungKey() {
        return COLUMN_COF_BEZEICHNUNG_KEY;
    }

    public static String getColumnCofRefCodeKey() {
        return COLUMN_COF_REF_CODE_KEY;
    }

    public static String getColumnCofSollEinwaageKey() {
        return COLUMN_COF_SOLL_EINWAAGE_KEY;
    }

    public static String getColumnCofSollEinwaageMgKey() {
        return COLUMN_COF_SOLL_EINWAAGE_MG_KEY;
    }

    public static String getColumnSollTemperaturKey() {
        return COLUMN_SOLL_TEMPERATUR_KEY;
    }

    public static String getColumnLoesungsmittelFuerApiCofKey() {
        return COLUMN_LOESUNGSMITTEL_FUER_API_COF_KEY;
    }

    public static String getColumnVorgabeInfoVolumenKey() {
        return COLUMN_VORGABE_INFO_VOLUMEN_KEY;
    }

    public static String getColumnLoesungsmittelIstVolumenKey() {
        return COLUMN_LOESUNGSMITTEL_IST_VOLUMEN_KEY;
    }

    public static String getColumnBeobachtungenZumLoesevorgangOderAenderungenDesExperimentsKey() {
        return COLUMN_BEOBACHTUNGEN_ZUM_LOESEVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY;
    }

    public static String getColumnBeobachtungenZumExperimentverlaufKey() {
        return COLUMN_BEOBACHTUNGEN_ZUM_EXPERIMENTVERLAUF_KEY;
    }

    public static String getColumnExperimentEnde() {
        return COLUMN_EXPERIMENT_ENDE;
    }

    public static String getColumnStatusExperimentKey() {
        return COLUMN_STATUS_EXPERIMENT_KEY;
    }

    public static String getColumnAufbereitungPraesentationPxrdKey() {
        return COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY;
    }

    public static String getColumnBeobachtungZumEndeDesExperimentsAufbereitungKey() {
        return COLUMN_BEOBACHTUNG_ZUM_ENDE_DES_EXPERIMENTS_AUFBEREITUNG_KEY;
    }

    public static String getColumnAuswaageVialKristallisatKey() {
        return COLUMN_AUSWAAGE_VIAL_KRISTALLISAT_KEY;
    }

    public static String getColumnAusbeuteVonPraepAnalystikKey() {
        return COLUMN_AUSBEUTE_VON_PRAEP_ANALYSTIK_KEY;
    }

    public static String getColumnStandortLagerorteDerFinalenProbe() {
        return COLUMN_STANDORT_LAGERORTE_DER_FINALEN_PROBE;
    }

    public static String getColumnPrioritaetAnalytikKey() {
        return COLUMN_PRIORITAET_ANALYTIK_KEY;
    }

    public static String getColumnErstanalytikPxrdKey() {
        return COLUMN_ERSTANALYTIK_PXRD_KEY;
    }

    public static String getColumnPxrdMoeglichKey() {
        return COLUMN_PXRD_MOEGLICH_KEY;
    }

    public static String getColumnMitAusbeuteRestWeitereAnalytikMoeglichKey() {
        return COLUMN_MIT_AUSBEUTE_REST_WEITERE_ANALYTIK_MOEGLICH_KEY;
    }

    public static String getColumnErgebnisPxrdKey() {
        return COLUMN_ERGEBNIS_PXRD_KEY;
    }

    public static String getColumnFolgeanalytikDscKey() {
        return COLUMN_FOLGEANALYTIK_DSC_KEY;
    }

    public static String getColumnFolgeanalytikTgKey() {
        return COLUMN_FOLGEANALYTIK_TG_KEY;
    }

    public static String getColumnFolgeanalytikPxrdIiKey() {
        return COLUMN_FOLGEANALYTIK_PXRD_II_KEY;
    }

    public static String getColumnFolgeanalytikHNmrKey() {
        return COLUMN_FOLGEANALYTIK_H_NMR_KEY;
    }

    public static String getColumnFolgeanalytikIrKey() {
        return COLUMN_FOLGEANALYTIK_IR_KEY;
    }

    public static String getColumnFolgeanalytikRamanKey() {
        return COLUMN_FOLGEANALYTIK_RAMAN_KEY;
    }

    public static String getColumnFolgeanalytikOmiKey() {
        return COLUMN_FOLGEANALYTIK_OMI_KEY;
    }

    public static String getColumnInformationenZurFolgeanalytikKey() {
        return COLUMN_INFORMATIONEN_ZUR_FOLGEANALYTIK_KEY;
    }

    public static String getColumnErgebnisDscKey() {
        return COLUMN_ERGEBNIS_DSC_KEY;
    }

    public static String getColumnErgebnisTgKey() {
        return COLUMN_ERGEBNIS_TG_KEY;
    }

    public static String getColumnErgebnisPxrdIiKey() {
        return COLUMN_ERGEBNIS_PXRD_II_KEY;
    }

    public static String getColumnErgebnisHNmrKey() {
        return COLUMN_ERGEBNIS_H_NMR_KEY;
    }

    public static String getColumnErgebnisIrKey() {
        return COLUMN_ERGEBNIS_IR_KEY;
    }

    public static String getColumnErgebnisRamanKey() {
        return COLUMN_ERGEBNIS_RAMAN_KEY;
    }

    public static String getColumnErgebnisOmiKey() {
        return COLUMN_ERGEBNIS_OMI_KEY;
    }

    public static String getColumnStatusAnalytikKey() {
        return COLUMN_STATUS_ANALYTIK_KEY;
    }

    public static String getColumnGesamtErgebnisKey() {
        return COLUMN_GESAMT_ERGEBNIS_KEY;
    }

    public static String getColumnEinstufungErgebnisKey() {
        return COLUMN_EINSTUFUNG_ERGEBNIS_KEY;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public String getValuesAsSQLString() {
        return null;
    }

    @Override
    public String getRelationSchema() {
        return null;
    }
}
