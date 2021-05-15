const contextMenu = (function () {

    this.menu = {};
    let bearbeiten;

    this.menu.initPartner = function () {
        bearbeiten = id => MainState
            .setProjektPartner(id)
            .then(r => $("#projekt_partner_bearbeiten").click());
        init();
    }

    this.menu.initProjekt = function () {
        bearbeiten = id => MainState
            .setProjekt(id)
            .then(r => $("#projekt_bearbeiten").click());
        init();
    }

    this.menu.initProbe = function () {
        bearbeiten = id => MainState
            .setProbe(id)
            .then(r => $("#probeneingang_bearbeiten").click());
        init();
    }

    this.menu.initExperiment = function () {
        bearbeiten = id => MainState
            .setExperiment(id)
            .then(r => $("#experiment_bearbeiten").click());
        init();
    }

    this.menu.initAnalyse = function () {
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
                menu.css({left: ev.pageX, top: ev.pageY});
                menu.find("#editProject").click(() => bearbeiten(id));
                menu.removeAttr("hidden");
            })
        })

        // EventListener um Kontextmenü wieder auszuschalten
        document.addEventListener( 'click', function(e) {
            if ( e.button === 0 ) $("#contextMenu").attr("hidden", "");
        });
    }

    return this.menu;
})();