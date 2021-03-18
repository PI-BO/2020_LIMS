package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetInterface;
import model.database.manager.DatabaseManager;
import utility.JSON;
import utility.JSONArray;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ModelTable implements DummyResultSetInterface, JSONModel{

    private static final Logger LOGGER = LogManager.getLogger(ModelTable.class.getSimpleName());

    private String table;
    private Model model;
    private List<Model> modelList = new LinkedList<>();
    private Database databse = DatabaseManager.getDatabaseInstance();

    public ModelTable(Model model) throws SQLException, ModelNotFoundException {
        this.model = model;
        table = model.getTable();
        databse.getTable(this);
    }

    public String getTable() {
        return table;
    }
    
    public List<Model> getModelList() {
        return modelList;
    }
    
    public void setAttributes(ResultSet resultSet) throws SQLException {
    	modelList = new LinkedList<>();

        while (!resultSet.isLast()) {
            try {
                Model concreteModel = model.getClass().getConstructor().newInstance();
                concreteModel.setAttributes(resultSet);
                modelList.add(concreteModel);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                LOGGER.error("Model has not implemented base constructor");
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException | ModelNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		
		for(Model model : modelList){
			dummyResultSet.addResultSet((model.returnAsDummyResultSet()));
		}
		
		return dummyResultSet;
	}
	
	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		JSONArray jsonArray = new JSONArray();
		
		for(Model model : modelList){
			jsonArray.addJSON(model.toJSON());
		}
		
		json.addJSONArray("ModelList", jsonArray);
		
		return json;
	}
}
