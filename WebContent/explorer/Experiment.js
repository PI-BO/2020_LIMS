import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import '../jstree/jstree.min.js';

export default class Experiment extends ViewModel {

    constructor() {
        super(Address.EXPLORER.EXPERIMENT);
        this.init = () => {

        }
    }
}