package database.relations;

import exceptions.ModelNotFoundException;

import java.sql.SQLException;

import database.model.Experimenttyp;
import database.model.ExperimenttypSlurry;

public class ExperimenttypExperimentypSlurry extends OneToOne<Experimenttyp, ExperimenttypSlurry> {
    public ExperimenttypExperimentypSlurry(Experimenttyp parent) throws ModelNotFoundException, SQLException {
        super(parent, new ExperimenttypSlurry(parent.getPrimaryKey()));
    }

    public ExperimenttypSlurry getChild() {
        return super.getChild();
    }
}
