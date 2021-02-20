package database.connection;

import database.inerfaces.Database;
import database.model.Model;
import database.model.ModelList;
import database.relations.ManyToManyA;
import database.relations.OneToMany;
import exceptions.ModelNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MariaDB implements Database {

    private DatabaseConnection databaseConnection;
    private static final Logger LOGGER = LogManager.getLogger(DatabaseConnection.class.getSimpleName());

    public MariaDB() {
        databaseConnection = DatabaseConnection.getInstance();
        if (databaseConnection == null) LOGGER.debug("MariaDB constructor: databaseConnection == NULL");
    }

    @Override
    public void getModel(Model model) throws SQLException, ModelNotFoundException {
        String sqlStatement = "SELECT * FROM " + model.getTable() + " WHERE " + model.getPrimaryKeyColumn() + "=\"" + model.getPrimaryKey() + "\";";
        System.out.println(sqlStatement);
        ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);

        model.setAttributes(resultSet);
    }

    @Override
    public void getTable(Model modelList) throws SQLException, ModelNotFoundException {

        String sqlStatement = "SELECT * FROM " + modelList.getTable() + ";";

        System.out.println(sqlStatement);

        ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);

        modelList.setAttributes(resultSet);
    }

    @Override
    public void getRelation(Model model) throws SQLException, ModelNotFoundException {

        String sqlStatement = "SELECT * FROM " + model.getTable() + " WHERE " + model.getPrimaryKeyColumn() + "=\"" + model.getPrimaryKey() + "\";";

        ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);

        model.setAttributes(resultSet);
    }

    public <T extends Model, U extends Model> void resolveOneToMany(OneToMany<T, U> reslation) throws SQLException, ModelNotFoundException {
        try {
            String sqlStatement = "SELECT * FROM " + reslation.getManyTable() + " WHERE " + reslation.getOneKeyColumn() + "=\"" + reslation.getOneKey() + "\";";

            ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);

            reslation.setAttributes(resultSet);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <M extends Model, N extends Model> void resolveManyToMany(ManyToManyA<N, M> relation) throws SQLException {
        try {
            String sqlStatement = "SELECT * FROM " + relation.getManyTable() + "Where " + relation.getManyKeyColumn() +
                    " in (SELECT " + relation.getManyKeyColumn() + " FROM " + relation.getRelationTable() +
                    " where " + relation.getOneKeyColumn() + " = '" + relation.getOneKey() + "');";

            ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);

            relation.setAttributes(resultSet);
        } catch (NoSuchFieldException | IllegalAccessException | ModelNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet findSubstring(Class<? extends Model> m, String str) throws NoSuchFieldException, IllegalAccessException, SQLException {
        String table = (String) m.getDeclaredField("TABLE").get(null);
        String primary = (String) m.getDeclaredField("COLUMN_PRIMARY_KEY").get(null);

        String sqlStatement = "SELECT * FROM " + table + " WHERE " + primary + " like " + "\"%" + str + "%\";";

        ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);

        return resultSet;
    }

    @Override
    public void setModel(Model model) throws SQLException {

        String sqlStatement = "INSERT INTO " + model.getTable() + " (" + model.getRelationSchema() + ") VALUES (" + model.getValues() + ");";

        System.out.println(sqlStatement);

        databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
    }

    @Override
    public void updateModel(Model model) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public void deleteModel(Model model) throws SQLException {
        // TODO Auto-generated method stub
    }
}
