package database.connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.inerfaces.Database;
import database.model.Model;
import database.relations.OneToMany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.ModelNotFoundException;

public class MariaDB implements Database {

	private DatabaseConnection databaseConnection;
	private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class.getSimpleName());
	
	public MariaDB() {

		databaseConnection = DatabaseConnection.getInstance();
		if(databaseConnection == null) LOGGER.debug("MariaDB constructor: databaseConnection == NULL");
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

	public <T extends Model, U extends Model> void resolveOneToMany(OneToMany<T, U> reslation) throws SQLException, ModelNotFoundException {
		try {
			String sqlStatement = "SELECT * FROM " + reslation.getManyTable() + " WHERE " + reslation.getOneKeyColumn() + "=\"" + reslation.getOneKey() + "\";";

			ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);

			reslation.setAttributes(resultSet);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
