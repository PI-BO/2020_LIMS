package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Probe extends Model{
    private String primaryKey;
    private String substanzID;
    public static final String COLUMN_PRIMARY_KEY = "proben_nr";
    public static final String COLUMN_SUBSTANZ_ID = "substanz_ID";
    public static final String TABLE = "substanz";

    public Probe(String id) throws SQLException, ModelNotFoundException {
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
            substanzID = resultSet.getString(resultSet.findColumn(COLUMN_SUBSTANZ_ID));

        } else {

            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    public String getSubstanzID() {
        return substanzID;
    }
}
