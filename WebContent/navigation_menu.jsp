<%@page import="controller.WelcomeServlet"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page import="database.model.ProjekteIdList"%>

<link rel="stylesheet" href="navigation_menu.css">
<div>
	<table id="navigation_table">
		<tr>
			<th class="navigation_table_header">Navigation</th>
		</tr>
		<tr class="navigation_table_row">
			<td class="navigation_table_data">
				<ul id="navigation_tree">

					<li><span class="navigation_tree_node navigation_tree_node_user">Account</span>
						<ul class="navigation_tree_branches">
							<li><span
								class="navigation_tree_node navigation_tree_node_edit">bearbeiten</span></li>
							<li><span
								class="navigation_tree_node navigation_tree_node_logout">Logout</span></li>
						</ul></li>

					<li><span class="navigation_tree_node navigation_tree_node_user">Projektpartner</span>
						<ul class="navigation_tree_branches">
							<li><span
								class="navigation_tree_node navigation_tree_node_add">hinzufuegen</span></li>
							<li><span
								class="navigation_tree_node navigation_tree_node_search">suchen</span></li>
							<li><span
								class="navigation_tree_node navigation_tree_node_edit">bearbeiten</span></li>
						</ul>
						
					<li><span class="navigation_tree_node">Projekte</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node navigation_tree_node_list" id="projekte_auflisten">auflisten</span></li>
						</ul></li>

					<li><span class="navigation_tree_node">Substanzen</span>
						<ul class="navigation_tree_branches">
							<li><span
								class="navigation_tree_node navigation_tree_node_add">hinzufuegen</span></li>
							<li><span
								class="navigation_tree_node navigation_tree_node_search">suchen</span></li>
							<li><span
								class="navigation_tree_node navigation_tree_node_edit">bearbeiten</span></li>
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
		
		$("#projekte_auflisten").click(function(){
			var url = "http://localhost:8080/2020_LIMS/projekte.jsp";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#container_content" ).empty().append( data );
			});
		});
	</script>
