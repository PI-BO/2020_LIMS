package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;

public class Eingangsanalyse extends Model{
	
	
	private String noId;
	private String screeningNo;
	private String planungErfolgtDurch;
	private String substanz;
	private String apiStartMeterialRefCode;
	private String projektleiterNotiz;
	private String verweis;
	private String startFreigabe;
	private String erledigtBisSoll;
	private String hinweis;
	private String planungAbgeschlossen;
	private String sicherheitshinweis;
	
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
		System.out.println("EINGANGSANALYSE SAVED");
		
	}
	public void setNoId(String value) {
		this.noId = value;
	}
	public void setScreeningNo(String value) {
		this.screeningNo = value;		
	}
	public void setPlanungErfolgtDurch(String value) {
		this.planungErfolgtDurch = value;		
	}
	public void setSubstanz(String value) {
		this.substanz = value;		
	}
	public void setApiStartMeterialRefCode(String value) {
		this.apiStartMeterialRefCode = value;		
	}
	public void setProjektleiterNotiz(String value) {
		this.projektleiterNotiz = value;		
	}
	public void setVerweis(String value) {
		this.verweis = value;		
	}
	public void setStartFreigabe(String value) {
		this.startFreigabe = value;		
	}
	public void setErledigtBisSoll(String value) {
		this.erledigtBisSoll = value;		
	}
	public void setHinweis(String value) {
		this.hinweis = value;		
	}
	public void setPlanungAbgeschlossen(String value) {
		this.planungAbgeschlossen = value;		
	}
	public void setSicherheitshinweis(String value) {
		this.sicherheitshinweis = value;		
	}
	public void addMethode(String value) {
		// TODO Auto-generated method stub		
	}
	public void addAuswahl(String value) {
		// TODO Auto-generated method stub
		
	}
	public void addOperator(String value) {
		// TODO Auto-generated method stub
		
	}
	public void addParameter(String value) {
		// TODO Auto-generated method stub
		
	}
	public void addMessfile(String value) {
		// TODO Auto-generated method stub
		
	}
	public void addStatus(String value) {
		// TODO Auto-generated method stub
		
	}
	
	
}
