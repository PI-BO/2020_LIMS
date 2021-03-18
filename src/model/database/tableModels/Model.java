package model.database.tableModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.dummyDB.DummyRelation;
import model.database.dummyDB.DummyResultSetInterface;
import model.database.manager.DatabaseManager;

public abstract class Model implements DummyResultSetInterface, DummyRelation, JSONModel{
	
	protected Database database = DatabaseManager.getDatabaseInstance();
	private List<Model> parents = new LinkedList<>();
	private List<Model> children = new LinkedList<>();
	protected String primaryKey;

	public abstract String getPrimaryKeyColumn();
	public abstract String getTable();
	public abstract void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException;
	public abstract String getValuesAsSQLString();
	public abstract String getRelationSchema();
	public abstract void saveToDatabase();
	
	
	/**
	 * creates Model as root-node (no parent models)
	 */
	public Model(){
		
	}
	
	/**
	 * creates empty Model
	 * @param parent if parent is null, this model is root-node in hierarchy (for example: Projektpartner)
	 */
	public Model(Model parent){
		if(parent != null) addParent(parent);
	}
	
	/**
	 * retrieves Model from database
	 * @param primaryKey of Model that is requested
	 * @throws SQLException
	 * @throws ModelNotFoundException
	 */
	public Model(String primaryKey) throws SQLException, ModelNotFoundException {
		this.primaryKey = primaryKey;
        database.getModel(this);
	}
	
	public String getPrimaryKey(){
		return primaryKey;
	}
	
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	public List<Model> getParents(){
		return parents;
	};
	
	public List<Model> getChildren(){
		return children;
	};
	
	public void addParent(Model parent){
		parents.add(parent);
		parent.addChild(this);
	}
	
	protected void addChild(Model child){
		children.add(child);
	}
	
	public boolean hasParents(){
		return (parents.size() > 0);
	}
	
	public boolean hasChildren(){
		return (children.size() > 0);
	}
}