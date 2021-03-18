package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimenttypSlurry extends Model {
    private String primaryKey;
    private int planung;
    private String serie;
    private String durchfuehrung;
    private String notiz_intention;
    private String verweis;
    private Date startfreigabe;
    private Date erledigt_bis;
    private String hinweis_laborleiter;
    private boolean planung_abgeschlossen;
    private String prioritaet;
    private String sicherheitshinweis;
    private int operator;
    private Date experiment_start;
    private double vial_tara;
    private double soll_einwaage;
    private double einwaage;
    private String cof_bezeichnung;
    private String cof_ref_code;
    private double cof_soll_einwaaage;
    private double cof_einwaage;
    private double soll_temperatur;
    private String loesungsmittel;
    private String vorgabe_volumen;
    private String ist_volumen;
    private String beobachtungen_aenderungen;
    private Date start;
    private String beobachtungen_verlauf;
    private Date dummyResultSetEntry;
    private String status;
    private Date aufarbeitung_präsentation_pxrd;
    private String beobachtung_aufarbeitung;
    private double ausbeute_vial_kristallisat;
    private double ausbeute_praep_analystik;
    private String lagerorte;
    private String prioritaet_analytik;
    private String erstanalytik_pxrd;
    private boolean pxrd_moeglich;
    private String weitere_analytik_moeglich;
    private boolean ergebnis_pxrd;
    private boolean folgeanalytik_dsc;
    private boolean folgeanalytik_tg;
    private boolean folgeanalytik_pxrd_ii;
    private boolean folgeanalytik_h_nmr;
    private boolean folgeanalytik_ir;
    private boolean folgeanalytik_raman;
    private boolean folgeanalytik_omi;
    private String informationen_folgeanalytik;
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
    public static final String COLUMN_PLANUNG_KEY = "planung";
    public static final String COLUMN_SERIE_KEY = "serie";
    public static final String COLUMN_DURCHFÜHRUNG_KEY = "durchfuehrung";
    public static final String COLUMN_NOTIZ_INTENTION_KEY = "notiz_intention";
    public static final String COLUMN_VERWEIS_KEY = "verweis";
    public static final String COLUMN_STARTFREIGABE_KEY = "startfreigabe";
    public static final String COLUMN_ERLEDIGT_BIS_KEY = "erledigt_bis";
    public static final String COLUMN_HINWEIS_LABORLEITER_KEY = "hinweis_laborleiter";
    public static final String COLUMN_PLANUNG_ABGESCHLOSSEN_KEY = "planung_abgeschlossen";
    public static final String COLUMN_PRIORITAET_KEY = "prioritaet";
    public static final String COLUMN_SICHERHEITSHINWEIS_KEY = "sicherheitshinweis";
    public static final String COLUMN_OPERATOR_KEY = "operator";
    public static final String COLUMN_EXPERIMENT_START_KEY = "experiment_start";
    public static final String COLUMN_SOLL_EINWAAGE_KEY = "soll_einwaage";
    public static final String COLUMN_EINWAAGE_KEY = "einwaage";
    public static final String COLUMN_COF_BEZEICHNUNG_KEY = "cof_bezeichnung";
    public static final String COLUMN_COF_REF_CODE_KEY = "cof_ref_code";
    public static final String COLUMN_COF_SOLL_EINWAAGE_KEY = "cof_soll_einwaaage";
    public static final String COLUMN_COF_EINWAAGE_KEY = "cof_einwaage";
    public static final String COLUMN_SOLL_TEMPERATUR_KEY = "soll_temperatur";
    public static final String COLUMN_LOESUNGSMITTEL_KEY = "loesungsmittel";
    public static final String COLUMN_VORGABE_VOLUMEN_KEY = "vorgabe_volumen";
    public static final String COLUMN_IST_VOLUMEN_KEY = "ist_volumen";
    public static final String COLUMN_BEOBACHTUNGEN_AENDERUNGEN_KEY = "beobachtungen_aenderungen";
    public static final String COLUMN_START_KEY = "start";
    public static final String COLUMN_BEOBACHTUNGEN_VERLAUF_KEY = "beobachtungen_verlauf";
    public static final String COLUMN_ENDE_KEY = "ende";
    public static final String COLUMN_STATUS_KEY = "status";
    public static final String COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY = "aufarbeitung_präsentation_pxrd";
    public static final String COLUMN_BEOBACHTUNG_AUFBEREITUNG_KEY = "beobachtung_aufarbeitung";
    public static final String COLUMN_LAGERORTE_KEY = "lagerorte";
    public static final String CPLUMN_PRIORITAET_ANALYSTIK_KEY = "prioritaet_analytik";
    public static final String COLUMN_ERSTANALYTIK_PXRD_KEY = "erstanalytik_pxrd";
    public static final String COLUMN_PXRD_MOEGLICH_KEY = "pxrd_moeglich";
    public static final String COLUMN_WEITERE_ANALYTIK_MOEGLICH_KEY = "weitere_analytik_moeglich";
    public static final String COLUMN_ERGEBNIS_PXRD_KEY = "ergebnis_pxrd";
    public static final String COLUMN_FOLGEANALYTIK_DSC_KEY = "folgeanalytik_dsc";
    public static final String COLUMN_FOLGEANALYTIK_TG_KEY = "folgeanalytik_tg";
    public static final String COLUMN_FOLGEANALYTIK_PXRD_II_KEY = "folgeanalytik_pxrd_ii";
    public static final String COLUMN_FOLGEANALYTIK_H_NMR_KEY = "folgeanalytik_h-nmr";
    public static final String COLUMN_FOLGEANALYTIK_IR_KEY = "folgeanalytik_ir";
    public static final String COLUMN_FOLGEANALYTIK_RAMAN_KEY = "folgeanalytik_raman";
    public static final String COLUMN_FOLGEANALYTIK_OMI_KEY = "folgeanalytik_omi";
    public static final String COLUMN_INFORMATIONEN_FOLGEANALYTIK_KEY = "informationen_folgeanalytik";
    public static final String COLUMN_ERGEBNIS_DSC_KEY = "ergebnis_dsc";
    public static final String COLUMN_ERGEBNIS_TG_KEY = "ergebnis_tg";
    public static final String COLUMN_ERGEBNIS_PXRD_II_KEY = "ergebnis_pxrd_ii";
    public static final String COLUMN_ERGEBNIS_H_NMR_KEY = "ergebnis_h-nmr";
    public static final String COLUMN_ERGEBNIS_IR_KEY = "ergebnis_ir";
    public static final String COLUMN_ERGEBNIS_RAMAN_KEY = "ergebnis_raman";
    public static final String COLUMN_ERGEBNIS_OMI_KEY = "ergebnis_omi";
    public static final String COLUMN_STATUS_ANALYTIK_KEY = "status_analytik";
    public static final String COLUMN_GESAMT_ERGEBNIS_KEY = "gesamt_ergebnis";
    public static final String COLUMN_EINSTUFUNG_ERGEBNIS_KEY = "einstufung_ergebnis";
    public static final String TABLE = "experimenttyp_verdampfung";

    public ExperimenttypSlurry(){
		super();
	}
	
	public ExperimenttypSlurry(Model parent) {
    	super(parent);
    }
    
    public ExperimenttypSlurry(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
    }

    public int getPlanung() {
        return planung;
    }

    public void setPlanung(int planung) {
        this.planung = planung;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDurchfuehrung() {
        return durchfuehrung;
    }

    public void setDurchfuehrung(String durchfuehrung) {
        this.durchfuehrung = durchfuehrung;
    }

    public String getNotiz_intention() {
        return notiz_intention;
    }

    public void setNotiz_intention(String notiz_intention) {
        this.notiz_intention = notiz_intention;
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

    public String getHinweis_laborleiter() {
        return hinweis_laborleiter;
    }

    public void setHinweis_laborleiter(String hinweis_laborleiter) {
        this.hinweis_laborleiter = hinweis_laborleiter;
    }

    public boolean isPlanung_abgeschlossen() {
        return planung_abgeschlossen;
    }

    public void setPlanung_abgeschlossen(boolean planung_abgeschlossen) {
        this.planung_abgeschlossen = planung_abgeschlossen;
    }

    public String getPrioritaet() {
        return prioritaet;
    }

    public void setPrioritaet(String prioritaet) {
        this.prioritaet = prioritaet;
    }

    public String getSicherheitshinweis() {
        return sicherheitshinweis;
    }

    public void setSicherheitshinweis(String sicherheitshinweis) {
        this.sicherheitshinweis = sicherheitshinweis;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
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

    public double getSoll_einwaage() {
        return soll_einwaage;
    }

    public void setSoll_einwaage(double soll_einwaage) {
        this.soll_einwaage = soll_einwaage;
    }

    public double getEinwaage() {
        return einwaage;
    }

    public void setEinwaage(double einwaage) {
        this.einwaage = einwaage;
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

    public double getCof_einwaage() {
        return cof_einwaage;
    }

    public void setCof_einwaage(double cof_einwaage) {
        this.cof_einwaage = cof_einwaage;
    }

    public double getSoll_temperatur() {
        return soll_temperatur;
    }

    public void setSoll_temperatur(double soll_temperatur) {
        this.soll_temperatur = soll_temperatur;
    }

    public String getLoesungsmittel() {
        return loesungsmittel;
    }

    public void setLoesungsmittel(String loesungsmittel) {
        this.loesungsmittel = loesungsmittel;
    }

    public String getVorgabe_volumen() {
        return vorgabe_volumen;
    }

    public void setVorgabe_volumen(String vorgabe_volumen) {
        this.vorgabe_volumen = vorgabe_volumen;
    }

    public String getIst_volumen() {
        return ist_volumen;
    }

    public void setIst_volumen(String ist_volumen) {
        this.ist_volumen = ist_volumen;
    }

    public String getBeobachtungen_aenderungen() {
        return beobachtungen_aenderungen;
    }

    public void setBeobachtungen_aenderungen(String beobachtungen_aenderungen) {
        this.beobachtungen_aenderungen = beobachtungen_aenderungen;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getBeobachtungen_verlauf() {
        return beobachtungen_verlauf;
    }

    public void setBeobachtungen_verlauf(String beobachtungen_verlauf) {
        this.beobachtungen_verlauf = beobachtungen_verlauf;
    }

    public Date getDummyResultSetEntry() {
        return dummyResultSetEntry;
    }

    public void setDummyResultSetEntry(Date dummyResultSetEntry) {
        this.dummyResultSetEntry = dummyResultSetEntry;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAufarbeitung_präsentation_pxrd() {
        return aufarbeitung_präsentation_pxrd;
    }

    public void setAufarbeitung_präsentation_pxrd(Date aufarbeitung_präsentation_pxrd) {
        this.aufarbeitung_präsentation_pxrd = aufarbeitung_präsentation_pxrd;
    }

    public String getBeobachtung_aufarbeitung() {
        return beobachtung_aufarbeitung;
    }

    public void setBeobachtung_aufarbeitung(String beobachtung_aufarbeitung) {
        this.beobachtung_aufarbeitung = beobachtung_aufarbeitung;
    }

    public double getAusbeute_vial_kristallisat() {
        return ausbeute_vial_kristallisat;
    }

    public void setAusbeute_vial_kristallisat(double ausbeute_vial_kristallisat) {
        this.ausbeute_vial_kristallisat = ausbeute_vial_kristallisat;
    }

    public double getAusbeute_praep_analystik() {
        return ausbeute_praep_analystik;
    }

    public void setAusbeute_praep_analystik(double ausbeute_praep_analystik) {
        this.ausbeute_praep_analystik = ausbeute_praep_analystik;
    }

    public String getLagerorte() {
        return lagerorte;
    }

    public void setLagerorte(String lagerorte) {
        this.lagerorte = lagerorte;
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

    public String getWeitere_analytik_moeglich() {
        return weitere_analytik_moeglich;
    }

    public void setWeitere_analytik_moeglich(String weitere_analytik_moeglich) {
        this.weitere_analytik_moeglich = weitere_analytik_moeglich;
    }

    public boolean isErgebnis_pxrd() {
        return ergebnis_pxrd;
    }

    public void setErgebnis_pxrd(boolean ergebnis_pxrd) {
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

    public String getInformationen_folgeanalytik() {
        return informationen_folgeanalytik;
    }

    public void setInformationen_folgeanalytik(String informationen_folgeanalytik) {
        this.informationen_folgeanalytik = informationen_folgeanalytik;
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

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnPlanungKey() {
        return COLUMN_PLANUNG_KEY;
    }

    public static String getColumnSerieKey() {
        return COLUMN_SERIE_KEY;
    }

    public static String getColumnDurchfuehrungKey() {
        return COLUMN_DURCHFÜHRUNG_KEY;
    }

    public static String getColumnNotizIntentionKey() {
        return COLUMN_NOTIZ_INTENTION_KEY;
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

    public static String getColumnPrioritaetKey() {
        return COLUMN_PRIORITAET_KEY;
    }

    public static String getColumnSicherheitshinweisKey() {
        return COLUMN_SICHERHEITSHINWEIS_KEY;
    }

    public static String getColumnOperatorKey() {
        return COLUMN_OPERATOR_KEY;
    }

    public static String getColumnExperimentStartKey() {
        return COLUMN_EXPERIMENT_START_KEY;
    }

    public static String getColumnSollEinwaageKey() {
        return COLUMN_SOLL_EINWAAGE_KEY;
    }

    public static String getColumnEinwaageKey() {
        return COLUMN_EINWAAGE_KEY;
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

    public static String getColumnCofEinwaageKey() {
        return COLUMN_COF_EINWAAGE_KEY;
    }

    public static String getColumnSollTemperaturKey() {
        return COLUMN_SOLL_TEMPERATUR_KEY;
    }

    public static String getColumnLoesungsmittelKey() {
        return COLUMN_LOESUNGSMITTEL_KEY;
    }

    public static String getColumnVorgabeVolumenKey() {
        return COLUMN_VORGABE_VOLUMEN_KEY;
    }

    public static String getColumnIstVolumenKey() {
        return COLUMN_IST_VOLUMEN_KEY;
    }

    public static String getColumnBeobachtungenAenderungenKey() {
        return COLUMN_BEOBACHTUNGEN_AENDERUNGEN_KEY;
    }

    public static String getColumnStartKey() {
        return COLUMN_START_KEY;
    }

    public static String getColumnBeobachtungenVerlaufKey() {
        return COLUMN_BEOBACHTUNGEN_VERLAUF_KEY;
    }

    public static String getColumnEndeKey() {
        return COLUMN_ENDE_KEY;
    }

    public static String getColumnStatusKey() {
        return COLUMN_STATUS_KEY;
    }

    public static String getColumnAufbereitungPraesentationPxrdKey() {
        return COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY;
    }

    public static String getColumnBeobachtungAufbereitungKey() {
        return COLUMN_BEOBACHTUNG_AUFBEREITUNG_KEY;
    }

    public static String getColumnLagerorteKey() {
        return COLUMN_LAGERORTE_KEY;
    }

    public static String getCplumnPrioritaetAnalystikKey() {
        return CPLUMN_PRIORITAET_ANALYSTIK_KEY;
    }

    public static String getColumnErstanalytikPxrdKey() {
        return COLUMN_ERSTANALYTIK_PXRD_KEY;
    }

    public static String getColumnPxrdMoeglichKey() {
        return COLUMN_PXRD_MOEGLICH_KEY;
    }

    public static String getColumnWeitereAnalytikMoeglichKey() {
        return COLUMN_WEITERE_ANALYTIK_MOEGLICH_KEY;
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

    public static String getColumnInformationenFolgeanalytikKey() {
        return COLUMN_INFORMATIONEN_FOLGEANALYTIK_KEY;
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
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(COLUMN_PRIMARY_KEY);
            planung = resultSet.getInt(COLUMN_PLANUNG_KEY);
            serie = resultSet.getString(COLUMN_SERIE_KEY);
            durchfuehrung = resultSet.getString(COLUMN_DURCHFÜHRUNG_KEY);
            notiz_intention = resultSet.getString(COLUMN_NOTIZ_INTENTION_KEY);
            verweis = resultSet.getString(COLUMN_VERWEIS_KEY);
            startfreigabe = resultSet.getDate(COLUMN_STARTFREIGABE_KEY);
            erledigt_bis = resultSet.getDate(COLUMN_ERLEDIGT_BIS_KEY);
            hinweis_laborleiter = resultSet.getString(COLUMN_HINWEIS_LABORLEITER_KEY);
            planung_abgeschlossen = resultSet.getBoolean(COLUMN_PLANUNG_ABGESCHLOSSEN_KEY);
            prioritaet = resultSet.getString(COLUMN_PRIORITAET_KEY);
            sicherheitshinweis = resultSet.getString(COLUMN_SICHERHEITSHINWEIS_KEY);
            operator = resultSet.getInt(COLUMN_OPERATOR_KEY);
            experiment_start = resultSet.getDate(COLUMN_EXPERIMENT_START_KEY);
            soll_einwaage = resultSet.getDouble(COLUMN_SOLL_EINWAAGE_KEY);
            einwaage = resultSet.getDouble(COLUMN_EINWAAGE_KEY);
            cof_bezeichnung = resultSet.getString(COLUMN_COF_BEZEICHNUNG_KEY);
            cof_ref_code = resultSet.getString(COLUMN_COF_REF_CODE_KEY);
            cof_soll_einwaaage = resultSet.getDouble(COLUMN_COF_SOLL_EINWAAGE_KEY);
            cof_einwaage = resultSet.getDouble(COLUMN_COF_EINWAAGE_KEY);
            soll_temperatur = resultSet.getDouble(COLUMN_SOLL_TEMPERATUR_KEY);
            loesungsmittel = resultSet.getString(COLUMN_LOESUNGSMITTEL_KEY);
            vorgabe_volumen = resultSet.getString(COLUMN_VORGABE_VOLUMEN_KEY);
            ist_volumen = resultSet.getString(COLUMN_IST_VOLUMEN_KEY);
            beobachtungen_aenderungen = resultSet.getString(COLUMN_BEOBACHTUNGEN_AENDERUNGEN_KEY);
            start = resultSet.getDate(COLUMN_START_KEY);
            beobachtungen_verlauf = resultSet.getString(COLUMN_BEOBACHTUNGEN_VERLAUF_KEY);
            dummyResultSetEntry = resultSet.getDate(COLUMN_ENDE_KEY);
            status = resultSet.getString(COLUMN_STATUS_KEY);
            aufarbeitung_präsentation_pxrd = resultSet.getDate(COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY);
            beobachtung_aufarbeitung = resultSet.getString(COLUMN_BEOBACHTUNG_AUFBEREITUNG_KEY);
            lagerorte = resultSet.getString(COLUMN_LAGERORTE_KEY);
            prioritaet_analytik = resultSet.getString(CPLUMN_PRIORITAET_ANALYSTIK_KEY);
            erstanalytik_pxrd = resultSet.getString(COLUMN_ERSTANALYTIK_PXRD_KEY);
            pxrd_moeglich = resultSet.getBoolean(COLUMN_PXRD_MOEGLICH_KEY);
            weitere_analytik_moeglich = resultSet.getString(COLUMN_WEITERE_ANALYTIK_MOEGLICH_KEY);
            ergebnis_pxrd = resultSet.getBoolean(COLUMN_ERGEBNIS_PXRD_KEY);
            folgeanalytik_dsc = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_DSC_KEY);
            folgeanalytik_tg = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_TG_KEY);
            folgeanalytik_pxrd_ii = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_PXRD_II_KEY);
            folgeanalytik_h_nmr = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_H_NMR_KEY);
            folgeanalytik_ir = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_IR_KEY);
            folgeanalytik_raman = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_RAMAN_KEY);
            folgeanalytik_omi = resultSet.getBoolean(COLUMN_FOLGEANALYTIK_OMI_KEY);
            informationen_folgeanalytik = resultSet.getString(COLUMN_INFORMATIONEN_FOLGEANALYTIK_KEY);
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
            throw new ModelNotFoundException("ExperimenttypSlurry nicht gefunden");
        }
    }

    @Override
    public String getValuesAsSQLString() {
        return primaryKey + ",";
    }

    @Override
    public String getRelationSchema() {
        return COLUMN_PRIMARY_KEY + ",";
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {

        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
        dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
        dummyResultSetEntry.addKeyValuePair(COLUMN_PLANUNG_KEY, Integer.toString(planung));
        dummyResultSetEntry.addKeyValuePair(COLUMN_SERIE_KEY, serie);
        dummyResultSetEntry.addKeyValuePair(COLUMN_DURCHFÜHRUNG_KEY, durchfuehrung);
        dummyResultSetEntry.addKeyValuePair(COLUMN_NOTIZ_INTENTION_KEY, notiz_intention);
        dummyResultSetEntry.addKeyValuePair(COLUMN_VERWEIS_KEY, verweis);
        dummyResultSetEntry.addKeyValuePair(COLUMN_STARTFREIGABE_KEY, startfreigabe.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERLEDIGT_BIS_KEY, erledigt_bis.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_HINWEIS_LABORLEITER_KEY, hinweis_laborleiter);
        dummyResultSetEntry.addKeyValuePair(COLUMN_PLANUNG_ABGESCHLOSSEN_KEY, Boolean.toString(planung_abgeschlossen));
        dummyResultSetEntry.addKeyValuePair(COLUMN_PRIORITAET_KEY, prioritaet);
        dummyResultSetEntry.addKeyValuePair(COLUMN_SICHERHEITSHINWEIS_KEY, sicherheitshinweis);
        dummyResultSetEntry.addKeyValuePair(COLUMN_OPERATOR_KEY, Integer.toString(operator));
        dummyResultSetEntry.addKeyValuePair(COLUMN_EXPERIMENT_START_KEY, experiment_start.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_SOLL_EINWAAGE_KEY, Double.toString(soll_einwaage));
        dummyResultSetEntry.addKeyValuePair(COLUMN_EINWAAGE_KEY, Double.toString(einwaage));
        dummyResultSetEntry.addKeyValuePair(COLUMN_COF_BEZEICHNUNG_KEY, cof_bezeichnung);
        dummyResultSetEntry.addKeyValuePair(COLUMN_COF_REF_CODE_KEY, cof_ref_code);
        dummyResultSetEntry.addKeyValuePair(COLUMN_COF_SOLL_EINWAAGE_KEY, Double.toString(cof_soll_einwaaage));
        dummyResultSetEntry.addKeyValuePair(COLUMN_COF_EINWAAGE_KEY, Double.toString(cof_einwaage));
        dummyResultSetEntry.addKeyValuePair(COLUMN_SOLL_TEMPERATUR_KEY, Double.toString(soll_temperatur));
        dummyResultSetEntry.addKeyValuePair(COLUMN_LOESUNGSMITTEL_KEY, loesungsmittel);
        dummyResultSetEntry.addKeyValuePair(COLUMN_VORGABE_VOLUMEN_KEY, vorgabe_volumen);
        dummyResultSetEntry.addKeyValuePair(COLUMN_IST_VOLUMEN_KEY, ist_volumen);
        dummyResultSetEntry.addKeyValuePair(COLUMN_BEOBACHTUNGEN_AENDERUNGEN_KEY, beobachtungen_aenderungen);
        dummyResultSetEntry.addKeyValuePair(COLUMN_START_KEY, start.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_BEOBACHTUNGEN_VERLAUF_KEY, beobachtungen_verlauf);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ENDE_KEY, this.dummyResultSetEntry.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_STATUS_KEY, status);
        dummyResultSetEntry.addKeyValuePair(COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY, aufarbeitung_präsentation_pxrd.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_BEOBACHTUNG_AUFBEREITUNG_KEY, beobachtung_aufarbeitung);
        dummyResultSetEntry.addKeyValuePair(COLUMN_LAGERORTE_KEY, lagerorte);
        dummyResultSetEntry.addKeyValuePair(CPLUMN_PRIORITAET_ANALYSTIK_KEY, prioritaet_analytik);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERSTANALYTIK_PXRD_KEY, erstanalytik_pxrd);
        dummyResultSetEntry.addKeyValuePair(COLUMN_PXRD_MOEGLICH_KEY, Boolean.toString(pxrd_moeglich));
        dummyResultSetEntry.addKeyValuePair(COLUMN_WEITERE_ANALYTIK_MOEGLICH_KEY, weitere_analytik_moeglich);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERGEBNIS_PXRD_KEY, Boolean.toString(ergebnis_pxrd));
        dummyResultSetEntry.addKeyValuePair(COLUMN_FOLGEANALYTIK_DSC_KEY, Boolean.toString(folgeanalytik_dsc));
        dummyResultSetEntry.addKeyValuePair(COLUMN_FOLGEANALYTIK_TG_KEY, Boolean.toString(folgeanalytik_tg));
        dummyResultSetEntry.addKeyValuePair(COLUMN_FOLGEANALYTIK_PXRD_II_KEY, Boolean.toString(folgeanalytik_pxrd_ii));
        dummyResultSetEntry.addKeyValuePair(COLUMN_FOLGEANALYTIK_H_NMR_KEY, Boolean.toString(folgeanalytik_h_nmr));
        dummyResultSetEntry.addKeyValuePair(COLUMN_FOLGEANALYTIK_IR_KEY, Boolean.toString(folgeanalytik_ir));
        dummyResultSetEntry.addKeyValuePair(COLUMN_FOLGEANALYTIK_RAMAN_KEY, Boolean.toString(folgeanalytik_raman));
        dummyResultSetEntry.addKeyValuePair(COLUMN_FOLGEANALYTIK_OMI_KEY, Boolean.toString(folgeanalytik_omi));
        dummyResultSetEntry.addKeyValuePair(COLUMN_INFORMATIONEN_FOLGEANALYTIK_KEY, informationen_folgeanalytik);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERGEBNIS_DSC_KEY, ergebnis_dsc);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERGEBNIS_TG_KEY, ergebnis_tg);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERGEBNIS_PXRD_II_KEY, ergebnis_pxrd_ii);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERGEBNIS_H_NMR_KEY, ergebnis_h_nmr);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERGEBNIS_IR_KEY, ergebnis_ir);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERGEBNIS_RAMAN_KEY, ergebnis_raman);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ERGEBNIS_OMI_KEY, ergebnis_omi);
        dummyResultSetEntry.addKeyValuePair(COLUMN_STATUS_ANALYTIK_KEY, status_analytik);
        dummyResultSetEntry.addKeyValuePair(COLUMN_GESAMT_ERGEBNIS_KEY, gesamt_ergebnis);
        dummyResultSetEntry.addKeyValuePair(COLUMN_EINSTUFUNG_ERGEBNIS_KEY, einstufung_ergebnis);
        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
    }

	@Override
	public String getForeignKey() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("id", primaryKey);
		json.addKeyValue("table", TABLE);
		
        json.addKeyValue(COLUMN_PLANUNG_KEY, Integer.toString(planung));
        json.addKeyValue(COLUMN_SERIE_KEY, serie);
        json.addKeyValue(COLUMN_DURCHFÜHRUNG_KEY, durchfuehrung);
        json.addKeyValue(COLUMN_NOTIZ_INTENTION_KEY, notiz_intention);
        json.addKeyValue(COLUMN_VERWEIS_KEY, verweis);
        json.addKeyValue(COLUMN_STARTFREIGABE_KEY, startfreigabe.toString());
        json.addKeyValue(COLUMN_ERLEDIGT_BIS_KEY, erledigt_bis.toString());
        json.addKeyValue(COLUMN_HINWEIS_LABORLEITER_KEY, hinweis_laborleiter);
        json.addKeyValue(COLUMN_PLANUNG_ABGESCHLOSSEN_KEY, Boolean.toString(planung_abgeschlossen));
        json.addKeyValue(COLUMN_PRIORITAET_KEY, prioritaet);
        json.addKeyValue(COLUMN_SICHERHEITSHINWEIS_KEY, sicherheitshinweis);
        json.addKeyValue(COLUMN_OPERATOR_KEY, Integer.toString(operator));
        json.addKeyValue(COLUMN_EXPERIMENT_START_KEY, experiment_start.toString());
        json.addKeyValue(COLUMN_SOLL_EINWAAGE_KEY, Double.toString(soll_einwaage));
        json.addKeyValue(COLUMN_EINWAAGE_KEY, Double.toString(einwaage));
        json.addKeyValue(COLUMN_COF_BEZEICHNUNG_KEY, cof_bezeichnung);
        json.addKeyValue(COLUMN_COF_REF_CODE_KEY, cof_ref_code);
        json.addKeyValue(COLUMN_COF_SOLL_EINWAAGE_KEY, Double.toString(cof_soll_einwaaage));
        json.addKeyValue(COLUMN_COF_EINWAAGE_KEY, Double.toString(cof_einwaage));
        json.addKeyValue(COLUMN_SOLL_TEMPERATUR_KEY, Double.toString(soll_temperatur));
        json.addKeyValue(COLUMN_LOESUNGSMITTEL_KEY, loesungsmittel);
        json.addKeyValue(COLUMN_VORGABE_VOLUMEN_KEY, vorgabe_volumen);
        json.addKeyValue(COLUMN_IST_VOLUMEN_KEY, ist_volumen);
        json.addKeyValue(COLUMN_BEOBACHTUNGEN_AENDERUNGEN_KEY, beobachtungen_aenderungen);
        json.addKeyValue(COLUMN_START_KEY, start.toString());
        json.addKeyValue(COLUMN_BEOBACHTUNGEN_VERLAUF_KEY, beobachtungen_verlauf);
        json.addKeyValue(COLUMN_ENDE_KEY, this.dummyResultSetEntry.toString());
        json.addKeyValue(COLUMN_STATUS_KEY, status);
        json.addKeyValue(COLUMN_AUFBEREITUNG_PRAESENTATION_PXRD_KEY, aufarbeitung_präsentation_pxrd.toString());
        json.addKeyValue(COLUMN_BEOBACHTUNG_AUFBEREITUNG_KEY, beobachtung_aufarbeitung);
        json.addKeyValue(COLUMN_LAGERORTE_KEY, lagerorte);
        json.addKeyValue(CPLUMN_PRIORITAET_ANALYSTIK_KEY, prioritaet_analytik);
        json.addKeyValue(COLUMN_ERSTANALYTIK_PXRD_KEY, erstanalytik_pxrd);
        json.addKeyValue(COLUMN_PXRD_MOEGLICH_KEY, Boolean.toString(pxrd_moeglich));
        json.addKeyValue(COLUMN_WEITERE_ANALYTIK_MOEGLICH_KEY, weitere_analytik_moeglich);
        json.addKeyValue(COLUMN_ERGEBNIS_PXRD_KEY, Boolean.toString(ergebnis_pxrd));
        json.addKeyValue(COLUMN_FOLGEANALYTIK_DSC_KEY, Boolean.toString(folgeanalytik_dsc));
        json.addKeyValue(COLUMN_FOLGEANALYTIK_TG_KEY, Boolean.toString(folgeanalytik_tg));
        json.addKeyValue(COLUMN_FOLGEANALYTIK_PXRD_II_KEY, Boolean.toString(folgeanalytik_pxrd_ii));
        json.addKeyValue(COLUMN_FOLGEANALYTIK_H_NMR_KEY, Boolean.toString(folgeanalytik_h_nmr));
        json.addKeyValue(COLUMN_FOLGEANALYTIK_IR_KEY, Boolean.toString(folgeanalytik_ir));
        json.addKeyValue(COLUMN_FOLGEANALYTIK_RAMAN_KEY, Boolean.toString(folgeanalytik_raman));
        json.addKeyValue(COLUMN_FOLGEANALYTIK_OMI_KEY, Boolean.toString(folgeanalytik_omi));
        json.addKeyValue(COLUMN_INFORMATIONEN_FOLGEANALYTIK_KEY, informationen_folgeanalytik);
        json.addKeyValue(COLUMN_ERGEBNIS_DSC_KEY, ergebnis_dsc);
        json.addKeyValue(COLUMN_ERGEBNIS_TG_KEY, ergebnis_tg);
        json.addKeyValue(COLUMN_ERGEBNIS_PXRD_II_KEY, ergebnis_pxrd_ii);
        json.addKeyValue(COLUMN_ERGEBNIS_H_NMR_KEY, ergebnis_h_nmr);
        json.addKeyValue(COLUMN_ERGEBNIS_IR_KEY, ergebnis_ir);
        json.addKeyValue(COLUMN_ERGEBNIS_RAMAN_KEY, ergebnis_raman);
        json.addKeyValue(COLUMN_ERGEBNIS_OMI_KEY, ergebnis_omi);
        json.addKeyValue(COLUMN_STATUS_ANALYTIK_KEY, status_analytik);
        json.addKeyValue(COLUMN_GESAMT_ERGEBNIS_KEY, gesamt_ergebnis);
        json.addKeyValue(COLUMN_EINSTUFUNG_ERGEBNIS_KEY, einstufung_ergebnis);
		
		return json;
	}
}
