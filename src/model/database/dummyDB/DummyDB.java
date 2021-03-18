package model.database.dummyDB;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.relations.ManyToManyA;
import model.database.relations.OneToMany;
import model.database.tableModels.Experiment;
import model.database.tableModels.Mitarbeiter;
import model.database.tableModels.Model;
import model.database.tableModels.Partner;
import model.database.tableModels.Probe;
import model.database.tableModels.Projekt;
import model.database.tableModels.Substanz;

public class DummyDB implements Database{

	private List<Model> modelList = new LinkedList<>();
	private List<Model[]> modelRelationList = new LinkedList<>();
	
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
		
		//------------Relation-Test fuer Suche------------//
		
		Partner partner1 = new Partner();
		partner1.setName("Partner A");
		partner1.setPrimaryKey("1");
		partner1.setEmail("partner-A@gmail.com");
		modelList.add(partner1);
		
		Partner partner2 = new Partner();
		partner2.setName("Partner B");
		partner2.setPrimaryKey("2");
		partner2.setEmail("partner-B@gmx.com");
		modelList.add(partner2);
		
		Projekt projekt1 = new Projekt(partner1);
		projekt1.setPrimaryKey("Projekt A");
		projekt1.setVertragsnummer("1");
		
		Projekt projekt2 = new Projekt(partner2);
		projekt2.setPrimaryKey("Projekt B");
		projekt2.setVertragsnummer("2");
		
		Probe probe1 = new Probe(projekt1);
		probe1.setPrimaryKey("0001A");
		probe1.setName("Probe A");
		
		Probe probe2 = new Probe(projekt2);
		probe2.setPrimaryKey("0001B");
		probe2.setName("Probe B");
		
		Experiment experiment1 = new Experiment(probe1);
		experiment1.setPrimaryKey("0001Aexp0");
		experiment1.setTyp("Slurry");
		
		Experiment experiment2 = new Experiment(probe1);
		experiment2.setPrimaryKey("0001Aexp1");
		experiment2.setTyp("Vedampfung");
		
		Experiment experiment3 = new Experiment(probe2);
		experiment3.setPrimaryKey("0001Bexp0");
		experiment3.setTyp("Slurry");
		
		Experiment experiment4 = new Experiment(probe2);
		experiment4.setPrimaryKey("0001Bexp1");
		experiment4.setTyp("Vedampfung");
		
//		Model[] relation1 = {partner1, projekt1, probe1, experiment1};
//		Model[] relation2 = {partner1, projekt1, probe1, experiment2};
//		Model[] relation3 = {partner2, projekt2, probe2, experiment3};
//		Model[] relation4 = {partner2, projekt2, probe2, experiment4};
//		
//		modelRelationList.add(relation1);
//		modelRelationList.add(relation2);
//		modelRelationList.add(relation3);
//		modelRelationList.add(relation4);
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
	public void saveModel(Model model) throws SQLException {
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

	@Override
	public List<List<Model>> getRelationList() {
		
		List<List<Model>> branches = new LinkedList<>();
		
		List<Model> rootModelList = getAllRootModels();
		
		for(Model rootModel : rootModelList){
			
			List<Model> branch = new LinkedList<>();
			branches.add(branch);
			
			branch.add(rootModel);
			
			System.out.println(rootModel.toJSON());
			
			extendBranch(branch, rootModel, branches);
			
		}
		
		return branches;
	}

	private void extendBranch(List<Model> branch, Model nextModel, List<List<Model>> branches) {
		
		while(nextModel.hasChildren()){
			
			if(nextModel.getChildren().size() > 1){
				
				for(int i = 1; i < nextModel.getChildren().size(); i++){
					
					List<Model> newBranch = new LinkedList<>();
					newBranch.addAll(branch);
					branches.add(newBranch);
					
					Model child = nextModel.getChildren().get(i);
					newBranch.add(child);
					
					extendBranch(newBranch, child, branches);
				}
			}
				
			nextModel = nextModel.getChildren().get(0);
			System.out.println(nextModel.toJSON());
			branch.add(nextModel);
		}
	}

	private List<Model> getAllRootModels() {
		List<Model> rootModelList = new LinkedList<>();
		
		for(Model model : modelList){
			if(model.getClass() == Partner.class) rootModelList.add(model);
		}
		return rootModelList;
	}
}
