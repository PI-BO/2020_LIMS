package model.database.tableModels.experimente;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.dummyDB.DummyResultSetEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExperimenttypGrinding extends ExperimenteModel {
    public static final String TABLE = "experimenttyp_grinding";

    public ExperimenttypGrinding(String primaryKey) throws ModelNotFoundException, SQLException {
        super(primaryKey);
        database.getModel(this);
    }

    public ExperimenttypGrinding() {
		// TODO Auto-generated constructor stub
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
            super.setAttributes(resultSet);
        } else {
            throw new ModelNotFoundException("Mitarbeiter nicht gefunden");
        }
    }

	@Override
	public String getValuesAsSQLString() {
		return getPrimaryKey();
	}

	@Override
	public String getRelationSchema() {
		return COLUMN_PRIMARY_KEY;
	}

	@Override
	public DummyResultSet returnAsDummyResultSet() {

		DummyResultSet dummyResultSet = new DummyResultSet();
		
		DummyResultSetEntry dummyResultSetEntry = super.getDummyResultsetEntry();
		dummyResultSet.addEntry(dummyResultSetEntry);
		
		return dummyResultSet;
	}

	@Override
	public String getForeignKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
