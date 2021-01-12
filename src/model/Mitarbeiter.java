package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.exceptions.LoginInputInvalidException;
import controller.exceptions.MitarbeiterNotFoundException;
import controller.exceptions.PasswordIncorrectException;

public class Mitarbeiter extends Model implements Login {
	
	private int id;
	private String password;
	private String vorname;
	private String nachname;
	
    public Mitarbeiter(int id) throws SQLException, MitarbeiterNotFoundException {
    	
    	this.id = id;
    	ResultSet mitarbeiterResultSet = database.getMitarbeiter(id);
		setAttributes(mitarbeiterResultSet);
    	
    }

	private void setAttributes(ResultSet mitarbeiterResultSet) throws SQLException, MitarbeiterNotFoundException {

		if (mitarbeiterResultSet.next()) {

	       	int vornameIndex = mitarbeiterResultSet.findColumn("vorname");
	       	int nachnameIndex = mitarbeiterResultSet.findColumn("nachname");
	       	int passwordIndex = mitarbeiterResultSet.findColumn("passwort");
	       	
	       	vorname = mitarbeiterResultSet.getString(vornameIndex);
	       	nachname = mitarbeiterResultSet.getString(nachnameIndex);
	       	password = mitarbeiterResultSet.getString(passwordIndex); 
	       	
		}else{
       	
			throw new MitarbeiterNotFoundException("Mitarbeiter nicht gefunden");
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

	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
}
