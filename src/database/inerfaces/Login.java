package database.inerfaces;

import exceptions.PasswordIncorrectException;

public interface Login {
	void validate(String password) throws PasswordIncorrectException;
}
