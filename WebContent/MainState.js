import { Parameters, Parameter } from './suche/Parameter.js';
import Model from './Model.js';
import GlobaleSuche from './suche/Suche.js';
import EventType from './EventType.js';
import Listenable from './Listenable.js';

export default class MainState extends Listenable {

    init() {

        this.partnerNavigationState = "partner_navigation_state";
        this.projektNavigationState = "projekte_navigation_state";
        this.probeNavigationState = "probe_navigation_state";
        this.experimentNavigationState = "experiment_navigation_state";
        this.analyseNavigationState = "analyse_navigation_state";

        this.resetState();
    }

    resetState() {
        this.state = new State();

        const event = new Event(EventType.STATE.PARTNER);
        event.data = " - ";
        this.dispatchEvent(event);
    }

    setPartner(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, id)
                ], (searchResults) => {
                    this.resetState();
                    this.state.PARTNER = searchResults[0][0];
                    const event = new Event(EventType.STATE.PARTNER);
                    event.data = this.state.PARTNER[Parameters.PARTNER.NAME];
                    this.dispatchEvent(event);
                    resolve();
                }
            )
        });
    }

    setProjekt(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, id)
                ], async (searchResults) => {
                    const projekt = searchResults[0][0];
                    await this.setPartner(projekt[Parameters.PROJEKT.FK])
                    this.state.PROJEKT = projekt;
                    const event = new Event(EventType.STATE.PROJEKT);
                    event.data = this.state.PROJEKT[Parameters.PROJEKT.PK];
                    this.dispatchEvent(event);
                    resolve();
                }
            )
        })
    }

    setProbe(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, id)
                ], async (searchResults) => {
                    const probe = searchResults[0][0];
                    await this.setProjekt(probe[Parameters.PROBE.FK]);
                    this.state.PROBE = probe;
                    const event = new Event(EventType.STATE.PROBE);
                    event.data = this.state.PROBE[Parameters.PROBE.PK];
                    this.dispatchEvent(event);
                    resolve();
                }
            )
        })
    }

    setExperiment(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.EXPERIMENT.CATEGORY, Parameters.EXPERIMENT.PK, id)
                ], async (searchResults) => {
                    const experiment = searchResults[0][0];
                    await this.setProbe(experiment[Parameters.EXPERIMENT.FK]);
                    this.state.EXPERIMENT = experiment;
                    const event = new Event(EventType.STATE.EXPERIMENT);
                    event.data = this.state.EXPERIMENT[Parameters.EXPERIMENT.PK];
                    this.dispatchEvent(event);
                    resolve();
                }
            )
        })
    }

    setAnalyse(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.ANALYSE.CATEGORY, Parameters.ANALYSE.PK, id)
                ], async (searchResults) => {
                    const analyse = searchResults[0][0];
                    await this.setExperiment(analyse[Parameters.ANALYSE.FK]);
                    this.state.ANALYSE = analyse;
                    const event = new Event(EventType.STATE.ANALYSE);
                    event.data = this.state.ANALYSE[Parameters.ANALYSE.PK];
                    this.dispatchEvent(event);
                    resolve();
                }
            )
        })
    }

    constructor() {
        super();
        this.init();
    }
}

class State {
    constructor() {
        this.PARTNER = new Model(Parameters.PARTNER),
            this.PROJEKT = new Model(Parameters.PROJEKT),
            this.PROBE = new Model(Parameters.PROBE),
            this.EXPERIMENT = new Model(Parameters.EXPERIMENT),
            this.ANALYSE = new Model(Parameters.ANALYSE)
    }
}