package model.database.relations;

import config.Const;
import exceptions.ModelNotFoundException;
import model.database.tableModels.experimente.Experiment;
import model.database.tableModels.experimente.ExperimenteModel;
import model.database.tableModels.experimente.Experimenttyp;
import model.database.tableModels.Model;

import java.sql.SQLException;

public class ExperimentExperimenttyp extends OneToOne<Experiment, Experimenttyp> {
    OneToOne typ;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";

    public ExperimentExperimenttyp(Experiment parent) throws ModelNotFoundException, SQLException {
        super(parent, new Experimenttyp(parent.getTyp()));
        switch (getChild().getTyp()) {
            case Const.EXPERIMENT_TYP_VERDAMPFUNG:
                typ = new ExperimentExperimenttypVerdampfung(parent);
                break;
            case Const.EXPERIMENT_TYP_SLURRY:
                typ = new ExperimentExperimentypSlurry(parent);
                break;
            case "Grinding":
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

    public ExperimenteModel getTypModel() {
        return (ExperimenteModel) typ.getChild();
    }
}
