package model;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Eigenschaften;

public class Gefahrensymbol extends Eigenschaften{
	private String primaryKey;
	private InputStream image;
	public static final String COLUMN_PRIMARY_KEY = "eigenschaft_key";
	public static final String COLUMN_IMAGE = "image";
	public static final String TABLE = "gefahrensymbole";

	public Gefahrensymbol(){

	}

	public Gefahrensymbol(String id) throws SQLException, ModelNotFoundException{
		super(id);
	}

	@Override
	public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
		if (resultSet.next()) {
			int gefahrensymbolIdIndex = resultSet.findColumn(COLUMN_PRIMARY_KEY);
			primaryKey = resultSet.getString(gefahrensymbolIdIndex);

			setValue(resultSet.getString(resultSet.findColumn(COLUMN_VALUE)));

			int imageIdIndex = resultSet.findColumn(COLUMN_IMAGE);
			image = resultSet.getBinaryStream(imageIdIndex);
		} else {
			throw new ModelNotFoundException("Projekt nicht gefunden");
		}
	}

	public void save() throws SQLException{
		database.saveModel(this);
	}

	@Override
	public String getPrimaryKey() {
		return primaryKey;
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
	public String getValuesAsSQLString() {
		return super.getValuesAsSQLString() + ",\"" + image + "\"";
	}

	@Override
	public String getRelationSchema() {
		return super.getRelationSchema() + "," + COLUMN_IMAGE;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
	}
}
