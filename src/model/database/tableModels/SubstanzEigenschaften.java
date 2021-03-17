package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubstanzEigenschaften extends Model{
    private String substanzKey;
    private String eigenschaftKey;
    private final String COLUMN_SUBSTANZ_KEY = Substanz.COLUMN_PRIMARY_KEY;
    private final String COLUMN_EIGENSCHAFT_KEY = Eigenschaften.COLUMN_PRIMARY_KEY;
    private final String TABLE = "substanz_eigenschaften";

    public SubstanzEigenschaften() {

    }

    public SubstanzEigenschaften(String substanzId, String eigenschaftId) throws SQLException, ModelNotFoundException {
        this.substanzKey = substanzId;
        this.eigenschaftKey = eigenschaftId;
        database.getModel(this);
    }

	@Override
    public String getPrimaryKey() {
        return substanzKey;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_SUBSTANZ_KEY + "," + COLUMN_EIGENSCHAFT_KEY;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        while (resultSet.next()) {
            int substanzIdIndex = resultSet.findColumn(COLUMN_SUBSTANZ_KEY);
            substanzKey = resultSet.getString(substanzIdIndex);

            int eigenschaftIdIndex = resultSet.findColumn(COLUMN_EIGENSCHAFT_KEY);
            eigenschaftKey = resultSet.getString(eigenschaftIdIndex);
        }
    }

    @Override
    public String getValuesAsSQLString() {
        return "\"" + substanzKey + "\",\"" + eigenschaftKey + "\"";
    }

    @Override
    public String getRelationSchema() {
        return COLUMN_SUBSTANZ_KEY + "," + COLUMN_EIGENSCHAFT_KEY;
    }

    @Override
    public void saveToDatabase() {

    }

	@Override
	public DummyResultSet returnAsDummyResultSet() {
		
		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_SUBSTANZ_KEY, substanzKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_EIGENSCHAFT_KEY, eigenschaftKey);
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		return eigenschaftKey;
	}
}
