package database.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.LoginServlet;
import database.connection.MariaDB;
import database.inerfaces.Database;
import exceptions.ModelNotFoundException;
import exceptions.NotImplementedException;

public class ModelList {
	
protected Database database = new MariaDB();

	private static final Logger LOGGER = LogManager.getLogger(ModelList.class.getSimpleName());

	private String table;
	private Model model;
	private List<Model>modelList;

	public ModelList(Model model) throws SQLException, ModelNotFoundException {
		this.model = model;
		table = model.getTable();
		database.getTable(this);
	}
	
	public String getTable(){
		return table;
	}
	
	public List<Model> getModelList() {
		return modelList;
	}
	
	public void setAttributes(ResultSet resultSet) throws SQLException{
		
		modelList = new LinkedList<>();
		
		Model concreteModel;
		
		while(true){
			
			//TODO more models
			if(model.getClass().equals(Analysen.class)) 					concreteModel = new Analysen();
			else if(model.getClass().equals(Eigenschaften.class)) 			concreteModel = new Eigenschaften();
			else if(model.getClass().equals(Experiment.class)) 				concreteModel = new Experiment();
			else if(model.getClass().equals(Experimenttyp.class)) 			concreteModel = new Experimenttyp();
			else if(model.getClass().equals(ExperimenttypGrinding.class)) 	concreteModel = new ExperimenttypGrinding();
			else if(model.getClass().equals(ExperimenttypSlurry.class)) 	concreteModel = new ExperimenttypSlurry();
			else if(model.getClass().equals(Mitarbeiter.class)) 			concreteModel = new Mitarbeiter();
			else if(model.getClass().equals(Partner.class)) 				concreteModel = new Partner();
			else if(model.getClass().equals(Probe.class)) 					concreteModel = new Probe();
			else if(model.getClass().equals(Projekt.class))					concreteModel = new Projekt();
			else if(model.getClass().equals(Substanz.class)) 				concreteModel = new Substanz();
			else{
				try {
					throw new NotImplementedException();
				}
				catch (NotImplementedException e) {
					e.printStackTrace();
					LOGGER.debug("Model not implemented in if-statement");
					break;
				}
			}
			
			try {
				concreteModel.setAttributes(resultSet);
				modelList.add(concreteModel);
			}
			catch (ModelNotFoundException e) {
				break;
			}
		}
	}
}
