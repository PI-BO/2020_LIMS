package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;

public class Bild extends Model{
	
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

	@Override
	public String getPrimaryKey() {
		return primaryKey;
	}

	@Override
	public String getPrimaryKeyColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRelationSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	public byte[] getBild() {
		return bild;
	}

	public void setBild(byte[] bild) {
		this.bild = bild;
	}

	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		
	}
}
