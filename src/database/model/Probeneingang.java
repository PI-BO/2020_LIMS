package database.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exceptions.ModelNotFoundException;

public class Probeneingang extends Model{
												// GEHÖRT ZU:
	
	private String interneVergabenummer;		// ??? Projekt-Id, Substanz-Vergabenummer, Substanz-Id / Probe-Id ???
	private String wirkstoff;					// Substanz
	private String auftraggeber;				// Partner
	private String probenNr;					// Substanz-Id
	private String projektvertragnummer;		// Projekt-Id
	private String anlagennummer;				// Substanz
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
	private String messungDSC;					// Substanz
	private String messungPulver;				// Substanz
	private String messungIR;					// Substanz
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
	public String getAuftraggeber() {
		return auftraggeber;
	}
	public void setAuftraggeber(String auftraggeber) {
		this.auftraggeber = auftraggeber;
	}
	public String getProbenNr() {
		return probenNr;
	}
	public void setProbenNr(String probenNr) {
		this.probenNr = probenNr;
	}
	public String getProjektvertragnummer() {
		return projektvertragnummer;
	}
	public void setProjektvertragnummer(String projektvertragnummer) {
		this.projektvertragnummer = projektvertragnummer;
	}
	public String getAnlagennummer() {
		return anlagennummer;
	}
	public void setAnlagennummer(String anlagennummer) {
		this.anlagennummer = anlagennummer;
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
	public String getMessungDSC() {
		return messungDSC;
	}
	public void setMessungDSC(String messungDSC) {
		this.messungDSC = messungDSC;
	}
	public String getMessungPulver() {
		return messungPulver;
	}
	public void setMessungPulver(String messungPulver) {
		this.messungPulver = messungPulver;
	}
	public String getMessungIR() {
		return messungIR;
	}
	public void setMessungIR(String messungIR) {
		this.messungIR = messungIR;
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
	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPrimaryKeyColumn() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getValues() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRelationSchema() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		System.out.println("PROBENEINGANG SAVED");
		
	}
	
	
}
