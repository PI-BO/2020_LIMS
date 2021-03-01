package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Experimenttyp;
import model.database.tableModels.ExperimenttypSlurry;

import java.sql.SQLException;

public class ExperimenttypExperimentypGrinding extends OneToOne<Experimenttyp, ExperimenttypSlurry> {
    public ExperimenttypExperimentypGrinding(Experimenttyp parent) throws ModelNotFoundException, SQLException {
        super(parent, new ExperimenttypSlurry(parent.getPrimaryKey()));
    }
}
