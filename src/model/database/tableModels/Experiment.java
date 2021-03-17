package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Experiment extends Model{
	
    private String primaryKey;
    private String typ;
    private String proben_nr;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";
    public static final String COLUMN_PROBEN_NR = "proben_nr";
    public static final String TABLE = "experiment";

    /**
     * Model Class for Database Eigenschaften
     * @param primaryKey
     * Primary key of Database TAbele
     * @throws ModelNotFoundException
     * @throws SQLException
     */
    public Experiment(String primaryKey) throws ModelNotFoundException, SQLException {
        this.primaryKey = primaryKey;
        database.getModel(this);
    }

    public Experiment() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public String getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return null;
    }

    @Override
    public String getTable() {
        return null;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            typ = resultSet.getString(resultSet.findColumn(COLUMN_TYP));
            proben_nr = resultSet.getString(resultSet.findColumn(COLUMN_PROBEN_NR));
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    public String getTyp() {
        return typ;
    }

    public String getProbenNr() {
        return proben_nr;
    }

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + proben_nr;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_PROBEN_NR;
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
		dummyResultSetEntry.addKeyValuePair(COLUMN_PROBEN_NR, proben_nr);
		dummyResultSetEntry.addKeyValuePair(COLUMN_TYP, typ);
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		return proben_nr;
	}
	
	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", getTable());
		json.addKeyValue("id", getPrimaryKey());
		json.addKeyValue(COLUMN_PROBEN_NR, getProbenNr());	
		json.addKeyValue(COLUMN_TYP, getTyp());	
		
		return json;
	}
}
