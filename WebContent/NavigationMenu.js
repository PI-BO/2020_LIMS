const NavigationMenu = (function () {

    const mainContentId = "main-content";
    const subPagesShowDelay = 500;
    const navigationNodeShowDelay = 400;
    const globalSearchMainContentContainerId = "main-content-global-search";

    public = {};

    public.hideAllExcept = function hideAllExcept(id) {
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

    public.show = function show(id) {
        $(id).show(subPagesShowDelay);
    }

    public.open = function open(buttonId) {
        $(buttonId).click();
    }

    public.hide = function hide(id) {
        $(id).hide();
    }

    public.initInputMaskListener = function initInputMaskListener(containerId, buttonIdAndUrl) {

        for (let key in buttonIdAndUrl) {

            const id = key;
            const url = buttonIdAndUrl[key];

            $(id).click(function () {
                var posting = $.post(url, {});
                posting.done(function (data) {
                    $(containerId).empty().append(data);
                    public.hideAllExcept(containerId);
                });
            });
        }
    }

    public.initSucheListener = function initSucheListener(buttonId, containerId) {

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
            public.show(containerId);
            GlobaleSuche.resetPositionIfOutOfBounds();
        });
    }

    public.initAuswaehlenButton = function initAuswaehlenButton(buttonId, containerId, template, callback, returnParameter) {

        $(buttonId).click(function () {

            public.openStateSearch(template, callback, returnParameter)

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

    public.openStateSearch = function openStateSearch(template, callback, returnParameter) {

        NavigationMenu.hideAllExcept(globalSearchMainContentContainerId);
        GlobaleSuche.initTemplateParameters(template);
        GlobaleSuche.addSearchCallback((callbackData) => {
            callback(callbackData);
            NavigationMenu.hide("#" + globalSearchMainContentContainerId);
        }, startSearch = true, returnParameter)
        public.show("#main-content-global-search");
        GlobaleSuche.resetPositionIfOutOfBounds();
    }

    public.initExplorerListener = function initExplorerListener(buttonId, containerId) {
        $(buttonId).click(function () {
            public.hideAllExcept(containerId);
            $('#lazy').jstree(true).refresh();
        });
    }

    public.initOpenAllNavigationNodesListener = function initOpenAllNavigationNodesListener(headerClass, branchesClass) {
        $(headerClass).click(function () {
            if ($(branchesClass).is(":hidden")) {
                $(branchesClass).show(navigationNodeShowDelay);
                $(".symbol_folder_closed").show(navigationNodeShowDelay);
            } else {
                $(branchesClass).hide(navigationNodeShowDelay);
            }
        });
    }

    public.initNavigationNodeListener = function initNavigationNodeListener(treeNodeClass) {
        $(treeNodeClass).click(function () {
            $(this).next().toggle(navigationNodeShowDelay);
            $(this).toggleClass("symbol_folder_open");
        });
    }

    return public;

})();