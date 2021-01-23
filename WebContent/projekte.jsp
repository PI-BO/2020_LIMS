<%@page import="database.model.ProjekteIdList"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>LIMS | Projekt</title>
<script src="jquery-3.5.1.js"></script>
</head>

<%
	ProjekteIdList projekte = new ProjekteIdList();
%>

<body>
		<table id="projekte_list_table">
			<tr>
				<th class="projekt_list_table_header" onclick="sortTableProjekte(0)">Projekt
					ID</th>
			</tr>
			<%
				for (String id : projekte.getProjekteIdList()) {
			%>

			<tr>
				<td class="projekt_list_element navigation_tree_node"><%=id%></td>
			</tr>

			<%
				}
			%>
		</table>

		<script>
		
			var toggler = document.getElementsByClassName("navigation_tree_node");
			var i;
		
			for (i = 0; i < toggler.length; i++) {
				toggler[i].addEventListener("click", function() {
					this.classList.toggle("navigation_tree_node_open");
				});
			}
		
			function sortTableProjekte(n) {
				var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
				table = document.getElementById("projekte_list_table");
				switching = true;
				//Set the sorting direction to ascending:
				dir = "asc";
				/*Make a loop that will continue until
				no switching has been done:*/
				while (switching) {
					//start by saying: no switching is done:
					switching = false;
					rows = table.rows;
					/*Loop through all table rows (except the
					first, which contains table headers):*/
					for (i = 1; i < (rows.length - 1); i++) {
						//start by saying there should be no switching:
						shouldSwitch = false;
						/*Get the two elements you want to compare,
						one from current row and one from the next:*/
						x = rows[i].getElementsByTagName("TD")[n];
						y = rows[i + 1].getElementsByTagName("TD")[n];
						/*check if the two rows should switch place,
						based on the direction, asc or desc:*/
						if (dir == "asc") {
							if (x.innerHTML.toLowerCase() > y.innerHTML
									.toLowerCase()) {
								//if so, mark as a switch and break the loop:
								shouldSwitch = true;
								break;
							}
						} else if (dir == "desc") {
							if (x.innerHTML.toLowerCase() < y.innerHTML
									.toLowerCase()) {
								//if so, mark as a switch and break the loop:
								shouldSwitch = true;
								break;
							}
						}
					}
					if (shouldSwitch) {
						/*If a switch has been marked, make the switch
						and mark that a switch has been done:*/
						rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
						switching = true;
						//Each time a switch is done, increase this count by 1:
						switchcount++;
					} else {
						/*If no switching has been done AND the direction is "asc",
						set the direction to "desc" and run the while loop again.*/
						if (switchcount == 0 && dir == "asc") {
							dir = "desc";
							switching = true;
						}
					}
				}
			}
			
			$(".projekt_list_element").click(function(){
				var url = "http://localhost:8080/2020_LIMS/projekt.jsp";
				var posting = $.post( url, {projekt_id : $(this).text()} );
				console.log("Projekt: ", $(this).text());
				posting.done(function( data ) {
					$( "#container_content2" ).empty().append( data );
				});
			});
			
		</script>

</body>
</html>