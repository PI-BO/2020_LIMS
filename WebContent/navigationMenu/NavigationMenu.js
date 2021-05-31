import Address from '../Address.js';
import EventType from '../EventType.js';

const NavigationMenu = {};

const mainContentId = "main-content";
const subPagesShowDelay = 500;
const navigationNodeShowDelay = 400;
const globalSearchMainContentContainerId = "main-content-global-search";

NavigationMenu.render = function(htmlElement) {

    const url = Address.NAVIGATION_MENU_JSP;

    fetch(url, {
        method: "post",
    })
        .then(response => response.text())
        .then(response => {
            
            htmlElement.innerHTML = response
            
            const partnerMenu = htmlElement.getElementsByTagName("Partner")[0];
            const auswaehlen = partnerMenu.getElementsByTagName("auswaehlen")[0];
            const erstellen = partnerMenu.getElementsByTagName("erstellen")[0];
            const bearbeiten = partnerMenu.getElementsByTagName("bearbeiten")[0];
            
            auswaehlen.addEventListener("click", () => {
                const event = new Event(EventType.PARTNER.AUSWAEHLEN);
                htmlElement.dispatchEvent(event);
            });
            
            erstellen.addEventListener("click", () => {
                const event = new Event(EventType.PARTNER.ERSTELLEN);
                event.data = "test";
                htmlElement.dispatchEvent(event);
            });
            
            bearbeiten.addEventListener("click", () => {
                const event = new Event(EventType.PARTNER.BEARBEITEN);
                htmlElement.dispatchEvent(event);
            });

            initDropDownMenus(".navigation_tree_node");
            initOpenAllDropDownMenus(".navigation_table_header", ".navigation_tree_branches");
        });
}

NavigationMenu.hideAllExcept = function hideAllExcept(id) {
    let toggleList = [];
    const mainContentElement = document.getElementById(mainContentId);

    for (let i = 0; i < mainContentElement.children.length; i++) {
        toggleList.push(mainContentElement.children[i].id);
    }

    toggleList.forEach(element => {
        if ("#" + element === id) {
            $("#" + element).hide();
            $("#" + element).show(subPagesShowDelay);
        } else {
            $("#" + element).hide();
        }
    })
}

NavigationMenu.show = function show(id) {
    $(id).show(subPagesShowDelay);
}

NavigationMenu.open = function open(buttonId) {
    $(buttonId).click();
}

NavigationMenu.hide = function hide(id) {
    $(id).hide();
}

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

NavigationMenu.openStateSearch = function openStateSearch(template, callback, returnParameter) {

    NavigationMenu.hideAllExcept(globalSearchMainContentContainerId);
    GlobaleSuche.initTemplateParameters(template);
    GlobaleSuche.addSearchCallback((callbackData) => {
        callback(callbackData);
        NavigationMenu.hide("#" + globalSearchMainContentContainerId);
    }, startSearch = true, returnParameter)
    NavigationMenu.show("#main-content-global-search");
    GlobaleSuche.resetPositionIfOutOfBounds();
}

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

NavigationMenu.setPartner = (partner) => {
    document.getElementById("partner_navigation_state").innerText = partner;
}

export default NavigationMenu;