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
}

function newTemperaturprogrammRow() {
    const temperaturprogramm = $("#template_temperaturprogramme tr:last")
    const row = $(".temperaturprogramm_tamplate_table_row").clone();
    row.attr("class", "temperaturprogramm_table_row")
    temperaturprogramm.after(row)
}

function replaceContent(id, text, color){
    const element = document.getElementById(id);
    element.style.color = color;
    while(element.firstChild){
        element.removeChild(element.firstChild);
    }
    element.append(text);
}