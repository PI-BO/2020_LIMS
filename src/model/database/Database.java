package model.database;

import java.sql.SQLException;
import java.util.List;

import exceptions.ModelNotFoundException;
import model.database.relations.ManyToManyA;
import model.database.relations.OneToMany;
import model.database.tableModels.Model;

public interface Database {

    public void getModel(Model model) throws SQLException, ModelNotFoundException;

    public void saveModel(Model model) throws SQLException;

    public void updateModel(Model model) throws SQLException;

    public void deleteModel(Model model) throws SQLException;

    public void getTable(Model modelList) throws SQLException, ModelNotFoundException;

    public void getRelation(Model model) throws SQLException, ModelNotFoundException;

    <T extends Model, U extends Model> void resolveOneToMany(OneToMany<T, U> relation) throws SQLException, ModelNotFoundException;

    <M extends Model, N extends Model> void resolveManyToMany(ManyToManyA<N,M> manyToMany) throws NoSuchFieldException, IllegalAccessException, SQLException;
    
    public List<List<Model>> getRelationList();
}
