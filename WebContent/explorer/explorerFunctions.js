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
	var url = pageAddress;
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
		this.partnerList = null;
		this.partner = null;
		this.projekt = null;
		this.substanz = null;
		this.probe = null;
		this.experiment = null;
//		this.projekteListAddress = null;
	}

	setPartnerListAddress(address){
		this.partnerListAddress = address;
	}
	
	setPartnerAddress(address){
		this.partnerAddress = address;
	}

	setProjektAddress(address){
		this.projektAddress = address;
	}

	setSubstanzAddress(address){
		this.substanzAddress = address;
	}

	setProbeAddress(address){
		this.probeAddress = address;
	}

	setExperimentAddress(address){
		this.experimentAddress = address;
	}

	setStatePartnerList(partnerList) {
		this.partnerList = partnerList;
		this.partner = null;
		this.projekt = null;
		this.substanz = null;
		this.probe = null;
		this.experiment = null;
		$("#explorer-header").empty().append(this.getPath());
	}

	setStatePartner(partner) {
		this.partner = partner;
		this.projekt = null;
		this.substanz = null;
		this.probe = null;
		this.experiment = null;
		$("#explorer-header").empty().append(this.getPath());
	}
	
	setStateProjekt(projekt){
		this.projekt = projekt;
		this.substanz = null;
		this.probe = null;
		this.experiment = null;
		$("#explorer-header").empty().append(this.getPath());
	}
	
	setStateSubstanz(substanz){
		this.substanz = substanz;
		this.probe = null;
		this.experiment = null;
		$("#explorer-header").empty().append(this.getPath());
	}

	setStateProbe(s) {
		this.probe = s;
		this.experiment = null;
		$("#explorer-header").empty().append(this.getPath());
	}

	setStateExperiment(s) {
		this.experiment = s;
		$("#explorer-header").empty().append(this.getPath());
	}
	
	createPathElement(){
		
		var pathDiv = document.createElement("DIV");
		
		pathDiv.setAttribute("class", "explorer-path-element");
		
		return pathDiv;
	}
	
	createPathDivisor(){
		
		var pathDiv = document.createElement("DIV");
		pathDiv.setAttribute("class", "explorer-path-divisor symbol_triangle_right");
		
		return pathDiv;
	}
	
	getPath(){
		
		let divList = []
		
		let partnerListDiv = this.createPathElement();
		partnerListDiv.onclick = () => loadPage(this.partnerListAddress, {});
		partnerListDiv.innerHTML = this.partnerList;
		divList.push(partnerListDiv);
		
		if(this.partner == null) return divList;
		
		let partnerDiv = this.createPathElement();
		partnerDiv.innerHTML = this.partner;
		partnerDiv.onclick = () => loadPage(this.partnerAddress, {projekt_id : this.partner});
		divList.push(this.createPathDivisor());
		divList.push(partnerDiv);

		if(this.projekt == null) return divList;

		let projektDiv = this.createPathElement();
		projektDiv.innerHTML = this.projekt;
		projektDiv.onclick = () => loadPage(this.projektAddress, {projekt_id : this.projekt});
		divList.push(this.createPathDivisor());
		divList.push(projektDiv);

		if(this.substanz == null) return divList;

		let substanzDiv = this.createPathElement();
		substanzDiv.innerHTML = this.substanz;
		substanzDiv.onclick = () => loadPage(this.substanzAddress, {projekt_id : this.substanz});
		divList.push(this.createPathDivisor());
		divList.push(substanzDiv);

		if(this.probe == null) return divList;

		let probeDiv = this.createPathElement();
		probeDiv.innerHTML = this.probe;
		probeDiv.onclick = () => loadPage(this.projektAddress, {projekt_id : this.probe});
		divList.push(this.createPathDivisor());
		divList.push(probeDiv);

		if(this.experiment == null) return divList;

		let experimentDiv = this.createPathElement();
		experimentDiv.innerHTML = this.experiment;
		experimentDiv.onclick = () => loadPage(this.experimentAddress, {projekt_id : this.experiment});
		divList.push(this.createPathDivisor());
		divList.push(experimentDiv);
				
		return divList; 
	}
}










