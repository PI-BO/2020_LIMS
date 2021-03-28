const NavigationMenu = (function () {

    const mainContentId = "main-content";
    const subPagesShowDelay = 500;
    const navigationNodeShowDelay = 400;

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

    public.initInputMasks = function initInputMasks(containerId, buttonIdAndUrl) {

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

    public.initSubpages = function initSubpages(subpages) {

        for (let key in subpages) {

            const url = key;
            const id = subpages[key];

            $(document).ready(function () {
                var posting = $.post(url, {});
                posting.done(function (data) {
                    $(id).empty().append(data);
                });
            });
        }
    }

    public.initSuche = function initSuche(buttonId, containerId) {

        $(buttonId).click(function () {
            const template = [
                { "partner": "id" },
                { "partner": "name" },
                { "partner": "email" },
                { "projekte": "id" },
                { "projekte": "vertragsnummer" }
            ];
            // GlobaleSuche.initTemplateParameters(template);
            // public.hideAllExcept(containerId);
            public.show(containerId);
        });
    }

    public.initExplorer = function initExplorer(buttonId, containerId) {
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