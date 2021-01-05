package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.exceptions.MitarbeiterNotFoundException;
import controller.exceptions.PasswordIncorrectException;
import model.Mitarbeiter;
import model.Projekt;

public class MariaDBController implements Database{

	private DatabaseConnection databaseConnection;
	private static final Logger logger = LogManager.getLogger(DatabaseConnection.class.getSimpleName());
	
	@Override
	public Mitarbeiter getMitarbeiter(int id) throws SQLException, MitarbeiterNotFoundException, PasswordIncorrectException {

		connectToDatabase();
		
		Mitarbeiter mitarbeiter = getMitarbeiterFromDatabase(id);

		disconnectFromDatabase();
		
		return mitarbeiter;
	}

	@Override
	public void validateMitarbeiter(Mitarbeiter mitarbeiter, String password) throws PasswordIncorrectException, SQLException, MitarbeiterNotFoundException {

		connectToDatabase();
		String passwordFromDB = getMitarbeiterPasswordFromDatabase(mitarbeiter.getId());
		disconnectFromDatabase();
		
		if(!password.equals(passwordFromDB)){
			throw new PasswordIncorrectException();
		}
		
	}

	@Override
	public Projekt getProjekt(String projektName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Projekt> getProjekte() throws SQLException {

		connectToDatabase();
		List<Projekt> projekte = getProjekteFromDatabase();
		disconnectFromDatabase();
		
		return projekte;
	}
	
	private void connectToDatabase() throws SQLException {
		
		databaseConnection = new DatabaseConnection();
		databaseConnection.connectToDatabase();
		logger.debug("" + this.getClass().toString() + " connected to database");
	}
	
	private void disconnectFromDatabase() throws SQLException {
		
		databaseConnection.disconnectFromDatabase();
		logger.debug("" + this.getClass().toString() + " disconnected from database");
	}
	
	private Mitarbeiter getMitarbeiterFromDatabase(int mitarbeiterId) throws SQLException, MitarbeiterNotFoundException  {

        String sqlStatement = "SELECT * FROM mitarbeiter WHERE mitarbeiterID=" + mitarbeiterId + ";";

        ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
        
        Mitarbeiter mitarbeiter = new Mitarbeiter(mitarbeiterId);
        
        setMitarbeiterAttributes(mitarbeiter, resultSet);
        
        return mitarbeiter;
	}
	
	private String getMitarbeiterPasswordFromDatabase(int mitarbeiterId) throws SQLException, MitarbeiterNotFoundException  {
		
		String sqlStatement = "SELECT * FROM mitarbeiter WHERE mitarbeiterID=" + mitarbeiterId + ";";
		
		ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
		
		String password = getPasswordFromResultSet(resultSet);
		
		return password;
	}
	
	private List<Projekt> getProjekteFromDatabase() throws SQLException{
		
		String sqlStatement = "SELECT * FROM projekte;";
		
		ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
		
		List<Projekt> projekte = getProjekte(resultSet);
		
		return projekte;
		
	}
	
	private void setMitarbeiterAttributes(Mitarbeiter mitarbeiter, ResultSet mitarbeiterResultSet) throws SQLException, MitarbeiterNotFoundException{
		
		if (mitarbeiterResultSet.next()) {

	       	int mitarbeiterIdIndex = mitarbeiterResultSet.findColumn("mitarbeiterID");
	       	int mitarbeiterVornameIndex = mitarbeiterResultSet.findColumn("vorname");
	       	int mitarbeiterNachnameIndex = mitarbeiterResultSet.findColumn("nachname");
	       	
	       	mitarbeiter.setId(mitarbeiterResultSet.getInt(mitarbeiterIdIndex));
	       	mitarbeiter.setVorname(mitarbeiterResultSet.getString(mitarbeiterVornameIndex));
	       	mitarbeiter.setNachname(mitarbeiterResultSet.getString(mitarbeiterNachnameIndex));
       	
		}else{
       	
			throw new MitarbeiterNotFoundException("Mitarbeiter nicht gefunden");
       }
	}
	
	private String getPasswordFromResultSet(ResultSet resultSet) throws SQLException, MitarbeiterNotFoundException{
		
		String password;
		
		if (resultSet.next()) {
			
			int mitarbeiterPasswordIndex = resultSet.findColumn("passwort");
			
			password = resultSet.getString(mitarbeiterPasswordIndex);
			
		}else{
			
			throw new MitarbeiterNotFoundException("Mitarbeiter nicht gefunden");
		}
		
		return password;
	}
	
	private List<Projekt> getProjekte(ResultSet resultSet) throws SQLException{
		
		List<Projekt> projekte = new LinkedList<Projekt>();
		
		while (resultSet.next()) {

			int projektIdIndex = resultSet.findColumn("projekt_id");

			Projekt projekt = new Projekt();
			
			String projektName = resultSet.getString(projektIdIndex);
			
			projekt.setProjektName(projektName);
			
			projekte.add(projekt);
			
		}
		
		return projekte;
	}
}
