package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Experiment;
import model.database.tableModels.Probe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProbeExperiment extends OneToMany<Probe, Experiment>{
    private List<Experiment> experimente;

    public ProbeExperiment(Probe one) throws ModelNotFoundException, SQLException {
        super(one, Experiment.class);
        database.resolveOneToMany(this);
    }

    @Override
    public List<String> getManyKeys() {
        return experimente.stream().map(Experiment::getPrimaryKey).collect(Collectors.toList());
    }

    @Override
    public void setAttributes(ResultSet resultSet) throws SQLException, ModelNotFoundException, NoSuchFieldException, IllegalAccessException {
        experimente = new LinkedList<>();

        while (resultSet.next()) {
            int experinentIdIndex = resultSet.findColumn(getManyKeyColumn());
            String experimentId = resultSet.getString(experinentIdIndex);
            Experiment experiment = new Experiment(experimentId);

            experimente.add(experiment);
        }
    }

    public List<Experiment> getExperimente() {
        return experimente;
    }
}
