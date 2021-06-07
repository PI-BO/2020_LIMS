import Partner from './partner/Partner.js';
import Projekt from './projekt/Projekt.js';
import Probeneingang from './probe/Probeneingang.js';
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
let probeneingang;
let experiment;
let analyse;

/************** Init Listener ***************/

navigationMenu.addEventListener(EventType.PARTNER.ERSTELLEN, () => ExecuteEvent.PARTNER.ERSTELLEN());
navigationMenu.addEventListener(EventType.PARTNER.BEARBEITEN, () => ExecuteEvent.PARTNER.BEARBEITEN());
navigationMenu.addEventListener(EventType.PARTNER.AUSWAEHLEN, async () => ExecuteEvent.PARTNER.AUSWAEHLEN());

navigationMenu.addEventListener(EventType.PROJEKT.ERSTELLEN, () => ExecuteEvent.PROJEKT.ERSTELLEN());
navigationMenu.addEventListener(EventType.PROJEKT.BEARBEITEN, () => ExecuteEvent.PROJEKT.BEARBEITEN());
navigationMenu.addEventListener(EventType.PROJEKT.AUSWAEHLEN, async () => ExecuteEvent.PROJEKT.AUSWAEHLEN());
navigationMenu.addEventListener(EventType.PARTNER.AUSWAEHLEN, async () => ExecuteEvent.PARTNER.AUSWAEHLEN());

navigationMenu.addEventListener(EventType.PROBE.EINGANG, () => ExecuteEvent.PROBE.ERSTELLEN());
navigationMenu.addEventListener(EventType.PROBE.BEARBEITEN, () => ExecuteEvent.PROBE.BEARBEITEN());
navigationMenu.addEventListener(EventType.PROBE.AUSWAEHLEN, async () => ExecuteEvent.PROBE.AUSWAEHLEN());

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
    const partnerId = await partnerSuchen();
    await mainState.setPartner(partnerId);
    hide(searchContainer);
}

ExecuteEvent.PARTNER.ERSTELLEN = () => {
    partner = new Partner(mainState.state);
    partner.erstellen();
    partner.addEventListener(EventType.PARTNER.GESPEICHERT, (partner) => {
        ExecuteEvent.PARTNER.GESPEICHERT(partner);
    });
    partner.render(inputMasksContainer);
    show(inputMasksContainer);
}

ExecuteEvent.PARTNER.BEARBEITEN = () => {
    partner = new Partner(mainState.state);
    partner.bearbeiten();
    partner.addEventListener(EventType.PARTNER.GESPEICHERT, (partner) => {
        mainState.setPartner(partner);
    });
    partner.addEventListener(EventType.PARTNER.SUCHEN, async () => {
        alert("bitte Partner auswaehlen!");
        hideFast(inputMasksContainer);
        show(searchContainer);
        const partnerId = await partnerSuchen();
        await mainState.setPartner(partnerId);
        partner.state = mainState.state;
        partner.render(inputMasksContainer);
        hide(searchContainer);
        show(inputMasksContainer);
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
    const projektId = await projektSuchen();
    await mainState.setProjekt(projektId);
    hide(searchContainer);
}

ExecuteEvent.PROJEKT.ERSTELLEN = () => {
    projekt = new Projekt(mainState.state);
    projekt.erstellen();
    projekt.addEventListener(EventType.PROJEKT.GESPEICHERT, (projekt) => {
        ExecuteEvent.PROJEKT.GESPEICHERT(projekt);
    });
    projekt.addEventListener(EventType.PARTNER.SUCHEN, async () => {
        alert("bitte Partner auswaehlen!");
        hideFast(inputMasksContainer);
        show(searchContainer);
        const partnerId = await partnerSuchen();
        await mainState.setPartner(partnerId);
        projekt.state = mainState.state;
        projekt.render(inputMasksContainer);
        hide(searchContainer);
        show(inputMasksContainer);
    });
    projekt.render(inputMasksContainer);
    show(inputMasksContainer);
}

ExecuteEvent.PROJEKT.BEARBEITEN = () => {
    projekt = new Projekt(mainState.state);
    projekt.bearbeiten();
    projekt.addEventListener(EventType.PROJEKT.GESPEICHERT, (projekt) => {
        mainState.setProjekt(projekt);
    });
    projekt.addEventListener(EventType.PROJEKT.SUCHEN, async () => {
        alert("bitte Projekt auswaehlen!");
        hideFast(inputMasksContainer);
        show(searchContainer);
        const projektId = await projektSuchen();
        await mainState.setProjekt(projektId);
        hide(searchContainer);
        show(inputMasksContainer);
        projekt.state = mainState.state;
        projekt.render(inputMasksContainer);
    });
    projekt.render(inputMasksContainer);
    show(inputMasksContainer);
}

ExecuteEvent.PROJEKT.GESPEICHERT = (projekt) => {
    mainState.setProjekt(projekt);
}

ExecuteEvent.PROBE.AUSWAEHLEN = async () => {
    hide(inputMasksContainer);
    show(searchContainer);
    const probeId = await probeSuchen();
    await mainState.setProbe(probeId);
    hide(searchContainer);
}

ExecuteEvent.PROBE.ERSTELLEN = () => {
    probeneingang = new Probeneingang(mainState.state);
    probeneingang.erstellen();
    probeneingang.addEventListener(EventType.PROBE.GESPEICHERT, (probe) => {
        ExecuteEvent.PROBE.GESPEICHERT(probe);
    });
    probeneingang.addEventListener(EventType.PROBE.SUCHEN, async () => {
        alert("bitte Projekt auswaehlen!");
        hideFast(inputMasksContainer);
        show(searchContainer);
        const probeId = await projektSuchen();
        await mainState.setProjekt(probeId);
        probeneingang.state = mainState.state;
        probeneingang.render(inputMasksContainer);
        hide(searchContainer);
        show(inputMasksContainer);
    });
    probeneingang.render(inputMasksContainer);
    show(inputMasksContainer);
}

ExecuteEvent.PROBE.BEARBEITEN = () => {
    probeneingang = new Probeneingang(mainState.state);
    probeneingang.bearbeiten();
    probeneingang.addEventListener(EventType.PROBE.GESPEICHERT, (probe) => {
        mainState.setProbe(probe);
    });
    probeneingang.addEventListener(EventType.PROBE.SUCHEN, async () => {
        alert("bitte Probe auswaehlen!");
        hideFast(inputMasksContainer);
        show(searchContainer);
        const probeId = await probeSuchen();
        await mainState.setProbe(probeId);
        hide(searchContainer);
        show(inputMasksContainer);
        probeneingang.state = mainState.state;
        probeneingang.render(inputMasksContainer);
    });
    probeneingang.render(inputMasksContainer);
    show(inputMasksContainer);
}

ExecuteEvent.PROBE.GESPEICHERT = (probe) => {
    mainState.setProbe(probe);
}

async function partnerSuchen() {
    let partnerId;
    await new Promise(async (resolve) => {
        const template =
            [
                new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, ""),
                new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, "")
            ];
        const callback = async (callbackData) => {
            partnerId = callbackData[Parameters.PARTNER.PK];
            resolve();
        };
        const returnParameter = new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK);
        Suche.resetPositionIfOutOfBounds();
        Suche.initTemplateParameters(template);
        Suche.addSearchCallback((callbackData) => {
            callback(callbackData);
        }, true, returnParameter)
    });
    return partnerId;
}

async function projektSuchen() {
    let projektId;
    await new Promise(async (resolve) => {
        const template =
            [
                new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, ""),
                new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.NAME, mainState.state.PARTNER[Parameters.PARTNER.NAME])
            ];
        const callback = async (callbackData) => {
            projektId = callbackData[Parameters.PROJEKT.PK];
            resolve();
        };
        const returnParameter = new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK);
        Suche.resetPositionIfOutOfBounds();
        Suche.initTemplateParameters(template);
        Suche.addSearchCallback((callbackData) => {
            callback(callbackData);
        }, true, returnParameter)
    });
    return projektId;
}

async function probeSuchen() {
    let probeId;
    await new Promise(async (resolve) => {
        const template =
            [
                new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, ""),
                new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, mainState.state.PROJEKT[Parameters.PROJEKT.PK])
            ];
        const callback = async (callbackData) => {
            probeId = callbackData[Parameters.PROBE.PK];
            resolve();
        };
        const returnParameter = new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK);
        Suche.resetPositionIfOutOfBounds();
        Suche.initTemplateParameters(template);
        Suche.addSearchCallback((callbackData) => {
            callback(callbackData);
        }, true, returnParameter)
    });
    return probeId;
}