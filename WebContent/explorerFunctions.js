function addSymbolToggleListenerToCssClass(className, cssSymbolId) {

	var toggler = document.getElementsByClassName(className);
	var i;

	for (i = 0; i < toggler.length; i++) {
		toggler[i].addEventListener("click", function() {
			this.classList.toggle(cssSymbolId);
		});
	}
}

function loadPage(pageAddress, data) {
	var url = "http://localhost:8080/2020_LIMS" + pageAddress;
	var posting = $.post(url, data);
	posting.done(function(data) {
		$("#explorer-content").empty().append(data);
	});
}

function sortExplorerTable(n) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("explorer_table");
	switching = true;
	// Set the sorting direction to ascending:
	dir = "asc";
	/*
	 * Make a loop that will continue until no switching has been done:
	 */
	while (switching) {
		// start by saying: no switching is done:
		switching = false;
		rows = table.rows;
		/*
		 * Loop through all table rows (except the first, which contains table
		 * headers):
		 */
		for (i = 1; i < (rows.length - 1); i++) {
			// start by saying there should be no switching:
			shouldSwitch = false;
			/*
			 * Get the two elements you want to compare, one from current row
			 * and one from the next:
			 */
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			/*
			 * check if the two rows should switch place, based on the
			 * direction, asc or desc:
			 */
			if (dir == "asc") {
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					// if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					// if so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) {
			/*
			 * If a switch has been marked, make the switch and mark that a
			 * switch has been done:
			 */
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			// Each time a switch is done, increase this count by 1:
			switchcount++;
		} else {
			/*
			 * If no switching has been done AND the direction is "asc", set the
			 * direction to "desc" and run the while loop again.
			 */
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}

class ExplorerState {
	
	constructor(){
		this.projekte = null;
		this.projekt = null;
		this.substanz = null;
	}
	
	setStateProjekte(projekte){
		this.projekte = projekte;
		this.projekt = null;
		this.substanz = null;
		$("#explorer-header").empty().append(this.getPath());
	}
	
	setStateProjekt(projekt){
		this.projekt = projekt;
		this.substanz = null;
		$("#explorer-header").empty().append(this.getPath());
	}
	
	setStateSubstanz(substanz){
		this.substanz = substanz;
		$("#explorer-header").empty().append(this.getPath());
	}
	
	createPathElement(){
		
		var pathDiv = document.createElement("DIV");
		
		pathDiv.setAttribute("class", "explorer-path-element");
//		pathDiv.setAttribute("style", "background-color : #BEEBFF");
		
		return pathDiv;
	}
	
	createPathDivisor(){
		
		var pathDiv = document.createElement("DIV");
		pathDiv.setAttribute("class", "explorer-path-divisor symbol_triangle_right");
		
		return pathDiv;
	}
	
	getPath(){
		
		let divList = []
		
		let projekteDiv = this.createPathElement();
		projekteDiv.onclick = () => loadPage("/projekte.jsp", {});
		projekteDiv.innerHTML = this.projekte;
		divList.push(projekteDiv);
		
		if(this.projekt == null) return divList;
		
		let projektDiv = this.createPathElement();
		projektDiv.innerHTML = this.projekt;
		projektDiv.onclick = () => loadPage("/projekt.jsp", {projekt_id : this.projekt});
		divList.push(this.createPathDivisor());
		divList.push(projektDiv);
				
		return divList; 
	}
}










