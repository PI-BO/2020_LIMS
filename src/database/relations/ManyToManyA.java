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
    private final Model relationTable;

    protected ManyToManyA(N n, Class<M> m, Model relationTable) throws ModelNotFoundException, SQLException {
        super(n, m);
        this.relationTable = relationTable;
    }

    public String getRelationTable() {
        return relationTable.getTable();
    }

    @Override
    public abstract List<String> getManyKeys();

    @Override
    public abstract void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException;
}
