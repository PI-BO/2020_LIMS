package database.relations;

import database.connection.MariaDB;
import database.inerfaces.Database;
import database.model.Model;
import exceptions.ModelNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ManyToManyB<N extends Model, M extends Model> {

    protected Database database = new MariaDB();

    private Class<N> n;
    private Class<M> m;
    private Model relationTable;

    private AsOneToMany oneToMany;

    public ManyToManyB(Class<N> n, Class<M> m, Model relationTable) throws ModelNotFoundException, SQLException {
        this.n = n;
        this.m = m;
        this.relationTable = relationTable;
    }

    public AsOneToMany resolveAsOneToMany(Model oneTable) throws Exception {
        oneToMany = null;
        if (oneTable.getClass().equals(n)) {
            oneToMany = new AsOneToMany<Model, M>(oneTable,m);
        } else if(oneTable.getClass().equals(m)) {
            oneToMany = new AsOneToMany<Model, N>(oneTable, n);
        } else throw new Exception("No matching Class found");
        return oneToMany;
    }

    public abstract void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException;

    private class AsOneToMany<O extends Model, I extends Model> extends OneToMany<O, I> {
        private List<I> manys;

        protected AsOneToMany(O one, Class<I> many) throws ModelNotFoundException, SQLException {
            super(one, many);
            //database.resolveManyToMany(ManyToManyB.this);
        }

        @Override
        public List<String> getManyKeys() {
            return manys.stream().map(I::getPrimaryKey).collect(Collectors.toList());
        }

        @Override
        public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException {
            manys = new LinkedList<>();

            while (resultSet.next()) {
                int experinentIdIndex = resultSet.findColumn(getManyKeyColumn());
                String experimentId = resultSet.getString(experinentIdIndex);
                try {
                    I experiment = many.getConstructor(String.class).newInstance(experimentId);
                    manys.add(experiment);
                } catch (InstantiationException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }

        public List<I> getManys() {
            return manys;
        }
    }

}
