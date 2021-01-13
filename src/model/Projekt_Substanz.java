package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exceptions.ModelNotFoundException;

public class Projekt_Substanz extends Model {

	private String primaryKey;
	private final String COLUMN_PRIMARY_KEY = Projekt.COLUMN_PRIMARY_KEY;
	private final String COLUMN_SECONDARY_KEY = Substanz.COLUMN_PRIMARY_KEY;
	private final String TABLE = "projekt_substanz";
	private List<Substanz> substanzen;
	
	public Projekt_Substanz(String projektId) throws SQLException, ModelNotFoundException {

		this.primaryKey = projektId;
		database.getModel(this);
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

	@Override
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {

		substanzen = new LinkedList<>();
		
		while (resultSet.next()) {
			
			int projektIdIndex = resultSet.findColumn(COLUMN_PRIMARY_KEY);
			primaryKey = resultSet.getString(projektIdIndex);
			
			int substanzIdIndex = resultSet.findColumn(COLUMN_SECONDARY_KEY);
			String substanzId = resultSet.getString(substanzIdIndex);
			Substanz substanz = new Substanz(substanzId);

			substanzen.add(substanz);
		}
	}

	public List<Substanz> getSubstanzen() {
		return substanzen;
	}

}
