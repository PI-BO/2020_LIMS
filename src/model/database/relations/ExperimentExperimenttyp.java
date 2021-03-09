package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Experiment;
import model.database.tableModels.Experimenttyp;
import model.database.tableModels.Model;

import java.sql.SQLException;

public class ExperimentExperimenttyp extends OneToOne<Experiment, Experimenttyp> {
    OneToOne typ;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";

    protected ExperimentExperimenttyp(Experiment parent) throws ModelNotFoundException, SQLException {
        super(parent, new Experimenttyp(parent.getPrimaryKey()));
        switch (getChild().getTyp()) {
            case "Verdampfung 1Lömi":
                typ = new ExperimentExperimenttypVerdampfung(parent);
                break;
            case "Slurry 1Lömi":
                typ = new ExperimentExperimentypSlurry(parent);
                break;
            case "grinding":
                typ = new ExperimentExperimentypGrinding(parent);
                break;
            default:
                throw new ModelNotFoundException("Experimenttyp not Implemented");
        }
    }

    public String getPrimaryKey() {
        return getParent().getPrimaryKey();
    }

    public String getTyp() {
        return getParent().getTyp();
    }

    public Model getTypModel() {
        return typ.getChild();
    }
}
