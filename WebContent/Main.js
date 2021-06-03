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

const mainState = new MainState();
const navigationMenu = new NavigationMenu();
let partner;

navigationMenu.addEventListener(EventType.PARTNER.ERSTELLEN, () => {
    partner = new Partner();
    partner.addEventListener(EventType.PARTNER.GESPEICHERT, (partner) => {
        mainState.setPartner(partner);
    });
    partner.render(inputMasksContainer);
    show(inputMasksContainer);
});

navigationMenu.addEventListener(EventType.PARTNER.BEARBEITEN, () => {
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
});

navigationMenu.addEventListener(EventType.PARTNER.AUSWAEHLEN, async () => {
    hide(inputMasksContainer);
    show(searchContainer);
    await partnerSuchen();
    hide(searchContainer);
});

mainState.addEventListener(EventType.STATE.PARTNER, (partner) => {
    navigationMenu.setPartner(partner);
})

navigationMenu.render(mainMenuContainer);
Suche.render(searchContainer);
hide(searchContainer);

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