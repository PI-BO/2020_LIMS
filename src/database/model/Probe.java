package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Probe extends Model{
    private String primaryKey;
    private String substanzID;
    public static final String COLUMN_PRIMARY_KEY = "proben_nr";
    public static final String COLUMN_SUBSTANZ_ID = "substanz_ID";
    public static final String TABLE = "probe";

    public Probe(String id) throws SQLException, ModelNotFoundException {
        this.primaryKey = id;
        database.getModel(this);
    }

    public Probe() {
		// TODO Auto-generated constructor stub
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

    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {

        if (resultSet.next()) {

            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            substanzID = resultSet.getString(resultSet.findColumn(COLUMN_SUBSTANZ_ID));

        } else {

            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    public String getSubstanzID() {
        return substanzID;
    }

	@Override
	public String getValues() {
		return primaryKey + "," + substanzID;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_SUBSTANZ_ID;
	}

	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		
	}
}
