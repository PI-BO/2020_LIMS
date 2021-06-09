import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import '../jstree/jstree.min.js';

export default class ProjekteList extends ViewModel {

    constructor() {
        super(Address.EXPLORER.PROJEKTE_LIST);
        this.init = () => {

        }
    }
}