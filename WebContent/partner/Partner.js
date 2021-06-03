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
    }

    render(htmlElementId) {

        const htmlElement = document.getElementById(htmlElementId);

        const url = Address.PARTNER.ERSTELLEN_JSP;

        fetch(url, {
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
            "partner_id_input_field",
            [
                new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
            ],
            new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
        );

        const servletUrl = Address.PARTNER.ERSTELLEN_SERVLET;
        const formId = "form_partner_erstellen";
        const messageId = "partner_erstellen_save_message";

        const callbackOnSuccess = () => {
            const partnerID = document.getElementById("partner_id_input_field").value;
            const event = new Event(EventType.PARTNER.GESPEICHERT);
            event.data = partnerID;
            this.dispatchEvent(event);
        }

        Form.addSubmit(
            servletUrl,
            formId,
            messageId,
            callbackOnSuccess
        );
    }

    initPartnerBearbeiten(model) {

        if (model[Parameters.PARTNER.PK] === "") {
            const event = new Event(EventType.PARTNER.SUCHEN);
            this.dispatchEvent(event);
            return false;
        }

        let partnerIdInput = document.getElementById("partner_id_input_field");
        partnerIdInput.value = model[Parameters.PARTNER.PK];

        let partnerNameInput = document.getElementById("partner_name_input_field");
        partnerNameInput.value = model[Parameters.PARTNER.NAME];

        let partnerEmailInput = document.getElementsByName("partner_email_input_field");
        partnerEmailInput.value = model[Parameters.PARTNER.EMAIL];

        const servletUrl = Address.PARTNER.ERSTELLEN_SERVLET;
        const formId = "form_partner_erstellen";
        const messageId = "partner_erstellen_save_message";

        const callbackOnSuccess = () => {
            const partnerID = document.getElementById("partner_id_input_field").value;
            const event = new Event(EventType.PARTNER.GESPEICHERT);
            event.data = partnerID;
            this.dispatchEvent(event);
        }

        Form.addSubmit(
            servletUrl,
            formId,
            messageId,
            callbackOnSuccess
        );

        return true;
    }
}

