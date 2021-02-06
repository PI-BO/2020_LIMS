package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimenttypSlurry extends Model{
    private String primaryKey;
    private String value;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_VALUE = "value";
    public static final String TABLE = "experimenttyp_slurry";

    public ExperimenttypSlurry(String primaryKey) throws ModelNotFoundException, SQLException {
        this.primaryKey = primaryKey;
        database.getModel(this);
    }

    public ExperimenttypSlurry() {
		// TODO Auto-generated constructor stub
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
        if (resultSet.next()) {
            primaryKey = resultSet.getString(resultSet.findColumn(COLUMN_PRIMARY_KEY));
            value = resultSet.getString(resultSet.findColumn(COLUMN_VALUE));
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

    public String getValue() {
        return value;
    }

	@Override
	public String getValues() {
		return primaryKey + "," + value;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY + "," + COLUMN_VALUE;
	}

	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		
	}
}
