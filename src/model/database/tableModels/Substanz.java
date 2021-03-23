package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.relations.ProjekteSubstanz;
import model.database.relations.SubstanzenProbe;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Substanz extends Model {

    private String projektID;
    public static final String COLUMN_PRIMARY_KEY = "substanz_id";
    public static final String COLUMN_PROJEKT_ID = "projekt_id";
	public static final String COLUMN_FOREIGN_KEY = COLUMN_PROJEKT_ID;
	public static final String TABLE = "substanz";

    public Substanz(){
		super();
	}
	
    public Substanz(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
    }

	public void setProjektID(String projektID) throws SQLException, ModelNotFoundException {
		this.projektID = projektID;
		Projekt projekt = new Projekt(projektID);
		this.addParent(projekt);
	}

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            projektID = resultSet.getString(resultSet.findColumn(COLUMN_PROJEKT_ID));
        } else {
            throw new ModelNotFoundException("Substanz nicht gefunden");
        }
    }

    public String getProjektID() {
        return projektID;
    }

    public Projekt getProjekt() throws ModelNotFoundException, SQLException {
        return new Projekt(projektID);
    }

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + projektID;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_PROJEKT_ID;
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_PROJEKT_ID, projektID);
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		return projektID;
	}
	
	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", TABLE);
		json.addKeyValue("id", primaryKey);
		json.addKeyValue(COLUMN_PROJEKT_ID, projektID);
		
		return json;
	}

	public List<Probe> getProben() throws ModelNotFoundException, SQLException {
		SubstanzenProbe substanzenProbe = new SubstanzenProbe(this);
		return substanzenProbe.getProben();
	}
}
