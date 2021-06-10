import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import '../jstree/jstree.min.js';
import { loadPage } from './Explorer.js';
import EventType from '../EventType.js';

export default class PartnerList extends ViewModel {

    constructor() {
        super(Address.EXPLORER.PARTNER_LIST);
        this.explorerTableId = "explorer_table";
        this.explorerTableRowClass = "explorer_table_row";
        this.init = () => {

            const explorerTableRows = document.getElementsByClassName(this.explorerTableRowClass);

            for (let i = 0; i < explorerTableRows.length; i++) {

                const id = explorerTableRows[i].getElementsByTagName("id")[0].innerText.trim();

                console.log({ id })

                explorerTableRows[i].addEventListener("click", () => {


                    let data = { projekt_id: id };
                    this.dispatchEvent(new Event(EventType.EXPLORER.PARTNER_LIST))
                    // loadPage(Address.EXPLORER.PARTNER, data);
                    // explorerState.pushToState({
                    //     table: '<%=partner.getTable()%>',
                    //     id: '<%=partner.getPrimaryKey()%>',
                    //     text: '<%=((Partner) partner).getName()%>'
                    // });

                });
            }
        }
    }
}