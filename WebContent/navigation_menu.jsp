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

					<li><span class="navigation_tree_node symbol_user">Account</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_briefcase" id="logout">Logout</span></li>
						</ul></li>

					<li><span class="navigation_tree_node symbol_user">Projektpartner</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard">erstellen</span></li>
							<li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_search">suchen</span></li>
						</ul></li>

					<li><span class="navigation_tree_node symbol_cabinet" id="explorer_anzeigen">Explorer</span></li>

					<li><span class="navigation_tree_node symbol_folder_closed">Projekte</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard" id="projekt_erstellen">erstellen</span></li>
						</ul></li>

					<li><span class="navigation_tree_node symbol_folder_closed">Substanzen</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard" id="probeneingang_erstellen">erstellen</span></li>
							<!-- 							<li><span class="navigation_tree_node symbol_clipboard" id="substanz_erstellen">erstellen</span></li> -->
							<li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_search">suchen</span></li>
						</ul></li>

					<li><span class="navigation_tree_node symbol_folder_closed">Analysen</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_folder_closed">Eingangsanalyse</span>
								<ul class="navigation_tree_branches">
									<li><span class="navigation_tree_node symbol_clipboard" id="eingangsanalytik_erstellen">erstellen</span></li>
								</ul></li>
							<li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
							<li><span class="navigation_tree_node symbol_search">suchen</span></li>
						</ul></li>
					<li><span class="navigation_tree_node symbol_search" id="globale_suche_anzeigen2">Suche v2</span>
				</ul>
			</td>
		</tr>
	</table>
</div>

<script>
	
// 		init explorer
		$( document ).ready(function(){
			var url = "<%=Address.getExplorerJSP()%>";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-explorer" ).empty().append( data );
			});
		});
		
// 		init Globale Suche
		$( document ).ready(function(){
			var url = "http://localhost:8080/2020_LIMS/suche/globale_suche2.html";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-global-search" ).empty().append( data );
			});
		});
		
		function hideAllExcept(id){
			
			let toggleList = [];
			
			const mainContentElement = document.getElementById("main-content");
			
			for(let i = 0; i < mainContentElement.children.length; i++){
				toggleList.push(mainContentElement.children[i].id);
			}
			
			toggleList.forEach(element => {
				if("#"+element == id){
					$("#"+element).hide();
					$("#"+element).show(500);
				}else{
					$("#"+element).hide();
				}
			})
		}
		
// 		Navigation komplett oeffnen
		$(".navigation_table_header").click(function(){
			
			if($(".navigation_tree_branches").is(":hidden")){

				$(".navigation_tree_branches").show(400);
				$(".symbol_folder_closed").show(400);
			}else{
				
				$(".navigation_tree_branches").hide(400);
			}
		});
		
// 		init listener, zum oeffnen der einzelnen nodes 
		$(".navigation_tree_node").click(function(){
			$(this).next().toggle(400);
			$(this).toggleClass("symbol_folder_open");
		});
		
		
		$("#explorer_anzeigen").click(function(){
			hideAllExcept("#main-content-explorer");
		});
		
		$("#projekt_erstellen").click(function(){
			var url = "<%=Address.getProjektJSP()%>";
			var url = "http://localhost:8080/2020_LIMS/projekt/projekt_erstellen.jsp";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-input-masks" ).empty().append( data );
				hideAllExcept("#main-content-input-masks");
			});
		});
		
		$("#substanz_erstellen").click(function(){
			var url = "http://localhost:8080/2020_LIMS/substanz_erstellen.html";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-input-masks" ).empty().append( data );
				hideAllExcept("#main-content-input-masks");
			});
		});
		
		$("#probeneingang_erstellen").click(function(){
			var url = "<%=Address.getProbeneingangJSP()%>";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-input-masks" ).empty().append( data );
				hideAllExcept("#main-content-input-masks");
			});
		});
		
		$("#eingangsanalytik_erstellen").click(function(){
			var url = "<%=Address.getEingangsAnalytikJSP()%>";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-input-masks" ).empty().append( data );
				hideAllExcept("#main-content-input-masks");
			});
		});
		
		$("#logout").click(function(){
			var url = "http://localhost:8080/2020_LIMS/login.html";
			$(".navigation_tree_branches").hide(800);
			hideAllExcept("");
			setTimeout(function() {
				$(location).attr("href", url);
			}, 1000);
		});
		
		$("#globale_suche_anzeigen2").click(function(){
			hideAllExcept("#main-content-global-search");
		});
	</script>
