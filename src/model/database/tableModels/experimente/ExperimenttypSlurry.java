package model.database.tableModels.experimente;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimenttypSlurry extends ExperimenteModel {

    private String beobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments;

    public static final String COLUMN_BEOBACHTUNGEN_ZUR_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY = "beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments";

    public static final String TABLE = "experimenttyp_verdampfung";

    public ExperimenttypSlurry(String primaryKey) throws ModelNotFoundException, SQLException {
        super(primaryKey);
    }

    public ExperimenttypSlurry() {
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            super.setAttributes(resultSet);
            beobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments = resultSet.getString(COLUMN_BEOBACHTUNGEN_ZUR_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY);
        } else {
            throw new ModelNotFoundException("Experiment Slurry nicht gefunden");
        }
    }

    public String getBeobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments() {
        return beobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments;
    }

    public void setBeobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments(String beobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments) {
        this.beobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments = beobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments;
    }

    public static String getColumnBeobachtungenZurSlurryerstellungOderAenderungenDesExperimentsKey() {
        return COLUMN_BEOBACHTUNGEN_ZUR_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY;
    }

    @Override
    public String getValuesAsSQLString() {
        return getPrimaryKey() + ",";
    }

    @Override
    public String getRelationSchema() {
        return COLUMN_PRIMARY_KEY + ",";
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {

        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry dummyResultSetEntry = super.getDummyResultsetEntry();
        dummyResultSetEntry.addKeyValuePair(COLUMN_BEOBACHTUNGEN_ZUR_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY, beobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments);

        dummyResultSet.addEntry(dummyResultSetEntry);

        return dummyResultSet;
    }

    @Override
    public String getForeignKey() {
        return null;
    }

    @Override
    public JSON toJSON() {

        JSON json = super.toJSON();
        json.addKeyValue("table", TABLE);
        json.addKeyValue(COLUMN_BEOBACHTUNGEN_ZUR_SLURRYERSTELLUNG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY, beobachtungen_zur_slurryerstellung_oder_aenderungen_des_Experiments);

        return json;
    }
}
