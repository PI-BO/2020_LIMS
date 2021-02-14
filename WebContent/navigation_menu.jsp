<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

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
								class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
							<li><span
								class="navigation_tree_node symbol_briefcase" id="logout">Logout</span></li>
						</ul></li>

					<li><span class="navigation_tree_node symbol_user">Projektpartner</span>
						<ul class="navigation_tree_branches">
							<li><span
								class="navigation_tree_node symbol_clipboard">erstellen</span></li>
							<li><span
								class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
							<li><span
								class="navigation_tree_node symbol_search">suchen</span></li>
						</ul>
						
					<li><span class="navigation_tree_node symbol_folder_closed">Projekte</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard" id="projekt_erstellen">erstellen</span></li>
							<li><span class="navigation_tree_node symbol_cabinet" id="explorer_anzeigen">anzeigen</span></li>
						</ul></li>

					<li><span class="navigation_tree_node symbol_folder_closed">Substanzen</span>
						<ul class="navigation_tree_branches">
							<li><span class="navigation_tree_node symbol_clipboard" id="probeneingang_erstellen">Probeneingang</span></li>
							<li><span class="navigation_tree_node symbol_clipboard" id="eingangsanalytik_erstellen">Eingangsanalytik</span></li>
<!-- 							<li><span class="navigation_tree_node symbol_clipboard" id="substanz_erstellen">erstellen</span></li> -->
							<li><span class="navigation_tree_node symbol_pen_paper">bearbeiten</span></li>
							<li><span
								class="navigation_tree_node symbol_search">suchen</span></li>
						</ul>
				</ul>
			</td>
		</tr>
	</table>
</div>

	<script>

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
		
// 		init explorer
		$( document ).ready(function(){
			var url = "http://localhost:8080/2020_LIMS/explorer.jsp";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-explorer" ).empty().append( data );
			});
		});
		
		$("#explorer_anzeigen").click(function(){
			var url = "http://localhost:8080/2020_LIMS/explorer.jsp";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-input-masks" ).hide();
				$( "#main-content-explorer" ).show(500);
			});
		});
		
		$("#projekt_erstellen").click(function(){
			var url = "http://localhost:8080/2020_LIMS/projekt_erstellen.jsp";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-explorer" ).hide();
				$( "#main-content-input-masks" ).hide();
				$( "#main-content-input-masks" ).empty().append( data );
				$( "#main-content-input-masks" ).show(500);
			});
		});
		
		$("#substanz_erstellen").click(function(){
			var url = "http://localhost:8080/2020_LIMS/substanz_erstellen.html";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-explorer" ).hide();
				$( "#main-content-input-masks" ).hide();
				$( "#main-content-input-masks" ).empty().append( data );
				$( "#main-content-input-masks" ).show(500);
			});
		});
		
		$("#probeneingang_erstellen").click(function(){
			var url = "http://localhost:8080/2020_LIMS/probeneingang.jsp";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-explorer" ).hide();
				$( "#main-content-input-masks" ).hide();
				$( "#main-content-input-masks" ).empty().append( data );
				$( "#main-content-input-masks" ).show(500);
			});
		});
		
		$("#eingangsanalytik_erstellen").click(function(){
			var url = "http://localhost:8080/2020_LIMS/eingangsanalytik.jsp";
			var posting = $.post( url, {} );
			posting.done(function( data ) {
				$( "#main-content-explorer" ).hide();
				$( "#main-content-input-masks" ).hide();
				$( "#main-content-input-masks" ).empty().append( data );
				$( "#main-content-input-masks" ).show(500);
			});
		});
		
		$("#logout").click(function(){
			var url = "http://localhost:8080/2020_LIMS/login";
			$(".navigation_tree_branches").hide(800);
			$("#main-content-explorer").hide(800);
			$("#main-content-input-masks").hide(800);
			setTimeout(function() {
				$(location).attr("href", url);
			}, 1000);
		});
	</script>
