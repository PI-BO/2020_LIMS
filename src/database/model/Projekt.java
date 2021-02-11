package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.relations.ProjekteSubstanz;
import exceptions.ModelNotFoundException;

public class Projekt extends Model {
	
	private String primaryKey;
	private String vertragsnummer;
	public static final String COLUMN_PRIMARY_KEY = "projekt_id";
	public static final String COLUMN_VERTRAGSNUMMER = "vertragsnummer";
	public static final String TABLE = "projekte";

	public Projekt(){
		
	}
	
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
	
	public void save() throws SQLException{
		database.setModel(this);
	}

	public List<Substanz> getSubstanzen() throws ModelNotFoundException, SQLException {
		ProjekteSubstanz projekteSubstanz = new ProjekteSubstanz(this);
		return projekteSubstanz.getSubstanzen();
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
	public String getValues() {
		return "\"" + primaryKey + "\",\"" + vertragsnummer + "\"";
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_VERTRAGSNUMMER;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public void setVertragsnummer(String vertragsnummer) {
		this.vertragsnummer = vertragsnummer;
	}

	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		
	}
}
