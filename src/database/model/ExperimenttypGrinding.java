package database.model;

import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimenttypGrinding extends Model{
    private String primaryKey;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String TABLE = "experimenttyp_grinding";

    public ExperimenttypGrinding(String primaryKey) throws ModelNotFoundException, SQLException {
        this.primaryKey = primaryKey;
        database.getModel(this);
    }

    public ExperimenttypGrinding() {
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
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

	@Override
	public String getValues() {
		return primaryKey;
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY;
	}

	@Override
	public void saveToDatabase() {
		// TODO Auto-generated method stub
		
	}
}
