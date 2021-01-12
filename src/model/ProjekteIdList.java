package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProjekteIdList extends Model{

	private List<String> projekteIdList;
	
	public ProjekteIdList() throws SQLException{
		
		ResultSet resultSet = database.getProjekteIdList();
		
		setProjekteIdList(resultSet);
	}
	
	private void setProjekteIdList(ResultSet resultSet) throws SQLException{
		
		projekteIdList = new LinkedList<String>();
		
		while (resultSet.next()) {

			int projektIdIndex = resultSet.findColumn("projekt_id");

			String projektName = resultSet.getString(projektIdIndex);
			
			projekteIdList.add(projektName);
		}
	}

	public List<String> getProjekteIdList() {
		return projekteIdList;
	}
}
