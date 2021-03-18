package model.database.tableModels;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;
import exceptions.PasswordIncorrectException;
import model.Login;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

public class Mitarbeiter extends Model implements Login {
	
	private String password;
	private String vorname;
	private String nachname;
	private int rolle;
	
	public static final String TABLE = "mitarbeiter";
	public static final String COLUMN_PRIMARY_KEY = "mitarbeiterID";
	public static final String COLUMN_VORNAME = "vorname";
	public static final String COLUMN_NACHNAME = "nachname";
	public static final String COLUMN_PASSWORD= "passwort";
	public static final String COLUMN_ROLLE = "rolle";

	public Mitarbeiter(){
		super();
	}
	
	public Mitarbeiter(Model parent) {
    	super(parent);
    }
    
    public Mitarbeiter(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
    }
    
	public void setAttributes(ResultSet mitarbeiterResultSet) throws SQLException, ModelNotFoundException {
		if (mitarbeiterResultSet.next()) {
	       	int vornameIndex = mitarbeiterResultSet.findColumn(COLUMN_VORNAME);
	       	int nachnameIndex = mitarbeiterResultSet.findColumn(COLUMN_NACHNAME);
	       	int passwordIndex = mitarbeiterResultSet.findColumn(COLUMN_PASSWORD);

	       	primaryKey = mitarbeiterResultSet.getString(COLUMN_PRIMARY_KEY);
	       	vorname = mitarbeiterResultSet.getString(vornameIndex);
	       	nachname = mitarbeiterResultSet.getString(nachnameIndex);
	       	password = mitarbeiterResultSet.getString(passwordIndex);
	       	rolle = mitarbeiterResultSet.getInt(COLUMN_ROLLE);
		}else{
			throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
       }
	}
    
	@Override
	public void validate(String password) throws PasswordIncorrectException {
		if(!this.password.equals(password)) throw new PasswordIncorrectException();
	}

	public int getRolle() {
		return rolle;
	}

	public void setRolle(int rolle) {
		this.rolle = rolle;
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
	public String getPrimaryKeyColumn() {
		return COLUMN_PRIMARY_KEY;
	}

	@Override
	public String getTable() {
		return TABLE;
	}

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + vorname + "," + nachname + "," + password;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_VORNAME + "," + COLUMN_NACHNAME + "," + COLUMN_PASSWORD;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {
		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry entry = new DummyResultSetEntry();
		entry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		entry.addKeyValuePair(COLUMN_NACHNAME, nachname);
		entry.addKeyValuePair(COLUMN_VORNAME, vorname);
		entry.addKeyValuePair(COLUMN_PASSWORD, password);
		entry.addKeyValuePair(COLUMN_ROLLE, Integer.toString(rolle));
		
		dummyResultSet.addEntry(entry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		return rolle+"";
	}
	
	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", TABLE);
		json.addKeyValue("id", primaryKey);
		json.addKeyValue(COLUMN_NACHNAME, nachname);
		json.addKeyValue(COLUMN_VORNAME, vorname);
		json.addKeyValue(COLUMN_ROLLE, Integer.toString(rolle));
		
		return json;
	}
}
