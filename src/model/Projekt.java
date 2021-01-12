package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exceptions.ProjektNotFoundException;

public class Projekt extends Model {
	
	private String id;
	private List<Substanz> substanzen;
	public static final String COLUMN_PROJEKT_ID = "projekt_id";
	public static final String COLUMN_VORNAME = "vorname";
	public static final String COLUMN_NACHNAME = "nachname";
	public static final String COLUMN_PASSWORT = "passwort";

	
	public Projekt(String id) throws SQLException, ProjektNotFoundException{
		
		this.id = id;
		ResultSet resultSet = database.getProjekt(id);
		setAttributes(resultSet);
	}
	
	private void setAttributes(ResultSet resultSet) throws SQLException {

		substanzen = new LinkedList<>();
		
		while (resultSet.next()) {
			
			int projektIdIndex = resultSet.findColumn(COLUMN_PROJEKT_ID);
			id = resultSet.getString(projektIdIndex);
			
			int substanzIdIndex = resultSet.findColumn(Substanz.COLUMN_SUBSTANZ_ID);
			Substanz substanz = new Substanz();
			substanz.setId(resultSet.getString(substanzIdIndex));

			substanzen.add(substanz);
		}
	}

	
	public List<Substanz> getSubstanzen() {
		return substanzen;
	}

	public void setSubstanzen(List<Substanz> substanzen) {
		this.substanzen = substanzen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
