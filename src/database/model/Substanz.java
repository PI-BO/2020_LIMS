package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Substanz extends Model {

    private String primaryKey;
    private String projektID;
    public static final String COLUMN_PRIMARY_KEY = "substanz_id";
    public static final String COLUMN_PROJEKT_ID = "projekt_ID";
    public static final String TABLE = "substanz";

    public Substanz(String id) throws SQLException, ModelNotFoundException {
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
            projektID = resultSet.getString(resultSet.findColumn(COLUMN_PROJEKT_ID));
        } else {
            throw new ModelNotFoundException("Model nicht gefunden");
        }
    }

    public String getProjektID() {
        return projektID;
    }
}
