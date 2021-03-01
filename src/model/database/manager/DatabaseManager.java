package model.database.manager;

import model.database.Database;
import model.database.dummyDB.DummyDB;

public class DatabaseManager {
	
	private static Database database = new DummyDB();
	
	private DatabaseManager(){
	}
	
	public static Database getDatabaseInstance(){
		
		return database;
	}

}
