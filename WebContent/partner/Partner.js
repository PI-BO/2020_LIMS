import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';

export default class Partner extends ViewModel {

    constructor(state) {
        super();
        this.state = state;
        this.inputIdPartnerId = "partner_id_input_field";
        this.inputIdPartnerName = "partner_name_input_field";
        this.inputIdPartnerEmail = "partner_email_input_field";
        this.formId = "form_partner_erstellen";
        this.messageId = "partner_erstellen_save_message";
        this.partnerHeaderId = "partner_header";
        this.html = new Promise((resolve) => {
            fetch(Address.PARTNER.ERSTELLEN_JSP, {
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

        hallo = "hallo";

        this.init = () => {

            document.getElementById(this.partnerHeaderId).innerHTML = "Partner erstellen"
    
            Suche.addGenerierenLinkToInputWithName(
                this.inputIdPartnerId,
                [
                    new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
                ],
                new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
            );

            Form.addSubmit(
                Address.PARTNER.ERSTELLEN_SERVLET,
                this.formId,
                this.messageId,
                this.callbackOnSuccess.bind(this)                
            );
        }
    }

    bearbeiten() {

        this.init = () => {

            document.getElementById(this.partnerHeaderId).innerHTML = "Partner bearbeiten"
    
            if (this.state.PARTNER[Parameters.PARTNER.PK] === "") {
                const event = new Event(EventType.PARTNER.SUCHEN);
                this.dispatchEvent(event);
                return;
            }
    
            let partnerIdInput = document.getElementById(this.inputIdPartnerId);
            partnerIdInput.value = this.state.PARTNER[Parameters.PARTNER.PK];
            partnerIdInput.disabled = true;
    
            let partnerNameInput = document.getElementById(this.inputIdPartnerName);
            partnerNameInput.value = this.state.PARTNER[Parameters.PARTNER.NAME];
    
            let partnerEmailInput = document.getElementsByName(this.inputIdPartnerEmail);
            partnerEmailInput.value = this.state.PARTNER[Parameters.PARTNER.EMAIL];
    
            Form.addSubmit(
                Address.PARTNER.BEARBEITEN_SERVLET,
                this.formId,
                this.messageId,
                this.callbackOnSuccess.bind(this)
            );
        }

    }
    
    callbackOnSuccess() {
        const partnerID = document.getElementById(this.inputIdPartnerId).value;
        const event = new Event(EventType.PARTNER.GESPEICHERT);
        event.data = partnerID;
        this.dispatchEvent(event);
    }
}

