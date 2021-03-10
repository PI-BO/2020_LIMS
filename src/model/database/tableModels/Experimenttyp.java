package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Experimenttyp extends Model{
    private String primaryKey;
    private String typ;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";
    public static final String TABLE = "experimenttyp";

    public Experimenttyp(String primaryKey) throws ModelNotFoundException, SQLException {
        this.primaryKey = primaryKey;
        database.getModel(this);
    }

    public Experimenttyp() {
		// TODO Auto-generated constructor stub
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

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            typ = resultSet.getString(resultSet.findColumn(COLUMN_TYP));
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    public String getTyp() {
        return typ;
    }

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + typ;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_TYP;
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
		dummyResultSetEntry.addKeyValuePair(COLUMN_TYP, typ);
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		// TODO Auto-generated method stub
		return null;
	}
}