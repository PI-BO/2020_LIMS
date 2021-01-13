package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;
import exceptions.PasswordIncorrectException;

public class Mitarbeiter extends Model implements Login {
	
	private String primaryKey;
	private String password;
	private String vorname;
	private String nachname;
	
	private static final String TABLE= "mitarbeiter";
	private static final String COLUMN_PRIMARY_KEY= "mitarbeiterID";
	private static final String COLUMN_VORNAME= "vorname";
	private static final String COLUMN_NACHNAME = "nachname";
	private static final String COLUMN_PASSWORD= "passwort";
	
    public Mitarbeiter(String primaryKey) throws SQLException, ModelNotFoundException {
    	
    	this.primaryKey = primaryKey;
    	database.getModel(this);
    }

	public void setAttributes(ResultSet mitarbeiterResultSet) throws SQLException, ModelNotFoundException {

		if (mitarbeiterResultSet.next()) {

	       	int vornameIndex = mitarbeiterResultSet.findColumn(COLUMN_VORNAME);
	       	int nachnameIndex = mitarbeiterResultSet.findColumn(COLUMN_NACHNAME);
	       	int passwordIndex = mitarbeiterResultSet.findColumn(COLUMN_PASSWORD);
	       	
	       	vorname = mitarbeiterResultSet.getString(vornameIndex);
	       	nachname = mitarbeiterResultSet.getString(nachnameIndex);
	       	password = mitarbeiterResultSet.getString(passwordIndex); 
	       	
		}else{
       	
			throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
       }
	}
    
	@Override
	public void validate(String password) throws PasswordIncorrectException {
		
		if(!this.password.equals(password)) throw new PasswordIncorrectException();
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
	
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	@Override
	public String getPrimaryKey() {
		return primaryKey;
	}

	@Override
	public String getPrimaryKeyColumn() {
		return COLUMN_PRIMARY_KEY;
	}

	@Override
	public String getTable() {
		return TABLE;
	}
}
