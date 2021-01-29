package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperimenteAnalysen extends Model{
    private String primaryKey;
    private final String COLUMN_PRIMARY_KEY = Experiment.COLUMN_PRIMARY_KEY;
    private final String COLUMN_SECONDARY_KEY = Analysen.COLUMN_PRIMARY_KEY;
    private final String TABLE = "experimente_analysen";
    private List<Analysen> analysen;

    public ExperimenteAnalysen(String experimentId) throws SQLException, ModelNotFoundException {
        this.primaryKey = experimentId;
        database.getModel(this);
    }

    @Override
    public String getPrimaryKey() {
        return primaryKey;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_PRIMARY_KEY;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {

        analysen = new ArrayList<>();

        while (resultSet.next()) {

            int experimentIdIndex = resultSet.findColumn(COLUMN_PRIMARY_KEY);
            primaryKey = resultSet.getString(experimentIdIndex);

            int analyseIdIndex = resultSet.findColumn(COLUMN_SECONDARY_KEY);
            String analyseId = resultSet.getString(analyseIdIndex);
            Analysen analyse = new Analysen(analyseId);

            analysen.add(analyse);
        }
    }

    public List<Analysen> getAnalysen() {
        return analysen;
    }
}
