package model.database.tableModels;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Substanz extends Model {

    private String projektID;
    private String wirkstoff;
    public static final String COLUMN_PRIMARY_KEY = "substanz_id";
    public static final String COLUMN_PROJEKT_ID = "projekt_id";
    public static final String COLUMN_WIRKSTOFF = "wirkstoff";
    public static final String TABLE = "substanz";

    public Substanz(){
		super();
	}
	
    public Substanz(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
    }

	public void setProjektID(String projektID) throws SQLException, ModelNotFoundException {
		this.projektID = projektID;
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
	public void saveToDatabase() throws SQLException, DublicateModelException, ModelNotFoundException {

		Projekt projekt = new Projekt(projektID);
		super.saveToDatabase();
		this.addParent(projekt);
	}

    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            projektID = resultSet.getString(resultSet.findColumn(COLUMN_PROJEKT_ID));
            wirkstoff = resultSet.getString(resultSet.findColumn(COLUMN_WIRKSTOFF));
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
		return primaryKey + "," + projektID + "," + wirkstoff;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_PROJEKT_ID + "," + COLUMN_WIRKSTOFF;
	}
	
	public void setWirkstoff(String name) {
		this.wirkstoff = name;
	}
	
	public String getName() {
		return wirkstoff;
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_PROJEKT_ID, projektID);
		dummyResultSetEntry.addKeyValuePair(COLUMN_WIRKSTOFF, wirkstoff);
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
		json.addKeyValue(COLUMN_WIRKSTOFF, wirkstoff);
		
		return json;
	}
}
