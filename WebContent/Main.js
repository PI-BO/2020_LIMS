const Main = (function () {

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

    return public;

})();