package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimenttypSlurry extends Model{

	private String value;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_VALUE = "value";
    public static final String TABLE = "experimenttyp_slurry";

    public ExperimenttypSlurry(){
		super();
	}
	
	public ExperimenttypSlurry(Model parent) {
    	super(parent);
    }
    
    public ExperimenttypSlurry(String primaryKey) throws SQLException, ModelNotFoundException {
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

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            value = resultSet.getString(resultSet.findColumn(COLUMN_VALUE));
        } else {
            throw new ModelNotFoundException("ExperimenttypSlurry nicht gefunden");
        }
    }

    public String getValue() {
        return value;
    }

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + value;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_VALUE;
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_VALUE, value);
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", getTable());
		json.addKeyValue("id", getPrimaryKey());
		json.addKeyValue(COLUMN_VALUE, getValue());
		
		return json;
	}
}
