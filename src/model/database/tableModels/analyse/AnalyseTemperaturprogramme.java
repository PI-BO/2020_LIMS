package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.tableModels.Model;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyseTemperaturprogramme extends Model {
    private String primaryKey;
    private String schritt;
    private String temperatur;
    private String rampe;
    private String zeit;
    private String segmenttyp;
    public static final String COLUMN_PRIMARY_KEY = "table";
    public static final String COLUMN_SCHRITT = "schritt";
    public static final String COLUMN_TEMPERATUR = "temperatur";
    public static final String COLUMN_RAMPE = "rampe";
    public static final String COLUMN_ZEIT = "zeit";
    public static final String COLUMN_SEGENTTYP = "segmenttyp";
    public static final String TABLE = "temperaturprogramme";

    public AnalyseTemperaturprogramme() {
        super();
    }
    
    public AnalyseTemperaturprogramme(String tabelle, String schritt) throws ModelNotFoundException, SQLException {
        this.primaryKey = tabelle;
        this.schritt = schritt;
        database.getModelAnalyseTemperaturprogramme(this);
    }

    @Override
    public String getForeignKey() {
    	//TODO
        return null;
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {
        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry dummyResultSetEntry = new DummyResultSetEntry();
        dummyResultSetEntry.addKeyValuePair(COLUMN_PRIMARY_KEY, primaryKey);
        dummyResultSetEntry.addKeyValuePair(COLUMN_SCHRITT, schritt);
        dummyResultSetEntry.addKeyValuePair(COLUMN_TEMPERATUR, temperatur);
        dummyResultSetEntry.addKeyValuePair(COLUMN_RAMPE, rampe);
        dummyResultSetEntry.addKeyValuePair(COLUMN_ZEIT, zeit);
        dummyResultSetEntry.addKeyValuePair(COLUMN_SEGENTTYP, segmenttyp);
        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
    }

    public String[] getPrimaryKeys() {
        return new String[]{primaryKey, schritt};
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    public String[] getPrimaryKeyColumns() {
        return new String[]{COLUMN_PRIMARY_KEY, COLUMN_SCHRITT};
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    public String getTabelle() {
        return primaryKey;
    }

    public void setTabelle(String tabelle) {
        this.primaryKey = tabelle;
    }

    public String getSchritt() {
        return schritt;
    }

    public void setSchritt(String schritt) {
        this.schritt = schritt;
    }

    public String getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(String temperatur) {
        this.temperatur = temperatur;
    }

    public String getRampe() {
        return rampe;
    }

    public void setRampe(String rampe) {
        this.rampe = rampe;
    }

    public String getZeit() {
        return zeit;
    }

    public void setZeit(String zeit) {
        this.zeit = zeit;
    }

    public String getSegmenttyp() {
        return segmenttyp;
    }

    public void setSegmenttyp(String segmenttyp) {
        this.segmenttyp = segmenttyp;
    }

    public static String getColumnSchritt() {
        return COLUMN_SCHRITT;
    }

    public static String getColumnTemperatur() {
        return COLUMN_TEMPERATUR;
    }

    public static String getColumnRampe() {
        return COLUMN_RAMPE;
    }

    public static String getColumnZeit() {
        return COLUMN_ZEIT;
    }

    public static String getColumnSegenttyp() {
        return COLUMN_SEGENTTYP;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            schritt = resultSet.getString(resultSet.findColumn(COLUMN_SCHRITT));
            temperatur = resultSet.getString(resultSet.findColumn(COLUMN_TEMPERATUR));
            rampe = resultSet.getString(resultSet.findColumn(COLUMN_RAMPE));
            zeit = resultSet.getString(resultSet.findColumn(COLUMN_ZEIT));
            segmenttyp = resultSet.getString(resultSet.findColumn(COLUMN_SEGENTTYP));
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    @Override
    public void saveToDatabase() throws SQLException {
        database.replaceModel(this);
    }

    @Override
    public String getValuesAsSQLString() {
    	//TODO
        return null;
    }

    @Override
    public String getRelationSchema() {
    	//TODO
        return null;
    }
    
	@Override
	public JSON toJSON() {
		
		JSON json = new JSON();
		json.addKeyValue("table", getPrimaryKey());
		json.addKeyValue(COLUMN_RAMPE, rampe);
		json.addKeyValue(COLUMN_SCHRITT, schritt);
		json.addKeyValue(COLUMN_SEGENTTYP, segmenttyp);
		json.addKeyValue(COLUMN_TEMPERATUR, temperatur);
		json.addKeyValue(COLUMN_ZEIT, zeit);

		return json;
	}

    private AnalyseTemperaturprogramme(String primaryKey) throws SQLException, ModelNotFoundException {
        super(primaryKey);
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public void setPrimaryKey(String primaryKey) {
        setTabelle(primaryKey);
    }
}
