package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.exceptions.MitarbeiterNotFoundException;
import controller.exceptions.PasswordIncorrectException;
import controller.exceptions.ProjektNotFoundException;

public class MariaDBModel implements Database{

	private DatabaseConnection databaseConnection;
	private static final Logger logger = LogManager.getLogger(DatabaseConnection.class.getSimpleName());
	
	public MariaDBModel() {

		databaseConnection = DatabaseConnection.getInstance();
	}
	
	@Override
	public ResultSet getMitarbeiter(int mitarbeiterId) throws SQLException{

        String sqlStatement = "SELECT * FROM mitarbeiter WHERE mitarbeiterID=" + mitarbeiterId + ";";

        ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
        
        return resultSet;
	}

	@Override
	public ResultSet getProjekt(String projektId) throws SQLException {

        String sqlStatement = "SELECT * FROM projekt_substanz WHERE projekt_id=\"" + projektId + "\";";

        ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
        
        return resultSet;
	}

	@Override
	public ResultSet getProjekteIdList() throws SQLException {

		String sqlStatement = "SELECT * FROM projekte;";
		
		ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
		
		return resultSet;
	}
}
