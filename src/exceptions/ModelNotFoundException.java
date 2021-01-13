package exceptions;

public class ModelNotFoundException extends Exception{
	
	private static final long serialVersionUID = -6079487922246832257L;
	
	public ModelNotFoundException(){
		super();
	}

	public ModelNotFoundException(String message){
		super(message);
	}
}