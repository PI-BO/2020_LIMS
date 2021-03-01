package model;

import exceptions.PasswordIncorrectException;

public interface Login {
	void validate(String password) throws PasswordIncorrectException;
}
