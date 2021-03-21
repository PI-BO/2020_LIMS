package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.tableModels.Model;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Analysen extends Model {
    private String primaryKey;
    private String api;
    private String bemerkung;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_API = "api";
    public static final String COLUMN_BEMERKUNG = "bemerkung";
    public static final String TABLE = "analysen";

    public Analysen(){
    	super();
    }
    
    public Analysen(String primaryKey) throws SQLException, ModelNotFoundException {
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
            api = resultSet.getString(resultSet.findColumn(COLUMN_API));
            bemerkung = resultSet.getString(resultSet.findColumn(COLUMN_BEMERKUNG));
        } else {
            throw new ModelNotFoundException("Analysen nicht gefunden");
        }
    }

    public String getApi() {
        return api;
    }

    public String getBemerkung() {
        return bemerkung;
    }

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + api + "," + bemerkung;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_API + "," + COLUMN_BEMERKUNG;
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_API, api);
		dummyResultSetEntry.addKeyValuePair(COLUMN_BEMERKUNG, bemerkung);
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
		json.addKeyValue("table", TABLE);
		json.addKeyValue("id", primaryKey);
		json.addKeyValue(COLUMN_API, api);
		json.addKeyValue(COLUMN_BEMERKUNG, bemerkung);
		
		
		return json;
	}
}
