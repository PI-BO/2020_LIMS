package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Narwutsch Dominic
 * created on 11.03.2021
 */
public class ExperimentSerie extends Model{
    
    public static final String COLUMN_PRIMARY_KEY = "serie";
    public static final String TABLE = "experiment_serie";

	public ExperimentSerie(){
		super();
	}
	
	public ExperimentSerie(Model parent) {
    	super(parent);
    }
    
    public ExperimentSerie(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
    }
    
    @Override
    public String getForeignKey() {
    	// TODO 
        return null;
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {
        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
        dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
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
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
        } else {
            throw new ModelNotFoundException("Durchführungstext nicht gefunden");
        }
    }

    @Override
    public String getValuesAsSQLString() {
    	// TODO 
        return null;
    }

    @Override
    public String getRelationSchema() {
    	// TODO 
        return null;
    }
    
	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", TABLE);
		json.addKeyValue("id", primaryKey);
		
		return json;
	}
}
