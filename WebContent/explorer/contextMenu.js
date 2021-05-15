const contextMenu = (function () {

    this.menu = {};
    let func;

    this.menu.initPartner = function () {
        func = id => {
            MainState.setProjektPartner(id)
            setTimeout(() => $("#projekt_partner_bearbeiten").click(), 60);
        }
        init()
    }

    this.menu.initProjekt = function () {
        func = id => {
            MainState.setProjekt(id)
            setTimeout(() => $("#projekt_bearbeiten").click(), 60);
        }
        init()
    }

    this.menu.initProbe = function () {
        func = id => {
            MainState.setProbe(id)
            setTimeout(() => $("#probeneingang_bearbeiten").click(), 60);
        }
        init()
    }

    this.menu.initExperiment = function () {
        func = id => {
            MainState.setExperiment(id)
            setTimeout(() => $("#experiment_bearbeiten").click(), 60);
        }
        init()
    }

    this.menu.initAnalyse = function () {
        func = id => {
            MainState.setAnalyse(id)
            setTimeout(() => $("#analyse_bearbeiten").click(), 60);
        }
        init()
    }

    function init() {
        $(".explorer_table_row").each(function () {
            const id = $(this).attr('class').split(' ')[1]
            this.addEventListener('contextmenu', ev => {
                ev.preventDefault();
                const menu = $("#contextMenu")
                menu.css({left: ev.pageX, top: ev.pageY})
                menu.find("#editProject").click(() => func(id))
                menu.removeAttr("hidden")
            })
        })

        // EventListener um Kontextmenü wieder auszuschalten
        document.addEventListener( 'click', function(e) {
            if ( e.button === 0 ) $("#contextMenu").attr("hidden", "");
        });
    }

    return this.menu;
})();