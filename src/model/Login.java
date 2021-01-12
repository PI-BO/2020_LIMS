package model;

import controller.exceptions.PasswordIncorrectException;

public interface Login {

	public void validate(String password) throws PasswordIncorrectException;
}
