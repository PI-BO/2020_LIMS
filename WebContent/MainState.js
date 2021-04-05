const MainState = (function () {

    let currentProjekt;
    let currentProbe;
    let currentExperiment;
    let currentAnalyse;
    let stateData;

    public.getState = function getState(){
        return stateData;
    }

    public.setCurrentProjekt = function setCurrentProjekt(id) {
        currentProjekt = id;
        GlobaleSuche.backgroundSearch(
            [
                new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, currentProjekt)
            ], (searchResults) => {
                stateData = searchResults;
            }
        )
    }

    public.setCurrentProbe = function setCurrentProbe(id) {
        currentProbe = id;
        GlobaleSuche.backgroundSearch(
            [
                new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, currentProjekt),
                new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, currentProbe)
            ], (searchResults) => {
                stateData = searchResults;
            }
        )
    }

    public.setCurrentExperiment = function setCurrentExperiment(id) {
        currentExperiment = id;
        GlobaleSuche.backgroundSearch(
            [
                new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, currentProjekt),
                new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, currentProbe),
                new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK, currentExperiment)
            ], (searchResults) => {
                stateData = searchResults;
            }
        )
    }

    public.setCurrentAnalyse = function setCurrentAnalyse(id) {
        currentAnalyse = id;
        GlobaleSuche.backgroundSearch(
            [
                new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, currentProjekt),
                new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, currentProbe),
                new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK, currentExperiment),
                new Parameter(Parameters.ANALYSE.CATEGORY, Parameters.ANALYSE.PK, currentAnalyse)
            ], (searchResults) => {
                stateData = searchResults;
            }
        )
    }

    return public;
})();