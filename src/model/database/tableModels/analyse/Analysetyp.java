package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.tableModels.Model;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Analysetyp extends Model {
	
    private String typ;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";

    public static final String TABLE = "analysetyp";

	public Analysetyp(String primaryKey) throws ModelNotFoundException, SQLException {
		super(primaryKey);
	}

	public Analysetyp() {
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
			throw new ModelNotFoundException("Experimenttyp nicht gefunden");
		}
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
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
		// TODO Does not have a foreign key
		return null;
	}

	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", getTable());
		json.addKeyValue("id", getPrimaryKey());
		json.addKeyValue(COLUMN_TYP, typ);

		return json;
	}
}
