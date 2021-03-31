package model.database.tableModels.analyse;

import exceptions.ModelNotFoundException;

import java.sql.SQLException;

public abstract class AnalyseDatenmaskeDscTgaConst extends AnalyseModel {
    public static final String COLUMN_EINWAAGE = "einwaage";
    public static final String COLUMN_RAMPE = "rammpe";
    public static final String COLUMN_TEMPERATURPROGRAMM = "temperaturprogramm";

    protected AnalyseDatenmaskeDscTgaConst() {
        super();
    }

    protected AnalyseDatenmaskeDscTgaConst(String primaryKey) throws ModelNotFoundException, SQLException {
        super(primaryKey);
    }
}
