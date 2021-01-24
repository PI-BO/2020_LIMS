<%@page import="database.model.ProjektSubstanzen"%>
<%@page import="database.model.Substanz"%>
<%@page import="database.model.Projekt"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<link rel="stylesheet" href="projekt.css">
<title>LIMS | Projekt</title>
<script src="jquery-3.5.1.js"></script>
</head>

<%
	String projekt_id = (String) request.getParameter("projekt_id");
	Projekt projekt = new Projekt(projekt_id);
	ProjektSubstanzen projektSubstanzen = new ProjektSubstanzen(projekt_id);
%>

<body>
		<table id="projekt_table">
			<tr>
				<th colspan=3 class="projekt_table_header" onclick="sortTable(0)"><%=projekt.getPrimaryKey()%></th>
			</tr>
			
			<tr id="projekt_list_table_row_sortfuntions">
				<td class="projekt_list_table_data_sortfuntion symbol_triangle_up" onclick="sortTableSubstanz(0)">Name</td>
				<td class="projekt_list_table_data_sortfuntion symbol_triangle_up" onclick="">Datum</td>
				<td class="projekt_list_table_data_sortfuntion symbol_triangle_up" onclick="">etc</td>
			</tr>
			
			
			<%
				for (Substanz substanz : projektSubstanzen.getSubstanzen()) {
			%>

			<tr>
				<td class="projekt_table_data"><%=substanz.getPrimaryKey()%></td>
			</tr>

			<%
				}
			%>
		</table>

		<script>
			
			var toggler = document.getElementsByClassName("projekt_list_table_data_sortfuntion");
			var i;
		
			for (i = 0; i < toggler.length; i++) {
				toggler[i].addEventListener("click", function() {
					this.classList.toggle("symbol_triangle_down");
				});
			}
		
			function sortTableSubstanz(n) {
				var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
				table = document.getElementById("projekt_table");
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
					first and second, which contains table headers):*/
					for (i = 2; i < (rows.length - 1); i++) {
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
			
			
		</script>

</body>
</html>