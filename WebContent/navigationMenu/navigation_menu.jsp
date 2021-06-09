<%@page import="config.Address" %>
	<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>

		<link rel="stylesheet" href="<%=Address.getNavigationMenuCSS()%>">
		<div>
			<table id="navigation_table">
				<tr>
					<th class="navigation_table_header">Navigation</th>
				</tr>
				<tr class="navigation_table_row">
					<td class="navigation_table_data">
						<ul id="navigation_tree">

							<Account>
								<li>

									<span class="navigation_tree_node symbol_user" style="opacity: 0.3">Account</span>
									<ul class="navigation_tree_branches">
										<bearbeiten>
											<li><span class="navigation_tree_node symbol_pen_paper"
													style="opacity: 0.3">bearbeiten</span></li>
										</bearbeiten>
										<logout>
											<li><span class="navigation_tree_node symbol_briefcase" id="logout"
													style="opacity: 0.3">Logout</span></li>
										</logout>
									</ul>
								</li>
							</Account>

							<Partner>
								<span class="navigation_tree_node symbol_user">Partner [<span style="color: blue;" id="partner_navigation_state"> - </span>]
								</span>
								<ul class="navigation_tree_branches">
									<auswaehlen>
										<li>
											<span class="navigation_tree_node symbol_search"
												id="partner_auswaehlen">auswaehlen</span>
										</li>
									</auswaehlen>
									<erstellen>
										<li>
											<span class="navigation_tree_node symbol_clipboard"
												id="partner_erstellen">erstellen</span>
										</li>
									</erstellen>
									<bearbeiten>
										<li>
											<span class="navigation_tree_node symbol_pen_paper"
												id="partner_bearbeiten">bearbeiten</span>
										</li>
									</bearbeiten>
								</ul>
							</Partner>

							<Projekt>
								<li><span class="navigation_tree_node symbol_folder_closed">Projekt [<span
											style="color: blue;" id="projekt_navigation_state"> - </span>]
									</span>
									<ul class="navigation_tree_branches">
										<auswaehlen>
											<li>
												<span class="navigation_tree_node symbol_search"
													id="projekt_auswaehlen">auswaehlen</span>
											</li>
										</auswaehlen>
										<erstellen>
											<li>
												<span class="navigation_tree_node symbol_clipboard"
													id="projekt_erstellen">erstellen</span>
											</li>
										</erstellen>
										<bearbeiten>
											<li>
												<span class="navigation_tree_node symbol_pen_paper" style="opacity: 1"
													id="projekt_bearbeiten">bearbeiten</span>
											</li>
										</bearbeiten>
									</ul>
								</li>
							</Projekt>

							<Probe>
								<li><span class="navigation_tree_node symbol_folder_closed">Probe [<span
											style="color: blue;" id="probe_navigation_state"> - </span>]</span>
									<ul class="navigation_tree_branches">
										<auswaehlen>
											<li>
												<span class="navigation_tree_node symbol_search"
													id="probe_auswaehlen">auswaehlen</span>
											</li>
										</auswaehlen>
										<Probeneingang>
											<li>
												<span class="navigation_tree_node symbol_clipboard"
													id="probeneingang_erstellen">Probeneingang</span>
											</li>
										</Probeneingang>
										<!-- 							<li><span class="navigation_tree_node symbol_clipboard" id="probe_erstellen">erstellen</span></li> -->
										<bearbeiten>
											<li>
												<span class="navigation_tree_node symbol_pen_paper"
													id="probeneingang_bearbeiten">bearbeiten</span>
											</li>
										</bearbeiten>
									</ul>
								</li>
							</Probe>
							<Experiment>
								<li><span class="navigation_tree_node symbol_folder_closed">Experiment [<span
											style="color: blue;" id="experiment_navigation_state"> - </span>]
									</span>
									<ul class="navigation_tree_branches">
										<auswaehlen>
											<li>
												<span class="navigation_tree_node symbol_search"
													id="experiment_auswaehlen" style="opacity: 1.0">auswaehlen</span>
											</li>
										</auswaehlen>
										<erstellen>
											<li>
												<span class="navigation_tree_node symbol_clipboard"
													id="experiment_erstellen">erstellen</span>
											</li>
										</erstellen>
										<bearbeiten>
											<li>
												<span class="navigation_tree_node symbol_pen_paper"
													id="experiment_bearbeiten" style="opacity: 0.3">bearbeiten</span>
											</li>
										</bearbeiten>
									</ul>
								</li>
							</Experiment>

							<Analyse>
								<li><span class="navigation_tree_node symbol_folder_closed">Analyse [<span
											style="color: blue;" id="analyse_navigation_state"> - </span>]
									</span>
									<ul class="navigation_tree_branches">
										<auswaehlen>
											<li>
												<span class="navigation_tree_node symbol_search" id="analyse_auswaehlen"
													style="opacity: 1.0">auswaehlen</span>
											</li>
										</auswaehlen>
										<erstellen>
											<li>
												<span class="navigation_tree_node symbol_clipboard"
													id="analyse_erstellen">erstellen</span>
											</li>
										</erstellen>
										<Eingangsanalyse>
											<li><span class="navigation_tree_node symbol_folder_closed"
													id="eingangsanalytik_erstellen"
													style="opacity: 0.3">Eingangsanalyse</span>
											</li>
										</Eingangsanalyse>
										<bearbeiten>
											<li>
												<span class="navigation_tree_node symbol_pen_paper" style="opacity: 0.3"
													id="analyse_bearbeiten">bearbeiten</span>
											</li>
										</bearbeiten>
									</ul>
								</li>

							</Analyse>
							<Explorer>
								<li><span class="navigation_tree_node symbol_cabinet"
										id="explorer_anzeigen">Explorer</span>
								</li>
							</Explorer>
							<Suche>
								<li><span class="navigation_tree_node symbol_search" id="suche_anzeigen">Suche</span>
								</li>
							</Suche>
						</ul>
					</td>
				</tr>
			</table>
		</div>

		<script type="module" src="NavigationMenu.js"></script>