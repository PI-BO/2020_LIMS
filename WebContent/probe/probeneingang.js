import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';

export default class Probeneingang extends ViewModel {

    constructor(state) {
        super();
        this.state = state;
        this.inputIdProjektId = "projekt_id_input_field";
        this.inputIdPartnerName = "partner_name_input_field";
        this.inputIdProbeId = "probe_id_input_field";
        this.formId = "form_probeneingang";
        this.messageId = "probeneingang_erstellen_save_message";
        this.imageUploadElementId = "input_image_upload";
        this.imageResetButtonId = "input_image_reset_button";
        this.previewContainerId = "preview-container";
        this.imageContainerId = "image-container";
        this.previewImageClass = "preview-image";
        this.html = new Promise((resolve) => {
            fetch(Address.PROBE.ERSTELLEN_JSP, {
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

            if (this.state.PROJEKT[Parameters.PROJEKT.PK] === "") {
                const event = new Event(EventType.PROBE.SUCHEN);
                this.dispatchEvent(event);
                return;
            }

            Suche.addGenerierenLinkToInputWithName(
                this.inputIdProbeId,
                [
                    new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, "")
                ],
                new Parameter(Parameters.PROBE.CATEGORY, Parameters.PROBE.PK, "")
            );

            let partnerNameInput = document.getElementById(this.inputIdPartnerName);
            partnerNameInput.value = this.state.PARTNER[Parameters.PARTNER.NAME];
            partnerNameInput.disabled = true;

            let projektIdInput = document.getElementById(this.inputIdProjektId);
            projektIdInput.value = this.state.PROJEKT[Parameters.PROJEKT.PK];
            projektIdInput.disabled = true;

            Form.addMultipartSubmit(
                Address.PROBE.ERSTELLEN_SERVLET,
                this.formId,
                this.messageId,
                this.callbackOnSuccess.bind(this)
            );

            const imgUploadElement = document.getElementById(this.imageUploadElementId)
            imgUploadElement.addEventListener("change", () => {
                loadPreviewImage.bind(this)(imgUploadElement);
                
            })

            const imgUploadResetButton = document.getElementById(this.imageResetButtonId);
            imgUploadResetButton.addEventListener("click", () => {
                deletePreviewImages.bind(this)(imgUploadElement);
            })

        }
    }

    bearbeiten() {

        this.init = () => {

            if (this.state.PROJEKT[Parameters.PROJEKT.PK] === "") {
                const event = new Event(EventType.PROBE.SUCHEN);
                this.dispatchEvent(event);
            }

            let partnerNameInput = document.getElementById(this.inputIdPartnerName);
            partnerNameInput.value = this.state.PARTNER[Parameters.PARTNER.NAME];
            partnerNameInput.disabled = true;

            let projektIdInput = document.getElementById(this.inputIdProjektId);
            projektIdInput.value = this.state.PROJEKT[Parameters.PROJEKT.PK];
            projektIdInput.disabled = true;

            Form.addMultipartSubmit(
                Address.PROBE.BEARBEITEN_SERVLET,
                this.formId,
                this.messageId,
                this.callbackOnSuccess.bind(this)
            );
        }
    }

    callbackOnSuccess() {
        const probeId = document.getElementById(this.inputIdProbeId).value;
        const event = new Event(EventType.PROBE.GESPEICHERT);
        event.data = probeId;
        this.dispatchEvent(event);
    }
}

// load small preview pictures when files have been selected
function loadPreviewImage(input) {

    const previewContainer = document.getElementById(this.previewContainerId);
    previewContainer.innerHTML = "";

    for (let i = 0; i < input.files.length; i++) {
        const objectURL = URL.createObjectURL(input.files[i]);
        console.log({ objectURL })
        previewContainer.innerHTML += "<div class=" + this.imageContainerId + "><img class=" + this.previewImageClass + " style='width:100%; height=100%' src=" + objectURL + "></div>";
        freeMemory(previewContainer);

    }
}

function freeMemory(element) {
    URL.revokeObjectURL(element.src);
}

function deletePreviewImages(imgUploadElement) {
    imgUploadElement.value = "";
    const previewContainer = document.getElementById(this.previewContainerId);
    previewContainer.innerHTML = "";
}

function replaceContent(id, text, color) {
    const element = document.getElementById(id);
    element.style.color = color;
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
    element.append(text);
}

