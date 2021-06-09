function showAnalysetypFieldsMethode(select) {
    let analyseErstellenSubPage;
    switch (select) {
        case "1":
            analyseErstellenSubPage = "analyse/datenmaske_pxrd.jsp";
            break;
        case "2":
            analyseErstellenSubPage = "analyse/datenmaske_dsc.jsp";
            break;
        case "3":
            analyseErstellenSubPage = "analyse/datenmaske_tga.jsp";
            break;
        case "4":
            analyseErstellenSubPage = "analyse/datenmaske_ir.jsp";
            break;
        default:
            return;
    }

    const analyseErstellenPosting = $.post(analyseErstellenSubPage, {});
    analyseErstellenPosting.done(function (data) {
        $("#analyse_erstellen_content").empty().append(data);
    });

    $('#analyse_erstellen_speichern').empty().append('<button id="button_analyse_speichern" type="submit">Speichern</button>')
}

function newTemperaturprogrammRow() {
    const temperaturprogramm = $("#template_temperaturprogramme tr:last")
    const row = $(".temperaturprogramm_tamplate_table_row").clone();
    row.attr("class", "temperaturprogramm_table_row")
    row.children("td.eingangsanalyse_entry").children(`input[name="ANALYSE_TEMPERATURPROGRAMM_SCHRITT"]`).prop('required', true)
    temperaturprogramm.after(row)
}

function replaceContent(id, text, color){
    $(`#${id}`).empty().append(`<div style="color: ${color}">${text}</div>`)
}