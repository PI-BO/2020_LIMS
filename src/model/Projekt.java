package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;

public class Projekt extends Model {
	
	private String primaryKey;
	public static final String COLUMN_PRIMARY_KEY = "projekt_id";
	public static final String TABLE = "projekte";

	
	public Projekt(String id) throws SQLException, ModelNotFoundException{
		
		this.primaryKey = id;
		database.getModel(this);
	}
	
	public void setAttributes(ResultSet resultSet) throws SQLException {

		
		if (resultSet.next()) {
			
			int projektIdIndex = resultSet.findColumn(COLUMN_PRIMARY_KEY);
			primaryKey = resultSet.getString(projektIdIndex);
		}
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
}
