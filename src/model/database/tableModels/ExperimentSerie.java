package model.database.tableModels;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Narwutsch Dominic
 * created on 11.03.2021
 */
public class ExperimentSerie extends Model{
    @Override
    public String getForeignKey() {
        return null;
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {
        return null;
    }

    @Override
    public String getPrimaryKey() {
        return null;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return null;
    }

    @Override
    public String getTable() {
        return null;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {

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
    public void saveToDatabase() {

    }
}
