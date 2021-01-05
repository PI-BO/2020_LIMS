package controller.exceptions;

public class PasswordIncorrectException extends Exception {

	public PasswordIncorrectException(){
		
	}
	
	public PasswordIncorrectException(String message){
		super(message);
	}
}
