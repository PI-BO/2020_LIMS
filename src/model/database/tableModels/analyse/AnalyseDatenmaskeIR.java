package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyseDatenmaskeIR extends AnalyseModel {
    private Integer scans;
    private Integer aufloesung;
    private String geometrie;
    private String praeparation;
    private String background;

    public static final String COLUMN_SCANS = "scans";
    public static final String COLUMN_AUFLOESUNG = "aufloesung";
    public static final String COLUMN_GEOMETRIE = "geometrie";
    public static final String COLUMN_PRAEPARATION = "praeparation";
    public static final String COLUMN_BACKGROUND = "background";

    public static final String TABLE = "datanmaske_ir";

    @Override
    public String getForeignKey() {
        return getPrimaryKey();
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {
        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry dummyResultSetEntry = super.getDummyResultsetEntry();
        dummyResultSetEntry.addKeyValuePair(COLUMN_SCANS, scans.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_AUFLOESUNG, aufloesung.toString());
        dummyResultSetEntry.addKeyValuePair(COLUMN_GEOMETRIE, geometrie);
        dummyResultSetEntry.addKeyValuePair(COLUMN_PRAEPARATION, praeparation);
        dummyResultSetEntry.addKeyValuePair(COLUMN_BACKGROUND, background);

        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
    }

    public Integer getScans() {
        return scans;
    }

    public void setScans(Integer scans) {
        this.scans = scans;
    }

    public Integer getAufloesung() {
        return aufloesung;
    }

    public void setAufloesung(Integer aufloesung) {
        this.aufloesung = aufloesung;
    }

    public String getGeometrie() {
        return geometrie;
    }

    public void setGeometrie(String geometrie) {
        this.geometrie = geometrie;
    }

    public String getPraeparation() {
        return praeparation;
    }

    public void setPraeparation(String praeparation) {
        this.praeparation = praeparation;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnScans() {
        return COLUMN_SCANS;
    }

    public static String getColumnAufloesung() {
        return COLUMN_AUFLOESUNG;
    }

    public static String getColumnGeometrie() {
        return COLUMN_GEOMETRIE;
    }

    public static String getColumnPraeparation() {
        return COLUMN_PRAEPARATION;
    }

    public static String getColumnBackground() {
        return COLUMN_BACKGROUND;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            super.setAttributes(resultSet);
            scans = resultSet.getInt(resultSet.findColumn(COLUMN_SCANS));
            aufloesung = resultSet.getInt(resultSet.findColumn(COLUMN_AUFLOESUNG));
            geometrie = resultSet.getString(resultSet.findColumn(COLUMN_GEOMETRIE));
            praeparation = resultSet.getString(resultSet.findColumn(COLUMN_PRAEPARATION));
            background = resultSet.getString(resultSet.findColumn(COLUMN_BACKGROUND));
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
