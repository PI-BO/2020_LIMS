const NavigationMenu = (function () {

    const mainContentId = "main-content";

    pub = {};

    pub.hideAllExcept = function hideAllExcept(id) {
        let toggleList = [];
        const mainContentElement = document.getElementById(mainContentId);

        for (let i = 0; i < mainContentElement.children.length; i++) {
            toggleList.push(mainContentElement.children[i].id);
        }

        toggleList.forEach(element => {
            if ("#" + element == id) {
                $("#" + element).hide();
                $("#" + element).show(500);
            } else {
                $("#" + element).hide();
            }
        })
    }

    pub.initInputMasks = function initInputMasks(containerId, buttonIdAndUrl) {

        for (let key in buttonIdAndUrl) {

            const id = key;
            const url = buttonIdAndUrl[key];

            $(id).click(function () {
                var posting = $.post(url, {});
                posting.done(function (data) {
                    $(containerId).empty().append(data);
                    NavigationMenu.hideAllExcept(containerId);
                });
            });
        }
    }

    pub.initSubpages = function initSubpages(subpages) {

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

    pub.initSuche = function initSuche(buttonId, containerId) {

        $(buttonId).click(function () {
            const template = [
                { "partner": "id" },
                { "partner": "name" },
                { "partner": "email" },
                { "projekte": "id" },
                { "projekte": "vertragsnummer" }
            ];
            GlobaleSuche.initTemplateParameters(template);
            NavigationMenu.hideAllExcept(containerId);
        });
    }

    pub.initExplorer = function initExplorer(buttonId, containerId) {
        $(buttonId).click(function () {
            NavigationMenu.hideAllExcept(containerId);
            $('#lazy').jstree(true).refresh();
        });
    }

    pub.initOpenCompleteNavigationMenuListener = function initOpenCompleteNavigationMenuListener(headerClass, branchesClass) {
        $(headerClass).click(function () {
            if ($(branchesClass).is(":hidden")) {
                $(branchesClass).show(400);
                $(".symbol_folder_closed").show(400);
            } else {
                $(branchesClass).hide(400);
            }
        });
    }

    pub.initOpenNavigationNodeListener = function initOpenNavigationNodeListener(treeNodeClass) {
        $(treeNodeClass).click(function () {
            $(this).next().toggle(400);
            $(this).toggleClass("symbol_folder_open");
        });
    }

    return pub;

})();