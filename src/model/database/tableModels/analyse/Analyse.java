package model.database.tableModels.analyse;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.tableModels.Model;
import model.database.tableModels.Probe;
import model.database.tableModels.experimente.Experiment;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Analyse extends Model {
    private String typ;
    private String experimentId;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";
    public static final String COLUMN_EXPERIMENT_ID = "experiment";
    public static final String COLUMN_FOREIGN_KEY = COLUMN_EXPERIMENT_ID;
    public static final String TABLE = "analysen";

    /**
     * Model Class for Database Analyse
     * @param primaryKey
     * Primary key of Database Tabele
     * @throws ModelNotFoundException
     * @throws SQLException
     */
    public Analyse(String primaryKey) throws ModelNotFoundException, SQLException {
        super(primaryKey);
    }

    /**
     * Create Empty Analyse for new Database Entry
     * Add values over setter
     */
    public Analyse() {
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
            typ = resultSet.getString(resultSet.findColumn(COLUMN_TYP));
            experimentId = resultSet.getString(resultSet.findColumn(COLUMN_EXPERIMENT_ID));
        } else {
            throw new ModelNotFoundException("Analyse nicht gefunden");
        }
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(String experiment_id) {
        this.experimentId = experiment_id;
    }
    
	@Override
	public void saveToDatabase() throws SQLException, DublicateModelException, ModelNotFoundException {

		Experiment experiment = new Experiment(experimentId);
		super.saveToDatabase();
		this.addParent(experiment);
	}

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + experimentId;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_EXPERIMENT_ID;
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {
		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_EXPERIMENT_ID, experimentId);
		dummyResultSetEntry.addKeyValuePair(COLUMN_TYP, typ);
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		return experimentId;
	}

    @Override
    public JSON toJSON() {
        JSON json = new JSON();
        json.addKeyValue("table", TABLE);
        json.addKeyValue("id", primaryKey);
        json.addKeyValue(COLUMN_TYP, typ);
        json.addKeyValue(COLUMN_EXPERIMENT_ID, experimentId);

        return json;
    }
}
