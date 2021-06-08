import Address from '../Address.js';
import EventType from '../EventType.js';
import ViewModel from '../ViewModel.js';

const navigationNodeShowDelay = 400;

export default class NavigationMenu extends ViewModel {

    constructor() {
        super(Address.NAVIGATION_MENU_JSP);
        this.mainContentId = "main-content";
        this.partnerStateElementId = "partner_navigation_state";
        this.projektStateElementId = "projekt_navigation_state";
        this.probeStateElementId = "probe_navigation_state";
        this.experimentStateElementId = "experiment_navigation_state";
        this.analyseStateElementId = "analyse_navigation_state";
        this.navigationElementId = "navigation_table";
    }

    init() {

        const htmlElement = document.getElementById(this.navigationElementId);

        this.initPartner(htmlElement);
        this.initProjekt(htmlElement);
        this.initProbe(htmlElement);
        this.initExperiment(htmlElement);
        this.initAnalyse(htmlElement);

        initDropDownMenus(".navigation_tree_node");
        initOpenAllDropDownMenus(".navigation_table_header", ".navigation_tree_branches");
    }

    setPartner(stateText) {
        document.getElementById(this.partnerStateElementId).innerText = stateText;
    }

    setProjekt(stateText) {
        document.getElementById(this.projektStateElementId).innerText = stateText;
    }

    setExperiment(stateText) {
        document.getElementById(this.experimentStateElementId).innerText = stateText;
    }

    setProbe(stateText) {
        document.getElementById(this.probeStateElementId).innerText = stateText;
    }

    setAnalyse(stateText) {
        document.getElementById(this.analyseStateElementId).innerText = stateText;
    }

    initPartner(htmlElement) {
        const partner = {};
        const partnerMenu = htmlElement.getElementsByTagName("Partner")[0];
        partner.auswaehlen = partnerMenu.getElementsByTagName("auswaehlen")[0];
        partner.erstellen = partnerMenu.getElementsByTagName("erstellen")[0];
        partner.bearbeiten = partnerMenu.getElementsByTagName("bearbeiten")[0];

        partner.auswaehlen.addEventListener("click", () => {
            const event = new Event(EventType.PARTNER.AUSWAEHLEN);
            this.dispatchEvent(event);
        });

        partner.erstellen.addEventListener("click", () => {
            const event = new Event(EventType.PARTNER.ERSTELLEN);
            this.dispatchEvent(event);
        });

        partner.bearbeiten.addEventListener("click", () => {
            const event = new Event(EventType.PARTNER.BEARBEITEN);
            this.dispatchEvent(event);
        });
    }

    initProjekt(htmlElement) {
        const projekt = {};
        const projektMenu = htmlElement.getElementsByTagName("Projekt")[0];
        projekt.auswaehlen = projektMenu.getElementsByTagName("auswaehlen")[0];
        projekt.erstellen = projektMenu.getElementsByTagName("erstellen")[0];
        projekt.bearbeiten = projektMenu.getElementsByTagName("bearbeiten")[0];

        projekt.auswaehlen.addEventListener("click", () => {
            const event = new Event(EventType.PROJEKT.AUSWAEHLEN);
            this.dispatchEvent(event);
        });

        projekt.erstellen.addEventListener("click", () => {
            const event = new Event(EventType.PROJEKT.ERSTELLEN);
            this.dispatchEvent(event);
        });

        projekt.bearbeiten.addEventListener("click", () => {
            const event = new Event(EventType.PROJEKT.BEARBEITEN);
            this.dispatchEvent(event);
        });
    }

    initProbe(htmlElement) {
        const probe = {};
        const probeMenu = htmlElement.getElementsByTagName("Probe")[0];
        probe.auswaehlen = probeMenu.getElementsByTagName("auswaehlen")[0];
        probe.erstellen = probeMenu.getElementsByTagName("Probeneingang")[0];
        probe.bearbeiten = probeMenu.getElementsByTagName("bearbeiten")[0];

        probe.auswaehlen.addEventListener("click", () => {
            const event = new Event(EventType.PROBE.AUSWAEHLEN);
            this.dispatchEvent(event);
        });

        probe.erstellen.addEventListener("click", () => {
            const event = new Event(EventType.PROBE.EINGANG);
            this.dispatchEvent(event);
        });

        probe.bearbeiten.addEventListener("click", () => {
            const event = new Event(EventType.PROBE.BEARBEITEN);
            this.dispatchEvent(event);
        });
    }

    initExperiment(htmlElement) {
        const experiment = {};
        const experimentMenu = htmlElement.getElementsByTagName("Experiment")[0];
        experiment.auswaehlen = experimentMenu.getElementsByTagName("auswaehlen")[0];
        experiment.erstellen = experimentMenu.getElementsByTagName("erstellen")[0];
        experiment.bearbeiten = experimentMenu.getElementsByTagName("bearbeiten")[0];

        experiment.auswaehlen.addEventListener("click", () => {
            const event = new Event(EventType.EXPERIMENT.AUSWAEHLEN);
            this.dispatchEvent(event);
        });

        experiment.erstellen.addEventListener("click", () => {
            const event = new Event(EventType.EXPERIMENT.ERSTELLEN);
            this.dispatchEvent(event);
        });

        experiment.bearbeiten.addEventListener("click", () => {
            const event = new Event(EventType.EXPERIMENT.BEARBEITEN);
            this.dispatchEvent(event);
        });
    }

    initAnalyse(htmlElement) {
        const analyse = {};
        const analyseMenu = htmlElement.getElementsByTagName("Analyse")[0];
        analyse.auswaehlen = analyseMenu.getElementsByTagName("auswaehlen")[0];
        analyse.erstellen = analyseMenu.getElementsByTagName("erstellen")[0];
        analyse.bearbeiten = analyseMenu.getElementsByTagName("bearbeiten")[0];
        analyse.Eingangsanalyse = analyseMenu.getElementsByTagName("bearbeiten")[0];

        analyse.auswaehlen.addEventListener("click", () => {
            const event = new Event(EventType.ANALYSE.AUSWAEHLEN);
            this.dispatchEvent(event);
        });

        analyse.erstellen.addEventListener("click", () => {
            const event = new Event(EventType.ANALYSE.ERSTELLEN);
            this.dispatchEvent(event);
        });

        analyse.bearbeiten.addEventListener("click", () => {
            const event = new Event(EventType.ANALYSE.BEARBEITEN);
            this.dispatchEvent(event);
        });

        analyse.Eingangsanalyse.addEventListener("click", () => {
            const event = new Event(EventType.ANALYSE.EINGANGSANALYSE);
            this.dispatchEvent(event);
        });
    }
}



// NavigationMenu.hideAllExcept = function hideAllExcept(id) {
//     let toggleList = [];
//     const mainContentElement = document.getElementById(mainContentId);

//     for (let i = 0; i < mainContentElement.children.length; i++) {
//         toggleList.push(mainContentElement.children[i].id);
//     }

//     toggleList.forEach(element => {
//         if ("#" + element === id) {
//             $("#" + element).hide();
//             $("#" + element).show(showDelay);
//         } else {
//             $("#" + element).hide();
//         }
//     })
// }

// NavigationMenu.show = function show(id) {
//     $(id).show(showDelay);
// }

// NavigationMenu.open = function open(buttonId) {
//     $(buttonId).click();
// }

// NavigationMenu.hide = function hide(id) {
//     $(id).hide();
// }

NavigationMenu.initInputMaskListener = function initInputMaskListener(containerId, buttonIdAndUrl) {

    for (let key in buttonIdAndUrl) {

        const id = key;
        const url = buttonIdAndUrl[key];

        $(id).click(function () {
            var posting = $.post(url, {});
            posting.done(function (data) {

                console.log({ containerId }, { key });

                $(containerId).empty().append(data);
                NavigationMenu.hideAllExcept(containerId);
            });
        });
    }
}

NavigationMenu.initSucheListener = function initSucheListener(buttonId, containerId) {

    $(buttonId).click(function () {
        const template = [
            { "partner": "id" },
            { "partner": "name" },
            { "partner": "email" },
            { "projekte": "id" },
            { "projekte": "vertragsnummer" }
        ];
        // GlobaleSuche.initTemplateParameters(template);
        GlobaleSuche.disableCallbackMode();
        NavigationMenu.show(containerId);
        GlobaleSuche.resetPositionIfOutOfBounds();
    });
}

NavigationMenu.initAuswaehlenButton = function initAuswaehlenButton(buttonId, containerId, template, callback, returnParameter) {

    $(buttonId).click(function () {

        NavigationMenu.openStateSearch(template, callback, returnParameter)

        // NavigationMenu.hideAllExcept(globalSearchMainContentContainerId);
        // GlobaleSuche.initTemplateParameters(template);
        // GlobaleSuche.addSearchCallback((callbackData) => {
        //     callback(callbackData);
        //     NavigationMenu.hide("#" + globalSearchMainContentContainerId);
        // }, startSearch = true, returnParameter)
        // public.show(containerId);
        // GlobaleSuche.resetPositionIfOutOfBounds();
    });
}

// NavigationMenu.openStateSearch = function openStateSearch(template, callback, returnParameter) {

//     NavigationMenu.hideAllExcept(globalSearchMainContentContainerId);
//     GlobaleSuche.initTemplateParameters(template);
//     GlobaleSuche.addSearchCallback((callbackData) => {
//         callback(callbackData);
//         NavigationMenu.hide("#" + globalSearchMainContentContainerId);
//     }, startSearch = true, returnParameter)
//     NavigationMenu.show("#main-content-global-search");
//     GlobaleSuche.resetPositionIfOutOfBounds();
// }

NavigationMenu.initExplorerListener = function initExplorerListener(buttonId, containerId) {
    $(buttonId).click(function () {
        NavigationMenu.hideAllExcept(containerId);
        $('#lazy').jstree(true).refresh();
    });
}

function initOpenAllDropDownMenus(headerClass, branchesClass) {
    $(headerClass).click(function () {
        if ($(branchesClass).is(":hidden")) {
            $(branchesClass).show(navigationNodeShowDelay);
            $(".symbol_folder_closed").show(navigationNodeShowDelay);
        } else {
            $(branchesClass).hide(navigationNodeShowDelay);
        }
    });
}

function initDropDownMenus(treeNodeClass) {
    $(treeNodeClass).click(function () {
        $(this).next().toggle(navigationNodeShowDelay);
        $(this).toggleClass("symbol_folder_open");
    });
}
