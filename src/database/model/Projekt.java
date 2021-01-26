package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.relations.ProjekteSubstanz;
import exceptions.ModelNotFoundException;

public class Projekt extends Model {
	
	private String primaryKey;
	public static final String COLUMN_PRIMARY_KEY = "projekt_id";
	public static final String TABLE = "projekte";
	private List<Substanz> substanzen;
	
	public Projekt(String id) throws SQLException, ModelNotFoundException{
		this.primaryKey = id;
		database.getModel(this);
	}
	
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
		if (resultSet.next()) {
			int projektIdIndex = resultSet.findColumn(COLUMN_PRIMARY_KEY);
			primaryKey = resultSet.getString(projektIdIndex);
		} else {
			throw new ModelNotFoundException("Projekt nicht gefunden");
		}
	}

	public List<Substanz> getSubstanzen() throws ModelNotFoundException, SQLException {
		substanzen = new ArrayList<>();
		ProjekteSubstanz projekteSubstanz = new ProjekteSubstanz(this);
		substanzen = projekteSubstanz.getSubstanzen();
		

		return substanzen;

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
