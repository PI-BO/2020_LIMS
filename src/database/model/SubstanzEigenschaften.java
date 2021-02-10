package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubstanzEigenschaften extends Model{
    private String substanz;
    private String eigenschaft;
    private final String COLUMN_SUBSTANZ_KEY = Substanz.COLUMN_PRIMARY_KEY;
    private final String COLUMN_EIGENSCHAFT_KEY = Eigenschaften.COLUMN_PRIMARY_KEY;
    private final String TABLE = "substanz_eigenschaften";

    public SubstanzEigenschaften() {

    }

    public SubstanzEigenschaften(String substanzId, String eigenschaftId) throws SQLException, ModelNotFoundException {
        this.substanz = substanzId;
        this.eigenschaft = eigenschaftId;
        database.getModel(this);
    }

	@Override
    public String getPrimaryKey() {
        return "\"" + substanz + "\",\"" + eigenschaft + "\"";
    }

    @Override
    public String getPrimaryKeyColumn() {
        return COLUMN_SUBSTANZ_KEY + "," + COLUMN_EIGENSCHAFT_KEY;
    }

    @Override
    public String getTable() {
        return TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        while (resultSet.next()) {
            int substanzIdIndex = resultSet.findColumn(COLUMN_SUBSTANZ_KEY);
            substanz = resultSet.getString(substanzIdIndex);

            int eigenschaftIdIndex = resultSet.findColumn(COLUMN_EIGENSCHAFT_KEY);
            eigenschaft = resultSet.getString(eigenschaftIdIndex);
        }
    }

    @Override
    public String getValues() {
        return "\"" + substanz + "\",\"" + eigenschaft + "\"";
    }

    @Override
    public String getRelationSchema() {
        return COLUMN_SUBSTANZ_KEY + "," + COLUMN_EIGENSCHAFT_KEY;
    }

    @Override
    public void saveToDatabase() {

    }
}
