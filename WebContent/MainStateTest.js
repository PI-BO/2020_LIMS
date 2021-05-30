export default (function () {
    
    const MainState = {};

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

    MainState.state = {};

    resetState();

    function resetState() {
        MainState.state = {
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

    MainState.setProjektPartner2 = function setProjektPartner(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, id)
                ], (searchResults) => {
                    resetState();
                    MainState.state[PARTNER] = searchResults[0][0];
                    document.getElementById(partnerNavigationState).innerText = MainState.state[PARTNER][Parameters.PARTNER.NAME];
                    resolve();
                }
            )
        });
    }

    MainState.setProjekt = function setProjekt(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, id)
                ], async (searchResults) => {
                    const projekt = searchResults[0][0];
                    await MainState.setProjektPartner2(projekt[Parameters.PROJEKT.FK])
                    MainState.state[PROJEKT] = projekt;
                    document.getElementById(projektNavigationState).innerText = MainState.state[PROJEKT][Parameters.PROJEKT.PK];
                    resolve();
                }
            )
        })
    }

    MainState.setProbe = function setProbe(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, id)
                ], async (searchResults) => {
                    const probe = searchResults[0][0];
                    await MainState.setProjekt(probe[Parameters.PROBE.FK]);
                    MainState.state[PROBE] = probe;
                    document.getElementById(probeNavigationState).innerText = MainState.state[PROBE][Parameters.PROBE.PK];
                    resolve();
                }
            )
        })
    }

    MainState.setExperiment = function setExperiment(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK, id)
                ], async (searchResults) => {
                    const experiment = searchResults[0][0];
                    await MainState.setProbe(experiment[Parameters.EXPERIMENT.FK]);
                    MainState.state[EXPERIMENT] = experiment;
                    document.getElementById(experimentNavigationState).innerText = MainState.state[EXPERIMENT][Parameters.EXPERIMENT.PK];
                    resolve();
                }
            )
        })
    }

    MainState.setAnalyse = function setAnalyse(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.ANALYSE.CATEGORY, Parameters.ANALYSE.PK, id)
                ], async (searchResults) => {
                    const analyse = searchResults[0][0];
                    await MainState.setExperiment(analyse[Parameters.ANALYSE.FK]);
                    MainState.state[ANALYSE] = analyse;
                    document.getElementById(analyseNavigationState).innerText = MainState.state[ANALYSE][Parameters.ANALYSE.PK];
                    resolve();
                }
            )
        })
    }

    return MainState;
})();