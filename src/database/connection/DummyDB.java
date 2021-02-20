package database.connection;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import database.dummyDatabase.DummyResultSet;
import database.dummyDatabase.DummyResultSetEntry;
import database.inerfaces.Database;
import database.model.Mitarbeiter;
import database.model.Model;
import database.model.ModelList;
import database.model.Projekt;
import database.relations.ManyToManyA;
import database.relations.OneToMany;
import exceptions.ModelNotFoundException;

public class DummyDB implements Database{

	private DummyResultSet analysenDummy = new DummyResultSet();
	private DummyResultSet bildDummy = new DummyResultSet();
	private DummyResultSet eigenschaftenDummy = new DummyResultSet();
	private DummyResultSet experimentDummy = new DummyResultSet();
	private DummyResultSet experimentTypDummy = new DummyResultSet();
	private DummyResultSet experimentTypGrindingDummy = new DummyResultSet();
	private DummyResultSet experimentTypSlurryDummy = new DummyResultSet();
	private DummyResultSet gefahrensymbolDummy = new DummyResultSet();
	private DummyResultSet mitarbeiterDummy = new DummyResultSet();
	private DummyResultSet modelListDummy = new DummyResultSet();
	private DummyResultSet partnerDummy = new DummyResultSet();
	private DummyResultSet probeDummy = new DummyResultSet();
	private DummyResultSet projekt = new DummyResultSet();
	private DummyResultSet projekteListDummy = new DummyResultSet();
	private DummyResultSet sessionsDummy = new DummyResultSet();
	private DummyResultSet substanzDummy = new DummyResultSet();
	
	private List<Model> modelList = new LinkedList<>();
	
	private static DummyDB dummyDB = new DummyDB();
	
	private DummyDB() {
		
		initModels();
	}
	
	public static DummyDB getInstance(){
		return dummyDB;
	}

	private void initModels() {
		
		Mitarbeiter mitarbeiter = new Mitarbeiter();
		mitarbeiter.setVorname("Max");
		mitarbeiter.setNachname("Mustermann");
		mitarbeiter.setPassword("abc");
		mitarbeiter.setPrimaryKey("123");
		modelList.add(mitarbeiter);
		
		Projekt projekt = new Projekt();
		projekt.setPrimaryKey("A");
		projekt.setVertragsnummer("0");
		modelList.add(projekt);
		
		projekt = new Projekt();
		projekt.setPrimaryKey("B");
		projekt.setVertragsnummer("1");
		modelList.add(projekt);
		
		projekt = new Projekt();
		projekt.setPrimaryKey("C");
		projekt.setVertragsnummer("2");
		modelList.add(projekt);
		
	}

	@Override
	public void getModel(Model requestedModel) throws SQLException, ModelNotFoundException {

		for(Model listModel : modelList){
			if(listModel.getTable() != requestedModel.getTable()) continue;
			if(listModel.getPrimaryKey() != requestedModel.getPrimaryKey()) continue;
			DummyResultSet dummyResultSet = listModel.returnAsDummyResultSet();
			requestedModel.setAttributes(dummyResultSet);
			break;
		}
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
	public void getTable(Model requestedModel) throws SQLException, ModelNotFoundException {

		DummyResultSet dummyResultSet = new DummyResultSet();
		
		for(Model listModel : modelList){
			if(listModel.getTable() != requestedModel.getTable()) continue;
			dummyResultSet.addResultSet(listModel.returnAsDummyResultSet());
		}
		
		requestedModel.setAttributes(dummyResultSet);
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
