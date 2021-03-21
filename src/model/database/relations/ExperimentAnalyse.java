package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.analyse.Analyse;
import model.database.tableModels.experimente.Experiment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ExperimentAnalyse extends OneToMany<Experiment, Analyse> {
    private List<Analyse> analyses;

    public ExperimentAnalyse(Experiment one) throws ModelNotFoundException, SQLException {
        super(one, Analyse.class);
        database.resolveOneToMany(this);
    }

    @Override
    public List<String> getManyKeys() {
        return analyses.stream().map(Analyse::getPrimaryKey).collect(Collectors.toList());
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException {
        analyses = new LinkedList<>();

        while (resultSet.next()) {
            int analyseIdIndex = resultSet.findColumn(getManyKeyColumn());
            String analyseId = resultSet.getString(analyseIdIndex);
            Analyse analyse = new Analyse(analyseId);

            analyses.add(analyse);
        }
    }

    public List<Analyse> getAnalysen() {
        return analyses;
    }

}
