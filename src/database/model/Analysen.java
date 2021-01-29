package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Analysen extends Model{
    private String primaryKey;
    private String api;
    private String bemerkung;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_API = "api";
    public static final String COLUMN_BEMERKUNG = "bemerkung";
    public static final String TABLE = "analysen";

    public Analysen(String id) throws SQLException, ModelNotFoundException {
        this.primaryKey = id;
        database.getModel(this);
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
            api = resultSet.getString(resultSet.findColumn(COLUMN_API));
            bemerkung = resultSet.getString(resultSet.findColumn(COLUMN_BEMERKUNG));
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    public String getApi() {
        return api;
    }

    public String getBemerkung() {
        return bemerkung;
    }

	@Override
	public String getValues() {
		return primaryKey + "," + api + "," + bemerkung;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_API + "," + COLUMN_BEMERKUNG;
	}
}
