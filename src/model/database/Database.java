package model.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ModelNotFoundException;
import model.database.relations.ManyToManyA;
import model.database.relations.OneToMany;
import model.database.tableModels.AnalyseTemperaturprogramme;
import model.database.tableModels.Model;

public interface Database {

    public void getModel(Model model) throws SQLException, ModelNotFoundException;

    void getModelAnalyseTemperaturprogramme(AnalyseTemperaturprogramme requestModel)  throws ModelNotFoundException, SQLException;

    public void setModel(Model model) throws SQLException;

    public void updateModel(Model model) throws SQLException;

    public void deleteModel(Model model) throws SQLException;

    public void getTable(Model modelList) throws SQLException, ModelNotFoundException;

    public void getRelation(Model model) throws SQLException, ModelNotFoundException;

    <T extends Model, U extends Model> void resolveOneToMany(OneToMany<T, U> relation) throws SQLException, ModelNotFoundException;

    <M extends Model, N extends Model> void resolveManyToMany(ManyToManyA<N,M> manyToMany) throws NoSuchFieldException, IllegalAccessException, SQLException;

    public ResultSet findSubstring(Class<? extends Model> m, String str, String... fields) throws NoSuchFieldException, IllegalAccessException, SQLException;

}
