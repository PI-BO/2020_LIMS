package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.tableModels.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.JSON;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AnalyseTemperaturprogrammTabelle extends Model {
    private static final Logger LOGGER = LogManager.getLogger(AnalyseTemperaturprogrammTabelle.class.getSimpleName());

    private List<Model> modelList = new LinkedList<>();

    public AnalyseTemperaturprogrammTabelle(String tabelle) throws ModelNotFoundException, SQLException {
        super(tabelle);
    }

    @Override
    public String getForeignKey() {
        return null;
    }

    @Override
    public DummyResultSet returnAsDummyResultSet() {
        DummyResultSet dummyResultSet = new DummyResultSet();

        for (Model model : modelList) {
            dummyResultSet.addResultSet(model.returnAsDummyResultSet());
        }

        return dummyResultSet;
    }

    @Override
    public String getPrimaryKeyColumn() {
        return AnalyseTemperaturprogramme.COLUMN_PRIMARY_KEY;
    }

    @Override
    public String getTable() {
        return AnalyseTemperaturprogramme.TABLE;
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException {
        while (!resultSet.isLast()) {
            AnalyseTemperaturprogramme concreteModel = new AnalyseTemperaturprogramme();
            concreteModel.setAttributes(resultSet);
            modelList.add(concreteModel);
        }
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
        return null;
    }
}
