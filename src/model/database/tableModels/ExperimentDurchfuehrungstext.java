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
public class ExperimentDurchfuehrungstext extends Model{

	private String text;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TEXT = "text";
    public static final String TABLE = "experiment_durchführungstext";		//TODO

	public ExperimentDurchfuehrungstext(){
		super();
	}
	
    public ExperimentDurchfuehrungstext(String primaryKey) throws SQLException, ModelNotFoundException {
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
        dummyResultSetEntry.addKeyValuePair(COLUMN_TEXT, text);
        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnPrimaryKey() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnText() {
        return COLUMN_TEXT;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            text = resultSet.getString(resultSet.findColumn(COLUMN_TEXT));
        } else {
            throw new ModelNotFoundException("Durchführungstext nicht gefunden"); // TODO 
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
		json.addKeyValue(COLUMN_TEXT, text);
		
		return json;
	}
}
