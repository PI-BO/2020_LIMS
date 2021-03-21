package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.tableModels.Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AnalyseModel extends Model {
    private int primaryKey;
    private Date datum;
    private String bemerkung;
    private String operator;

    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_DATUM = "datum";
    public static final String COLUMN_BEMERKUNG = "bemerkung";
    public static final String COLUMN_OPERATOR = "operator";

    protected AnalyseModel() {
    }

    protected AnalyseModel(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
           primaryKey = resultSet.getInt(COLUMN_PRIMARY_KEY);
           datum = resultSet.getDate(COLUMN_DATUM);
           bemerkung = resultSet.getString(COLUMN_BEMERKUNG);
           operator = resultSet.getString(COLUMN_OPERATOR);
    }

    protected DummyResultSetEntry getDummyResultsetEntry() {
        DummyResultSetEntry entry = new DummyResultSetEntry();
        entry.addKeyValuePair(COLUMN_PRIMARY_KEY, Integer.toString(primaryKey));
        entry.addKeyValuePair(COLUMN_DATUM, datum.toString());
        entry.addKeyValuePair(COLUMN_BEMERKUNG, bemerkung);
        entry.addKeyValuePair(COLUMN_OPERATOR, operator);

        return entry;
    }

    @Override
    public String getForeignKey() {
        return Integer.toString(primaryKey);
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String getPrimaryKey() {
        return Integer.toString(primaryKey);
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getBemerkung() {
        return bemerkung;
    }

    public void setBemerkung(String bemerkung) {
        this.bemerkung = bemerkung;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnDatum() {
        return COLUMN_DATUM;
    }

    public static String getColumnBemerkung() {
        return COLUMN_BEMERKUNG;
    }

    public static String getColumnOperator() {
        return COLUMN_OPERATOR;
    }
}
