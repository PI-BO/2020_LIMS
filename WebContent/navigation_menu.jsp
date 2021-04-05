<%@page import="config.Address"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<link rel="stylesheet" href="<%=Address.getNavigationMenuCSS()%>">
<div>
	<table id="navigation_table">
		<tr>
			<th class="navigation_table_header">Navigation</th>
		</tr>
		<tr class="navigation_table_row">
			<td class="navigation_table_data">
				<ul id="navigation_tree">

					<li><span class="navigation_tree_node symbol_user" style="opacity: 0.3">Account</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_pen_paper"
									style="opacity: 0.3">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_briefcase" id="logout"
									style="opacity: 0.3">Logout</span></li>
						</ul>
					</li>

					<li><span class="navigation_tree_node symbol_user">Projektpartner</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard"
									id="projekt_partner_erstellen">erstellen</span></li>
							<li><span class="navigation_tree_node symbol_pen_paper"
									id="projekt_partner_bearbeiten">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_search" style="opacity: 0.3">suchen</span></li>
						</ul>
					</li>

					<li><span class="navigation_tree_node symbol_folder_closed">Projekte</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard"
									id="projekt_erstellen">erstellen</span></li>
							<li><span class="navigation_tree_node symbol_pen_paper"
									  id="projekt_bearbeiten">bearbeiten</span></li>
						</ul>
					</li>

					<li><span class="navigation_tree_node symbol_folder_closed">Substanzen</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard"
									id="substanz_erstellen">erstellen</span></li>
							<li><span class="navigation_tree_node symbol_pen_paper"
									style="opacity: 0.3">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_search" style="opacity: 0.3">suchen</span></li>
						</ul>
					</li>

					<li><span class="navigation_tree_node symbol_folder_closed">Proben</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard"
									id="probeneingang_erstellen">Probeneingang</span></li>
							<!-- 							<li><span class="navigation_tree_node symbol_clipboard" id="probe_erstellen">erstellen</span></li> -->
							<li><span class="navigation_tree_node symbol_pen_paper"
									style="opacity: 0.3">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_search" style="opacity: 0.3">suchen</span></li>
						</ul>
					</li>

					<li><span class="navigation_tree_node symbol_folder_closed">Experiment</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard"
									id="experiment_erstellen">erstellen</span></li>
							<li><span class="navigation_tree_node symbol_pen_paper"
									id="experiment_bearbeiten">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_search" style="opacity: 0.3">suchen</span></li>
						</ul>
					</li>

					<li><span class="navigation_tree_node symbol_folder_closed">Analysen</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard"
									id="analyse_erstellen">erstellen</span></li>
							<li><span class="navigation_tree_node symbol_folder_closed"
									style="opacity: 0.3">Eingangsanalyse</span>
								<ul class="navigation_tree_branches">
									<li><span class="navigation_tree_node symbol_clipboard"
											id="eingangsanalytik_erstellen" style="opacity: 0.3">erstellen</span></li>
								</ul>
							</li>
							<li><span class="navigation_tree_node symbol_pen_paper"
									id="analyse_bearbeiten">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_search" style="opacity: 0.3">suchen</span></li>
						</ul>
					</li>

					<li><span class="navigation_tree_node symbol_cabinet" id="explorer_anzeigen">Explorer</span></li>

					<li><span class="navigation_tree_node symbol_search" id="globale_suche_anzeigen2">Suche</span>
				</ul>
			</td>
		</tr>
	</table>
</div>

<script src="NavigationMenu.js"></script>

<script>

	$(document).ready(function () {

		NavigationMenu.initNavigationNodeListener(".navigation_tree_node");

		NavigationMenu.initOpenAllNavigationNodesListener(".navigation_table_header", ".navigation_tree_branches");

		NavigationMenu.initInputMaskListener("#main-content-input-masks",
			{
				"#projekt_partner_erstellen": 	"<%=Address.getProjektPartnerErstellenJsp()%>",
				"#projekt_erstellen": 			"<%=Address.getProjektErstellenJsp()%>",
				"#substanz_erstellen": 			"<%=Address.getSubstanzErstellenJsp()%>",
				"#experiment_erstellen": 		"<%=Address.getExperimentErstellenJsp()%>",
				"#analyse_erstellen": 			"<%=Address.getAnalyseErstellenJsp()%>",
				"#probeneingang_erstellen": 	"<%=Address.getProbeneingangJSP()%>",
				"#eingangsanalytik_erstellen":	"<%=Address.getEingangsAnalytikJSP()%>",
				"#experiment_bearbeiten":		"<%=Address.getExperimentBearbeitenJsp()%>",
				"#analyse_bearbeiten":			"<%=Address.getAnalyseBearbeitenJsp()%>",
				"#projekt_partner_bearbeiten":	"<%=Address.getPartnerBearbeitenJsp()%>",
				"#projekt_bearbeiten":			"<%=Address.getProjektBearbeitenJsp()%>"
			}
		);

		NavigationMenu.initSucheListener("#globale_suche_anzeigen2", "#main-content-global-search");

		NavigationMenu.initExplorerListener("#explorer_anzeigen", "#main-content-explorer");

		setTimeout(() => {

			// 	NavigationMenu.open("#projekt_partner_erstellen");
			NavigationMenu.open("#projekt_erstellen");
			// NavigationMenu.open("#substanz_erstellen");
			// 	NavigationMenu.open("#experiment_erstellen");
			// 	NavigationMenu.open("#analyse_erstellen");
			// 	NavigationMenu.open("#probeneingang_erstellen");
			// 	NavigationMenu.open("#eingangsanalytik_erstellen");
		}, 800)

	});

</script>