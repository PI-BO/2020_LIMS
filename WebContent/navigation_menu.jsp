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

					<li><span class="navigation_tree_node symbol_user">Account</span>
						<ul class="navigation_tree_branches">
							<li><span
								class="navigation_tree_node symbol_edit">bearbeiten</span></li>
							<li><span
								class="navigation_tree_node symbol_logout" id="logout">Logout</span></li>
						</ul></li>

					<li><span class="navigation_tree_node symbol_user">Projektpartner</span>
						<ul class="navigation_tree_branches">
							<li><span
								class="navigation_tree_node symbol_add">hinzufuegen</span></li>
							<li><span
								class="navigation_tree_node symbol_search">suchen</span></li>
							<li><span
								class="navigation_tree_node symbol_edit">bearbeiten</span></li>
						</ul>
						
					<li><span class="navigation_tree_node symbol_folder_closed">Projekte</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_list" id="projekte_auflisten">auflisten</span></li>
							<li><span class="navigation_tree_node symbol_add" id="projekt_erstellen">erstellen</span></li>
						</ul></li>

					<li><span class="navigation_tree_node symbol_folder_closed">Substanzen</span>
						<ul class="navigation_tree_branches">
							<li><span
								class="navigation_tree_node symbol_search">suchen</span></li>
							<li><span
								class="navigation_tree_node symbol_add" id="substanz_erstellen">erstellen</span></li>
							<li><span
								class="navigation_tree_node symbol_edit">bearbeiten</span></li>
						</ul>
				</ul>
			</td>
		</tr>
	</table>
</div>

	<script>

	
// 		alle Unterpunkte auf einmal oeffnen
		$(".navigation_table_header").click(function(){
			
			if($(".navigation_tree_branches").is(":hidden")){

				$(".navigation_tree_branches").show(400);
				$(".symbol_folder_closed").show(400);
			}else{
				
				$(".navigation_tree_branches").hide(400);
			}
		});
		
// 		listener auf einzelnen tree_node zum oeffnen von Unterpunkten 
		$(".navigation_tree_node").click(function(){
			$(this).next().toggle(400);
			$(this).toggleClass("symbol_folder_open");
		});
		
// 		projekte auflisten
		$("#projekte_auflisten").click(function(){
			var url = "http://localhost:8080/2020_LIMS/explorer.jsp";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content" ).empty().append( data );
			});
			
			url = "http://localhost:8080/2020_LIMS/projekte.jsp";
			posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#explorer-content" ).empty().append( data );
			});
		});
		
// 		projekt erstellen
		$("#projekt_erstellen").click(function(){
			var url = "http://localhost:8080/2020_LIMS/projekt_erstellen.html";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content" ).empty().append( data );
			});
		});
		
// 		substanz erstellen
		$("#substanz_erstellen").click(function(){
			var url = "http://localhost:8080/2020_LIMS/substanz_erstellen.html";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content" ).empty().append( data );
			});
		});
		
// 		ausloggen
		$("#logout").click(function(){
			var url = "http://localhost:8080/2020_LIMS/login";
			$(".navigation_tree_branches").hide(800);
			$("#main-content").hide(800);
			setTimeout(function() {
				$(location).attr("href", url);
			}, 1000);
		});
	</script>
