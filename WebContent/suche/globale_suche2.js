var GlobaleSuche = (function () {

	public = {};

	const addButton = "global_search_add_parameter_button";
	const searchButton = "global_search_button";
	const parameterTableId = "global_search_parameter_table";
	const resultTableId = "global_search_result_table";
	const rowTemplateId = "global_search_parameter_row_template";
	const categorySelectClass = "global_search_select_main_category"
	const deleteButtonClass = "global_search_delete_parameter_button";
	const searchParameterClass = "global_search_parameter";
	const searchValueClass = "global_search_parameter_input";

	const database = initDatabase();

	const databaseIndexTable = [	// Reihenfolge ist der Index
		"projektpartner",
		"projekt",
		"probe",
		"experiment",
		"methode",
		"operator"
	]

	const parameters = {
		"projektpartner": ["name", "pk"],
		"projekt": ["name", "fk", "pk"],
		"probe": ["name", "fk", "pk"],
		"experiment": ["name", "fk", "pk"],
		"methode": ["name", "fk", "pk"],
		"operator": ["name", "fk", "pk"]
	}

	public.init = function init() {

		initAddParameterButton();
		initSearchButton();
		initFirstParameterRow();
	}

	function initAddParameterButton() {
		document.getElementById(addButton).addEventListener("click", () => addParameterRow());
	}

	function initSearchButton() {
		document.getElementById(searchButton).addEventListener("click", () => search());
	}

	function initFirstParameterRow() {
		document.getElementById(addButton).click();
	}


	function initDeleteButton(element) {
		element.addEventListener("click", (element) => deleteSuchParameter(element.target));
	}

	function addEnterListener(element) {

		element.addEventListener("keyup", (event) => {

			if (event.keyCode === 13) {
				document.getElementById(searchButton).click();
			}
		})
	}

	function getSearchTable() {
		return document.getElementById(parameterTableId);
	}

	function initDatabase() {
		const partner1 = {
			"pk": "1",
			"name": "Partner A",
		};
		const projekt1 = {
			"pk": "1",
			"name": "Projekt 1",
			"fk": "1"
		};
		const probe1 = {
			"pk": "1",
			"name": "Probe 1",
			"fk": "1"
		};
		const experiment1 = {
			"pk": "1",
			"name": "Experiment 1",
			"fk": "1"
		};
		const experiment2 = {
			"pk": "2",
			"name": "Experiment 2",
			"fk": "1"
		};
		const methode1 = {
			"pk": "1",
			"name": "Methode 1",
			"fk": "1"
		};
		const methode2 = {
			"pk": "2",
			"name": "Methode 2",
			"fk": "1"
		};
		const methode3 = {
			"pk": "3",
			"name": "Methode 3",
			"fk": "2"
		};
		const methode4 = {
			"pk": "4",
			"name": "Methode 4",
			"fk": "2"
		};
		const operator1 = {
			"name": "John Doe",
			"pk": "1",
			"fk": "1"
		};
		const operator2 = {
			"name": "Jane Doe",
			"pk": "2",
			"fk": "2"
		};
		const operator3 = {
			"name": "Jane Doe",
			"pk": "2",
			"fk": "3"
		};
		const operator4 = {
			"name": "Jane Doe",
			"pk": "2",
			"fk": "4"
		};
		const database = {
			"projektpartner": [partner1],
			"projekt": [projekt1],
			"probe": [probe1],
			"experiment": [experiment1, experiment2],
			"methode": [methode1, methode2, methode3, methode4],
			"operator": [operator1, operator2, operator3, operator4]
		};
		return database;
	}

	function clearResultTable() {

		const resultTable = document.getElementById(resultTableId);

		while (resultTable.hasChildNodes()) {
			resultTable.removeChild(resultTable.lastChild);
		}
	}

	function addParameterRow() {

		let table = document.getElementById(parameterTableId);
		let template = document.getElementById(rowTemplateId);
		let templateChildNodes = template.children;
		let row = table.insertRow(-1);

		let mainCategorySelect;

		for (let i = 0; i < templateChildNodes.length; i++) {

			let cell = row.insertCell(i);
			var child = templateChildNodes[i].cloneNode(true);
			addEnterListener(child);
			cell.append(child);

			if (child.className == categorySelectClass) {

				mainCategorySelect = child;

			}
			if (child.className == deleteButtonClass) initDeleteButton(child);
		}

		initMainCategorySelect(mainCategorySelect);
	}

	function createMainCategory(event) {

		const htmlElement = event.target;
		let categories = []

		for (let key in parameters) {
			categories.push(capitalize(key));
		}

		insertParameters(categories, htmlElement);
	}

	function initMainCategorySelect(element) {
		element.addEventListener("change", (event) => createParameters(event));
		createMainCategory({ target: element });
		createParameters({ target: element });
	}

	function createParameters(event) {

		const htmlElement = event.target;
		const parameterCategory = htmlElement.value;
		let selectElement = htmlElement.parentElement.parentElement.getElementsByClassName(searchParameterClass)[0];

		removeOptions(selectElement);

		let parameters = getParameters(parameterCategory);
		insertParameters(parameters, selectElement);
	}

	function insertParameters(parameters, selectElement) {

		parameters.forEach(parameter => {
			let selectOption = document.createElement("option");
			selectOption.text = parameter;
			selectOption.value = parameter;
			selectElement.add(selectOption);
		});
	}

	function deleteSuchParameter(suchParameterElement) {

		let parent = suchParameterElement.parentElement.parentElement;
		parent.remove();
	}

	function resetSelects(element) {

		let allSelects = element.querySelectorAll("select");
		allSelects.forEach(element => {
			resetSelect(element);
		})
	}

	function resetSelect(element) {
		element.options[0].selected = "true";
	}

	function removeOptions(selectElement) {


		let selectOptions = selectElement.querySelectorAll("option");
		selectOptions.forEach(option => option.remove());
	}

	function getParameters(parameterCategory) {
		return parameters[parameterCategory.toLowerCase()];
	}

	function search() {

		clearResultTable();

		let searchCategories = getSearchCategories();
		let searchParameters = getSearchParameters();
		let searchInputFields = getSearchInputFields();

		const tupelList = getDatabaseAsTupelList(database);
		const results = getSearchMatchesFromTupelList(tupelList, searchCategories, searchParameters, searchInputFields);

		showResultHeader(results, resultTableId);
		showResults(results, resultTableId);
	}

	function showResultHeader(results, resultTableId) {

		let resultHeaderTupel = [];
		const firstTupel = results[0];
		if (firstTupel === undefined) return;
		firstTupel.forEach(tupelElement => {
			resultHeaderTupel.push(capitalize(tupelElement["category"]));
		})

		addTupelToTableHeader(resultHeaderTupel, resultTableId);
	}

	function addTupelToTableHeader(tupel, tableId) {

		let table = document.getElementById(tableId);
		let header = table.createTHead();
		let row = header.insertRow(-1);

		let index = 0;
		tupel.forEach(tupelElement => {
			let cell = row.insertCell(-1);
			cell.append(tupelElement);
			let n = index;
			cell.addEventListener("click", () => sortTable(resultTableId, n));
			index++;
		})
	}

	function showResults(results, resultTableId) {
		addTupelArrayToTable(results, resultTableId);
	}

	function addTupelArrayToTable(results, tableId) {

		let table = document.getElementById(tableId);

		results.forEach(tupel => {
			let row = table.insertRow(-1);
			tupel.forEach(tupelElement => {
				let cell = row.insertCell(-1);
				cell.append(tupelElement["name"]);
			})
		})
	}

	function capitalize(string) {
		return string[0].toUpperCase() + string.slice(1);
	}

	function getSearchMatchesFromTupelList(tupelList, searchCategories, searchParameters, searchInputFields) {

		let filteredRelationDatabase = [];

		tupelList.forEach(tupel => {
			if (foundSearchValuesInTupel(tupel, searchCategories, searchParameters, searchInputFields)) filteredRelationDatabase.push(tupel);
		})

		return filteredRelationDatabase;
	}

	function foundSearchValuesInTupel(tupel, searchCategories, searchParameters, searchInputFields) {

		for (let i = 0; i < searchCategories.length; i++) {

			let searchCategory = searchCategories[i];
			let searchParameter = searchParameters[i];
			let searchInputField = searchInputFields[i];

			if (!foundSearchValueInTupel(tupel, searchCategory, searchParameter, searchInputField)) return false;
		}
		return true;
	}

	function foundSearchValueInTupel(tupel, searchCategory, searchParameter, searchInputField) {

		let foundSearchValue = false;

		if (searchInputField == "") return true;

		for (let key in tupel) {
			let tupelElement = tupel[key];
			if (tupelElement["category"].toLowerCase() != searchCategory) continue;
			if (tupelElement[searchParameter].toLowerCase() != searchInputField) continue;
			foundSearchValue = true;
			break;
		}
		return foundSearchValue;
	}

	function htmlCollectionToLowerCaseArray(htmlCollection) {
		htmlCollection = Array.from(htmlCollection).map((element) => element.value.toLowerCase());
		return htmlCollection;
	}

	function getSearchInputFields() {
		let searchInputFields = getSearchTable().getElementsByClassName(searchValueClass);
		searchInputFields = htmlCollectionToLowerCaseArray(searchInputFields);
		return searchInputFields;
	}

	function getSearchParameters() {
		let searchParameters = getSearchTable().getElementsByClassName(searchParameterClass);
		searchParameters = htmlCollectionToLowerCaseArray(searchParameters);
		return searchParameters;
	}

	function getSearchCategories() {
		let searchCategories = getSearchTable().getElementsByClassName(categorySelectClass);
		searchCategories = htmlCollectionToLowerCaseArray(searchCategories);
		return searchCategories;
	}

	function getDatabaseAsTupelList(database) {

		let relationDatabase = [];

		let lowestBranchArray = getLowestCategory(databaseIndexTable, database);

		lowestBranchArray.forEach(lowestCategoryElement => {

			lowestCategoryElement["category"] = databaseIndexTable[databaseIndexTable.length - 1]

			let relationTupel = [];
			relationTupel.push(lowestCategoryElement);

			let fk = lowestCategoryElement["fk"];

			for (let i = databaseIndexTable.length - 2; i >= 0; i--) {

				let category = databaseIndexTable[i];

				let categoryElementArray = database[category];

				for (let key in categoryElementArray) {
					let element = categoryElementArray[key];
					if (element["pk"] == fk) {
						element["category"] = category;
						relationTupel.push(element);
						fk = element["fk"];
						break;
					}
				}
			}
			relationDatabase.push(relationTupel);
		})

		return relationDatabase;
	}

	function getLowestCategory(databaseIndexTable, database) {
		const lowestBranchIndex = databaseIndexTable.length - 1;
		const lowestBranchCategory = databaseIndexTable[lowestBranchIndex];
		const lowestBranchArray = database[lowestBranchCategory];
		return lowestBranchArray;
	}

	function sortTable(tableId, n) {
		console.log({n})
		var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
		table = document.getElementById(tableId);
		switching = true;
		// Set the sorting direction to ascending:
		dir = "asc";
		/* Make a loop that will continue until
		no switching has been done: */
		while (switching) {
			// Start by saying: no switching is done:
			switching = false;
			rows = table.rows;
			/* Loop through all table rows (except the
			first, which contains table headers): */
			for (i = 1; i < (rows.length - 1); i++) {
				// Start by saying there should be no switching:
				shouldSwitch = false;
				/* Get the two elements you want to compare,
				one from current row and one from the next: */
				x = rows[i].getElementsByTagName("TD")[n];
				y = rows[i + 1].getElementsByTagName("TD")[n];
				/* Check if the two rows should switch place,
				based on the direction, asc or desc: */
				if (dir == "asc") {
					if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
						// If so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				} else if (dir == "desc") {
					if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
						// If so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				}
			}
			if (shouldSwitch) {
				/* If a switch has been marked, make the switch
				and mark that a switch has been done: */
				rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
				switching = true;
				// Each time a switch is done, increase this count by 1:
				switchcount++;
			} else {
				/* If no switching has been done AND the direction is "asc",
				set the direction to "desc" and run the while loop again. */
				if (switchcount == 0 && dir == "asc") {
					dir = "desc";
					switching = true;
				}
			}
		}
	}

	return public;

})();

GlobaleSuche.init();