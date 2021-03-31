package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.tableModels.Model;
import utility.JSON;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AnalyseModel extends Model {
    private Date datum;
    private String bemerkung;
    private String operator;

    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_DATUM = "datum";
    public static final String COLUMN_BEMERKUNG = "bemerkung";
    public static final String COLUMN_OPERATOR = "operator";
    public static final String COLUMN_FOREIGN_KEY = COLUMN_PRIMARY_KEY;

    protected AnalyseModel() {
        super();
    }

    protected AnalyseModel(String primaryKey) throws ModelNotFoundException, SQLException {
        super(primaryKey);
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
           primaryKey = resultSet.getString(COLUMN_PRIMARY_KEY);
           datum = resultSet.getDate(COLUMN_DATUM);
           bemerkung = resultSet.getString(COLUMN_BEMERKUNG);
           operator = resultSet.getString(COLUMN_OPERATOR);
    }

    protected DummyResultSetEntry getDummyResultsetEntry() {
        DummyResultSetEntry entry = new DummyResultSetEntry();
        entry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
        try {
            entry.addKeyValuePair(COLUMN_DATUM, datum.toString());
        } catch (NullPointerException e) {
            entry.addKeyValuePair(COLUMN_DATUM, null);
        }
        entry.addKeyValuePair(COLUMN_BEMERKUNG, bemerkung);
        entry.addKeyValuePair(COLUMN_OPERATOR, operator);

        return entry;
    }

    @Override
    public String getForeignKey() {
        return primaryKey;
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

    @Override
    public JSON toJSON() {
        JSON json = new JSON();
        json.addKeyValue(COLUMN_PRIMARY_KEY, primaryKey);
        try {
            json.addKeyValue(COLUMN_DATUM, datum.toString());
        } catch (NullPointerException e) {
            json.addKeyValue(COLUMN_DATUM, null);
        }
        json.addKeyValue(COLUMN_BEMERKUNG, bemerkung);
        json.addKeyValue(COLUMN_OPERATOR, operator);

        return json;
    }
}
