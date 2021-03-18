package controller.servlets;

import config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/analyse/erstellen")
public class AnalyseServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Config.class.getName());

    public static final String TYP = "ANALYSE_TYP";
}
