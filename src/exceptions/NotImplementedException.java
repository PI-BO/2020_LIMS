package exceptions;

public class NotImplementedException extends Exception{

	private static final long serialVersionUID = 704369452474301522L;

	public NotImplementedException(){
		super();
	}
	
	public NotImplementedException(String message){
		super(message);
	}
}