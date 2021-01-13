package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exceptions.ModelNotFoundException;

public class ProjekteIdList extends Model{
	
	private final String TABLE = Projekt.TABLE;

	private List<String> projekteIdList;
	
	public ProjekteIdList() throws SQLException, ModelNotFoundException{
		
		database.getTable(this);
	}
	
	@Override
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
		
		projekteIdList = new LinkedList<String>();
		
		while (resultSet.next()) {

			int projektIdIndex = resultSet.findColumn(Projekt.COLUMN_PRIMARY_KEY);

			String projektName = resultSet.getString(projektIdIndex);
			
			projekteIdList.add(projektName);
		}
	}

	public List<String> getProjekteIdList() {
		return projekteIdList;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrimaryKeyColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTable() {
		return TABLE;
	}
}
