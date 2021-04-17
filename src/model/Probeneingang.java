package model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Probeneingang {
												// GEHÖRT ZU:
	
	private String interneVergabenummer;		// ??? Projekt-Id, Substanz-Vergabenummer, Substanz-Id / Probe-Id ???
	private String wirkstoff;					// Substanz
	private String wirkstoffId;					// Substanz
	private String auftraggeber;				// Partner
	private String projektId;					// Substanz-Id
	private String probenId;					// Substanz-Id
	private String projektvertragnummer;		// Projekt-Id
	private String summenformel;				// Substanz
	private String bezeichnung;					// Substanz
	private String originator;					// Substanz
	private String probeneingangDatum;			// Substanz
	private String probenmasse;					// Substanz
	private String besonderheiten;				// Substanz
	private String infos;						// Substanz
	private String standort;					// Substanz
	private String charge;						// Substanz
	private boolean msds = false;				// Substanz
	private String bemerkungenZurMessung;		// Substanz
	private boolean vertragVorhanden = false;	// Substanz weil Projekt mehre Substanzen
	private String vertragVorhandenDatum;		// Substanz weil Projekt mehre Substanzen
	private boolean vertragUnterzeichnet = false;	// Substanz weil Projekt mehre Substanzen
	private String vertragUnterzeichnetDatum;	// Substanz weil Projekt mehre Substanzen
	private boolean vertragVerschickt = false;	// Substanz weil Projekt mehre Substanzen
	private String vertragVerschicktDatum;		// Substanz weil Projekt mehre Substanzen
	private boolean vertragAbgerechnet = false;	// Substanz weil Projekt mehre Substanzen
	private String vertragAbgerechnetDatum;		// Substanz weil Projekt mehre Substanzen
	private boolean vertragBezahlt = false;		// Substanz weil Projekt mehre Substanzen
	private String vertragBezahltDatum;			// Substanz weil Projekt mehre Substanzen
	private String bemerkungen;					// Substanz
	private String literatur;					// Substanz
	private List<Bild> bilder = new LinkedList<>();	// Substanz
	private List<Gefahrensymbol> gefahrensymbole = new LinkedList<>();	// Substanz
	
	private static final Logger LOGGER = LogManager.getLogger(Probeneingang.class.getSimpleName());
	
	public static final String INTERNE_VERGABENUMMER = "INTERNE_VERGABENUMMER";
	public static final String WIRKSTOFF = "WIRKSTOFF";
	public static final String WIRKSTOFF_ID = "WIRKSTOFF_ID123";
	public static final String AUFTRAGGEBER = "AUFTRAGGEBER_NAME";
	public static final String PROJEKT_ID = "PROJEKT_NAME";
	public static final String PROBEN_ID = "PROBEN_ID";
	public static final String PROJEKTVERTRAGNUMMER= "PROJEKTVERTRAGNUMMER";
	public static final String SUMMENFORMEL = "SUMMENFORMEL";
	public static final String BEZEICHNUNG = "BEZEICHNUNG";
	public static final String ORIGINATOR = "ORIGINATOR";
	public static final String PROBENEINGANG = "PROBENEINGANG_DATUM";
	public static final String PROBENMASSE = "PROBENMASSE";
	public static final String BESONDERHEITEN = "BESONDERHEITEN";
	public static final String INFOS = "INFOS";
	public static final String STANDORT = "STANDORT";
	public static final String BEMERKUNGEN_ZUR_MESSUNG = "BEMERKUNGEN_ZUR_MESSUNG";
	public static final String VERTRAG_VORHANDEN = "VERTRAG_VORHANDEN";
	public static final String VERTRAG_VORHANDEN_DATUM = "VERTRAG_VORHANDEN_DATUM";
	public static final String VERTRAG_UNTERZEICHNET = "VERTRAG_UNTERZEICHNET";
	public static final String VERTRAG_UNTERZEICHNET_DATUM = "VERTRAG_UNTERZEICHNET_DATUM";
	public static final String VERTRAG_VERSCHICKT = "VERTRAG_VERSCHICKT";
	public static final String VERTRAG_VERSCHICKT_DATUM = "VERTRAG_VERSCHICKT_DATUM";
	public static final String VERTRAG_ABGERECHNET = "VERTRAG_ABGERECHNET";
	public static final String VERTRAG_ABGERECHNET_DATUM = "VERTRAG_ABGERECHNET_DATUM";
	public static final String VERTRAG_BEZAHLT = "VERTRAG_BEZAHLT";
	public static final String VERTRAG_BEZAHLT_DATUM = "VERTRAG_BEZAHLT_DATUM";
	public static final String BEMERKUNGEN = "BEMERKUNGEN";
	public static final String LITERATUR = "LITERATUR";
	
	public String getInterneVergabenummer() {
		return interneVergabenummer;
	}
	public void setInterneVergabenummer(String interneVergabenummer) {
		this.interneVergabenummer = interneVergabenummer;
	}
	public String getWirkstoff() {
		return wirkstoff;
	}
	public void setWirkstoff(String wirkstoff) {
		this.wirkstoff = wirkstoff;
	}
	public String getWirkstoffId() {
		return wirkstoffId;
	}
	public void setWirkstoffId(String wirkstoffId) {
		this.wirkstoffId = wirkstoffId;
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
	}
	public String getProbenId() {
		return probenId;
	}
	public void setProbenId(String probenId) {
		this.probenId = probenId;
	}
	public String getProjektvertragnummer() {
		return projektvertragnummer;
	}
	public void setProjektvertragnummer(String projektvertragnummer) {
		this.projektvertragnummer = projektvertragnummer;
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
	public boolean isVertragVorhanden() {
		return vertragVorhanden;
	}
	public void setVertragVorhanden(boolean vertragVorhanden) {
		this.vertragVorhanden = vertragVorhanden;
	}
	public boolean isMsds() {
		return msds;
	}
	public void setMsds(boolean msds) {
		this.msds = msds;
	}
	public String getVertragVorhandenDatum() {
		return vertragVorhandenDatum;
	}
	public void setVertragVorhandenDatum(String vertragVorhandenDatum) {
		this.vertragVorhandenDatum = vertragVorhandenDatum;
	}
	public boolean isVertragUnterzeichnet() {
		return vertragUnterzeichnet;
	}
	public void setVertragUnterzeichnet(boolean vertragUnterzeichnet) {
		this.vertragUnterzeichnet = vertragUnterzeichnet;
	}
	public String getVertragUnterzeichnetDatum() {
		return vertragUnterzeichnetDatum;
	}
	public void setVertragUnterzeichnetDatum(String vertragUnterzeichnetDatum) {
		this.vertragUnterzeichnetDatum = vertragUnterzeichnetDatum;
	}
	public boolean isVertragVerschickt() {
		return vertragVerschickt;
	}
	public void setVertragVerschickt(boolean vertragVerschickt) {
		this.vertragVerschickt = vertragVerschickt;
	}
	public String getVertragVerschicktDatum() {
		return vertragVerschicktDatum;
	}
	public void setVertragVerschicktDatum(String vertragVerschicktDatum) {
		this.vertragVerschicktDatum = vertragVerschicktDatum;
	}
	public boolean isVertragAbgerechnet() {
		return vertragAbgerechnet;
	}
	public void setVertragAbgerechnet(boolean vertragAbgerechnet) {
		this.vertragAbgerechnet = vertragAbgerechnet;
	}
	public String getVertragAbgerechnetDatum() {
		return vertragAbgerechnetDatum;
	}
	public void setVertragAbgerechnetDatum(String vertragAbgerechnetDatum) {
		this.vertragAbgerechnetDatum = vertragAbgerechnetDatum;
	}
	public boolean isVertragBezahlt() {
		return vertragBezahlt;
	}
	public void setVertragBezahlt(boolean vertragBezahlt) {
		this.vertragBezahlt = vertragBezahlt;
	}
	public String getVertragBezahltDatum() {
		return vertragBezahltDatum;
	}
	public void setVertragBezahltDatum(String vertragBezahltDatum) {
		this.vertragBezahltDatum = vertragBezahltDatum;
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

		int b = 0;
		
		while((b = inputStream.read()) != -1)  
		{  
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
	
	public void setParameters(String parameterName, String parameter){
		
//		LOGGER.debug(parameterName + ": " + parameter);
		
		if(parameterName.equals(PROJEKT_ID))                          		{ setProjektId(parameter);              return; }
		if(parameterName.equals(AUFTRAGGEBER))                          	{ setAuftraggeber(parameter);              return; }
		if(parameterName.equals(BEMERKUNGEN))                           	{ setBemerkungen(parameter);               return; }
		if(parameterName.equals(BEMERKUNGEN_ZUR_MESSUNG))               	{ setBemerkungenZurMessung(parameter);     return; }
		if(parameterName.equals(BESONDERHEITEN))                        	{ setBesonderheiten(parameter);            return; }
		if(parameterName.equals(BEZEICHNUNG))                           	{ setBezeichnung(parameter);               return; }
		if(parameterName.equals(INFOS))                                 	{ setInfos(parameter);                     return; }
		if(parameterName.equals(INTERNE_VERGABENUMMER))                 	{ setInterneVergabenummer(parameter);      return; }
		if(parameterName.equals(LITERATUR))                             	{ setLiteratur(parameter);                 return; }
		if(parameterName.equals(ORIGINATOR))                            	{ setOriginator(parameter);                return; }
		if(parameterName.equals(PROBEN_ID))                             	{ setProbenId(parameter);                  return; }
		if(parameterName.equals(PROBENEINGANG))                         	{ setProbeneingangDatum(parameter);        return; }
		if(parameterName.equals(PROBENMASSE))                           	{ setProbenmasse(parameter);               return; }
		if(parameterName.equals(PROJEKTVERTRAGNUMMER))                  	{ setProjektvertragnummer(parameter);      return; }
		if(parameterName.equals(STANDORT))                              	{ setStandort(parameter);                  return; }
		if(parameterName.equals(SUMMENFORMEL))                          	{ setSummenformel(parameter);              return; }
		if(parameterName.equals(VERTRAG_ABGERECHNET))                   	{ setVertragAbgerechnet(true);             return; }
		if(parameterName.equals(VERTRAG_ABGERECHNET_DATUM))             	{ setVertragAbgerechnetDatum(parameter);   return; }
		if(parameterName.equals(VERTRAG_BEZAHLT))                       	{ setVertragBezahlt(true);                 return; }
		if(parameterName.equals(VERTRAG_BEZAHLT_DATUM))                 	{ setVertragBezahltDatum(parameter);       return; }
		if(parameterName.equals(VERTRAG_UNTERZEICHNET))                 	{ setVertragUnterzeichnet(true);           return; }
		if(parameterName.equals(VERTRAG_UNTERZEICHNET_DATUM))           	{ setVertragUnterzeichnetDatum(parameter); return; }
		if(parameterName.equals(VERTRAG_VERSCHICKT))                    	{ setVertragVerschickt(true);              return; }
		if(parameterName.equals(VERTRAG_VERSCHICKT_DATUM))              	{ setVertragVerschicktDatum(parameter);    return; }
		if(parameterName.equals(VERTRAG_VORHANDEN))                     	{ setVertragVorhanden(true);               return; }
		if(parameterName.equals(VERTRAG_VORHANDEN_DATUM))               	{ setVertragVorhandenDatum(parameter);     return; }
		if(parameterName.equals(WIRKSTOFF))                             	{ setWirkstoff(parameter);                 return; }
		if(parameterName.equals(WIRKSTOFF_ID))                              { setWirkstoffId(parameter);               return; }
	}
}
