const Address = {}

Address.PATH = "http://localhost:8080/2020_LIMS";

Address.PARTNER = {
    ERSTELLEN_JSP : Address.PATH + "/partner/partner.jsp",
    ERSTELLEN_SERVLET : Address.PATH + "/partner_erstellen_servlet",
    BEARBEITEN_SERVLET : Address.PATH + "/partner/bearbeiten",
}

Address.PROJEKT = {
    ERSTELLEN_JSP : Address.PATH + "/projekt/projekt.jsp",
    ERSTELLEN_SERVLET : Address.PATH + "/projekt_erstellen_servlet",
    BEARBEITEN_SERVLET : Address.PATH + "/projekt/bearbeiten",
}

Address.PROBE = {
    ERSTELLEN_JSP : Address.PATH + "/probe/probeneingang.jsp",
    ERSTELLEN_SERVLET : Address.PATH + "/probeneingang/erstellen",
    BEARBEITEN_SERVLET : Address.PATH + "/probeneingang/bearbeiten",
}

Address.NAVIGATION_MENU_JSP = Address.PATH + "/navigationMenu/navigation_menu.jsp";

Address.SUCHE = {
    SERVLET : Address.PATH + "/Suche",
    JSP: Address.PATH + "/suche/suche.jsp",
}

export default Address;