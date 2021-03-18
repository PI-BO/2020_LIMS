package model.database.tableModels;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

public class Partner extends Model {

	private String primaryKey;
	private String name;
	private String email;
	public static final String COLUMN_PRIMARY_KEY = "vertragsnummer";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String TABLE = "partner";

	public Partner() {
		
	}
	
	public Partner(String vertragsnummer) throws SQLException, ModelNotFoundException {
		this.primaryKey = vertragsnummer;
		database.getModel(this);
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
		if(resultSet.next()){
			primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
			name = resultSet.getString(resultSet.findColumn(COLUMN_NAME));
			email = resultSet.getString(resultSet.findColumn(COLUMN_EMAIL));		
		}else{
			throw new ModelNotFoundException();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + name + "," + email;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_NAME + "," + COLUMN_EMAIL;
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_NAME, name);
		dummyResultSetEntry.addKeyValuePair(COLUMN_EMAIL, email);
		
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		// Partner has no Foreign key
		return null;
	}
}
