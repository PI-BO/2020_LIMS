const Main = (function () {
    
    const public = {};

    let subPages;
    let sucheServletAddress;

    public.setSubpages = function setSubpages(pages) {
        subPages = pages;
    }

    public.setSucheServletAddress = function setSucheServletAddress(address) {
        sucheServletAddress = address;
    }

    public.init = async function init() {
        
        await initSubpages(subPages);
        await GlobaleSuche.init(sucheServletAddress);

        NavigationMenu.open("#partner_erstellen");
        // NavigationMenu.open("#projekt_erstellen");
        // NavigationMenu.open("#substanz_erstellen");
        // NavigationMenu.open("#experiment_erstellen");
        // NavigationMenu.open("#analyse_erstellen");
        // NavigationMenu.open("#probeneingang_erstellen");
        // NavigationMenu.open("#eingangsanalytik_erstellen");
    }

    function initSubpages(subpages) {

        return new Promise(resolve => {

            const length = Object.keys(subpages).length;
            let i = 0;
            for (let key in subpages) {

                const url = key;
                const id = subpages[key];
                i++;

                $(document).ready(function () {
                    var posting = $.post(url, {});
                    posting.done(function (data) {
                        $(id).empty().append(data);
                        if (i === length) resolve();
                    })
                });
            }
        });
    }

    return public;

})();