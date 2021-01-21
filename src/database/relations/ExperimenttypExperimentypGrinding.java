package database.relations;

import database.model.Experimenttyp;
import database.model.ExperimenttypSlurry;
import exceptions.ModelNotFoundException;

import java.sql.SQLException;

public class ExperimenttypExperimentypGrinding extends OneToOne<Experimenttyp, ExperimenttypSlurry> {
    public ExperimenttypExperimentypGrinding(Experimenttyp parent) throws ModelNotFoundException, SQLException {
        super(parent, new ExperimenttypSlurry(parent.getPrimaryKey()));
    }
}
