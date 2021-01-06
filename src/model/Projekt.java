package model;

import java.util.List;

public class Projekt {
	
	private String id;
	private List<Substanz> substanzen;

	public List<Substanz> getSubstanzen() {
		return substanzen;
	}

	public void setSubstanzen(List<Substanz> substanzen) {
		this.substanzen = substanzen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
