package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Narwutsch Dominic
 * created on 11.03.2021
 */
public class ExperimentDurchfuehrungstext extends Model{
    private String primaryKey;
    private String text;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TEXT = "text";
    public static final String TABLE = "experiment_durchführungstext";

    @Override
    public String getForeignKey() {
        // Does not have a foreign key
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

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
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
            throw new ModelNotFoundException("Durchführungstext nicht gefunden");
        }
    }

    @Override
    public String getValuesAsSQLString() {
        return null;
    }

    @Override
    public String getRelationSchema() {
        return null;
    }

    @Override
    public void saveToDatabase() {

    }
}
