package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.manager.DatabaseManager;
import model.database.tableModels.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class OneToMany<O extends Model, M extends Model> {

    protected Database database = DatabaseManager.getDatabaseInstance();

    private final O one;
    protected final Class<M> many;

    protected OneToMany(O one, Class<M> many) throws ModelNotFoundException, SQLException {
        this.one = one;
        this.many = many;
    }

    public String getOneKey() {
        return one.getPrimaryKey();
    }

    public String getOneKeyColumn(){
        return one.getPrimaryKeyColumn();
    }

    public String getOneTable() {
        return one.getTable();
    }

    public abstract List<String> getManyKeys();

    public String getManyKeyColumn() throws NoSuchFieldException, IllegalAccessException {
        return (String) many.getDeclaredField("COLUMN_PRIMARY_KEY").get(null);
    }

    public String getManyTable() throws NoSuchFieldException, IllegalAccessException {
        return (String) many.getDeclaredField("TABLE").get(null);
    }

    public abstract void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException;

}
