package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubstanzEigenschaften extends Model{
    private String primaryKey;
    private final String COLUMN_PRIMARY_KEY = Substanz.COLUMN_PRIMARY_KEY;
    private final String COLUMN_SECONDARY_KEY = Eigenschaften.COLUMN_PRIMARY_KEY;
    private final String TABLE = "substanz_eigenschaften";
    private List<Eigenschaften> eigenschaften;

    public SubstanzEigenschaften(String substanzId) throws SQLException, ModelNotFoundException {
        this.primaryKey = substanzId;
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

        eigenschaften = new ArrayList<>();

        while (resultSet.next()) {

            int substanzIdIndex = resultSet.findColumn(COLUMN_PRIMARY_KEY);
            primaryKey = resultSet.getString(substanzIdIndex);

            int eigenschaftIdIndex = resultSet.findColumn(COLUMN_SECONDARY_KEY);
            String eigenschaftId = resultSet.getString(eigenschaftIdIndex);
            Eigenschaften eigenschaft = new Eigenschaften(eigenschaftId);

            eigenschaften.add(eigenschaft);
        }
    }

    @Override
    public String getValues() {
        return null;
    }

    @Override
    public String getRelationSchema() {
        return null;
    }

    @Override
    public void saveToDatabase() {

    }

    public List<Eigenschaften> getEigenschaften() {
        return eigenschaften;
    }
}
