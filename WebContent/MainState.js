const MainState = (function () {
    
    const public2 = {};

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

    public2.state = {};

    resetState();

    function resetState() {
        public2.state = {
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

    public2.setProjektPartner = function setProjektPartner(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, id)
                ], (searchResults) => {
                    resetState();
                    public2.state[PARTNER] = searchResults[0][0];
                    document.getElementById(partnerNavigationState).innerText = public2.state[PARTNER][Parameters.PARTNER.NAME];
                    resolve();
                }
            )
        });
    }

    public2.setProjekt = function setProjekt(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, id)
                ], async (searchResults) => {
                    const projekt = searchResults[0][0];
                    await public2.setProjektPartner(projekt[Parameters.PROJEKT.FK])
                    public2.state[PROJEKT] = projekt;
                    document.getElementById(projektNavigationState).innerText = public2.state[PROJEKT][Parameters.PROJEKT.PK];
                    resolve();
                }
            )
        })
    }

    public2.setProbe = function setProbe(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, id)
                ], async (searchResults) => {
                    const probe = searchResults[0][0];
                    await public2.setProjekt(probe[Parameters.PROBE.FK]);
                    public2.state[PROBE] = probe;
                    document.getElementById(probeNavigationState).innerText = public2.state[PROBE][Parameters.PROBE.PK];
                    resolve();
                }
            )
        })
    }

    public2.setExperiment = function setExperiment(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK, id)
                ], async (searchResults) => {
                    const experiment = searchResults[0][0];
                    await public2.setProbe(experiment[Parameters.EXPERIMENT.FK]);
                    public2.state[EXPERIMENT] = experiment;
                    document.getElementById(experimentNavigationState).innerText = public2.state[EXPERIMENT][Parameters.EXPERIMENT.PK];
                    resolve();
                }
            )
        })
    }

    public2.setAnalyse = function setAnalyse(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.ANALYSE.CATEGORY, Parameters.ANALYSE.PK, id)
                ], async (searchResults) => {
                    const analyse = searchResults[0][0];
                    await public2.setExperiment(analyse[Parameters.ANALYSE.FK]);
                    public2.state[ANALYSE] = analyse;
                    document.getElementById(analyseNavigationState).innerText = public2.state[ANALYSE][Parameters.ANALYSE.PK];
                    resolve();
                }
            )
        })
    }

    return public2;
})();