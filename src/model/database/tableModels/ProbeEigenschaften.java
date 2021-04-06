package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProbeEigenschaften extends Model{
    private String probeKey;
    private String eigenschaftKey;
    private final String COLUMN_PROBE_KEY = Probe.COLUMN_PRIMARY_KEY;
    private final String COLUMN_EIGENSCHAFT_KEY = Eigenschaften.COLUMN_PRIMARY_KEY;
    private final String TABLE = "substanz_eigenschaften";

    public ProbeEigenschaften() {

    }

    public ProbeEigenschaften(String probeId, String eigenschaftId) throws SQLException, ModelNotFoundException {
        this.probeKey = probeId;
        this.eigenschaftKey = eigenschaftId;
        database.getModel(this);
    }

	@Override
    public String getPrimaryKey() {
        return probeKey;
    }
	
	@Override
	public void setPrimaryKey(String probeKey){
		this.probeKey = probeKey;
	}

	@Override
	public String getForeignKey() {
		return eigenschaftKey;
	}

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PROBE_KEY + "," + COLUMN_EIGENSCHAFT_KEY;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        while (resultSet.next()) {
            int probeIdIndex = resultSet.findColumn(COLUMN_PROBE_KEY);
            probeKey = resultSet.getString(probeIdIndex);

            int eigenschaftIdIndex = resultSet.findColumn(COLUMN_EIGENSCHAFT_KEY);
            eigenschaftKey = resultSet.getString(eigenschaftIdIndex);
        }
    }

    @Override
    public String getValuesAsSQLString() {
        return "\"" + probeKey + "\",\"" + eigenschaftKey + "\"";
    }

    @Override
    public String getRelationSchema() {
        return COLUMN_PROBE_KEY + "," + COLUMN_EIGENSCHAFT_KEY;
    }

	@Override
	public DummyResultSet returnAsDummyResultSet() {
		
		DummyResultSet dummyResultSet = new DummyResultSet();
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PROBE_KEY, probeKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_EIGENSCHAFT_KEY, eigenschaftKey);
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}
	
	@Override
	public JSON toJSON() {
		JSON json = new JSON();
		json.addKeyValue("table", getTable());
		json.addKeyValue("id", getPrimaryKey());
		json.addKeyValue(COLUMN_EIGENSCHAFT_KEY, eigenschaftKey);
		
		return json;
	}
}
