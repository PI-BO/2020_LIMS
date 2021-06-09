import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import ExperimentTyp from '../experiment/ExperimentTyp.js';

export default class IR extends ViewModel {

    constructor(state) {
        super(Address.ANALYSE.DATENMASKE.IR);
        this.state = state;
    }

    erstellen() {

        this.init = () => {

        }
    }

    bearbeiten() {

        this.init = () => {

        }
    }
}