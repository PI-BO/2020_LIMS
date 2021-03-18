package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Substanz extends Model {

    private String primaryKey;
    private String projektID;
    public static final String COLUMN_PRIMARY_KEY = "substanz_id";
    public static final String COLUMN_PROJEKT_ID = "projekt_id";
    public static final String TABLE = "substanz";

    public Substanz(){
		super();
	}
	
	public Substanz(Model parent) {
    	super(parent);
    }
    
    public Substanz(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
    }

	public void setProjektID(String projektID) {
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
		json.addKeyValue("table", getTable());
		json.addKeyValue("id", getPrimaryKey());
		json.addKeyValue(COLUMN_PROJEKT_ID, getProjektID());
		
		return json;
	}
}
