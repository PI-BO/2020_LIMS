package exceptions;

public class MitarbeiterNotFoundException extends Exception{
	
	private static final long serialVersionUID = -6079487922246832257L;
	
	public MitarbeiterNotFoundException(){
		super();
	}

	public MitarbeiterNotFoundException(String message){
		super(message);
	}
}