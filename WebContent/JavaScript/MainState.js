import Parameter from "../JavaScript/Parameter.js";
import * as Parameters from "../JavaScript/Parameter.js";

export default class MainState {
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
                "category" : GlobaleSuche.MODEL.PROJEKT.CATEGORY,
                "parameter" : GlobaleSuche.MODEL.PROJEKT.PK,
                value : projektId
            }
        ], (searchResults) => {
            this.stateData = searchResults;
        })
    }


}