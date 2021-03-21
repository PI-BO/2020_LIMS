package model.database.relations;

import exceptions.ModelNotFoundException;
import model.database.tableModels.experimente.Experiment;
import model.database.tableModels.experimente.ExperimenttypVerdampfung;

import java.sql.SQLException;

/**
 * @author Narwutsch Dominic
 * created on 08.03.2021
 */
public class ExperimentExperimenttypVerdampfung extends OneToOne<Experiment, ExperimenttypVerdampfung> {
    public ExperimentExperimenttypVerdampfung(Experiment parent) throws ModelNotFoundException, SQLException {
        super(parent, new ExperimenttypVerdampfung(parent.getPrimaryKey()));
    }
}
