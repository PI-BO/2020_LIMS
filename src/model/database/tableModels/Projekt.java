package model.database.tableModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.relations.ProjekteSubstanz;
import utility.JSON;

public class Projekt extends Model {

	public static final String COLUMN_PRIMARY_KEY = "projekt_id";
	public static final String COLUMN_VERTRAGSNUMMER = "vertragsnummer";
	public static final String COLUMN_PROJEKTPARTNER = "projektpartner";
	public static final String TABLE = "projekte";
	private String vertragsnummer;
	private String projektPartnerId;

	public Projekt() {
		super();
	}

	public Projekt(String primaryKey) throws SQLException, ModelNotFoundException {
		super(primaryKey);
	}

	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
		if (resultSet.next()) {
			primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
			vertragsnummer = resultSet.getString(resultSet.findColumn(COLUMN_VERTRAGSNUMMER));
			projektPartnerId = resultSet.getString(resultSet.findColumn(COLUMN_PROJEKTPARTNER));
		} else {
			throw new ModelNotFoundException("Projekt nicht gefunden");
		}
	}

	public List<Substanz> getSubstanzen() throws ModelNotFoundException, SQLException {
		ProjekteSubstanz projekteSubstanz = new ProjekteSubstanz(this);
		return projekteSubstanz.getSubstanzen();
	}

	@Override
	public String getPrimaryKeyColumn() {
		return COLUMN_PRIMARY_KEY;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_VERTRAGSNUMMER + "," + COLUMN_PROJEKTPARTNER;
	}

	@Override
	public String getTable() {
		return TABLE;
	}

	@Override
	public String getValuesAsSQLString() {
		return "\"" + primaryKey + "\",\"" + vertragsnummer + "\",\"" + projektPartnerId + "\" ";
	}

	public void setVertragsnummer(String vertragsnummer) {
		this.vertragsnummer = vertragsnummer;
	}

	public String getVertragsnummer() {
		return vertragsnummer;
	}

	public void setProjektPartnerId(String projektPartnerId) throws SQLException, ModelNotFoundException {
		this.projektPartnerId = projektPartnerId;
	}
	
	@Override
	public void saveToDatabase() throws SQLException, DublicateModelException, ModelNotFoundException{
		super.saveToDatabase();
		Partner partner = new Partner(projektPartnerId);
		this.addParent(partner);
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();

		DummyResultSetEntry entry = new DummyResultSetEntry();
		entry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
		entry.addKeyValuePair(COLUMN_VERTRAGSNUMMER, vertragsnummer);
		entry.addKeyValuePair(COLUMN_PROJEKTPARTNER, projektPartnerId);

		dummyResultSet.addEntry(entry);

		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		return vertragsnummer;
	}

	public JSON toJSON() {

		JSON json = new JSON();
		json.addKeyValue("table", TABLE);
		json.addKeyValue("id", primaryKey);
		json.addKeyValue(COLUMN_VERTRAGSNUMMER, vertragsnummer);
		json.addKeyValue(COLUMN_PROJEKTPARTNER, projektPartnerId);

		return json;
	}
}
