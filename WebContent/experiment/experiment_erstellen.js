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
    experimentErstellenPosting.done(function (data) {
        $("#experiment_erstellen_content").empty().append(data);
    });
}

function newExperimentSerie(value) {
    const text = $("#experiment_serie_text")
    if (value === "new") text.show()
    else text.hide()
}

function newExperimentDurchfuehrungstext(select) {
    const text = $("#experiment_durchführungstext_text")
    const value = select.value
    const durchfuehrungstext = select.selectedOptions[0].firstChild.data.trim()
    text.val(durchfuehrungstext)
    text.prop("readonly", value !== "new")
    const titel = $("#experiment_durchfuehrungstext_titel")
    if (value === "new") titel.show()
    else titel.hide()
}