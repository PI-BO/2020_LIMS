package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;
import model.database.dummyDB.DummyResultSet;
import model.database.tableModels.Model;
import model.database.tableModels.ModelList;
import model.database.tableModels.analyse.AnalyseTemperaturprogramme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AnalyseTemperaturprogrammTabelle extends Model {
    private static final Logger LOGGER = LogManager.getLogger(ModelList.class.getSimpleName());

    private String tabelle;
    private List<Model> modelList = new LinkedList<>();

    public AnalyseTemperaturprogrammTabelle(String tabelle) throws ModelNotFoundException, SQLException {
        this.tabelle = tabelle;
        database.getModel(this);
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
    public String getPrimaryKey() {
        return tabelle;
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
}
