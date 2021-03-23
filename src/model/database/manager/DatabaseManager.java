package model.database.manager;

import java.sql.SQLException;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.dummyDB.DummyDB;
import model.database.mariaDB.MariaDB;

public class DatabaseManager {
	
	private static Database database = null;
	
	private DatabaseManager(){
	}
	
	public static Database getDatabaseInstance(){
		
		if(database == null){
			database = new DummyDB();
			DummyDB dummyDB = (DummyDB)database;
			try {
				dummyDB.initModels();
			}
			catch (SQLException | ModelNotFoundException | DublicateModelException e) {
				System.out.println("PROBLEM IN DER INIT() METHODE IN DUMMYDB");
				e.printStackTrace();
			}
		}
		
		return database;
	}

}
