package database.model;

import database.connection.MariaDB;
import database.dummyDatabase.DummyResultSet;
import database.inerfaces.Database;
import exceptions.ModelNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ModelList extends Model{


    private static final Logger LOGGER = LogManager.getLogger(ModelList.class.getSimpleName());

    private String table;
    private Model model;
    private List<Model> modelList = new LinkedList<>();;

    public ModelList(Model model) throws SQLException, ModelNotFoundException {
        this.model = model;
        table = model.getTable();
        database.getTable(this);
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
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrimaryKeyColumn() {
		return model.getPrimaryKeyColumn();
	}

	@Override
	public String getValues() {
		return model.getValues();
	}

	@Override
	public String getRelationSchema() {
		return model.getRelationSchema();
	}

	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		
		for(Model model : modelList){
			
			dummyResultSet.addResultSet((model.returnAsDummyResultSet()));
		}
		
		return dummyResultSet;
	}
}
