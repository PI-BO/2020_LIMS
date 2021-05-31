import Partner from './partner/Partner.js';
import NavigationMenu from './navigationMenu/NavigationMenu.js';
import EventType from './EventType.js';
import MainState from './MainState.js';
import Suche from './suche/Suche.js';

const mainContentInputMasks = document.getElementById("main-content-input-masks");
const mainMenu = document.getElementById("main-menu");
const mainContentSuche = document.getElementById("main-content-global-search");

const partner = new Partner();

mainMenu.addEventListener(EventType.PARTNER.ERSTELLEN, () => {
    partner.render(mainContentInputMasks);
    mainContentInputMasks.style.display = "unset";
});

mainContentInputMasks.addEventListener(EventType.PARTNER.GESPEICHERT, (event) => {
    MainState.setPartner(event.data);
});

MainState.addEventListener(EventType.STATE.PARTNER, (partner) => {
    NavigationMenu.setPartner(partner)
})

NavigationMenu.render(mainMenu);
Suche.render(mainContentSuche);
mainContentSuche.style.display = "unset";