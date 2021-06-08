import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import ExperimentTyp from './ExperimentTyp.js';

export default class Experiment extends ViewModel {

    constructor(state) {
        super();
        this.state = state;
        this.headerId = "experiment_erstellen_main_header";
        this.selectExperimentTypId = "select_experiment_typ";
        this.experimentTypContentId = "experiment_erstellen_content"
        this.html = new Promise((resolve) => {
            fetch(Address.EXPERIMENT.ERSTELLEN_JSP, {
                method: "post",
            })
                .then(response => response.text())
                .then(response => {
                    this.html = response;
                    resolve();
                });
        })
    }

    async render(htmlElementId) {

        const htmlElement = document.getElementById(htmlElementId);
        await this.html;
        htmlElement.innerHTML = this.html;
        this.init();
    }

    erstellen() {

        this.init = () => {

            document.getElementById(this.headerId).innerHTML = "Experiment erstellen"

            document.getElementById(this.selectExperimentTypId).addEventListener("change", (event) => {
                showExperimenttypFieldsMethode.bind(this)(event.target.value);
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

            document.getElementById(this.headerId).innerHTML = "Experiment bearbeiten"

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

function showExperimenttypFieldsMethode(select) {
    let typ;
    let experimentTyp;
    switch (select) {
        case "202":
            typ = "slurry";
            experimentTyp = new ExperimentTyp(typ);
            experimentTyp.erstellen();
            break;
        case "101":
            typ = "verdampfung";
            experimentTyp = new ExperimentTyp(typ);
            experimentTyp.erstellen();
            break;
        default:
            return;
    }

    experimentTyp.render(this.experimentTypContentId);
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

function replaceContent(id, text, color) {
    $(`#${id}`).empty().append(`<div style="color: ${color}">${text}</div>`)
}
