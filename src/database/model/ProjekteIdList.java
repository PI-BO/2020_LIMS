package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import exceptions.ModelNotFoundException;

public class ProjekteIdList extends Model{
	
	private final String TABLE = Projekt.TABLE;

	private List<String> projekteIdList;
	
	public ProjekteIdList() throws SQLException, ModelNotFoundException{
		database.getTable(this);
	}
	
	@Override
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
		projekteIdList = new LinkedList<>();
		
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
		return null;
	}

	@Override
	public String getPrimaryKeyColumn() {
		return Projekt.COLUMN_PRIMARY_KEY;
	}

	@Override
	public String getTable() {
		return TABLE;
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
}
