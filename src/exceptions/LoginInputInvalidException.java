package exceptions;

public class LoginInputInvalidException extends Exception{

	private static final long serialVersionUID = 1710376600317737295L;

	public LoginInputInvalidException(){
		super();
	}
	
	public LoginInputInvalidException(String message){
		super(message);
	}
}