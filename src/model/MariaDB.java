package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.ModelNotFoundException;

public class MariaDB implements Database{

	private DatabaseConnection databaseConnection;
	private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class.getSimpleName());
	
	public MariaDB() {

		databaseConnection = DatabaseConnection.getInstance();
	}
	
	@Override
	public void getModel(Model model) throws SQLException, ModelNotFoundException {
		
		String sqlStatement = "SELECT * FROM " + model.getTable() + " WHERE " + model.getPrimaryKeyColumn() + "=\"" + model.getPrimaryKey() + "\";";
		
		ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
		
		model.setAttributes(resultSet);
	}
	
	@Override
	public void getTable(Model model) throws SQLException, ModelNotFoundException {

		String sqlStatement = "SELECT * FROM " + model.getTable() + ";";
		
		System.out.println(sqlStatement);
		
		ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
		
		model.setAttributes(resultSet);
	}
}
