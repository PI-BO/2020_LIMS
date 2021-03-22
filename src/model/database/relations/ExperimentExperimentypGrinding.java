package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.experimente.Experiment;
import model.database.tableModels.experimente.ExperimenttypGrinding;
import java.sql.SQLException;

public class ExperimentExperimentypGrinding extends OneToOne<Experiment, ExperimenttypGrinding> {
    public ExperimentExperimentypGrinding(Experiment parent) throws ModelNotFoundException, SQLException {
        super(parent, new ExperimenttypGrinding(parent.getPrimaryKey()));
    }

    public ExperimenttypGrinding getChild() {
        return super.getChild();
    }

}
