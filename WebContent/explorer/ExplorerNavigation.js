import { Parameters } from '../suche/Parameter.js';
import Address from '../Address.js';
import ViewModel from '../ViewModel.js';
import ContextMenu from './contextMenu.js';
import '../jstree/jstree.min.js';
import PartnerList from './PartnerList.js';
import Partner from './Partner.js';
import Projekt from './Projekt.js';
import Probe from './Probe.js';
import Experiment from './Experiment.js';

export default class ExplorerNavigation extends ViewModel {

    constructor(state) {
        super(Address.EXPLORER.NAVIGATION);
        this.explorerState = state;
        this.explorerContentId = "explorer-content";
        this.init = () => {
            $('#evts_button').on("click", function () {
                var instance = $('#lazy').jstree(true);
                instance.deselect_all();
                instance.select_node('projekte:A');
            });

            $('#lazy').jstree({
                core: {
                    data: {
                        url: function (node) {
                            return 'jstree/nodes?id=' + node.id;
                        }
                    }
                },
                search: {
                    // search config
                    show_only_matches: true,
                    ajax: {
                        url: "jstree/search",
                        data: {
                            "Partner": ["${Partner.COLUMN_PRIMARY_KEY}"],
                            "Projekt": ["${Projekt.COLUMN_PRIMARY_KEY}"],
                            "Probe": ["${Probe.COLUMN_PRIMARY_KEY}"],
                            "Experiment": ["${Experiment.COLUMN_PRIMARY_KEY}"],
                            "Analyse": ["${Analyse.COLUMN_PRIMARY_KEY}"]
                            // "Partner": [Parameters.PARTNER.PK],
                            // "Projekt": [Parameters.PROJEKT.PK],
                            // "Probe": [Parameters.PROBE.PK],
                            // "Experiment": [Parameters.EXPERIMENT.PK],
                            // "Analyse": [Parameters.ANALYSE.PK]
                        }
                    }
                },
                plugins: ['search'],
            }).on("select_node.jstree", function (e, data) {
                this.loadNode(data);
            }.bind(this)).on("open_node.jstree", function (e, data) {
                data.instance.set_icon(data.node, "symbol_folder_open");
            }).on("close_node.jstree", function (e, data) {
                data.instance.set_icon(data.node, "symbol_folder_closed");
            });

            let to = false;
            $('#search').keyup(function () {
                if (to) {
                    clearTimeout(to);
                }
                to = setTimeout(function () {
                    let v = $('#search').val();
                    $('#lazy').jstree(true).search(v);
                }, 250);
            });

            // Get checkbox click
            $(".checkbox").change(function () {
                const json = $('#lazy').jstree(true).settings.search.ajax.data;
                const type = this.id.split(':')[0]
                const id = this.id.split(':')[1]
                if (!json.hasOwnProperty(type)) {
                    json[type] = []
                }
                if (this.checked) {
                    json[type].push(id);
                } else {
                    json[type].splice(id, 1);
                }
            });
        }
    }

    loadNode(data) {
        const nodeCategory = data.node.id.split(":")[0];
        const nodeId = data.node.id.split(":")[1]
        const path = this.createPath(data);

        let url;
        let explorerPage;
        if (nodeCategory == "j1_1") {	//Projekte Node
            // url = "<%=Address.getPartnerListJSP()%>";
            // url = Address.EXPLORER.PARTNER_LIST;
            explorerPage = new PartnerList();
            ContextMenu.initPartner();
        } else if (nodeCategory == Parameters.PARTNER.CATEGORY) {
            // url = "<%=Address.getPartnerJSP()%>"
            // url = Address.EXPLORER.PARTNER;
            explorerPage = new Partner();
            ContextMenu.initProjekt();
        } else if (nodeCategory == Parameters.PROJEKT.CATEGORY) {
            // url = "<%=Address.getProjektJSP()%>";
            // url = Address.EXPLORER.PROJEKT;
            explorerPage = new Projekt();
            ContextMenu.initProbe();
        } else if (nodeCategory == Parameters.PROBE.CATEGORY) {
            // url = "<%=Address.getProbeJSP()%>"
            // url = Address.EXPLORER.PROBE;
            explorerPage = new Probe();
            ContextMenu.initExperiment();
        } else if (nodeCategory == Parameters.EXPERIMENT.CATEGORY) {
            // url = "<%=Address.getExperimentJSP()%>"
            // url = Address.EXPLORER.EXPERIMENT;
            explorerPage = new Experiment();
            ContextMenu.initAnalyse();
        } else {
            return;
        }

        explorerPage.render(this.explorerContentId);

        this.explorerState.setState(path)
    }

    createPath(data) {
        const path = [];

        // actual node
        path.push({
            table: data.node.id.split(":")[0],
            id: data.node.id.split(":")[1],
            text: data.node.text
        })

        // parent nodes
        for (let i = 0; i < data.node.parents.length - 1; i++) {
            let parent_n = data.instance.get_node(data.node.parents[i]);
            path.unshift({
                table: parent_n.id.split(":")[0],
                id: parent_n.id.split(":")[1],
                text: parent_n.text
            })
        }

        return path;
    }
}