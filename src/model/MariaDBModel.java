package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MariaDBModel implements Database{

	private DatabaseConnection databaseConnection;
	private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class.getSimpleName());
	
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
