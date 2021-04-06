const MainState = (function () {

    public.state = {};
    
    resetState();
    
    function resetState(){
        public.state = {
            [Parameters.PARTNER.CATEGORY]: new Model(Parameters.PARTNER),
            [Parameters.PROJEKT.CATEGORY]: new Model(Parameters.PROJEKT),
            [Parameters.PROBE.CATEGORY]: new Model(Parameters.PROBE)
        }
    }

    public.setProjektPartner = function setProjektPartner(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, id)
                ], (searchResults) => {
                    resetState();
                    public.state[Parameters.PARTNER.CATEGORY] = searchResults[0][0];
                    resolve();
                }
            )
        });
    }

    public.setProjekt = function setProjekt(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, id)
                ], async (searchResults) => {
                    const projekt = searchResults[0][0];
                    await public.setProjektPartner(projekt[Parameters.PROJEKT.FK])
                    public.state[Parameters.PROJEKT.CATEGORY] = projekt;
                    resolve();
                }
            )
        })
    }

    public.setProbe = function setProbe(id) {
        return new Promise(resolve => {
            GlobaleSuche.backgroundSearch(
                [
                    new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, id)
                ], async (searchResults) => {
                    const probe = searchResults[0][0];
                    await public.setProjekt(probe[Parameters.PROBE.FK]);
                    public.state[Parameters.PROBE.CATEGORY] = probe;
                    resolve();
                }
            )
        })
    }

    return public;
})();