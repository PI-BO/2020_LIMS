package database.inerfaces;

import exceptions.PasswordIncorrectException;

public interface Login {

	public void validate(String password) throws PasswordIncorrectException;
}
