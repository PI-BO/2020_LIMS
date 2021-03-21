package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.experimente.Experiment;
import model.database.tableModels.experimente.ExperimenttypSlurry;

import java.sql.SQLException;

public class ExperimentExperimentypSlurry extends OneToOne<Experiment, ExperimenttypSlurry> {
    public ExperimentExperimentypSlurry(Experiment parent) throws ModelNotFoundException, SQLException {
        super(parent, new ExperimenttypSlurry(parent.getPrimaryKey()));
    }

    public ExperimenttypSlurry getChild() {
        return super.getChild();
    }
}
