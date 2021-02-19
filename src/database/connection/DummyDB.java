package database.connection;

import java.sql.SQLException;

import database.inerfaces.Database;
import database.model.Model;
import database.model.ModelList;
import database.relations.ManyToManyA;
import database.relations.OneToMany;
import exceptions.ModelNotFoundException;

public class DummyDB implements Database{

	@Override
	public void getModel(Model model) throws SQLException, ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(Model model) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateModel(Model model) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteModel(Model model) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getTable(ModelList modelList) throws SQLException, ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRelation(Model model) throws SQLException, ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T extends Model, U extends Model> void resolveOneToMany(OneToMany<T, U> relation) throws SQLException, ModelNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <M extends Model, N extends Model> void resolveManyToMany(ManyToManyA<N, M> manyToMany)
			throws NoSuchFieldException, IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		
	}

}
