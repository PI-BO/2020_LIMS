package model.database.tableModels;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.relations.ProbeExperiment;
import model.database.tableModels.experimente.Experiment;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Probe extends Model{

	private String projektID;
	
    public static final String COLUMN_PRIMARY_KEY = "probennummer";
	public static final String COLUMN_PROJEKT_ID = "projekt_id";
	public static final String COLUMN_FOREIGN_KEY = COLUMN_PROJEKT_ID;
	public static final String TABLE = "probe";

    public Probe(){
		super();
	}
    
    public Probe(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
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

            throw new ModelNotFoundException("Probe nicht gefunden");
        }
    }

    public String getSubstanzID() {
        return projektID;
    }
    
    public void setProjektID(String substanzID) throws SQLException, ModelNotFoundException {
    	this.projektID = substanzID;
    }
    
	@Override
	public void saveToDatabase() throws SQLException, DublicateModelException, ModelNotFoundException {

		Projekt projekt = new Projekt(projektID);
		super.saveToDatabase();
		this.addParent(projekt);
	}

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + projektID;
	}
	
	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_PROJEKT_ID;
	}

	public Projekt getProjekt() throws ModelNotFoundException, SQLException {
        return new Projekt(projektID);
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

	public List<Experiment> getExperimente() throws ModelNotFoundException, SQLException {
		ProbeExperiment probeExperiment = new ProbeExperiment(this);
		return probeExperiment.getExperimente();
	}
}
