import MainState from "./JavaScript/MainState.js";

export default class Main {

    initSubpages (subpages) {

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
}

const mainState = new MainState();
mainState.setCurrentProjekt("1");