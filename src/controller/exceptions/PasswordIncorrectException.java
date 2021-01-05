package controller.exceptions;

public class PasswordIncorrectException extends Exception {

	private static final long serialVersionUID = -9209353555506050661L;

	public PasswordIncorrectException(){
		
	}
	
	public PasswordIncorrectException(String message){
		super(message);
	}
}
