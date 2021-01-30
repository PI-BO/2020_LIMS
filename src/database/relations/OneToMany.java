package database.relations;

import database.connection.MariaDB;
import database.inerfaces.Database;
import database.model.Model;
import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class OneToMany<O extends Model, M extends Model> {

    protected Database database = new MariaDB();

    private final O one;
    private final Class<M> many;
    protected OneToMany(O one, Class<M> many) {
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
