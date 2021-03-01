package model;

import java.sql.SQLException;

import exceptions.ModelNotFoundException;

public class Bild{		//TODO die Klasse als Tabelle in DB?
	
	private String primaryKey;
	private byte[]bild;
	
	public Bild(){
		
	}
	
	public Bild(String primaryKey) throws SQLException, ModelNotFoundException{
		this.primaryKey = primaryKey;
		
		//TODO Bild aus der DB laden
	}

	public Bild(byte[] bild) {
		this.bild = bild;
	}

	public byte[] getBild() {
		return bild;
	}

	public void setBild(byte[] bild) {
		this.bild = bild;
	}
}
