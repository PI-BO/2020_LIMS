const MainState = (function () {

    const PARTNER = Parameters.PARTNER.CATEGORY;
    const PROJEKT = Parameters.PROJEKT.CATEGORY;
    const PROBE = Parameters.PROBE.CATEGORY;

    const partnerNavigationState = "partner_navigation_state";
    const projektNavigationState = "projekte_navigation_state";
    const probeNavigationState = "probe_navigation_state";

    public.state = {};

    resetState();

    function resetState() {
        public.state = {
            [PARTNER]: new Model(Parameters.PARTNER),
            [PROJEKT]: new Model(Parameters.PROJEKT),
            [PROBE]: new Model(Parameters.PROBE)
        }

        document.getElementById(partnerNavigationState).innerText = " - ";
        document.getElementById(projektNavigationState).innerText = " - ";
        document.getElementById(probeNavigationState).innerText = " - ";
    }

    public.setProjektPartner = function setProjektPartner(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, id)
                ], (searchResults) => {
                    resetState();
                    public.state[PARTNER] = searchResults[0][0];
                    document.getElementById(partnerNavigationState).innerText = public.state[PARTNER][Parameters.PARTNER.NAME];
                    resolve();
                }
            )
        });
    }

    public.setProjekt = function setProjekt(id) {
        console.log("setProejtk start")
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, id)
                ], async (searchResults) => {
                    const projekt = searchResults[0][0];
                    await public.setProjektPartner(projekt[Parameters.PROJEKT.FK])
                    public.state[PROJEKT] = projekt;
                    document.getElementById(projektNavigationState).innerText = public.state[PROJEKT][Parameters.PROJEKT.PK];
                    console.log("setProejtk end")
                    resolve();
                }
            )
        })
    }

    public.setProbe = function setProbe(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, id)
                ], async (searchResults) => {
                    const probe = searchResults[0][0];
                    await public.setProjekt(probe[Parameters.PROBE.FK]);
                    public.state[PROBE] = probe;
                    document.getElementById(probeNavigationState).innerText = public.state[PROBE][Parameters.PROBE.PK];
                    resolve();
                }
            )
        })
    }

    return public;
})();