package model.database.tableModels;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.dummyDB.DummyRelation;
import model.database.dummyDB.DummyResultSetInterface;
import model.database.manager.DatabaseManager;

public abstract class Model implements DummyResultSetInterface, DummyRelation{
	
	protected Database database = DatabaseManager.getDatabaseInstance();
	
	public abstract String getPrimaryKey();
	public abstract String getPrimaryKeyColumn();
	public abstract String getTable();
	public abstract void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException;
	public abstract String getValuesAsSQLString();
	public abstract String getRelationSchema();
	public abstract void saveToDatabase();
}