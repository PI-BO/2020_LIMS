package model.database.dummyDB;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.relations.ManyToManyA;
import model.database.relations.OneToMany;
import model.database.tableModels.Mitarbeiter;
import model.database.tableModels.Model;
import model.database.tableModels.Projekt;
import model.database.tableModels.Substanz;

public class DummyDB implements Database{

	private List<Model> modelList = new LinkedList<>();
	
	public DummyDB() {
		
		initModels();
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
		
		Substanz substanz = new Substanz();
		substanz.setPrimaryKey("SubstanzA1");
		substanz.setProjektID("A");
		modelList.add(substanz);
		
		substanz = new Substanz();
		substanz.setPrimaryKey("SubstanzA2");
		substanz.setProjektID("A");
		modelList.add(substanz);
		
		substanz = new Substanz();
		substanz.setPrimaryKey("SubstanzA3");
		substanz.setProjektID("A");
		modelList.add(substanz);
		
		substanz = new Substanz();
		substanz.setPrimaryKey("SubstanzB");
		substanz.setProjektID("B");
		modelList.add(substanz);
		
		substanz = new Substanz();
		substanz.setPrimaryKey("SubstanzC");
		substanz.setProjektID("C");
		modelList.add(substanz);
		
		substanz = new Substanz();
		substanz.setPrimaryKey("SubstanzG");
		substanz.setProjektID("A");
		modelList.add(substanz);
		
	}

	@Override
	public void getModel(Model requestedModel) throws SQLException, ModelNotFoundException {

		for(Model model : modelList){
			
			if(tableNotFound(requestedModel, model)) continue;
			if(primaryKeyNotFound(requestedModel, model)) continue;
			
			DummyResultSet dummyResultSet = model.returnAsDummyResultSet();
			requestedModel.setAttributes(dummyResultSet);
			
			break;
		}
	}

	private boolean primaryKeyNotFound(Model requestedModel, Model model) {
		return !model.getPrimaryKey().equals(requestedModel.getPrimaryKey());
	}

	private boolean tableNotFound(Model requestedModel, Model model) {
		return !model.getTable().equals(requestedModel.getTable());
	}
	
	private boolean primaryKeyNotFound(String requestedKey, DummyRelation model) {
		return !model.getForeignKey().equals(requestedKey);
	}
	
	private boolean tableNotFound(String requestedTable, Model model) {
		return !model.getTable().equals(requestedTable);
	}
	
	@Override
	public void setModel(Model model) throws SQLException {
		// TODO Auto-generated method stub
		System.out.print(this.getClass());
		System.out.println("setModel() not implemented");
	}

	@Override
	public void updateModel(Model model) throws SQLException {
		// TODO Auto-generated method stub
		System.out.print(this.getClass());
		System.out.println("updateModel() not implemented");
	}

	@Override
	public void deleteModel(Model model) throws SQLException {
		// TODO Auto-generated method stub
		System.out.print(this.getClass());
		System.out.println("deleteModel() not implemented");
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
		System.out.print(this.getClass());
		System.out.println("getRelation() not implemented");
	}

	@Override
	public <T extends Model, U extends Model> void resolveOneToMany(OneToMany<T, U> relation) throws SQLException, ModelNotFoundException {
		
		try {
			String requestedTable = relation.getManyTable();
			String requestedKey = relation.getOneKey();
			
			DummyResultSet dummyResultSet = new DummyResultSet();
			
			for(Model model : modelList){
				
				if(tableNotFound(requestedTable, model)) continue;
				if(primaryKeyNotFound(requestedKey, model)) continue;

				DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
				dummyResultSetEntry.addKeyValuePair(model.getPrimaryKeyColumn(), model.getPrimaryKey());
				dummyResultSet.addEntry(dummyResultSetEntry);
			}
			
			relation.setAttributes(dummyResultSet);
			
		}
		catch (NoSuchFieldException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public <M extends Model, N extends Model> void resolveManyToMany(ManyToManyA<N, M> manyToMany) throws NoSuchFieldException, IllegalAccessException, SQLException {
		// TODO Auto-generated method stub
		System.out.print(this.getClass());
		System.out.println("resolveManyToMany() not implemented");
	}

}
