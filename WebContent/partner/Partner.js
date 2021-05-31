import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';

export default class Partner {

    render(htmlElement) {

        const url = Address.PARTNER.ERSTELLEN_JSP;

        fetch(url, {
            method: "post",
        })
            .then(response => response.text())
            .then(response => {

                htmlElement.innerHTML = response

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
                    htmlElement.dispatchEvent(event);
                }

                Form.addSubmit(
                    servletUrl,
                    formId,
                    messageId,
                    callbackOnSuccess
                );
            });
    }
}