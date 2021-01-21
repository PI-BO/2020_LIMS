<%@page import="view.htmlElements.NavigationElement"%>
<%@page import="view.htmlElements.NavigationTreeNode"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page import="model.ProjekteIdList"%>

		<div id="navigation_container">
			<table>
				<tr>
					<th>Navigation</th>
				</tr>
				<tr>
					<td>
						<ul id="navigation_tree">
						
							<li><span class="navigation_tree_node" id="navigation_tree_node_user">Account</span>
								<ul class="navigation_tree_branches">
									<li><span class="navigation_tree_node navigation_tree_node_edit">bearbeiten</span></li>
									<li><span class="navigation_tree_node navigation_tree_node_logout">Logout</span></li>
								</ul>
							</li>

							<li><span class="navigation_tree_node" id="navigation_tree_node_user">Projektpartner</span>
								<ul class="navigation_tree_branches">
										<li><span class="navigation_tree_node navigation_tree_node_add">hinzufuegen</span></li>
										<li><span class="navigation_tree_node navigation_tree_node_search">suchen</span></li>
										<li><span class="navigation_tree_node navigation_tree_node_edit">bearbeiten</span></li>
									</ul>
							
							<li><span class="navigation_tree_node">Projekte</span>
								<ul class="navigation_tree_branches">
									<%
										ProjekteIdList sessionList = (ProjekteIdList) request.getSession().getAttribute("liste");
										
										if(sessionList != null){
											
											NavigationTreeNode node = new NavigationTreeNode();
											
											for(String projektId : sessionList.getProjekteIdList()){
												
												node.setContent(projektId);
												
												%>
												<%=node.getContent()%>
												<%
											}
										}
									%>
								</ul>
							</li>
							
							<li><span class="navigation_tree_node">Substanzen</span>
								<ul class="navigation_tree_branches">
									<li><span class="navigation_tree_node navigation_tree_node_add">hinzufuegen</span></li>
									<li><span class="navigation_tree_node navigation_tree_node_search">suchen</span></li>
									<li><span class="navigation_tree_node navigation_tree_node_edit">bearbeiten</span></li>
								</ul>
							
						</ul>
					</td>
				</tr>
			</table>
		</div>

	<script>
		var toggler = document.getElementsByClassName("navigation_tree_node");
		var i;
	
		for (i = 0; i < toggler.length; i++) {
			toggler[i].addEventListener("click", function() {
				this.parentElement.querySelector(".navigation_tree_branches").classList
						.toggle("navigation_tree_branches_open");
				this.classList.toggle("navigation_tree_node_open");
			});
		}		
	</script>
