import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import ExperimentTyp from '../experiment/ExperimentTyp.js';
import DSC from './DSC.js';
import PXRD from './PXRD.js';
import TGA from './TGA.js';
import IR from './IR.js';

export default class Analyse extends ViewModel {

    constructor(state) {
        super(Address.ANALYSE.ERSTELLEN_JSP);
        this.state = state;
        this.headerId = "analyse_erstellen_main_header";
        this.selectAnalyseTypId = "select_analyse_typ";
        this.contentId = "analyse_erstellen_content";
    }

    erstellen() {

        this.init = () => {

            document.getElementById(this.headerId).innerHTML = "Analyse erstellen"

            document.getElementById(this.selectAnalyseTypId).addEventListener("change", (event) => {
                showAnalysetypFieldsMethode.bind(this)(event.target.value); //TODO:
            })

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

function showAnalysetypFieldsMethode(select) {
    let datenmaske;
    switch (select) {
        case "1":
            datenmaske = new PXRD(this.state);
            datenmaske.erstellen();
            break;
        case "2":
            datenmaske = new DSC(this.state);
            datenmaske.erstellen();
            break;
        case "3":
            datenmaske = new TGA(this.state);
            datenmaske.erstellen();
            break;
        case "4":
            datenmaske = new IR(this.state);
            datenmaske.erstellen();
            break;
        default:
            return;
    }

    datenmaske.render(this.contentId);
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

