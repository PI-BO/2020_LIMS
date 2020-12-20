package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.DatabaseConnection;

public class Mitarbeiter{
	
	private int mitarbeiterID;
	private String vorname;
	private String nachname;
	private String password;
	
	private DatabaseConnection databaseConnection;
	
    private static final Logger logger = LogManager.getLogger(DatabaseConnection.class.getSimpleName());
	
    public Mitarbeiter(int mitarbeiterID, String mitarbeiterPassword) throws SQLException, MitarbeiterNotFoundException, PasswordIncorrectException{
    	
    	getMitarbeiterFromDatabase(mitarbeiterID);
    	validatePassword(mitarbeiterPassword);
    	
    }
    
	private void validatePassword(String mitarbeiterPassword) throws PasswordIncorrectException {

		if(!mitarbeiterPassword.equals(this.password)) throw new PasswordIncorrectException();
	}

	private void getMitarbeiterFromDatabase(int mitarbeiterID) throws SQLException, MitarbeiterNotFoundException{
		
		connectToDatabase();
		ResultSet mitarbeiterResultSet = getMitarbeiterDataFromDatabase(mitarbeiterID);
		setMitarbeiterAttributes(mitarbeiterResultSet);
	}
	
	private void connectToDatabase() throws SQLException {
		
		databaseConnection = new DatabaseConnection();
		databaseConnection.connectToDatabase();
			
	}

	private ResultSet getMitarbeiterDataFromDatabase(int mitarbeiterID) throws SQLException  {

        String sqlStatement = "SELECT * FROM mitarbeiter WHERE mitarbeiterID=" + mitarbeiterID + ";";

        ResultSet resultSet;
		resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
        
        logger.debug("table content returned");
        
        return resultSet;
	}
	
	private void setMitarbeiterAttributes(ResultSet mitarbeiterResultSet) throws SQLException, MitarbeiterNotFoundException{
		
		 if (mitarbeiterResultSet.next()) {

        	int mitarbeiterIdIndex = mitarbeiterResultSet.findColumn("mitarbeiterID");
        	int mitarbeiterVornameIndex = mitarbeiterResultSet.findColumn("vorname");
        	int mitarbeiterNachnameIndex = mitarbeiterResultSet.findColumn("nachname");
        	int mitarbeiterPasswordIndex = mitarbeiterResultSet.findColumn("passwort");
        	
        	this.mitarbeiterID = mitarbeiterResultSet.getInt(mitarbeiterIdIndex);
        	this.vorname = mitarbeiterResultSet.getString(mitarbeiterVornameIndex);
        	this.nachname = mitarbeiterResultSet.getString(mitarbeiterNachnameIndex);
        	this.password = mitarbeiterResultSet.getString(mitarbeiterPasswordIndex);
        	
        }else{
        	
        	throw new MitarbeiterNotFoundException("Mitarbeiter nicht gefunden");
        }
	}
}
