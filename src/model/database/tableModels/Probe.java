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

	private String substanzID;
    public static final String COLUMN_PRIMARY_KEY = "probennummer";
    public static final String COLUMN_SUBSTANZ_ID = "substanz_ID";
	public static final String COLUMN_FOREIGN_KEY = COLUMN_SUBSTANZ_ID;
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
            substanzID = resultSet.getString(resultSet.findColumn(COLUMN_SUBSTANZ_ID));

        } else {

            throw new ModelNotFoundException("Probe nicht gefunden");
        }
    }

    public String getSubstanzID() {
        return substanzID;
    }
    
    public void setSubstanzID(String substanzID) throws SQLException, ModelNotFoundException {
    	this.substanzID = substanzID;
    }
    
	@Override
	public void saveToDatabase() throws SQLException, DublicateModelException, ModelNotFoundException {

		Substanz substanz = new Substanz(substanzID);
		super.saveToDatabase();
		this.addParent(substanz);
	}

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + substanzID;
	}
	
	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_SUBSTANZ_ID;
	}

	public Substanz getSubstanz() throws ModelNotFoundException, SQLException {
        return new Substanz(substanzID);
    }
	
	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_SUBSTANZ_ID, substanzID);
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		return substanzID;
	}
	
	@Override
	public JSON toJSON() {
		
		JSON json = new JSON();
		json.addKeyValue("table", TABLE);
		json.addKeyValue("id", primaryKey);
		json.addKeyValue(COLUMN_SUBSTANZ_ID, substanzID);
		
		return json;
	}

	public List<Experiment> getExperimente() throws ModelNotFoundException, SQLException {
		ProbeExperiment probeExperiment = new ProbeExperiment(this);
		return probeExperiment.getExperimente();
	}
}
