import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import '../jstree/jstree.min.js';

export default class Probe extends ViewModel {

    constructor() {
        super(Address.EXPLORER.PROBE);
        this.init = () => {

        }
    }
}