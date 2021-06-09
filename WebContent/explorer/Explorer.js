import Suche from '../suche/Suche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';
import EventType from '../EventType.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import ContextMenu from './contextMenu.js';
import ExplorerNavigation from './ExplorerNavigation.js';

export default class Explorer extends ViewModel {

    constructor() {
        super(Address.EXPLORER.JSP);
        this.explorerState = new ExplorerState();
        this.explorerState.setPartnerListAddress(Address.EXPLORER.PARTNER_LIST);
        this.explorerState.setPartnerAddress(Address.EXPLORER.PARTNER);
        this.explorerState.setProjektAddress(Address.EXPLORER.PROJEKT);
        this.explorerState.setProbeAddress(Address.EXPLORER.PROBE);
        this.explorerState.setExperimentAddress(Address.EXPLORER.EXPERIMENT);
        this.navigationContentId = "explorer-navigation-tree";
        this.init = () => {
            
            this.explorerNavigation = new ExplorerNavigation(this.explorerState);
            this.explorerNavigation.render(this.navigationContentId);
        }
    }

    // erstellen() {

    //     this.init = () => {

    //     }
    // }

    // bearbeiten() {

    //     this.init = () => {

    //     }
    // }
}

function addSymbolToggleListenerToCssClass(className, cssSymbolId) {
    const toggler = document.getElementsByClassName(className);

    for (let i = 0; i < toggler.length; i++) {
        toggler[i].addEventListener("click", function () {
            this.classList.toggle(cssSymbolId);
        });
    }
}

function loadPage(pageAddress, data) {
    const posting = $.post(pageAddress, data);
    posting.done(function (data) {
        $("#explorer-content").empty().append(data);
    });
}

function sortExplorerTable(n) {
    let table, rows, switching, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("explorer_table");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /*
     * Make a loop that will continue until no switching has been done:
     */
    while (switching) {
        // start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        /*
         * Loop through all table rows (except the first, which contains table
         * headers):
         */
        for (let i = 1; i < (rows.length - 1); i++) {
            // start by saying there should be no switching:
            shouldSwitch = false;
            /*
             * Get the two elements you want to compare, one from current row
             * and one from the next:
             */
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /*
             * check if the two rows should switch place, based on the
             * direction, asc or desc:
             */
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    // if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    // if so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /*
             * If a switch has been marked, make the switch and mark that a
             * switch has been done:
             */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /*
             * If no switching has been done AND the direction is "asc", set the
             * direction to "desc" and run the while loop again.
             */
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

class ExplorerState {
    constructor() {
        this.state = null;
    }

    setPartnerListAddress(address) {
        this.partnerListAddress = address;
    }

    setPartnerAddress(address) {
        this.partnerAddress = address;
    }

    setProjektAddress(address) {
        this.projektAddress = address;
    }

    setProbeAddress(address) {
        this.probeAddress = address;
    }

    setExperimentAddress(address) {
        this.experimentAddress = address;
    }

    setState(state) {
        this.state = state;
        $("#explorer-header").empty().append(this.getPath());
    }

    pushToState(item) {
        this.state.push(item);
        $("#explorer-header").empty().append(this.getPath());
    }

    createPathElement() {
        const pathDiv = document.createElement("DIV");
        pathDiv.setAttribute("class", "explorer-path-element");

        return pathDiv;
    }

    createPathDivisor() {
        const pathDiv = document.createElement("DIV");
        pathDiv.setAttribute("class", "explorer-path-divisor symbol_triangle_right");

        return pathDiv;
    }

    getPath() {
        const divList = []

        for (let i = 0; i < this.state.length; i++) {
            const item = this.state[i]
            let url;
            if (item.table == "j1_1") {	//Projekte Node
                url = this.partnerListAddress;
            } else if (item.table == "partner") {
                url = this.partnerAddress;
            } else if (item.table == "projekte") {
                url = this.projektAddress;
            } else if (item.table == "probe") {
                url = this.probeAddress;
            } else if (item.table == "experiment") {
                url = this.experimentAddress;
            } else {
                return divList;
            }

            let partnerListDiv = this.createPathElement();
            partnerListDiv.onclick = () => {
                loadPage(url, { projekt_id: item.id });
                this.setState(this.state.slice(0, i + 1));
            }
            partnerListDiv.innerHTML = item.text;
            if (item.table != "j1_1") divList.push(this.createPathDivisor());
            divList.push(partnerListDiv);
        }

        return divList;
    }
}