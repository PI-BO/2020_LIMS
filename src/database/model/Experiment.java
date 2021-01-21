package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Experiment extends Model{
    private String primaryKey;
    private String typ;
    private String proben_nr;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";
    public static final String COLUMN_PROBEN_NR = "proben_nr";
    public static final String TABLE = "experiment";

    public Experiment(String primaryKey) throws ModelNotFoundException, SQLException {
        this.primaryKey = primaryKey;
        database.getModel(this);
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return null;
    }

    @Override
    public String getTable() {
        return null;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            typ = resultSet.getString(resultSet.findColumn(COLUMN_TYP));
            proben_nr = resultSet.getString(resultSet.findColumn(COLUMN_PROBEN_NR));
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    public String getTyp() {
        return typ;
    }

    public String getProbenNr() {
        return proben_nr;
    }
}
