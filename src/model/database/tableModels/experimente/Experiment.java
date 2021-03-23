package model.database.tableModels.experimente;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.tableModels.Model;
import model.database.tableModels.Probe;
import model.database.tableModels.Projekt;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Experiment extends Model {
	
    private String typ;
    private String proben_nr;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";
    public static final String COLUMN_PROBEN_NR = "proben_nr";
    public static final String TABLE = "experiment";

    /**
     * Create Empty Experiment for new Database Entry
     * Add values over setter
     */
    public Experiment() {
    	
    }
    
    /**
     * Model Class for Database Eigenschaften
     * @param primaryKey
     * Primary key of Database Tabele
     * @throws ModelNotFoundException
     * @throws SQLException
     */
    public Experiment(String primaryKey) throws ModelNotFoundException, SQLException {
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
            proben_nr = resultSet.getString(resultSet.findColumn(COLUMN_PROBEN_NR));
            typ = resultSet.getString(resultSet.findColumn(COLUMN_TYP));
        } else {
            throw new ModelNotFoundException("Experiment nicht gefunden");
        }
    }

    public String getTyp() {
        return typ;
    }

    public String getProbenNr() {
        return proben_nr;
    }

    public void setProbenNr(String proben_nr) throws SQLException, ModelNotFoundException {
    	this.proben_nr = proben_nr;
    }
    
	@Override
	public void saveToDatabase() throws SQLException, DublicateModelException, ModelNotFoundException {

		Probe probe = new Probe(proben_nr);
		super.saveToDatabase();
		this.addParent(probe);
	}
    
    public void setTyp(String typ) {
		this.typ = typ;
	}

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + proben_nr;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_PROBEN_NR + "," + COLUMN_TYP;
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
		json.addKeyValue("table", TABLE);
		json.addKeyValue("id", primaryKey);
		json.addKeyValue(COLUMN_TYP, typ);	
		json.addKeyValue(COLUMN_PROBEN_NR, proben_nr);	
		
		return json;
	}
}
