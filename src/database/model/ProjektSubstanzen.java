package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.relations.ProjekteSubstanz;
import exceptions.ModelNotFoundException;

public class ProjektSubstanzen extends Model{
	
	private String projektId;
	public static final String COLUMN_PRIMARY_KEY = "projekt_id";
	public static final String COLUMN_SECONDARY_KEY = "substanz_id";
	public static final String TABLE = "projekt_substanz";
	private List<Substanz> substanzen;
	
	public ProjektSubstanzen(String projektId) throws SQLException, ModelNotFoundException {
		
		this.projektId = projektId;
		database.getRelation(this);
	}

	@Override
	public String getPrimaryKey() {

		return projektId;
	}

	@Override
	public String getPrimaryKeyColumn() {
		
		return COLUMN_PRIMARY_KEY;
	}

	@Override
	public String getTable() {

		return TABLE;
	}

	@Override
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {

		substanzen = new ArrayList<Substanz>();
		
		while (resultSet.next()) {

			int projektIdIndex = resultSet.findColumn(COLUMN_PRIMARY_KEY);
			int substanzIdIndex = resultSet.findColumn(COLUMN_SECONDARY_KEY);

			String projektName = resultSet.getString(projektIdIndex);
			String substanzName = resultSet.getString(substanzIdIndex);

			System.out.println(projektName + "\t" + substanzName);
			
			Substanz substanz = new Substanz(substanzName);
			substanzen.add(substanz);
		}
	}
	
	public List<Substanz> getSubstanzen(){
		return substanzen;
	}
	

}
