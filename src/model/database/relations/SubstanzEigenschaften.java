package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Eigenschaften;
import model.database.tableModels.Experiment;
import model.database.tableModels.Model;
import model.database.tableModels.Substanz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Narwutsch Dominic
 * created on 10.02.2021
 */
public class SubstanzEigenschaften extends ManyToManyA<Substanz, Eigenschaften> {
    private List<Eigenschaften> eigenschaften;

    protected SubstanzEigenschaften(Substanz substanz, Class<Eigenschaften> m, Model relationTable) throws ModelNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException {
        super(substanz, m, relationTable);
        database.resolveManyToMany(this);
    }

    @Override
    public List<String> getManyKeys() {
        return eigenschaften.stream().map(Eigenschaften::getPrimaryKey).collect(Collectors.toList());
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException {
        eigenschaften = new LinkedList<>();

        while (resultSet.next()) {
            int eigenschaftenIdIndex = resultSet.findColumn(getManyKeyColumn());
            String eigenschafttId = resultSet.getString(eigenschaftenIdIndex);
            Eigenschaften eigenschaft = new Eigenschaften(eigenschafttId);

            eigenschaften.add(eigenschaft);
        }
    }

    public List<Eigenschaften> getEigenschaften() {
        return eigenschaften;
    }

}
