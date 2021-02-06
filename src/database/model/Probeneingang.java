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

	private String interneVergabenummer;
	private String wirkstoff;
	private String auftraggeber;
	private String probenNr;
	private String projektvertragnummer;
	private String anlagennummer;
	private String summenformel;
	private String bezeichnung;
	private String originator;
	private String probeneingangDatum;
	private String probenmasse;
	private String besonderheiten;
	private String infos;
	private String standort;
	private String messungDSC;
	private String messungPulver;
	private String messungIR;
	private String bemerkungenZurMessung;
	private boolean vertragVorhanden = false;
	private String vertragVorhandenDatum;
	private boolean vertragUnterzeichnet = false;
	private String vertragUnterzeichnetDatum;
	private boolean vertragVerschickt = false;
	private String vertragVerschicktDatum;
	private boolean vertragAbgerechnet = false;
	private String vertragAbgerechnetDatum;
	private boolean vertragBezahlt = false;
	private String vertragBezahltDatum;
	private String bemerkungen;
	private String literatur;
	private List<Bild> bilder = new LinkedList<>();
	
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
