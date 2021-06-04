import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';

export default class Projekt extends ViewModel {

    constructor(model) {
        super();
        this.model = model;
        this.showDelay = 500;
        this.inputProjektId = "projekt_id_input_field";
        this.inputPartnerName = "partner_name_input_field";
        this.formId = "form_projekt_erstellen";
        this.messageId = "projekt_erstellen_save_message";
    }

    render(htmlElementId) {

        const htmlElement = document.getElementById(htmlElementId);

        fetch(Address.PROJEKT.ERSTELLEN_JSP, {
            method: "post",
        })
            .then(response => response.text())
            .then(response => {

                htmlElement.innerHTML = response;

                if (this.model === undefined) this.initProjektErstellen();
                if (this.model !== undefined) this.initProjektBearbeiten(this.model);
            });
    }

    initProjektErstellen() {
        Suche.addGenerierenLinkToInputWithName(
            this.inputProjektId,
            [
                new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, "")
            ],
            new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, "")
        );

        Form.addSubmit(
            Address.PROJEKT.ERSTELLEN_SERVLET,
            this.formId,
            this.messageId,
            this.callbackOnSuccess
        );
    }

    initProjektBearbeiten(model) {

        if (model[Parameters.PROJEKT.PK] === "") {
            const event = new Event(EventType.PROJEKT.SUCHEN);
            this.dispatchEvent(event);
            return false;
        }

        //TODO: state benutzen anstelle von model
        // let partnerNameInput = document.getElementById(this.inputPartnerName);
        // partnerNameInput.value = model[Parameters.PARTNER.PK];

        let projektIdInput = document.getElementById(this.inputProjektId);
        projektIdInput.value = model[Parameters.PROJEKT.PK];

        Form.addSubmit(
            Address.PROJEKT.ERSTELLEN_SERVLET,
            this.formId,
            this.messageId,
            this.callbackOnSuccess
        );

        return true;
    }
    
    callbackOnSuccess() {
        const projektId = document.getElementById(this.inputProjektId).value;
        const event = new Event(EventType.PROJEKT.GESPEICHERT);
        event.data = projektId;
        this.dispatchEvent(event);
    }
}

