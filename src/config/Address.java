package config;

public class Address {
	
	private final static String MAIN_PATH = "http://localhost:8080/2020_LIMS";
	private final static String EINGANGSANALYTIK_JSP = "/eingangsanalytik/eingangsanalytik.jsp";
	private final static String EINGANGSANALYTIK_JS = "/eingangsanalytik/eingangsanalytik.js";
	private final static String EINGANGSANALYTIK_CSS = "/eingangsanalytik/eingangsanalytik.css";
	private final static String PROBENEINGANG_JSP = "/probeneingang/probeneingang.jsp";
	private final static String EXPLORER_JSP = "/explorer/explorer.jsp";
	private final static String EXPLORER_JS = "/explorer/explorerFunctions.js";
	private final static String EXPLORER_CSS = "/explorer/explorer.css";
	private final static String PROJEKT_JSP = "/projekt/projekt.jsp";
	private final static String PROJEKTE_LIST_JSP = "/projekt/projekteList.jsp";
	private final static String NAVIGATION_MENU_JSP = "/navigation_menu.jsp";
	private final static String NAVIGATION_MENU_CSS = "/navigation_menu.css";
	private final static String EXPLORER_NAVIGATION_MENU_JSP = "/main/explorer_navigation_menu.jsp";
	private final static String MAIN_JSP = "/main.jsp";
	private final static String MAIN_CSS = "/main.css";
	private final static String LOGIN_HTML = "/login.html";
	private final static String EXPERIMENT_ERSTELLEN_JSP = "/experiment/experiment_erstellen.jsp";
	private final static String EXPERIMENT_ERSTELLEN_CSS = "/experiment/experiment_erstellen.css";
	private final static String EXPERIMENT_ERSTELLEN_JS = "/experiment/experiment_erstellen.js";
	private final static String SUBSTANZ_ERSTELLEN_JSP = "/substanz/substanz_erstellen.jsp";
	private final static String PROJEKT_ERSTELLEN_JSP = "/projekt/projekt_erstellen.jsp";
	private final static String PROJEKT_PARTNER_ERSTELLEN_JSP = "/projektpartner/partner_erstellen.jsp";
	private static final String EXPERIMENT_ERSTELLEN_SERVLET = "/experiment/erstellen";
	private static final String ANALYSE_ERSTELLEN_JSP = "/analyse/analyse_erstellen.jsp";
	private static final String ANALYSE_ERSTELLEN_CSS = "/analyse/analyse_erstellen.css";
	private static final String ANALYSE_ERSTELLEN_JS = "/analyse/analyse_erstellen.js";
	private static final String ANALYSE_ERSTELLEN_SERVLET = "/analyse/erstellen";


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
	
	public static String getProjektJSP() {
		return MAIN_PATH + PROJEKT_JSP;
	}
	
	public static String getProjekteListJSP() {
		return MAIN_PATH + PROJEKTE_LIST_JSP;
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

	public static String getSubstanzErstellenJsp() {
		return MAIN_PATH + SUBSTANZ_ERSTELLEN_JSP;
	}
	
	public static String getProjektPartnerErstellenJsp() {
		return MAIN_PATH + PROJEKT_PARTNER_ERSTELLEN_JSP;
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
}
