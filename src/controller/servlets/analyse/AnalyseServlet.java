package controller.servlets.analyse;

import config.Config;
import exceptions.DublicateModelException;
import exceptions.ModelNotFoundException;
import model.database.relations.AnalyseAnalyseDatenmaske;
import model.database.tableModels.analyse.*;
import utility.JSON;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet(AnalyseServlet.ROUTE)
@MultipartConfig
public class AnalyseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 5831798414057815251L;
	private static final Logger LOGGER = LogManager.getLogger(Config.class.getName());
    public static final String ROUTE = "/analyse";

    public static final String TYP = "ANALYSE_" + Analyse.COLUMN_TYP;
    public static final String ANALYSE_ID = "ANALYSE_" + AnalyseModel.COLUMN_PRIMARY_KEY;
    public static final String EXPERIMENT_ID = "ANALYSE_" + Analyse.COLUMN_EXPERIMENT_ID;
    public static final String DATUM = "ANALYSE_" + AnalyseModel.COLUMN_DATUM;
    public static final String BEMERKUNG = "ANALYSE_" + AnalyseModel.COLUMN_BEMERKUNG;
    public static final String OPERATOR = "ANALYSE_" + AnalyseModel.COLUMN_OPERATOR;

    // Datenmaske PXRD
    public static final String GERAET = "ANALYSE_" + AnalyseDatenmaskePXRD.COLUMN_GERAET;
    public static final String PROBENPRAEPARATION = "ANALYSE_" + AnalyseDatenmaskePXRD.COLUMN_PRAEPARATION;
    public static final String POSITION = "ANALYSE_" + AnalyseDatenmaskePXRD.COLUMN_POSITION;
    public static final String PROGRAMM = "ANALYSE_" + AnalyseDatenmaskePXRD.COLUMN_PROGRAMM;
    public static final String MESSZEIT = "ANALYSE_" + AnalyseDatenmaskePXRD.COLUMN_MESSZEIT;

    // Datenmaske DSC
    public static final String AUSWAAGE_MG = "ANALYSE_" + AnalyseDatenmaskeDSC.COLUMN_AUSWAAGE;
    public static final String TIEGEL = "ANALYSE_" + AnalyseDatenmaskeDSC.COLUMN_TIEGEL;
    public static final String TIEGELPRAESENTATION = "ANALYSE_" + AnalyseDatenmaskeDSC.COLUMN_TIEGELPRAEPARATION;

    // Datenmaske DSC & TGA
    public static final String EINWAAGE_MG = "ANALYSE_" + AnalyseDatenmaskeDscTgaConst.COLUMN_EINWAAGE;
    public static final String RAMPE_K_MIN = "ANALYSE_" + AnalyseDatenmaskeDscTgaConst.COLUMN_RAMPE;
    public static final String TEMPERATURPROGRAMM = "ANALYSE_" + AnalyseDatenmaskeDscTgaConst.COLUMN_TEMPERATURPROGRAMM;

    // Datenmaske IR
    public static final String SCANS = "ANALYSE_" + AnalyseDatenmaskeIR.COLUMN_SCANS;
    public static final String AUFLOESUNG = "ANALYSE_" + AnalyseDatenmaskeIR.COLUMN_AUFLOESUNG;
    public static final String GEOMETRIE = "ANALYSE_" + AnalyseDatenmaskeIR.COLUMN_GEOMETRIE;
    public static final String PRAEPARATION = "ANALYSE_" + AnalyseDatenmaskeIR.COLUMN_PRAEPARATION;
    public static final String BACKGROUND = "ANALYSE_" + AnalyseDatenmaskeIR.COLUMN_BACKGROUND;

    // Temperaturprogramm
    public static final String TEMPERATURPROGRAMM_TITEL = "ANALYSE_TEMPERATURPROGRAMM_" + AnalyseTemperaturprogramme.COLUMN_PRIMARY_KEY;
    public static final String TEMPERATURPROGRAMM_SCHRITT = "ANALYSE_TEMPERATURPROGRAMM_" + AnalyseTemperaturprogramme.COLUMN_SCHRITT;
    public static final String TEMPERATURPROGRAMM_TEMPERATUR = "ANALYSE_TEMPERATURPROGRAMM_" + AnalyseTemperaturprogramme.COLUMN_TEMPERATUR;
    public static final String TEMPERATURPROGRAMM_RAMPE = "ANALYSE_TEMPERATURPROGRAMM_" + AnalyseTemperaturprogramme.COLUMN_RAMPE;
    public static final String TEMPERATURPROGRAMM_ZEIT = "ANALYSE_TEMPERATURPROGRAMM_" + AnalyseTemperaturprogramme.COLUMN_ZEIT;
    public static final String TEMPERATURPROGRAMM_SEGMENTTYP = "ANALYSE_TEMPERATURPROGRAMM_" + AnalyseTemperaturprogramme.COLUMN_SEGENTTYP;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	LOGGER.debug("doPost()");

        try {
            String typId = request.getParameter(TYP);
            String experiment = request.getParameter(EXPERIMENT_ID);
            Analysetyp typ = new Analysetyp(typId);
            AnalyseModel analyse;
            switch (typ.getTyp()) {
                case "PXRD":
                    analyse = createDatenmaskePXRD(request);
                    break;
                case "DSC":
                    analyse = createDatenmaskeDSC(request);
                    break;
                case "TGA":
                    analyse = createAnalyseTGA(request);
                    break;
                case "IR":
                    analyse = createAnalyseIR(request);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + typ.getTyp());
            }
            analyse.saveToDatabase();
            createAnalyse(analyse.getPrimaryKey(), experiment, typ.getPrimaryKey());
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            response.getWriter().println(throwables);
        } catch (IllegalStateException ise) {
            LOGGER.error(ise.getMessage());
        }
		catch (DublicateModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	LOGGER.debug("doGet()");
    	
        String primaryKey = req.getParameter("id");
        JSON json;

        if (primaryKey != null && !primaryKey.isEmpty())
            try {
                Analyse analyse = new Analyse(primaryKey);
                AnalyseModel analyseModel = new AnalyseAnalyseDatenmaske(analyse).getTypModel();

                if (analyseModel instanceof AnalyseDatenmaskeDSC) {
                    AnalyseDatenmaskeDSC dsc = (AnalyseDatenmaskeDSC) analyseModel;
                    json = dsc.toJSON();
                } else if (analyseModel instanceof AnalyseDatenmaskeIR) {
                    AnalyseDatenmaskeIR ir = (AnalyseDatenmaskeIR) analyseModel;
                    json = ir.toJSON();
                } else if (analyseModel instanceof AnalyseDatenmaskePXRD) {
                    AnalyseDatenmaskePXRD pxrd = (AnalyseDatenmaskePXRD) analyseModel;
                    json = pxrd.toJSON();
                } else if (analyseModel instanceof AnalyseDatenmaskeTGA) {
                    AnalyseDatenmaskeTGA tga = (AnalyseDatenmaskeTGA) analyseModel;
                    json = tga.toJSON();
                } else {
                    throw new ModelNotFoundException("");
                }

                json.addKeyValue(Analyse.COLUMN_EXPERIMENT_ID, analyse.getExperimentId());

                resp.setCharacterEncoding("utf-8");
                resp.setContentType("application/json;charset=utf-8");
                resp.getWriter().print(json);

            } catch (ModelNotFoundException e) {
                //e.printStackTrace();
            } catch (SQLException throwables) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.setContentType("text/plain");
                resp.getWriter().println(throwables);
            }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	LOGGER.debug("doPut()");
    	
        try {
            AnalyseModel analyseModel = updateAnalyseTyp(request);
            analyseModel.updateModel();
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            response.getWriter().println(throwables);
        }
        catch (DublicateModelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Analyse updateAnalyse(HttpServletRequest request) throws SQLException, ModelNotFoundException {
        String primaryKey = request.getParameter(ANALYSE_ID);
        String api = request.getParameter(EXPERIMENT_ID);
        Analyse analyse = new Analyse(primaryKey);
        analyse.setExperimentId(api);
        analyse.updateModel();
        return analyse;
    }
    
    private AnalyseModel updateAnalyseTyp(HttpServletRequest request) throws ModelNotFoundException, SQLException, DublicateModelException {
        Analyse analyse = updateAnalyse(request);
        AnalyseModel analyseModel = new AnalyseAnalyseDatenmaske(analyse).getTypModel();

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {
            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case DATUM:
                    try {
                        analyseModel.setDatum(Date.valueOf(parameter));
                    } catch (IllegalArgumentException e) {
                        analyseModel.setDatum(null);
                    }
                    break;
                case BEMERKUNG:
                    analyseModel.setBemerkung(parameter);
                    break;
                case OPERATOR:
                    analyseModel.setOperator(parameter);
                    break;
            }
        }

        if (analyseModel instanceof AnalyseDatenmaskeDSC) {
            return createDatenmaskeDSC(request, analyseModel);
        } else if (analyseModel instanceof AnalyseDatenmaskePXRD) {
            return createDatenmaskePXRD(request, analyseModel);
        } else if (analyseModel instanceof AnalyseDatenmaskeIR) {
            return createAnalyseIR(request, analyseModel);
        } else if (analyseModel instanceof AnalyseDatenmaskeTGA) {
            return createAnalyseTGA(request, analyseModel);
        } else return analyseModel;
    }
    
    private void createAnalyse(String primaryKey, String experiment, String typ) throws SQLException, DublicateModelException, ModelNotFoundException {
        Analyse analyse = new Analyse();
        analyse.setPrimaryKey(primaryKey);
        analyse.setExperimentId(experiment);
        analyse.setTyp(typ);
        analyse.saveToDatabase();
    }

    private AnalyseModel setBaseFields(String parameterName, String parameter, AnalyseModel model) {
        switch (parameterName) {
            case ANALYSE_ID:
                model.setPrimaryKey(parameter);
                break;
            case DATUM:
                try {
                    model.setDatum(Date.valueOf(parameter));
                } catch (IllegalArgumentException e) {
                    model.setDatum(null);
                }
                break;
            case BEMERKUNG:
                model.setBemerkung(parameter);
                break;
            case OPERATOR:
                model.setOperator(parameter);
                break;
        }
        return model;
    }
    

    private String createTemperaturprogramm(HttpServletRequest request, String parameter) throws SQLException {
        String[] schritte = request.getParameterValues(TEMPERATURPROGRAMM_SCHRITT);
        String[] temperatur = request.getParameterValues(TEMPERATURPROGRAMM_TEMPERATUR);
        String[] rampe = request.getParameterValues(TEMPERATURPROGRAMM_RAMPE);
        String[] zeit = request.getParameterValues(TEMPERATURPROGRAMM_ZEIT);
        String[] segmenttyp = request.getParameterValues(TEMPERATURPROGRAMM_SEGMENTTYP);
        String table = (parameter.equals("new")) ? request.getParameter(TEMPERATURPROGRAMM_TITEL) : parameter;
        for (int i = 0; i < schritte.length - 1; i++) {
            AnalyseTemperaturprogramme t = new AnalyseTemperaturprogramme();
            t.setTabelle(table);
            t.setSchritt(schritte[i]);
            t.setTemperatur(temperatur[i]);
            t.setRampe(rampe[i]);
            t.setZeit(zeit[i]);
            t.setSegmenttyp(segmenttyp[i]);
            t.saveToDatabase();
        }

        return table;
    }

    private AnalyseDatenmaskePXRD createDatenmaskePXRD(HttpServletRequest request, AnalyseModel model) {
        AnalyseDatenmaskePXRD pxrd = (AnalyseDatenmaskePXRD) model;

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case GERAET:
                    pxrd.setGeraet(parameter);
                    break;
                case PROBENPRAEPARATION:
                    pxrd.setPraeparation(parameter);
                    break;
                case POSITION:
                    try {
                        pxrd.setPosition(Integer.parseInt(parameter));
                    } catch (NumberFormatException e) {
                        pxrd.setPosition(null);
                    }
                    break;
                case PROGRAMM:
                    pxrd.setProgramm(parameter);
                    break;
                case MESSZEIT:
                    pxrd.setMesszeit(parameter);
                    break;
            }
        }
        return pxrd;
    }

    private AnalyseDatenmaskeDSC createDatenmaskeDSC(HttpServletRequest request, AnalyseModel model) throws SQLException, ModelNotFoundException {
        AnalyseDatenmaskeDSC dsc = (AnalyseDatenmaskeDSC) model;

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case EINWAAGE_MG:
                    try {
                        dsc.setEinwaage(Double.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        dsc.setEinwaage(null);
                    }
                    break;
                case AUSWAAGE_MG:
                    try {
                        dsc.setAuswaage(Double.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        dsc.setAuswaage(null);
                    }
                    break;
                case RAMPE_K_MIN:
                    try {
                        dsc.setRampe(Integer.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        dsc.setRampe(null);
                    }
                    break;
                case TEMPERATURPROGRAMM:
                    dsc.setTemperaturprgramm(createTemperaturprogramm(request, parameter));
                    break;
                case TIEGEL:
                    dsc.setTiegel(parameter);
                    break;
                case TIEGELPRAESENTATION:
                    dsc.setTiegelpaeparation(parameter);
                    break;
            }
        }
        return dsc;
    }

    private AnalyseDatenmaskeTGA createAnalyseTGA(HttpServletRequest request, AnalyseModel model) throws SQLException {
        AnalyseDatenmaskeTGA tga = (AnalyseDatenmaskeTGA) model;

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case EINWAAGE_MG:
                    try {
                        tga.setEinwaage(Double.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        tga.setEinwaage(null);
                    }
                    break;
                case RAMPE_K_MIN:
                    tga.setRampe(Integer.valueOf(parameter));
                    break;
                case TEMPERATURPROGRAMM:
                    tga.setTemperaturprgramm(createTemperaturprogramm(request, parameter));
                    break;
            }
        }
        return tga;
    }

    private AnalyseDatenmaskeIR createAnalyseIR(HttpServletRequest request, AnalyseModel model) {
        AnalyseDatenmaskeIR ir = (AnalyseDatenmaskeIR) model;

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case SCANS:
                    try {
                        ir.setScans(Integer.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        ir.setScans(null);
                    }
                    break;
                case AUFLOESUNG:
                    try {
                        ir.setAufloesung(Integer.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        ir.setAufloesung(null);
                    }
                    break;
                case GEOMETRIE:
                    ir.setGeometrie(parameter);
                    break;
                case PRAEPARATION:
                    ir.setPraeparation(parameter);
                    break;
                case BACKGROUND:
                    ir.setBackground(parameter);
                    break;
            }
        }
        return ir;
    }

    private AnalyseDatenmaskePXRD createDatenmaskePXRD(HttpServletRequest request) {
        AnalyseDatenmaskePXRD pxrd = new AnalyseDatenmaskePXRD();

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case GERAET:
                    pxrd.setGeraet(parameter);
                    break;
                case PROBENPRAEPARATION:
                    pxrd.setPraeparation(parameter);
                    break;
                case POSITION:
                    try {
                        pxrd.setPosition(Integer.parseInt(parameter));
                    } catch (NumberFormatException e) {
                        pxrd.setPosition(null);
                    }
                    break;
                case PROGRAMM:
                    pxrd.setProgramm(parameter);
                    break;
                case MESSZEIT:
                    pxrd.setMesszeit(parameter);
                    break;
                default:
                    pxrd = (AnalyseDatenmaskePXRD) setBaseFields(parameterName, parameter, pxrd);
            }
        }
        return pxrd;
    }

    private AnalyseDatenmaskeDSC createDatenmaskeDSC(HttpServletRequest request) throws SQLException, ModelNotFoundException {
        AnalyseDatenmaskeDSC dsc = new AnalyseDatenmaskeDSC();

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case EINWAAGE_MG:
                    try {
                        dsc.setEinwaage(Double.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        dsc.setEinwaage(null);
                    }
                    break;
                case AUSWAAGE_MG:
                    try {
                        dsc.setAuswaage(Double.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        dsc.setAuswaage(null);
                    }
                    break;
                case RAMPE_K_MIN:
                    try {
                        dsc.setRampe(Integer.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        dsc.setRampe(null);
                    }
                    break;
                case TEMPERATURPROGRAMM:
                    dsc.setTemperaturprgramm(createTemperaturprogramm(request, parameter));
                    break;
                case TIEGEL:
                    dsc.setTiegel(parameter);
                    break;
                case TIEGELPRAESENTATION:
                    dsc.setTiegelpaeparation(parameter);
                    break;
                default:
                    dsc = (AnalyseDatenmaskeDSC) setBaseFields(parameterName, parameter, dsc);
            }
        }
        return dsc;
    }

    private AnalyseDatenmaskeTGA createAnalyseTGA(HttpServletRequest request) throws SQLException {
        AnalyseDatenmaskeTGA tga = new AnalyseDatenmaskeTGA();

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case EINWAAGE_MG:
                    try {
                        tga.setEinwaage(Double.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        tga.setEinwaage(null);
                    }
                    break;
                case RAMPE_K_MIN:
                    tga.setRampe(Integer.valueOf(parameter));
                    break;
                case TEMPERATURPROGRAMM:
                    tga.setTemperaturprgramm(createTemperaturprogramm(request, parameter));
                    break;
                default:
                    tga = (AnalyseDatenmaskeTGA) setBaseFields(parameterName, parameter, tga);
            }
        }
        return tga;
    }

    private AnalyseDatenmaskeIR createAnalyseIR(HttpServletRequest request) {
        AnalyseDatenmaskeIR ir = new AnalyseDatenmaskeIR();

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case SCANS:
                    try {
                        ir.setScans(Integer.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        ir.setScans(null);
                    }
                    break;
                case AUFLOESUNG:
                    try {
                        ir.setAufloesung(Integer.valueOf(parameter));
                    } catch (NumberFormatException e) {
                        ir.setAufloesung(null);
                    }
                    break;
                case GEOMETRIE:
                    ir.setGeometrie(parameter);
                    break;
                case PRAEPARATION:
                    ir.setPraeparation(parameter);
                    break;
                case BACKGROUND:
                    ir.setBackground(parameter);
                    break;
                default:
                    ir = (AnalyseDatenmaskeIR) setBaseFields(parameterName, parameter, ir);
            }
        }
        return ir;
    }

}
