import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import '../jstree/jstree.min.js';

export default class Partner extends ViewModel {

    constructor() {
        super(Address.EXPLORER.PARTNER);
        this.init = () => {

        }
    }
}