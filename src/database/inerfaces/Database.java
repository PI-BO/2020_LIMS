package database.inerfaces;

import java.sql.SQLException;

import database.relations.OneToMany;
import exceptions.ModelNotFoundException;
import database.model.Model;

public interface Database {

	public void getModel(Model model) throws SQLException, ModelNotFoundException;

	public void  getTable(Model model) throws SQLException, ModelNotFoundException;
	
	public void  getRelation(Model model) throws SQLException, ModelNotFoundException;

	<T extends Model, U extends Model> void resolveOneToMany(OneToMany<T, U> relation) throws SQLException, ModelNotFoundException;
}
