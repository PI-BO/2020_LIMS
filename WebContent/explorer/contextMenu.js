const contextMenu = (function () {

    const menu = {};
    let bearbeiten;

    menu.initPartner = function () {
        bearbeiten = id => MainState
            .setProjektPartner(id)
            .then(r => $("#projekt_partner_bearbeiten").click());
        init();
    }

    menu.initProjekt = function () {
        bearbeiten = id => MainState
            .setProjekt(id)
            .then(r => $("#projekt_bearbeiten").click());
        init();
    }

    menu.initProbe = function () {
        bearbeiten = id => MainState
            .setProbe(id)
            .then(r => $("#probeneingang_bearbeiten").click());
        init();
    }

    menu.initExperiment = function () {
        bearbeiten = id => MainState
            .setExperiment(id)
            .then(r => $("#experiment_bearbeiten").click());
        init();
    }

    menu.initAnalyse = function () {
        bearbeiten = id => MainState
            .setAnalyse(id)
            .then(r => $("#analyse_bearbeiten").click());
        init();
    }

    function init() {
        // Kontextmenü jeder Tabellen reihe hinzufügen
        $(".explorer_table_row").each(function () {
            const id = $(this).attr('class').split(' ')[1];
            this.addEventListener('contextmenu', ev => {
                ev.preventDefault();
                const menu = $("#contextMenu");
                menu.css({ left: ev.pageX, top: ev.pageY });
                const button = menu.find("#editProject")
                button.unbind('click');
                button.click(() => bearbeiten(id));
                menu.removeAttr("hidden");
            })
        })

        // EventListener um Kontextmenü wieder auszuschalten
        document.addEventListener('click', function (e) {
            if (e.button === 0) $("#contextMenu").attr("hidden", "");
        });
    }

    return menu;
})();

export default contextMenu;