package model.database.tableModels.experimente;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;
import model.database.tableModels.experimente.ExperimenteModel;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimenttypVerdampfung extends ExperimenteModel {

    private double vial_tara;
    private String beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments;
    private double auswaage_vial_kristallisat;
    private double ausbeute_von_praep_analystik;

    public static final String COLUMN_VIAL_TARA_KEY = "vial_tara";
    public static final String COLUMN_BEOBACHTUNGEN_ZUM_LOESEVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY = "beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments";
    public static final String COLUMN_AUSWAAGE_VIAL_KRISTALLISAT_KEY = "ausbeute_vial_kristallisat";
    public static final String COLUMN_AUSBEUTE_VON_PRAEP_ANALYSTIK_KEY = "ausbeute_von_praep_analystik";

    public static final String TABLE = "experimenttyp_verdampfung";

    public ExperimenttypVerdampfung() {
    }

    public ExperimenttypVerdampfung(String primaryKey) throws ModelNotFoundException, SQLException {
        super(primaryKey);
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        if (resultSet.next()) {
            super.setAttributes(resultSet);
            vial_tara = resultSet.getDouble(COLUMN_VIAL_TARA_KEY);
            beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments = resultSet.getString(COLUMN_BEOBACHTUNGEN_ZUM_LOESEVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY);
            auswaage_vial_kristallisat = resultSet.getDouble(COLUMN_AUSWAAGE_VIAL_KRISTALLISAT_KEY);
            ausbeute_von_praep_analystik = resultSet.getDouble(COLUMN_AUSBEUTE_VON_PRAEP_ANALYSTIK_KEY);
        } else {
            throw new ModelNotFoundException("Experiment Verdampfung nicht gefunden");
        }
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {
        DummyResultSet dummyResultSet = new DummyResultSet();
        DummyResultSetEntry entry = super.getDummyResultsetEntry();
        entry.addKeyValuePair(COLUMN_VIAL_TARA_KEY, Double.toString(vial_tara));
        entry.addKeyValuePair(COLUMN_BEOBACHTUNGEN_ZUM_LOESEVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY, beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments);
        entry.addKeyValuePair(COLUMN_AUSWAAGE_VIAL_KRISTALLISAT_KEY, Double.toString(auswaage_vial_kristallisat));
        entry.addKeyValuePair(COLUMN_AUSBEUTE_VON_PRAEP_ANALYSTIK_KEY, Double.toString(ausbeute_von_praep_analystik));

        dummyResultSet.addEntry(entry);

        return dummyResultSet;
    }

    public double getVial_tara() {
        return vial_tara;
    }

    public void setVial_tara(double vial_tara) {
        this.vial_tara = vial_tara;
    }

    public String getBeobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments() {
        return beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments;
    }

    public void setBeobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments(String beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments) {
        this.beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments = beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments;
    }

    public double getAuswaage_vial_kristallisat() {
        return auswaage_vial_kristallisat;
    }

    public void setAuswaage_vial_kristallisat(double auswaage_vial_kristallisat) {
        this.auswaage_vial_kristallisat = auswaage_vial_kristallisat;
    }

    public double getAusbeute_von_praep_analystik() {
        return ausbeute_von_praep_analystik;
    }

    public void setAusbeute_von_praep_analystik(double ausbeute_von_praep_analystik) {
        this.ausbeute_von_praep_analystik = ausbeute_von_praep_analystik;
    }

    @Override
    public String getTable() {
        return TABLE;
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
        json.addKeyValue(COLUMN_VIAL_TARA_KEY, Double.toString(vial_tara));
        json.addKeyValue(COLUMN_BEOBACHTUNGEN_ZUM_LOESEVORGANG_ODER_AENDERUNGEN_DES_EXPERIMENTS_KEY, beobachtungen_zum_loesungsvorgang_oder_aenderungen_des_Experiments);
        json.addKeyValue(COLUMN_AUSWAAGE_VIAL_KRISTALLISAT_KEY, Double.toString(auswaage_vial_kristallisat));
        json.addKeyValue(COLUMN_AUSBEUTE_VON_PRAEP_ANALYSTIK_KEY, Double.toString(ausbeute_von_praep_analystik));

        return json;
    }
}