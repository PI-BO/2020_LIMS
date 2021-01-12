package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import controller.exceptions.MitarbeiterNotFoundException;
import controller.exceptions.ProjektNotFoundException;

public class Projekt extends Model {
	
	private String id;
	private List<Substanz> substanzen;

	
	public Projekt(String id) throws SQLException, ProjektNotFoundException{
		
		this.id = id;
		ResultSet resultSet = database.getProjekt(id);
		setAttributes(resultSet);
	}
	
	private void setAttributes(ResultSet resultSet) throws SQLException {

		substanzen = new LinkedList<>();
		
		while (resultSet.next()) {
			
			int projektIdIndex = resultSet.findColumn("projekt_id");		//TODO: muss nur einmal gemacht werden, key in Config auslagern
			id = resultSet.getString(projektIdIndex);
			
			int substanzIdIndex = resultSet.findColumn("substanz_id");		//TODO: key in Config auslagern
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
