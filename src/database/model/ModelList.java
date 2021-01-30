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
			if(model.getClass().equals(Partner.class)){
				concreteModel = new Partner();
			}else{
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
