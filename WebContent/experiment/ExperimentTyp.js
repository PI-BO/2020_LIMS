import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';

export default class ExperimentTyp extends ViewModel {

    constructor(typ) {
        super();
        this.typ = typ;
        this.html = new Promise((resolve) => {
            fetch(Address.EXPERIMENT_TYP.ERSTELLEN_JSP, {
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

            switch (this.typ) {
                case 'slurry':
                    console.log("remove Verdampfung")
                    $('.typ_Verdampfung').remove();
                    break;
                    case 'verdampfung':
                    console.log("remove Slurry")
                    $('.typ_Slurry').remove();
                    break;
            }
        }
    }

    bearbeiten() {

        this.init = () => {
        }
    }

    callbackOnSuccess() {
        //TODO:
        // const projektId = document.getElementById(this.inputIdProjektId).value;
        // const event = new Event(EventType.PROJEKT.GESPEICHERT);
        // event.data = projektId;
        // this.dispatchEvent(event);
    }
}
