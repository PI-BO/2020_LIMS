package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.connection.MariaDB;
import database.inerfaces.Database;
import exceptions.ModelNotFoundException;

public abstract class Model {
	
	protected Database database = new MariaDB();
	
	public abstract String getPrimaryKey();
	public abstract String getPrimaryKeyColumn();
	public abstract String getTable();
	public abstract void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException;
	public abstract String getValues();
	public abstract String getRelationSchema();

}