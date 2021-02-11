package database.relations;

import database.model.Eigenschaften;
import database.model.Experiment;
import database.model.Gefahrensymbol;
import exceptions.ModelNotFoundException;

import java.sql.SQLException;

/**
 * @author Narwutsch Dominic
 * created on 11.02.2021
 */
public class EigenschaftenGefahrensymbole extends OneToOne<Eigenschaften, Gefahrensymbol> {
    protected EigenschaftenGefahrensymbole(Eigenschaften eigenschaft) throws ModelNotFoundException, SQLException {
        super(eigenschaft, new Gefahrensymbol(eigenschaft.getPrimaryKey()));
    }
}
