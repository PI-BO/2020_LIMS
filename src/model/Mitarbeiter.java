package model;

public class Mitarbeiter{
	
	private int id;
	private String vorname;
	private String nachname;
	
    public Mitarbeiter(int id){
    	
    	this.id = id;
    }
    
	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
	public String getNachname() {
		return nachname;
	}
	
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
}
