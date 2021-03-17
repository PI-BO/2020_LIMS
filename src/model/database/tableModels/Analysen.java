package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Analysen extends Model{
    private String primaryKey;
    private String api;
    private String bemerkung;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_API = "api";
    public static final String COLUMN_BEMERKUNG = "bemerkung";
    public static final String TABLE = "analysen";
    
    public Analysen() {
    }

    public Analysen(String id) throws SQLException, ModelNotFoundException {
        this.primaryKey = id;
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

    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            api = resultSet.getString(resultSet.findColumn(COLUMN_API));
            bemerkung = resultSet.getString(resultSet.findColumn(COLUMN_BEMERKUNG));
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
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
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		
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
		json.addKeyValue("table", getTable());
		json.addKeyValue("id", getPrimaryKey());
		json.addKeyValue(COLUMN_API, getApi());
		json.addKeyValue(COLUMN_BEMERKUNG, getBemerkung());
		
		
		return json;
	}
}
