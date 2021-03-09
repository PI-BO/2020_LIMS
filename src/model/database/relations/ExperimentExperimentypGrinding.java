package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Experiment;
import model.database.tableModels.ExperimenttypSlurry;
import java.sql.SQLException;

public class ExperimentExperimentypGrinding extends OneToOne<Experiment, ExperimenttypSlurry> {
    public ExperimentExperimentypGrinding(Experiment parent) throws ModelNotFoundException, SQLException {
        super(parent, new ExperimenttypSlurry(parent.getPrimaryKey()));
    }
}
