package model.database.relations;

import exceptions.ModelNotFoundException;
import model.Gefahrensymbol;
import model.database.tableModels.Eigenschaften;

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
