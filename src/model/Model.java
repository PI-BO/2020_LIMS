package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;

public abstract class Model {
	
	protected Database database = new MariaDB();
	
	public abstract String getPrimaryKey();
	public abstract String getPrimaryKeyColumn();
	public abstract String getTable();
	public abstract void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException;

}