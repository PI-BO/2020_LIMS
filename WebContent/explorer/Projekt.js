import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import '../jstree/jstree.min.js';

export default class Projekt extends ViewModel {

    constructor() {
        super(Address.EXPLORER.PROJEKT);
        this.init = () => {
            $(".explorer_table_data").click(function(){
				let data = {projekt_id : $(this).text()};
				loadPage(Address.EXPLORER.PROJEKT, data);
			});
        }
    }
}