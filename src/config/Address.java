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
}
