package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyseDatenmaskeTGA extends AnalyseModel {
    private Double einwaage;
    private Integer rampe;
    private String temperaturprgramm;

    public static final String COLUMN_EINWAAGE = "einwaage";
    public static final String COLUMN_RAMPE = "rammpe";
    public static final String COLUMN_TEMPERATURPROGRAMM = "temperaturprogramm";

    public static final String TABLE = "datanmaske_tga";

    public AnalyseDatenmaskeTGA(String primaryKey) throws ModelNotFoundException, SQLException {
        super(primaryKey);
    }

    public AnalyseDatenmaskeTGA() {
        super();
    }

    @Override
    public String getForeignKey() {
        return getPrimaryKey();
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {
        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry dummyResultSetEntry = super.getDummyResultsetEntry();
        try {
            dummyResultSetEntry.addKeyValuePair(COLUMN_EINWAAGE, einwaage.toString());
        } catch (NullPointerException e) {
            dummyResultSetEntry.addKeyValuePair(COLUMN_EINWAAGE, null);
        }
        try {
            dummyResultSetEntry.addKeyValuePair(COLUMN_RAMPE, Integer.toString(rampe));
        } catch (NullPointerException e) {
            dummyResultSetEntry.addKeyValuePair(COLUMN_RAMPE, null);
        }
        dummyResultSetEntry.addKeyValuePair(COLUMN_TEMPERATURPROGRAMM, temperaturprgramm);

        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
    }

    public Double getEinwaage() {
        return einwaage;
    }

    public void setEinwaage(Double einwaage) {
        this.einwaage = einwaage;
    }

    public Integer getRampe() {
        return rampe;
    }

    public void setRampe(Integer rampe) {
        this.rampe = rampe;
    }

    public String getTemperaturprgramm() {
        return temperaturprgramm;
    }

    public void setTemperaturprgramm(String temperaturprgramm) {
        this.temperaturprgramm = temperaturprgramm;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnEinwaage() {
        return COLUMN_EINWAAGE;
    }

    public static String getColumnRampe() {
        return COLUMN_RAMPE;
    }

    public static String getColumnTemperaturprogramm() {
        return COLUMN_TEMPERATURPROGRAMM;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            super.setAttributes(resultSet);
            einwaage = resultSet.getDouble(resultSet.findColumn(COLUMN_EINWAAGE));
            rampe = resultSet.getInt(resultSet.findColumn(COLUMN_RAMPE));
            temperaturprgramm = resultSet.getString(resultSet.findColumn(COLUMN_TEMPERATURPROGRAMM));
        } else {
            throw new ModelNotFoundException("Datenmaske DSC nicht gefunden");
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
