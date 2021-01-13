package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;

public interface Database {

	public void getModel(Model model) throws SQLException, ModelNotFoundException;

	public void  getTable(Model model) throws SQLException, ModelNotFoundException;
}
