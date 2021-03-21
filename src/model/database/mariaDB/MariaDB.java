package model.database.mariaDB;

import exceptions.ModelNotFoundException;
import model.database.Database;
import model.database.connection.DatabaseConnection;
import model.database.relations.ManyToManyA;
import model.database.relations.OneToMany;
import model.database.sqlQuerryBuilder.SQLQueryBuilder;
import model.database.tableModels.analyse.AnalyseTemperaturprogramme;
import model.database.tableModels.Model;
import model.database.tableModels.ModelTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public Model findModel(Model model) throws SQLException, ModelNotFoundException {
    	// TODO Auto-generated method stub
    	return null;
    }

    @Override
    public void getModelAnalyseTemperaturprogramme(AnalyseTemperaturprogramme requestModel) throws ModelNotFoundException, SQLException {

    }

    @Override
    public void getTable(ModelTable modelTable) throws SQLException, ModelNotFoundException {

        String sqlStatement = "SELECT * FROM " + modelTable.getTable() + ";";

        System.out.println(sqlStatement);

        ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);

        modelTable.setAttributes(resultSet);
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

    /*        SQLQueryBuilder builder = new SQLQueryBuilder();
            String state = builder.select()
                    .columns("*")
                    .from(relation.getManyTable())
                    .where(builder.in(
                            relation.getManyKeyColumn(),
                            builder.select()
                                    .columns(relation.getManyKeyColumn())
                                    .from(relation.getRelationTable())
                                    .where(builder.equals(
                                            relation.getOneKeyColumn(),
                                            relation.getOneKey()
                                    ))
                    ))
                    .build();
    */

            ResultSet resultSet = databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);

            relation.setAttributes(resultSet);
        } catch (NoSuchFieldException | IllegalAccessException | ModelNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet findSubstring(Class<? extends Model> m, String str, String... fields) throws NoSuchFieldException, IllegalAccessException, SQLException {
        String table = (String) m.getDeclaredField("TABLE").get(null);

        SQLQueryBuilder builder = new SQLQueryBuilder();
        String sqlStatement = builder
                .select()
                .columns("*")
                .from(table)
                .or(builder.contains(fields, str))
                .build();

        return databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
    }

    @Override
    public void saveModel(Model model) throws SQLException {

        String sqlStatement = "INSERT INTO " + model.getTable() + " (" + model.getRelationSchema() + ") VALUES (" + model.getValuesAsSQLString() + ");";

        System.out.println(sqlStatement);

        databaseConnection.executeSQLStatementAndReturnResults(sqlStatement);
    }

    @Override
    public void replaceModel(Model model) throws SQLException {
        String sqlStatement = "REPLACE INTO " + model.getTable() + " (" + model.getRelationSchema() + ") VALUES (" + model.getValuesAsSQLString() + ");";
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
    
    @Override
    public List<List<Model>> getDatabaseAsTupelList() {
    	// TODO Auto-generated method stub
    	return null;
    }
}
