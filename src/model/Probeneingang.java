package model;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Probe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Probeneingang {
    private static final Logger LOGGER = LogManager.getLogger(Probeneingang.class.getSimpleName());
    Probe probe;

    // GEHÖRT ZU:

    private String wirkstoff;                    // Substanz
    private String auftraggeber;                // Partner
    private String projektId;                    // Substanz-Id
    private String probenId;                    // Proben-Id
    private String summenformel;                // Substanz
    private String bezeichnung;                    // Substanz
    private String originator;                    // Substanz
    private String probeneingangDatum;            // Substanz
    private String probenmasse;                    // Substanz
    private String besonderheiten;                // Substanz
    private String infos;                        // Substanz
    private String standort;                    // Substanz
    private String charge;                        // Substanz
    private String bemerkungenZurMessung;        // Substanz
    private String bemerkungen;                    // Substanz
    private String literatur;                    // Substanz
    private List<Bild> bilder = new LinkedList<>();    // Substanz
    private List<Gefahrensymbol> gefahrensymbole = new LinkedList<>();    // Substanz

    public static final String WIRKSTOFF = "WIRKSTOFF";
    public static final String AUFTRAGGEBER = "AUFTRAGGEBER_NAME";
    public static final String PROJEKT_ID = Probe.COLUMN_PROJEKT_ID;
    public static final String PROBEN_ID = Probe.COLUMN_PRIMARY_KEY;
    public static final String SUMMENFORMEL = "SUMMENFORMEL";
    public static final String BEZEICHNUNG = "BEZEICHNUNG";
    public static final String ORIGINATOR = "ORIGINATOR";
    public static final String PROBENEINGANG = "PROBENEINGANG_DATUM";
    public static final String PROBENMASSE = "PROBENMASSE";
    public static final String BESONDERHEITEN = "BESONDERHEITEN";
    public static final String INFOS = "INFOS";
    public static final String STANDORT = "STANDORT";
    public static final String BEMERKUNGEN_ZUR_MESSUNG = "BEMERKUNGEN_ZUR_MESSUNG";
    public static final String BEMERKUNGEN = "BEMERKUNGEN";
    public static final String LITERATUR = "LITERATUR";

    public Probeneingang() {
        this.probe = new Probe();
    }

    public Probeneingang(String id) throws SQLException, ModelNotFoundException {
        this.probe = new Probe(id);
    }

    public String getWirkstoff() {
        return wirkstoff;
    }

    public void setWirkstoff(String wirkstoff) {
        this.wirkstoff = wirkstoff;
    }

    public String getAuftraggeber() {
        return auftraggeber;
    }

    public void setAuftraggeber(String auftraggeber) {
        this.auftraggeber = auftraggeber;
    }

    public String getProjektId() {
        return projektId;
    }

    public void setProjektId(String projektId) {
        System.out.println("Probeneingang setProjektID = " + projektId);
        this.projektId = projektId;
        try {
            probe.setProjektID(projektId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getProbenId() {
        return probenId;
    }

    public void setProbenId(String probenId) {
        this.probenId = probenId;
        probe.setPrimaryKey(probenId);
    }

    public String getSummenformel() {
        return summenformel;
    }

    public void setSummenformel(String summenformel) {
        this.summenformel = summenformel;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getProbeneingangDatum() {
        return probeneingangDatum;
    }

    public void setProbeneingangDatum(String probeneingangDatum) {
        this.probeneingangDatum = probeneingangDatum;
    }

    public String getProbenmasse() {
        return probenmasse;
    }

    public void setProbenmasse(String probenmasse) {
        this.probenmasse = probenmasse;
    }

    public String getBesonderheiten() {
        return besonderheiten;
    }

    public void setBesonderheiten(String besonderheiten) {
        this.besonderheiten = besonderheiten;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public String getStandort() {
        return standort;
    }

    public void setStandort(String standort) {
        this.standort = standort;
    }

    public String getBemerkungenZurMessung() {
        return bemerkungenZurMessung;
    }

    public void setBemerkungenZurMessung(String bemerkungenZurMessung) {
        this.bemerkungenZurMessung = bemerkungenZurMessung;
    }

    public String getBemerkungen() {
        return bemerkungen;
    }

    public void setBemerkungen(String bemerkungen) {
        this.bemerkungen = bemerkungen;
    }

    public String getLiteratur() {
        return literatur;
    }

    public void setLiteratur(String literatur) {
        this.literatur = literatur;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public List<Bild> getBilder() {
        return bilder;
    }

    public void addBild(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int b;
        while ((b = inputStream.read()) != -1) {
            byteArrayOutputStream.write(b);
        }

        byte[] bildArray = byteArrayOutputStream.toByteArray();

        Bild bild = new Bild(bildArray);
        bilder.add(bild);
    }

    public List<Gefahrensymbol> getGefahrensymbole() {
        return gefahrensymbole;
    }

    public void setGefahrensymbole(List<Gefahrensymbol> gefahrensymbole) {
        this.gefahrensymbole = gefahrensymbole;
    }

    public void saveToDatabasePlaceholderMethod() {
        // TODO Auto-generated method stub
    }

    public void setParameters(String parameterName, String parameter) {
//		LOGGER.debug(parameterName + ": " + parameter);

        switch (parameterName) {
            case PROJEKT_ID:                setProjektId(parameter);
                break;
            case AUFTRAGGEBER:              setAuftraggeber(parameter);
                break;
            case BEMERKUNGEN:               setBemerkungen(parameter);
                break;
            case BEMERKUNGEN_ZUR_MESSUNG:   setBemerkungenZurMessung(parameter);
                break;
            case BESONDERHEITEN:            setBesonderheiten(parameter);
                break;
            case BEZEICHNUNG:               setBezeichnung(parameter);
                break;
            case INFOS:                     setInfos(parameter);
                break;
            case LITERATUR:                 setLiteratur(parameter);
                break;
            case ORIGINATOR:                setOriginator(parameter);
                break;
            case PROBEN_ID:                 setProbenId(parameter);
                break;
            case PROBENEINGANG:             setProbeneingangDatum(parameter);
                break;
            case PROBENMASSE:               setProbenmasse(parameter);
                break;
            case STANDORT:                  setStandort(parameter);
                break;
            case SUMMENFORMEL:              setSummenformel(parameter);
                break;
            case WIRKSTOFF:                 setWirkstoff(parameter);
                break;
        }
    }
}
