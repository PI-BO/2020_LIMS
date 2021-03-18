package model.database.tableModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.relations.ProjekteSubstanz;
import utility.JSON;

public class Projekt extends Model {

	public static final String COLUMN_PRIMARY_KEY = "projekt_id";
	public static final String COLUMN_VERTRAGSNUMMER = "vertragsnummer";
	public static final String TABLE = "projekte";
	private String vertragsnummer;
	
	public Projekt(){
		super();
	}
	
	public Projekt(Model parent) {
    	super(parent);
    }
    
    public Projekt(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
    }
	
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
		if (resultSet.next()) {
			int projektIdIndex = resultSet.findColumn(COLUMN_PRIMARY_KEY);
			primaryKey = resultSet.getString(projektIdIndex);
			vertragsnummer = resultSet.getString(COLUMN_VERTRAGSNUMMER);
		} else {
			throw new ModelNotFoundException("Projekt nicht gefunden");
		}
	}

	public List<Substanz> getSubstanzen() throws ModelNotFoundException, SQLException {
		ProjekteSubstanz projekteSubstanz = new ProjekteSubstanz(this);
		return projekteSubstanz.getSubstanzen();
	}
	
	@Override
	public String getPrimaryKeyColumn() {
		return COLUMN_PRIMARY_KEY;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_VERTRAGSNUMMER;
	}

	@Override
	public String getTable() {
		return TABLE;
	}

	@Override
	public String getValuesAsSQLString() {
		return "\"" + primaryKey + "\",\"" + vertragsnummer + "\"";
	}

	public void setVertragsnummer(String vertragsnummer) {
		this.vertragsnummer = vertragsnummer;
	}

	public String getVertragsnummer() {
		return vertragsnummer;
	}
	
	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		
		DummyResultSetEntry entry = new DummyResultSetEntry();
		entry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		entry.addKeyValuePair(COLUMN_VERTRAGSNUMMER, vertragsnummer);

		dummyResultSet.addEntry(entry);
		
		return dummyResultSet;
	}
	
	@Override
	public String getForeignKey() {
		return vertragsnummer;
	}
	
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", getTable());
		json.addKeyValue("id", getPrimaryKey());
		json.addKeyValue(COLUMN_VERTRAGSNUMMER, getVertragsnummer());
		
		return json;
	}
}
