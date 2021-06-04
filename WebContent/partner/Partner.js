import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';

export default class Partner extends ViewModel {

    constructor(model) {
        super();
        this.model = model;
        this.showDelay = 500;
        this.inputPartnerId = "partner_id_input_field";
        this.inputPartnerName = "partner_name_input_field";
        this.inputPartnerEmail = "partner_email_input_field";
        this.formId = "form_partner_erstellen";
        this.messageId = "partner_erstellen_save_message";
    }

    render(htmlElementId) {

        const htmlElement = document.getElementById(htmlElementId);

        fetch(Address.PARTNER.ERSTELLEN_JSP, {
            method: "post",
        })
            .then(response => response.text())
            .then(response => {

                htmlElement.innerHTML = response;

                if (this.model === undefined) this.initPartnerErstellen();
                if (this.model !== undefined) this.initPartnerBearbeiten(this.model);
            });
    }

    initPartnerErstellen() {
        Suche.addGenerierenLinkToInputWithName(
            this.inputPartnerId,
            [
                new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
            ],
            new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
        );

        Form.addSubmit(
            Address.PARTNER.ERSTELLEN_SERVLET,
            this.formId,
            this.messageId,
            this.callbackOnSuccess
        );
    }

    initPartnerBearbeiten(model) {

        if (model[Parameters.PARTNER.PK] === "") {
            const event = new Event(EventType.PARTNER.SUCHEN);
            this.dispatchEvent(event);
            return false;
        }

        let partnerIdInput = document.getElementById(this.inputPartnerId);
        partnerIdInput.value = model[Parameters.PARTNER.PK];

        let partnerNameInput = document.getElementById(this.inputPartnerName);
        partnerNameInput.value = model[Parameters.PARTNER.NAME];

        let partnerEmailInput = document.getElementsByName(this.inputPartnerEmail);
        partnerEmailInput.value = model[Parameters.PARTNER.EMAIL];

        Form.addSubmit(
            Address.PARTNER.ERSTELLEN_SERVLET,
            this.formId,
            this.messageId,
            this.callbackOnSuccess
        );

        return true;
    }
    
    callbackOnSuccess() {
        const partnerID = document.getElementById(this.inputPartnerId).value;
        const event = new Event(EventType.PARTNER.GESPEICHERT);
        event.data = partnerID;
        this.dispatchEvent(event);
    }
}

