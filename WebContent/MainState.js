import { Parameters, Parameter } from './suche/Parameter.js';
import Model from './Model.js';
import GlobaleSuche from './suche/Suche.js';
import EventType from './EventType.js';

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
    MainState.eventListener = [];

    MainState.addEventListener = function addEventListener(event, callback) {
        if (this.eventListener[event] === undefined) this.eventListener[event] = [];
        this.eventListener[event].push(callback);
    }

    MainState.dispatchEvent = function dispatchEvent(event) {
        const eventType = event.type;
        if (this.eventListener[eventType] === undefined) return;
        this.eventListener[eventType].forEach(listener => {
            let data = {};
            if (event.data !== undefined) data = event.data;
            listener(data);
        });
    }
    
    MainState.resetState = function resetState() {
        this.state = {
            [PARTNER]: new Model(Parameters.PARTNER),
            [PROJEKT]: new Model(Parameters.PROJEKT),
            [PROBE]: new Model(Parameters.PROBE),
            [EXPERIMENT]: new Model(Parameters.EXPERIMENT),
            [ANALYSE]: new Model(Parameters.ANALYSE)
        }
        
        const event = new Event(EventType.STATE.PARTNER);
        event.data = " - ";
        this.dispatchEvent(event);
        // document.getElementById(partnerNavigationState).innerText = " - ";
        // document.getElementById(projektNavigationState).innerText = " - ";
        // document.getElementById(probeNavigationState).innerText = " - ";
        // document.getElementById(experimentNavigationState).innerText = " - ";
        // document.getElementById(analyseNavigationState).innerText = " - ";
    }
    
    MainState.resetState();

    MainState.setPartner = function setProjektPartner(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, id)
                ], (searchResults) => {
                    MainState.resetState();
                    MainState.state[PARTNER] = searchResults[0][0];
                    const event = new Event(EventType.STATE.PARTNER);
                    event.data = MainState.state[PARTNER][Parameters.PARTNER.NAME];
                    this.dispatchEvent(event);
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
                    await MainState.setPartner(projekt[Parameters.PROJEKT.FK])
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