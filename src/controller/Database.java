package controller;

import java.sql.SQLException;
import java.util.List;

import controller.exceptions.MitarbeiterNotFoundException;
import controller.exceptions.PasswordIncorrectException;
import model.Mitarbeiter;
import model.Projekt;

public interface Database {

	public Mitarbeiter getMitarbeiter(int mitarbeiterId) throws SQLException, MitarbeiterNotFoundException, PasswordIncorrectException;
	
	public void validateMitarbeiter(Mitarbeiter mitarbeiter, String password) throws PasswordIncorrectException, SQLException, MitarbeiterNotFoundException;
	
	public Projekt getProjekt(String projektId) throws SQLException;
	
	public List<Projekt> getProjekte() throws SQLException;
}
