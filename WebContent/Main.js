import Partner from './partner/Partner.js';
import NavigationMenu from './navigationMenu/NavigationMenu.js';
import EventType from './EventType.js';
import MainState from './MainState.js';
import Suche from './suche/Suche.js';
import { Parameter, Parameters } from './suche/Parameter.js';

const inputMasksContainer = "main-content-input-masks";
const mainMenuContainer = "main-menu";
const searchContainer = "main-content-search";

const showDelay = 500;
const hideDelay = 100;

let ExecuteEvent = {};

const mainState = new MainState();
const navigationMenu = new NavigationMenu();
const explorer = {};
let partner;
let projekt;
let probe;
let experiment;
let analyse;

/************** Init Listener ***************/

navigationMenu.addEventListener(EventType.PARTNER.ERSTELLEN, () => ExecuteEvent.PARTNER.ERSTELLEN());
navigationMenu.addEventListener(EventType.PARTNER.BEARBEITEN, () => ExecuteEvent.PARTNER.BEARBEITEN());
navigationMenu.addEventListener(EventType.PARTNER.AUSWAEHLEN, async () => ExecuteEvent.PARTNER.AUSWAEHLEN());

navigationMenu.addEventListener(EventType.PROJEKT.ERSTELLEN, () => ExecuteEvent.PROJEKT.ERSTELLEN());
navigationMenu.addEventListener(EventType.PROJEKT.BEARBEITEN, () => ExecuteEvent.PROJEKT.BEARBEITEN());
navigationMenu.addEventListener(EventType.PROJEKT.AUSWAEHLEN, async () => ExecuteEvent.PROJEKT.AUSWAEHLEN());

mainState.addEventListener(EventType.STATE.PARTNER, (id) => navigationMenu.setPartner(id));
mainState.addEventListener(EventType.STATE.PROJEKT, (id) => navigationMenu.setProjekt(id));
mainState.addEventListener(EventType.STATE.EXPERIMENT, (id) => navigationMenu.setExperiment(id));
mainState.addEventListener(EventType.STATE.ANALYSE, (id) => navigationMenu.setAnalyse(id));
mainState.addEventListener(EventType.STATE.PROBE, (id) => navigationMenu.setProbe(id));

/************** Init App ***************/

navigationMenu.render(mainMenuContainer);
Suche.render(searchContainer);
hide(searchContainer);

/************** Behavior ***************/

async function show(htmlElementId) {
    $("#" + htmlElementId).show(showDelay);
    return new Promise(resolve => {
        setTimeout(() => {
            resolve();
        }, showDelay);
    })
}

async function hide(htmlElementId) {
    $("#" + htmlElementId).hide(hideDelay);
    return new Promise(resolve => {
        setTimeout(() => {
            resolve();
        }, hideDelay);
    })
}

function hideFast(htmlElementId) {
    $("#" + htmlElementId).hide(0);
}

ExecuteEvent.PARTNER = {};
ExecuteEvent.PROJEKT = {};
ExecuteEvent.EXPERIMENT = {};
ExecuteEvent.ANALYSE = {};
ExecuteEvent.PROBE = {};

ExecuteEvent.PARTNER.AUSWAEHLEN = async () => {
    hide(inputMasksContainer);
    show(searchContainer);
    await partnerSuchen();
    hide(searchContainer);
}

ExecuteEvent.PARTNER.ERSTELLEN = () => {
    partner = new Partner();
    partner.addEventListener(EventType.PARTNER.GESPEICHERT, (partner) => {
        ExecuteEvent.PARTNER.GESPEICHERT(partner);
    });
    partner.render(inputMasksContainer);
    show(inputMasksContainer);
}

ExecuteEvent.PARTNER.BEARBEITEN = () => {
    const model = mainState.state.PARTNER;
    partner = new Partner(model);
    partner.addEventListener(EventType.PARTNER.GESPEICHERT, (partner) => {
        mainState.setPartner(partner);
    });
    partner.addEventListener(EventType.PARTNER.SUCHEN, async () => {
        alert("bitte Partner auswaehlen!");
        hideFast(inputMasksContainer);
        show(searchContainer);
        await partnerSuchen();
        const model = mainState.state.PARTNER;
        hide(searchContainer);
        show(inputMasksContainer);
        partner.initPartnerBearbeiten(model);
    });
    partner.render(inputMasksContainer);
    show(inputMasksContainer);
}

ExecuteEvent.PARTNER.GESPEICHERT = (partner) => {
    mainState.setPartner(partner);
}

ExecuteEvent.PROJEKT.AUSWAEHLEN = async () => {
    hide(inputMasksContainer);
    show(searchContainer);
    await projektSuchen();
    hide(searchContainer);
}

ExecuteEvent.PROJEKT.ERSTELLEN = () => {

}

ExecuteEvent.PROJEKT.BEARBEITEN = () => {

}

ExecuteEvent.PROJEKT.GESPEICHERT = () => {

}


async function partnerSuchen() {
    return new Promise(async (resolve) => {
        const template =
            [
                new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, ""),
                new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, "")
            ];
        const callback = async (callbackData) => {
            await mainState.setPartner(callbackData[Parameters.PARTNER.PK]);
            resolve();
        };
        const returnParameter = new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK);
        Suche.resetPositionIfOutOfBounds();
        Suche.initTemplateParameters(template);
        Suche.addSearchCallback((callbackData) => {
            callback(callbackData);
        }, true, returnParameter)
    });
}

async function projektSuchen() {
    return new Promise(async (resolve) => {
        const template =
            [
                new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, ""),
                new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, "")
            ];
        const callback = async (callbackData) => {
            await mainState.setProjekt(callbackData[Parameters.PROJEKT.PK]);
            resolve();
        };
        const returnParameter = new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK);
        Suche.resetPositionIfOutOfBounds();
        Suche.initTemplateParameters(template);
        Suche.addSearchCallback((callbackData) => {
            callback(callbackData);
        }, true, returnParameter)
    });
}