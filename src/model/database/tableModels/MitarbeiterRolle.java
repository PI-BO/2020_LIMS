package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MitarbeiterRolle extends Model{
    private String primaryKey;
    private String typ;
    private String zugehoerigkeit;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";
    public static final String COLUMN_ZUGEHOERIGKEIT = "zugehoerigkeit";
    public static final String TABLE = "rollen";

    public MitarbeiterRolle() {

    }

    public MitarbeiterRolle(String primaryKey) throws ModelNotFoundException, SQLException {
        this.primaryKey = primaryKey;
        database.getModel(this);
    }

    @Override
    public String getForeignKey() {
        return null;
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {
        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
        dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ZUGEHOERIGKEIT, zugehoerigkeit);
        dummyResultSetEntry.addKeyValuePair(COLUMN_TYP, typ);
        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getZugehoerigkeit() {
        return zugehoerigkeit;
    }

    public void setZugehoerigkeit(String zugehoerigkeit) {
        this.zugehoerigkeit = zugehoerigkeit;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnTyp() {
        return COLUMN_TYP;
    }

    public static String getColumnZugehoerigkeit() {
        return COLUMN_ZUGEHOERIGKEIT;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            typ = resultSet.getString(resultSet.findColumn(COLUMN_TYP));
            zugehoerigkeit = resultSet.getString(resultSet.findColumn(COLUMN_ZUGEHOERIGKEIT));
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    @Override
    public String getValuesAsSQLString() {
        return null;
    }

    @Override
    public String getRelationSchema() {
        return null;
    }
}
