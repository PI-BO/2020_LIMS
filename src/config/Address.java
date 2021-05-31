package config;

import controller.servlets.analyse.AnalyseBearbeitenServlet;
import controller.servlets.analyse.AnalyseErstellenServlet;
import controller.servlets.experiment.ExperimentBearbeitenServlet;
import controller.servlets.experiment.ExperimentErstellenServlet;
import controller.servlets.partner.PartnerBearbeitenServlet;
import controller.servlets.partner.PartnerErstellenServlet;
import controller.servlets.projekt.ProjektBearbeitenServlet;
import controller.servlets.projekt.ProjektErstellenServlet;

public class Address {

	public final static String MAIN_PATH = "http://localhost:8080/2020_LIMS";
	// public final static String MAIN_PATH = "https://hbolims.herokuapp.com";

	public final static String EINGANGSANALYTIK_PATH = "/eingangsanalytik";
	public final static String EINGANGSANALYTIK_JSP = EINGANGSANALYTIK_PATH + "/eingangsanalytik.jsp";
	public final static String EINGANGSANALYTIK_JS = EINGANGSANALYTIK_PATH + "/eingangsanalytik.js";
	public final static String EINGANGSANALYTIK_CSS = EINGANGSANALYTIK_PATH + "/eingangsanalytik.css";

	public final static String ANALYSE_PATH = "/analyse";
	public final static String ANALYSE_ERSTELLEN_JSP = ANALYSE_PATH + "/analyse_erstellen.jsp";
	public final static String ANALYSE_ERSTELLEN_CSS = ANALYSE_PATH + "/analyse_erstellen.css";
	public final static String ANALYSE_ERSTELLEN_JS = ANALYSE_PATH + "/analyse_erstellen.js";
	public final static String ANALYSE_ERSTELLEN_SERVLET = AnalyseErstellenServlet.ROUTE;
	public final static String ANALYSE_DATENMASKE_PXRD_JSP = ANALYSE_PATH + "/datenmaske_pxrd.jsp";
	public final static String ANALYSE_DATENMASKE_IR_JSP = ANALYSE_PATH + "/datenmaske_ir.jsp";
	public final static String ANALYSE_DATENMASKE_DSC_JSP = ANALYSE_PATH + "/datenmaske_dsc.jsp";
	public final static String ANALYSE_DATENMASKE_TGA_JSP = ANALYSE_PATH + "/datenmaske_tga.jsp";
	public final static String ANALYSE_BEARBEITEN_SERVLET = AnalyseBearbeitenServlet.ROUTE;
	public final static String ANALYSE_BEARBEITEN_CSS = ANALYSE_PATH + "/analyse_bearbeiten.css";
	public final static String ANALYSE_BEARBEITEN_JSP = ANALYSE_PATH + "/analyse_bearbeiten.jsp";

	public final static String PROBENEINGANG_PATH = "/probe";
	public final static String PROBENEINGANG_JSP = PROBENEINGANG_PATH + "/probeneingang.jsp";
	public final static String PROBENEINGANG_ERSTELLEN_JSP = PROBENEINGANG_PATH + "/probeneingang_erstellen.jsp";
	public final static String PROBENEINGANG_BEARBEITEN_SERVLET = PROBENEINGANG_PATH + "/bearbeiten";
	public final static String PROBENEINGANG_BEARBEITEN_JSP = PROBENEINGANG_PATH + "/probeneingang_bearbeiten.jsp";
	public final static String PROBENEINGANG_JS = PROBENEINGANG_PATH + "/probeneingang.js";

	public final static String PROBE_JSP = PROBENEINGANG_PATH + "/probe.jsp";

	public final static String EXPLORER_PATH = "/explorer";
	public final static String EXPLORER_JSP = EXPLORER_PATH + "/explorer.jsp";
	public final static String EXPLORER_JS = EXPLORER_PATH + "/explorerFunctions.js";
	public final static String EXPLORER_CSS = EXPLORER_PATH + "/explorer.css";
	public final static String EXPLORER_NAVIGATION_MENU_JSP = EXPLORER_PATH + "/explorer_navigation_menu.jsp";

	public final static String PARTNER_PATH = "/partner";
	public final static String PARTNER_JSP = PARTNER_PATH + "/partner.jsp";
	public final static String PARTNER_LIST_JSP = PARTNER_PATH + "/partnerList.jsp";
	public final static String PARTNER_ERSTELLEN_JSP = PARTNER_PATH + "/partner_erstellen.jsp";
	public final static String PARTNER_BEARBEITEN_JSP = PARTNER_PATH + "/partner_bearbeiten.jsp";
	public final static String PARTNER_BEARBEITEN_SERVLET = PartnerBearbeitenServlet.ROUTE;
	public final static String PARTNER_SPEICHERN_SERVLET = PartnerErstellenServlet.ROUTE;

	public final static String PROJEKT_PATH = "/projekt";
	public final static String PROJEKT_JSP = PROJEKT_PATH + "/projekt.jsp";
	public final static String PROJEKTE_LIST_JSP = PROJEKT_PATH + "/projekteList.jsp";
	public final static String PROJEKT_ERSTELLEN_JSP = PROJEKT_PATH + "/projekt_erstellen.jsp";
	public final static String PROJEKT_BEARBEITEN_JSP = PROJEKT_PATH + "/projekt_bearbeiten.jsp";
	public final static String PROJEKT_BEARBEITEN_SERVLET = ProjektBearbeitenServlet.ROUTE;
	public final static String PROJEKT_SPEICHERN_SERVLET = ProjektErstellenServlet.ROUTE;

	public final static String EXPERIMENT_PATH = "/experiment";
	public final static String EXPERIMENT_JSP = EXPERIMENT_PATH + "/experiment.jsp";
	public final static String EXPERIMENT_ERSTELLEN_JSP = EXPERIMENT_PATH + "/experiment_erstellen.jsp";
	public final static String EXPERIMENT_ERSTELLEN_CSS = EXPERIMENT_PATH + "/experiment_erstellen.css";
	public final static String EXPERIMENT_ERSTELLEN_JS = EXPERIMENT_PATH + "/experiment_erstellen.js";
	public final static String EXPERIMENT_ERSTELLEN_SERVLET = ExperimentErstellenServlet.ROUTE;
	public final static String EXPERIMENT_BEARBEITEN_SERVLET = ExperimentBearbeitenServlet.ROUTE;
	public final static String EXPERIMENT_BEARBEITEN_JSP = EXPERIMENT_PATH + "/experiment_bearbeiten.jsp";
	public final static String EXPERIMENT_BEARBEITEN_CSS = EXPERIMENT_PATH + "/experiment_bearbeiten.css";
	public final static String EXPERIMENTTYP_JSP = EXPERIMENT_PATH + "/experimenttyp.jsp";

	public final static String NAVIGATION_MENU_PATH = "/navigationMenu";
	public final static String NAVIGATION_MENU_JSP = NAVIGATION_MENU_PATH + "/navigation_menu.jsp";
	public final static String NAVIGATION_MENU_CSS = NAVIGATION_MENU_PATH + "/navigation_menu.css";

	public final static String MAIN_JSP = "/main.jsp";
	public final static String MAIN_CSS = "/main.css";
	public final static String LOGIN_HTML = "/login.html";

	public final static String GLOBALE_SUCHE_PATH = "/suche";
	public final static String GLOBALE_SUCHE_JSP = GLOBALE_SUCHE_PATH + "/globale_suche.jsp";
	public final static String GLOBALE_SUCHE_JS = GLOBALE_SUCHE_PATH + "/GlobaleSuche.js";

	
	public static String getMainPath() {
		return MAIN_PATH;
	}

	public static String getEingangsAnalytikJSP() {
		return MAIN_PATH + EINGANGSANALYTIK_JSP;
	}

	public static String getEingangsAnalytikJS() {
		return MAIN_PATH + EINGANGSANALYTIK_JS;
	}

	public static String getEingangsAnalytikCSS() {
		return MAIN_PATH + EINGANGSANALYTIK_CSS;
	}

	public static String getProbeneingangJSP() {
		return MAIN_PATH + PROBENEINGANG_JSP;
	}

	public static String getExplorerJSP() {
		return MAIN_PATH + EXPLORER_JSP;
	}

	public static String getExplorerJS() {
		return MAIN_PATH + EXPLORER_JS;
	}

	public static String getExplorerCSS() {
		return MAIN_PATH + EXPLORER_CSS;
	}

	public static String getPartnerJSP() {
		return MAIN_PATH + PARTNER_JSP;
	}

	public static String getProjektJSP() {
		return MAIN_PATH + PROJEKT_JSP;
	}

	public static String getProbeJSP() {
		return MAIN_PATH + PROBE_JSP;
	}

	public static String getExperimentJSP() {
		return MAIN_PATH + EXPERIMENT_JSP;
	}

	public static String getProjekteListJSP() {
		return MAIN_PATH + PROJEKTE_LIST_JSP;
	}

	public static String getPartnerListJSP() {
		return MAIN_PATH + PARTNER_LIST_JSP;
	}

	public static String getNavigationMenuJSP() {
		return MAIN_PATH + NAVIGATION_MENU_JSP;
	}

	public static String getNavigationMenuRelativeJSP() {
		return NAVIGATION_MENU_JSP;
	}

	public static String getNavigationMenuCSS() {
		return MAIN_PATH + NAVIGATION_MENU_CSS;
	}

	public static String getExplorerNavigationMenuJSP() {
		return MAIN_PATH + EXPLORER_NAVIGATION_MENU_JSP;
	}

	public static String getMainJSP() {
		return MAIN_PATH + MAIN_JSP;
	}

	public static String getMainCSS() {
		return MAIN_PATH + MAIN_CSS;
	}

	public static String getMainRelativeJSP() {
		return MAIN_JSP;
	}

	public static String getMainRelativeCSS() {
		return MAIN_CSS;
	}

	public static String getLoginHtml() {
		return MAIN_PATH + LOGIN_HTML;
	}

	public static String getExperimentErstellenJsp() {
		return MAIN_PATH + EXPERIMENT_ERSTELLEN_JSP;
	}

	public static String getExperimentErstellenCss() {
		return MAIN_PATH + EXPERIMENT_ERSTELLEN_CSS;
	}

	public static String getProjektPartnerErstellenJsp() {
		return MAIN_PATH + PARTNER_ERSTELLEN_JSP;
	}

	public static String getProjektErstellenJsp() {
		return MAIN_PATH + PROJEKT_ERSTELLEN_JSP;
	}

	public static String getExperimentErstellenJS() {
		return MAIN_PATH + EXPERIMENT_ERSTELLEN_JS;
	}

	public static String getExperimentErstellenServlet() {
		return MAIN_PATH + EXPERIMENT_ERSTELLEN_SERVLET;
	}

	public static String getAnalyseErstellenServlet() {
		return MAIN_PATH + ANALYSE_ERSTELLEN_SERVLET;
	}

	public static String getAnalyseErstellenJsp() {
		return MAIN_PATH + ANALYSE_ERSTELLEN_JSP;
	}

	public static String getAnalyseErstellenCss() {
		return MAIN_PATH + ANALYSE_ERSTELLEN_CSS;
	}

	public static String getAnalyseErstellenJS() {
		return MAIN_PATH + ANALYSE_ERSTELLEN_JS;
	}

	public static String getGlobaleSucheJs() {
		return MAIN_PATH + GLOBALE_SUCHE_JS;
	}

	public static String getGlobaleSucheJsp() {
		return MAIN_PATH + GLOBALE_SUCHE_JSP;
	}

	public static String getExperimentBearbeitenServlet() {
		return MAIN_PATH + EXPERIMENT_BEARBEITEN_SERVLET;
	}

	public static String getExperimentBearbeitenJsp() {
		return MAIN_PATH + EXPERIMENT_BEARBEITEN_JSP;
	}

	public static String getExperimentTypJsp() {
		return MAIN_PATH + EXPERIMENTTYP_JSP;
	}

	public static String getExperimentBearbeitenCss() {
		return MAIN_PATH + EXPERIMENT_BEARBEITEN_CSS;
	}

	public static String getAnalyseDatenmaskePxrdJsp() {
		return MAIN_PATH + ANALYSE_DATENMASKE_PXRD_JSP;
	}

	public static String getAnalyseDatenmaskeIrJsp() {
		return MAIN_PATH + ANALYSE_DATENMASKE_IR_JSP;
	}

	public static String getAnalyseDatenmaskeDscJsp() {
		return MAIN_PATH + ANALYSE_DATENMASKE_DSC_JSP;
	}

	public static String getAnalyseDatenmaskeTgaJsp() {
		return MAIN_PATH + ANALYSE_DATENMASKE_TGA_JSP;
	}

	public static String getAnalyseBearbeitenServlet() {
		return MAIN_PATH + ANALYSE_BEARBEITEN_SERVLET;
	}

	public static String getAnalyseBearbeitenCss() {
		return MAIN_PATH + ANALYSE_BEARBEITEN_CSS;
	}

	public static String getAnalyseBearbeitenJsp() {
		return MAIN_PATH + ANALYSE_BEARBEITEN_JSP;
	}

	public static String getPartnerBearbeitenServlet() {
		return MAIN_PATH + PARTNER_BEARBEITEN_SERVLET;
	}

	public static String getPartnerBearbeitenJsp() {
		return MAIN_PATH + PARTNER_BEARBEITEN_JSP;
	}

	public static String getProjektBearbeitenServlet() {
		return MAIN_PATH + PROJEKT_BEARBEITEN_SERVLET;
	}

	public static String getProjektBearbeitenJsp() {
		return MAIN_PATH + PROJEKT_BEARBEITEN_JSP;
	}

	public static String getProbeneingangBearbeitenServlet() {
		return MAIN_PATH + PROBENEINGANG_BEARBEITEN_SERVLET;
	}

	public static String getProbeneingangBearbeitenJsp() {
		return MAIN_PATH + PROBENEINGANG_BEARBEITEN_JSP;
	}

	public static String getProbeneingangJS() {
		return MAIN_PATH + PROBENEINGANG_JS;
	}

	public static String getPartnerSpeichernServlet() {
		return PARTNER_SPEICHERN_SERVLET;
	}
}
