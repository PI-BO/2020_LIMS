import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';

export default class Projekt extends ViewModel {

    constructor(state) {
        super();
        this.state = state;
        this.inputIdProjektId = "projekt_id_input_field";
        this.inputIdPartnerName = "partner_name_input_field";
        this.inputIdPartnerId = "partner_id_input_field";
        this.formId = "form_projekt_erstellen";
        this.messageId = "projekt_erstellen_save_message";
        this.projektHeaderId = "projekt_header";
        this.html = new Promise((resolve) => {
            fetch(Address.PROJEKT.ERSTELLEN_JSP, {
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

            document.getElementById(this.projektHeaderId).innerHTML = "Projekt erstellen"

            if (this.state.PARTNER[Parameters.PARTNER.PK] === "") {
                const event = new Event(EventType.PARTNER.SUCHEN);
                this.dispatchEvent(event);
                return;
            }

            Suche.addGenerierenLinkToInputWithName(
                this.inputIdProjektId,
                [
                    new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, "")
                ],
                new Parameter(Parameters.PROJEKT.CATEGORY, Parameters.PROJEKT.PK, "")
            );

            let partnerNameInput = document.getElementById(this.inputIdPartnerName);
            partnerNameInput.value = this.state.PARTNER[Parameters.PARTNER.NAME];

            let partnerIdInput = document.getElementById(this.inputIdPartnerId);
            partnerIdInput.value = this.state.PARTNER[Parameters.PARTNER.PK];

            Form.addSubmit(
                Address.PROJEKT.ERSTELLEN_SERVLET,
                this.formId,
                this.messageId,
                this.callbackOnSuccess.bind(this)
            );
        }
    }

    bearbeiten() {

        this.init = () => {

            document.getElementById(this.projektHeaderId).innerHTML = "Projekt bearbeiten"

            if (this.state.PROJEKT[Parameters.PROJEKT.PK] === "") {
                const event = new Event(EventType.PROJEKT.SUCHEN);
                this.dispatchEvent(event);
                return;
            }

            let partnerNameInput = document.getElementById(this.inputIdPartnerName);
            partnerNameInput.value = this.state.PARTNER[Parameters.PARTNER.NAME];
            partnerNameInput.disabled = true;
            
            let partnerIdInput = document.getElementById(this.inputIdPartnerId);
            partnerIdInput.value = this.state.PARTNER[Parameters.PARTNER.PK];
            partnerIdInput.disabled = true;
            
            let projektIdInput = document.getElementById(this.inputIdProjektId);
            projektIdInput.value = this.state.PROJEKT[Parameters.PROJEKT.PK];
            projektIdInput.disabled = true;

            Form.addSubmit(
                Address.PROJEKT.BEARBEITEN_SERVLET,
                this.formId,
                this.messageId,
                this.callbackOnSuccess.bind(this)
            );
        }
    }

    callbackOnSuccess() {
        const projektId = document.getElementById(this.inputIdProjektId).value;
        const event = new Event(EventType.PROJEKT.GESPEICHERT);
        event.data = projektId;
        this.dispatchEvent(event);
    }
}

