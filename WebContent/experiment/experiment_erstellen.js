function showExperimenttypFieldsMethode(select) {
    let experimentErstellenSubPage;
    switch (select) {
        case "202":
            experimentErstellenSubPage = "experiment/experimenttyp_slurry.jsp";
            break;
        case "101":
            experimentErstellenSubPage = "experiment/experimenttyp_verdampfung.jsp";
            break;
        default:
            return;
    }

    const experimentErstellenPosting = $.post(experimentErstellenSubPage, {});
    experimentErstellenPosting.done(function(data) {
        $("#experiment_erstellen_content").empty().append(data);
    });
}

function newExperimentSerie(value) {
    const text = $("#experiment_serie_text")
    if (value === "new") text.show()
    else text.hide()
}

function newExperimentDurchfuehrungstext(value) {
    const text = $("#experiment_durchführungstext_text")
    text.prop( "disabled", value !== "new" );
}