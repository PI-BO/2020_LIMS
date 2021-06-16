const Address = {}

Address.PATH = "http://localhost:8080/2020_LIMS";

Address.PARTNER = {
    JSP : Address.PATH + "/partner/partner.jsp",
    SERVLET : Address.PATH + "/partner",
}

Address.PROJEKT = {
    JSP : Address.PATH + "/projekt/projekt.jsp",
    SERVLET : Address.PATH + "/projekt",
}

Address.PROBE = {
    JSP : Address.PATH + "/probe/probeneingang.jsp",
    SERVLET : Address.PATH + "/probeneingang",
}

Address.EXPERIMENT = {
    ERSTELLEN_JSP : Address.PATH + "/experiment/experiment.jsp",
    SERVLET : Address.PATH + "/experiment",
}

Address.EXPERIMENT_TYP = {
    ERSTELLEN_JSP : Address.PATH + "/experiment/experimenttyp.jsp",
}

Address.ANALYSE = {
    JSP : Address.PATH + "/analyse/analyse.jsp",
    SERVLET : Address.PATH + "/analyse",
    DATENMASKE : {
        DSC: Address.PATH + "/analyse/datenmaske_dsc.jsp",
        PXRD: Address.PATH + "/analyse/datenmaske_pxrd.jsp",
        TGA: Address.PATH + "/analyse/datenmaske_tga.jsp",
        IR: Address.PATH + "/analyse/datenmaske_ir.jsp",
    }
}

Address.NAVIGATION_MENU_JSP = Address.PATH + "/navigationMenu/navigation_menu.jsp";

Address.SUCHE = {
    SERVLET : Address.PATH + "/Suche",
    JSP: Address.PATH + "/suche/suche.jsp",
}

Address.EXPLORER = {
    JSP: Address.PATH + "/explorer/explorer.jsp",
    PARTNER_LIST: Address.PATH + "/explorer/partnerList.jsp",
    PARTNER: Address.PATH + "/explorer/partner.jsp",
    PROJEKT: Address.PATH + "/explorer/projekt.jsp",
    PROJEKTE_LIST: Address.PATH + "/explorer/projekteList.jsp",
    PROBE: Address.PATH + "/explorer/probe.jsp",
    EXPERIMENT: Address.PATH + "/explorer/experiment.jsp",
    NAVIGATION: Address.PATH + "/explorer/explorer_navigation_menu.jsp",
}

export default Address;