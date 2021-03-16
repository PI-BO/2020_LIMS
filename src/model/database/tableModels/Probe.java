package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Probe extends Model{
    private String primaryKey;
    private String substanzID;
    private String name;
    public static final String COLUMN_PRIMARY_KEY = "proben_nr";
    public static final String COLUMN_SUBSTANZ_ID = "substanz_ID";
    public static final String COLUMN_NAME = "substanz_name";
    public static final String TABLE = "probe";

    public Probe(String id) throws SQLException, ModelNotFoundException {
        this.primaryKey = id;
        database.getModel(this);
    }

    public Probe() {
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
            substanzID = resultSet.getString(resultSet.findColumn(COLUMN_SUBSTANZ_ID));

        } else {

            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    public String getSubstanzID() {
        return substanzID;
    }

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + substanzID;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_SUBSTANZ_ID;
	}

	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public JSON toJSON() {
		
		JSON json = new JSON();
		json.addKeyValue("category", "probe");
		json.addKeyValue("name", getName());
		
		return json;
	}
}
