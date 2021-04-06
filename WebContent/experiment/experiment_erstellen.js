function showExperimenttypFieldsMethode(select) {
    let experimentTyp;
    switch (select) {
        case "202":
            experimentTyp = "slurry";
            break;
        case "101":
            experimentTyp = "verdampfung";
            break;
        default:
            return;
    }

    const experimentErstellenPosting = $.post("experiment/experimenttyp.jsp", {typ: experimentTyp});
    experimentErstellenPosting.done(function (data) {
        $("#experiment_erstellen_content").empty().append(data);
    });

    $('#experiment_erstellen_speichern').empty().append('<button id="button_experiment_speichern" type="submit">Speichern</button>')
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
    $(`#${id}`).empty().append(`<div style="color: ${color}">${text}</div>`)
}