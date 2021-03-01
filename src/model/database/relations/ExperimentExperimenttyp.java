package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.Experiment;
import model.database.tableModels.Experimenttyp;
import model.database.tableModels.Model;

import java.sql.SQLException;

public class ExperimentExperimenttyp extends OneToOne<Experiment, Experimenttyp>{
    OneToOne typ;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";

    protected ExperimentExperimenttyp(Experiment parent) throws ModelNotFoundException, SQLException {
        super(parent, new Experimenttyp(parent.getPrimaryKey()));
        switch (getChild().getTyp()) {
                case "slurry": typ = new ExperimenttypExperimentypSlurry(getChild());
                break;
                case "grinding": typ = new ExperimenttypExperimentypGrinding(getChild());
                break;
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
