package model.database.tableModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.relations.PartnerProjekt;
import utility.JSON;

public class Partner extends Model {

	private String name;
	private String email;
	public static final String COLUMN_PRIMARY_KEY = "partner_id";
//	public static final String COLUMN_PRIMARY_KEY = "vertragsnummer";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String TABLE = "partner";

	public Partner(){
		super();
	}
	
    public Partner(String primaryKey) throws SQLException, ModelNotFoundException {
    	super(primaryKey);
    }

	@Override
	public String getPrimaryKeyColumn() {
		return COLUMN_PRIMARY_KEY;
	}

	@Override
	public String getTable() {
		return TABLE;
	}

	@Override
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
		if(resultSet.next()){
			primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
			name = resultSet.getString(resultSet.findColumn(COLUMN_NAME));
			email = resultSet.getString(resultSet.findColumn(COLUMN_EMAIL));		
		}else{
			throw new ModelNotFoundException();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getValuesAsSQLString() {
		return primaryKey + "," + name + "," + email;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_NAME + "," + COLUMN_EMAIL;
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		
		DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
		dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		dummyResultSetEntry.addKeyValuePair(COLUMN_NAME, name);
		dummyResultSetEntry.addKeyValuePair(COLUMN_EMAIL, email);
		
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		// Partner has no Foreign key
		return null;
	}
	
	@Override
	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", TABLE);
		json.addKeyValue("id",primaryKey);
		json.addKeyValue(COLUMN_NAME, getName());
		json.addKeyValue(COLUMN_EMAIL, getEmail());
		
		return json;
	}

	public List<Projekt> getProjekte() throws ModelNotFoundException, SQLException {
		PartnerProjekt partnerProjekt = new PartnerProjekt(this);
		return partnerProjekt.getProjekte();
	}
}
