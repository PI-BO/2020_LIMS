const Address = {}

Address.PATH = "http://localhost:8080/2020_LIMS";

Address.PARTNER = {
    ERSTELLEN_JSP : Address.PATH + "/partner/partner_erstellen.jsp",
    ERSTELLEN_SERVLET : Address.PATH + "/partner_erstellen_servlet",
}

Address.NAVIGATION_MENU_JSP = Address.PATH + "/navigationMenu/navigation_menu.jsp";

Address.SUCHE = {
    SERVLET : Address.PATH + "/Suche",
    JSP: Address.PATH + "/suche/suche.jsp",
}

export default Address;