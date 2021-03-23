package exceptions;

public class DublicateModelException extends Exception{
	
	private static final long serialVersionUID = -3679200723761828069L;

	public DublicateModelException(){
		super();
	}

	public DublicateModelException(String message){
		super(message);
	}
}