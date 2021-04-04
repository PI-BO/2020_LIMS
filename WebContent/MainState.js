class MainState {
    constructor(){
        this.currentProjekt;
        this.currentProbe;
        this.currentExperiment;
        this.currentAnalyse;
        this.stateData = [];
    }

    setCurrentProjekt(projektId){
        GlobaleSuche.backgroundSearch([
            {
                "category" : Parameters.PROJEKT.CATEGORY,
                "parameter" : Parameters.PROJEKT.PK,
                value : projektId
            }
        ], (searchResults) => {
            this.stateData = searchResults;
        })
    }
}