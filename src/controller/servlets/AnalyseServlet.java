package controller.servlets;

import config.Config;
import exceptions.ModelNotFoundException;
import model.database.tableModels.analyse.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;

@WebServlet("/analyse/erstellen")
public class AnalyseServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Config.class.getName());

    public static final String TYP = "ANALYSE_TYP";
    public static final String ANALYSE_ID = "ANALYSE_ANALYSE_ID";
    public static final String EXPERIMENT_ID = "ANALYSE_EXPERIMENT_ID";
    public static final String DATUM = "ANALYSE_DATUM";
    public static final String BEMERKUNG = "ANALYSE_BEMERKUNG";
    public static final String OPERATOR = "ANALYSE_OPERATOR";

    // Datenmaske PXRD
    public static final String GERAET = "ANALYS_GERAET";
    public static final String PROBENPRAEPARATION = "ANALYSE_PROBENPRAEPARATION";
    public static final String POSITION = "ANALYSE_POSIOTION";
    public static final String PROGRAMM = "ANALYSE_PROGRAMM";
    public static final String MESSZEIT = "ANALYSE_MESSZEIT";

    // Datenmaske DSC
    public static final String AUSWAAGE_MG = "ANALYSE_AUSWAAGE_MG";
    public static final String TIEGEL = "ANALYSE_TIEGEL";
    public static final String TIEGELPRAESENTATION = "ANALYSE_TIEGELPRAESENTATION";

    // Datenmaske DSC & TGA
    public static final String EINWAAGE_MG = "ANALYSE_EINWAAGE_MG";
    public static final String RAMPE_K_MIN = "ANALYSE_RAMPE_K_MIN";
    public static final String TEMPERATURPROGRAMM = "ANALYSE_TEMPERATURPROGRAMM";

    // Datenmaske IR
    public static final String SCANS = "ANALYSE_SCANS";
    public static final String AUFLOESUNG = "ANALYSE_AUFLOESUNG";
    public static final String GEOMETRIE = "ANALYSE_GEOMETRIE";
    public static final String PRAEPARATION = "ANALYSE_PREAPARATION";
    public static final String BACKGROUND = "ANALYSE_BACKGROUND";

    // Temperaturprogramm
    public static final String TEMPERATURPROGRAMM_TITEL = "ANALYSE_TEMPERATURPROGRAMM_TITEL";
    public static final String TEMPERATURPROGRAMM_SCHRITT = "ANALYSE_TEMPERATURPROGRAMM_SCHRITT";
    public static final String TEMPERATURPROGRAMM_TEMPERATUR = "ANALYSE_TEMPERATURPROGRAMM_TEMPERATUR";
    public static final String TEMPERATURPROGRAMM_RAMPE = "ANALYSE_TEMPERATURPROGRAMM_RAMPE";
    public static final String TEMPERATURPROGRAMM_ZEIT = "ANALYSE_TEMPERATURPROGRAMM_ZEIT";
    public static final String TEMPERATURPROGRAMM_SEGMENTTYP = "ANALYSE_TEMPERATURPROGRAMM_SEGMENTTYP";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        System.out.println(this.getClass() + ": doPost()");

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
            throwables.printStackTrace();
        } catch (IllegalStateException ise) {
            LOGGER.error(ise.getMessage());
        }
    }

    private void createAnalyse(String primaryKey, String experiment, String typ) throws SQLException {
        Analyse analyse = new Analyse();
        analyse.setPrimaryKey(primaryKey);
        analyse.setExperimentId(experiment);
        analyse.setTyp(typ);
        analyse.saveToDatabase();
    }

    private AnalyseModel setBaseFields(String parameterName, String parameter, AnalyseModel model) {
        switch (parameterName) {
            case ANALYSE_ID:
                model.setPrimaryKey(Integer.parseInt(parameter));
                break;
            case DATUM:
                model.setDatum(Date.valueOf(parameter));
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
                    pxrd.setPosition(Integer.parseInt(parameter));
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

    private AnalyseDatenmaskeDSC createDatenmaskeDSC(HttpServletRequest request) throws SQLException {
        AnalyseDatenmaskeDSC dsc = new AnalyseDatenmaskeDSC();

        Enumeration<String> parameterNames = request.getParameterNames();

        String parameterName;

        while (parameterNames.hasMoreElements()) {

            parameterName = parameterNames.nextElement();

            if (parameterName == null) continue;

            String parameter = request.getParameter(parameterName);

            switch (parameterName) {
                case EINWAAGE_MG:
                    dsc.setEinwaage(Double.valueOf(parameter));
                    break;
                case AUSWAAGE_MG:
                    dsc.setAuswaage(Double.valueOf(parameter));
                    break;
                case RAMPE_K_MIN:
                    dsc.setRampe(Integer.valueOf(parameter));
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
                    tga.setEinwaage(Double.valueOf(parameter));
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
                    ir.setScans(Integer.valueOf(parameter));
                    break;
                case AUFLOESUNG:
                    ir.setAufloesung(Integer.valueOf(parameter));
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
