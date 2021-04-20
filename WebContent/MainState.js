const MainState = (function () {

    const PARTNER = Parameters.PARTNER.CATEGORY;
    const PROJEKT = Parameters.PROJEKT.CATEGORY;
    const PROBE = Parameters.PROBE.CATEGORY;
    const EXPERIMENT = Parameters.EXPERIMENT.CATEGORY;
    const ANALYSE = Parameters.ANALYSE.CATEGORY;

    const partnerNavigationState = "partner_navigation_state";
    const projektNavigationState = "projekte_navigation_state";
    const probeNavigationState = "probe_navigation_state";
    const experimentNavigationState = "experiment_navigation_state";
    const analyseNavigationState = "analyse_navigation_state";

    public.state = {};

    resetState();

    function resetState() {
        public.state = {
            [PARTNER]: new Model(Parameters.PARTNER),
            [PROJEKT]: new Model(Parameters.PROJEKT),
            [PROBE]: new Model(Parameters.PROBE),
            [EXPERIMENT]: new Model(Parameters.EXPERIMENT),
            [ANALYSE]: new Model(Parameters.ANALYSE)
        }

        document.getElementById(partnerNavigationState).innerText = " - ";
        document.getElementById(projektNavigationState).innerText = " - ";
        document.getElementById(probeNavigationState).innerText = " - ";
        document.getElementById(experimentNavigationState).innerText = " - ";
        document.getElementById(analyseNavigationState).innerText = " - ";
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
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, id)
                ], async (searchResults) => {
                    const projekt = searchResults[0][0];
                    await public.setProjektPartner(projekt[Parameters.PROJEKT.FK])
                    public.state[PROJEKT] = projekt;
                    document.getElementById(projektNavigationState).innerText = public.state[PROJEKT][Parameters.PROJEKT.PK];
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

    public.setExperiment = function setExperiment(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK, id)
                ], async (searchResults) => {
                    const experiment = searchResults[0][0];
                    await public.setProbe(experiment[Parameters.EXPERIMENT.FK]);
                    public.state[EXPERIMENT] = experiment;
                    document.getElementById(experimentNavigationState).innerText = public.state[EXPERIMENT][Parameters.EXPERIMENT.PK];
                    resolve();
                }
            )
        })
    }

    public.setAnalyse = function setAnalyse(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.ANALYSE.CATEGORY, Parameters.ANALYSE.PK, id)
                ], async (searchResults) => {
                    const analyse = searchResults[0][0];
                    await public.setExperiment(analyse[Parameters.ANALYSE.FK]);
                    public.state[ANALYSE] = analyse;
                    document.getElementById(analyseNavigationState).innerText = public.state[ANALYSE][Parameters.ANALYSE.PK];
                    resolve();
                }
            )
        })
    }

    return public;
})();