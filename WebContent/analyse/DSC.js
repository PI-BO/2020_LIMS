import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import ExperimentTyp from '../experiment/ExperimentTyp.js';

export default class DSC extends ViewModel {

    constructor(state) {
        super(Address.ANALYSE.DATENMASKE.DSC);
        this.state = state;
    }

    erstellen() {

        this.init = () => {

            // document.getElementById(this.headerId).innerHTML = "Analyse erstellen"

            // document.getElementById(this.selectAnalyseTypId).addEventListener("change", (event) => {
            //     showAnalysetypFieldsMethode.bind(this)(event.target.value); //TODO:
            // })

            //TODO:
            // Form.addSubmit(
            //     Address.PROJEKT.ERSTELLEN_SERVLET,
            //     this.formId,
            //     this.messageId,
            //     this.callbackOnSuccess.bind(this)
            // );
        }
    }

    bearbeiten() {

        this.init = () => {

            document.getElementById(this.headerId).innerHTML = "Analyse bearbeiten"

            //TODO:
            // Form.addSubmit(
            //     Address.PROJEKT.BEARBEITEN_SERVLET,
            //     this.formId,
            //     this.messageId,
            //     this.callbackOnSuccess.bind(this)
            // );
        }
    }

    //TODO:
    callbackOnSuccess() {
        // const projektId = document.getElementById(this.inputIdProjektId).value;
        // const event = new Event(EventType.PROJEKT.GESPEICHERT);
        // event.data = projektId;
        // this.dispatchEvent(event);
    }
}

// function showExperimenttypFieldsMethode(select) {
//     let typ;
//     let experimentTyp;
//     switch (select) {
//         case "202":
//             typ = "slurry";
//             experimentTyp = new ExperimentTyp(typ);
//             experimentTyp.erstellen();
//             break;
//         case "101":
//             typ = "verdampfung";
//             experimentTyp = new ExperimentTyp(typ);
//             experimentTyp.erstellen();
//             break;
//         default:
//             return;
//     }

//     experimentTyp.render(this.experimentTypContentId);
// }

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

    console.log({analyseErstellenSubPage})

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

