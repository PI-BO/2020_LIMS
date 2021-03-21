package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyseDatenmaskePXRD extends AnalyseModel {
    private String geraet;
    private String praeparation;
    private Integer position;
    private String programm;
    private String messzeit;

    public static final String COLUMN_GERAET = "geraet";
    public static final String COLUMN_PRAEPARATION = "probenpaeparartion";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_PROGRAMM = "programm";
    public static final String COLUMN_MESSZEIT = "messzeit";

    public static final String TABLE = "datanmaske_pxrd";

    public AnalyseDatenmaskePXRD(String primaryKey) throws ModelNotFoundException, SQLException {
        super(primaryKey);
    }

    public AnalyseDatenmaskePXRD() {
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
        dummyResultSetEntry.addKeyValuePair(COLUMN_GERAET, geraet);
        dummyResultSetEntry.addKeyValuePair(COLUMN_PRAEPARATION, praeparation);
        dummyResultSetEntry.addKeyValuePair(COLUMN_POSITION, Integer.toString(position));
        dummyResultSetEntry.addKeyValuePair(COLUMN_PROGRAMM, programm);
        dummyResultSetEntry.addKeyValuePair(COLUMN_MESSZEIT, messzeit);

        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
    }

    public String getGeraet() {
        return geraet;
    }

    public void setGeraet(String geraet) {
        this.geraet = geraet;
    }

    public String getPraeparation() {
        return praeparation;
    }

    public void setPraeparation(String praeparation) {
        this.praeparation = praeparation;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getProgramm() {
        return programm;
    }

    public void setProgramm(String programm) {
        this.programm = programm;
    }

    public String getMesszeit() {
        return messzeit;
    }

    public void setMesszeit(String messzeit) {
        this.messzeit = messzeit;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public static String getColumnGeraet() {
        return COLUMN_GERAET;
    }

    public static String getColumnPraeparation() {
        return COLUMN_PRAEPARATION;
    }

    public static String getColumnPosition() {
        return COLUMN_POSITION;
    }

    public static String getColumnProgramm() {
        return COLUMN_PROGRAMM;
    }

    public static String getColumnMesszeit() {
        return COLUMN_MESSZEIT;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            super.setAttributes(resultSet);
            geraet = resultSet.getString(resultSet.findColumn(COLUMN_GERAET));
            praeparation = resultSet.getString(resultSet.findColumn(COLUMN_PRAEPARATION));
            position = resultSet.getInt(resultSet.findColumn(COLUMN_POSITION));
            programm = resultSet.getString(resultSet.findColumn(COLUMN_PROGRAMM));
            messzeit = resultSet.getString(resultSet.findColumn(COLUMN_MESSZEIT));
        } else {
            throw new ModelNotFoundException("Datenmaske PXRD nicht gefunden");
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

    @Override
    public JSON toJSON() {
        JSON json = super.toJSON();
        json.addKeyValue("table", TABLE);
        json.addKeyValue(COLUMN_GERAET, geraet);
        json.addKeyValue(COLUMN_PRAEPARATION, praeparation);
        json.addKeyValue(COLUMN_POSITION, position.toString());
        json.addKeyValue(COLUMN_PROGRAMM, programm);
        json.addKeyValue(COLUMN_MESSZEIT, messzeit);

        return json;
    }
}
