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
    const isNew = (value === "new")
    text.prop("required", isNew)
    if (isNew) text.show()
    else text.hide()
}

function newExperimentDurchfuehrungstext(select) {
    const text = $("#experiment_durchführungstext_text")
    const value = select.value
    const durchfuehrungstext = select.selectedOptions[0].firstChild.data.trim()
    text.val(durchfuehrungstext)
    const isNew = (value === 'new')
    text.prop("readonly", !isNew)
    const titel = $("#experiment_durchfuehrungstext_titel")
    titel.prop('required', isNew)
    if (isNew) titel.show()
    else titel.hide()
}

function replaceContent(id, text, color){
    const element = document.getElementById(id);
    element.style.color = color;
    while(element.firstChild){
        element.removeChild(element.firstChild);
    }
    element.append(text);
}