package database.relations;

import database.model.Model;
import exceptions.ModelNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Narwutsch Dominic
 * created on 10.02.2021
 */
public abstract class ManyToManyA<N extends Model, M extends Model> extends OneToMany<N, M> {
    private final String relationTable;

    protected ManyToManyA(N n, Class<M> m, String relationTable) throws ModelNotFoundException, SQLException {
        super(n, m);
        this.relationTable = relationTable;
    }

    public String getRelationTable() {
        return relationTable;
    }

    public String getNKeyColumn() {
        return getOneKeyColumn();
    }

    public String getMKeyColumn() throws NoSuchFieldException, IllegalAccessException {
        return getManyKeyColumn();
    }

    @Override
    public abstract List<String> getManyKeys();

    @Override
    public abstract void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException;
}
