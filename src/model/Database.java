package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {

	public ResultSet getMitarbeiter(int mitarbeiterId) throws SQLException;
	
	public ResultSet getProjekt(String projektId) throws SQLException;
	
	public ResultSet getProjekteIdList() throws SQLException;
}
