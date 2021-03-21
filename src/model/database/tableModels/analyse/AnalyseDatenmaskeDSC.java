package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyseDatenmaskeDSC extends AnalyseModel {
    private Double einwaage;
    private Double auswaage;
    private Integer rampe;
    private String temperaturprgramm;
    private String tiegel;
    private String tiegelpaeparation;

    public static final String COLUMN_EINWAAGE = "einwaage";
    public static final String COLUMN_AUSWAAGE = "auswaage";
    public static final String COLUMN_RAMPE = "rammpe";
    public static final String COLUMN_TEMPERATURPROGRAMM = "temperaturprogramm";
    public static final String COLUMN_TIEGEL = "tiegel";
    public static final String COLUMN_TIEGELPRAEPARATION = "tiegelpraeparation";

    public static final String TABLE = "datanmaske_dsc";

    @Override
    public String getForeignKey() {
        return getPrimaryKey();
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {
        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry dummyResultSetEntry = super.getDummyResultsetEntry();
        dummyResultSetEntry.addKeyValuePair(COLUMN_EINWAAGE, einwaage.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_AUSWAAGE, auswaage.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_RAMPE, Integer.toString(rampe));
        dummyResultSetEntry.addKeyValuePair(COLUMN_TEMPERATURPROGRAMM, temperaturprgramm);
        dummyResultSetEntry.addKeyValuePair(COLUMN_TIEGEL, tiegel);
        dummyResultSetEntry.addKeyValuePair(COLUMN_TIEGELPRAEPARATION, tiegelpaeparation);

        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
    }

    public Double getEinwaage() {
        return einwaage;
    }

    public void setEinwaage(Double einwaage) {
        this.einwaage = einwaage;
    }

    public Double getAuswaage() {
        return auswaage;
    }

    public void setAuswaage(Double auswaage) {
        this.auswaage = auswaage;
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

    public String getTiegel() {
        return tiegel;
    }

    public void setTiegel(String tiegel) {
        this.tiegel = tiegel;
    }

    public String getTiegelpaeparation() {
        return tiegelpaeparation;
    }

    public void setTiegelpaeparation(String tiegelpaeparation) {
        this.tiegelpaeparation = tiegelpaeparation;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnEinwaage() {
        return COLUMN_EINWAAGE;
    }

    public static String getColumnAuswaage() {
        return COLUMN_AUSWAAGE;
    }

    public static String getColumnRampe() {
        return COLUMN_RAMPE;
    }

    public static String getColumnTemperaturprogramm() {
        return COLUMN_TEMPERATURPROGRAMM;
    }

    public static String getColumnTiegel() {
        return COLUMN_TIEGEL;
    }

    public static String getColumnTiegelpraeparation() {
        return COLUMN_TIEGELPRAEPARATION;
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
            auswaage = resultSet.getDouble(resultSet.findColumn(COLUMN_AUSWAAGE));
            rampe = resultSet.getInt(resultSet.findColumn(COLUMN_RAMPE));
            temperaturprgramm = resultSet.getString(resultSet.findColumn(COLUMN_TEMPERATURPROGRAMM));
            tiegel = resultSet.getString(resultSet.findColumn(COLUMN_TIEGEL));
            tiegelpaeparation = resultSet.getString(resultSet.findColumn(COLUMN_TIEGELPRAEPARATION));
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
