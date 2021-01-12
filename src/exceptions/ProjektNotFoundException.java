package exceptions;

public class ProjektNotFoundException extends Exception{

	private static final long serialVersionUID = 8868297541337591051L;

	public ProjektNotFoundException(){
		super();
	}
	public ProjektNotFoundException(String message){
		super(message);
	}
}
