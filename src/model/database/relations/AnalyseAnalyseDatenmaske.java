package model.database.relations;

import config.Const;
import exceptions.ModelNotFoundException;
import model.database.tableModels.analyse.*;

import java.sql.SQLException;

public class AnalyseAnalyseDatenmaske extends OneToOne<Analyse, Analysetyp> {
    AnalyseModel typ;
    public static final String COLUMN_PRIMARY_KEY = "id";
    public static final String COLUMN_TYP = "typ";

    public AnalyseAnalyseDatenmaske(Analyse parent) throws ModelNotFoundException, SQLException {
        super(parent, new Analysetyp(parent.getTyp()));
        switch (getChild().getTyp()) {
            case Const.ANALYSE_DATENMASKE_DSC:
                typ = new AnalyseDatenmaskeDSC(parent.getPrimaryKey());
                break;
            case Const.ANALYSE_DATENMASKE_IR:
                typ = new AnalyseDatenmaskeIR(parent.getPrimaryKey());
                break;
            case Const.ANALYSE_DATENMASKE_PXRD:
                typ = new AnalyseDatenmaskePXRD(parent.getPrimaryKey());
                break;
            case Const.ANALYSE_DATENMASKE_TGA:
                typ = new AnalyseDatenmaskeTGA(parent.getPrimaryKey());
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

    public AnalyseModel getTypModel() {
        return typ;
    }
}
