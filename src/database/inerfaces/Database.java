package database.inerfaces;

import java.sql.SQLException;

import database.model.Model;
import database.model.ModelList;
import database.relations.ManyToManyA;
import database.relations.ManyToManyB;
import database.relations.OneToMany;
import exceptions.ModelNotFoundException;

public interface Database {

    public void getModel(Model model) throws SQLException, ModelNotFoundException;

    public void setModel(Model model) throws SQLException;

    public void updateModel(Model model) throws SQLException;

    public void deleteModel(Model model) throws SQLException;

    public void getTable(ModelList modelList) throws SQLException, ModelNotFoundException;

    public void getRelation(Model model) throws SQLException, ModelNotFoundException;

    <T extends Model, U extends Model> void resolveOneToMany(OneToMany<T, U> relation) throws SQLException, ModelNotFoundException;

    <M extends Model, N extends Model> void resolveManyToMany(ManyToManyA<N,M> manyToMany) throws NoSuchFieldException, IllegalAccessException, SQLException;
}
