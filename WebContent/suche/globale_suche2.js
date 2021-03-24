var GlobaleSuche = (function () {

	public = {};

	const addButtonId = "global_search_add_parameter_button";
	const searchButtonId = "global_search_button";
	const parameterTableId = "global_search_parameter_table";
	const resultTableId = "global_search_result_table";
	const rowTemplateId = "global_search_parameter_row_template";
	const categorySelectClass = "global_search_select_main_category";
	const deleteButtonClass = "global_search_delete_parameter_button";
	const searchParameterClass = "global_search_select_parameter";
	const searchFilterClass = "global_search_select_parameter_filter";
	const searchValueClass = "global_search_parameter_input";
	const firstHeaderClass = "global_search_result_table_first_header";
	const secondHeaderClass = "global_search_result_table_second_header";
	const resultRowClass = "global_search_result_table_result_row";
	const parameterRowClass = "global_search_parameter_row"

	const categoryKey = "table";
	const displayKey = "display";
	const sortFunctionSkipRows = 2;

//	const servletURL = "http://localhost:8080/2020_LIMS/Suche";
//	const servletURL = "https://hbolims.herokuapp.com/Suche";
	let servletURL = "";

	let parameters = {}

	const filterTypes = {
		matches: "entspricht",
		contains: "beinhaltet"
	};

	public.init = function init(servletAddress) {
		
		servletURL = servletAddress;

		fetchDatabase((tupelArray) => {

			initAddParameterButton();
			initSearchButton();
			fetchParameters(tupelArray);
			addParameterRow();

			const template = [
				{ "partner": "id" },
				{ "probe": "id" },
				{ "experiment": "typ" },
				{ "experiment": "id" },
				{ "projekte": "vertragsnummer" },
				{ "partner": "email" },
				{ "partner": "name" }
			];
			// public.initTemplateParameters(template);
		})
	}

	function fetchParameters(tupelArray) {

		tupelArray.forEach((tupel) => {
			tupel.forEach((tupelElement) => {

				let table = undefined;
				let tableParameters = [];

				// get table key
				for (let key in tupelElement) {
					if (key !== categoryKey) continue;
					table = tupelElement[key];
					break;
				}

				if (table === undefined) return;

				// fill table array with parameter values
				for (let key in tupelElement) {
					if (key === categoryKey) continue;
					tableParameters.push(key);
				}
				parameters[table] = tableParameters;
			})
		})
	}

	let globalCallbackInputMask = undefined;

	public.addSearchCallback = function addSearchCallback(callback) {
		globalCallbackInputMask = callback;
	}

	public.initTemplateParameters = function initTemplateParameters(template) {

		clearParameterRows();
		clearResultTable();

		addRowsForTemplate(template);
		setTemplateParameters(template);

		function addRowsForTemplate(template) {
			for (let i = 0; i < template.length; i++) addParameterRow();
		}

		function setTemplateParameters(template) {

			let table = document.getElementById(parameterTableId);
			let rows = table.rows;

			setTemplateParameterForEachRow(template, rows);
		}

		function setTemplateParameterForEachRow(template, rows) {
			let templateIndex = 0;

			for (let rowIndex = 1; rowIndex < rows.length; rowIndex++) {

				let row = rows[rowIndex];
				let categorySelect = row.getElementsByClassName(categorySelectClass)[0];
				let parameterSelect = row.getElementsByClassName(searchParameterClass)[0];
				let parameterOptions = parameterSelect.options;
				let templateElement = template[templateIndex++];
				let category = getCategory(templateElement)
				let parameter = templateElement[category].toLowerCase();

				selectOption(categorySelect, category)
				createParametersForSelectedCategory(parameterSelect, category);
				selectCorrectParameterOption(parameterOptions, parameter);
			}
		}

		function selectOption(selectElement, optionToSelect) {

			let selectOptions = selectElement.options;

			for (let optionIndex = 0; optionIndex < selectOptions.length; optionIndex++) {
				let option = selectOptions[optionIndex];
				let optionText = option.text.toLowerCase();
				if (optionText != optionToSelect.toLowerCase()) continue;
				option.selected = "true";
			}
		}

		function createParametersForSelectedCategory(selectElement, category) {

			removeOptions(selectElement);
			addCategoryOptions(category, selectElement);

		}

		function addCategoryOptions(category, selectElement) {
			let searchParameters = parameters[category];
			searchParameters.forEach(searchParameter => {
				let option = document.createElement("option");
				option.text = searchParameter;
				selectElement.add(option);
			})
		}

		function selectCorrectParameterOption(parameterOptions, parameter) {
			for (let parameterOptionIndex = 0; parameterOptionIndex < parameterOptions.length; parameterOptionIndex++) {
				let parameterOption = parameterOptions[parameterOptionIndex];
				if (parameterOption.text.toLowerCase() == parameter) parameterOption.selected = "true";
			}
		}

		function getCategory(categoryParameterKeyValuePair) {
			return Object.keys(categoryParameterKeyValuePair)[0].toLowerCase();
		}
	}

	function clearParameterRows() {
		let table = document.getElementById(parameterTableId);
		let rows = table.rows;
		removeAllExceptFirstRow();

		function removeAllExceptFirstRow() {
			for (let i = rows.length - 1; i > 0; i--) {
				rows[i].remove();
			}
		}
	}

	function initAddParameterButton() {
		document.getElementById(addButtonId).addEventListener("click", () => addParameterRow());
	}

	function initSearchButton() {
		document.getElementById(searchButtonId).addEventListener("click", () => search());
	}

	function initDeleteButton(element) {
		element.addEventListener("click", (element) => deleteSuchParameter(element.target));
	}

	function addEnterListenerToNode(element) {

		element.addEventListener("keyup", (event) => {

			if (event.keyCode === 13) {
				document.getElementById(searchButtonId).click();
			}
		})
	}

	function getSearchTable() {
		return document.getElementById(parameterTableId);
	}

	function fetchDatabase(callback) {

		fetch(servletURL, {
			method: "post",
		})
			.then(response => response.json())
			.then(data => {
				callback(data)
			});
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
		row.className = parameterRowClass;

		let mainCategorySelect;

		for (let i = 0; i < templateChildNodes.length; i++) {

			let cell = row.insertCell(i);
			var childNode = templateChildNodes[i].cloneNode(true);
			cell.append(childNode);
			addEnterListenerToNode(childNode);

			if (childNode.className == categorySelectClass) mainCategorySelect = childNode;
			if (childNode.className == deleteButtonClass) initDeleteButton(childNode);
			if (childNode.className == searchFilterClass) initFilterTypes(childNode);
		}

		initMainCategory(mainCategorySelect);
	}

	function createMainCategory(event) {

		const htmlElement = event.target;
		let categories = []

		for (let key in parameters) {
			categories.push(capitalize(key));
		}
		insertParameters(categories, htmlElement);
	}

	function initMainCategory(selectElement) {

		selectElement.addEventListener("change", (event) => createParameters(event));
		createMainCategory({ target: selectElement });
		createParameters({ target: selectElement });
	}

	function initFilterTypes(selectElement) {

		let filterTypesArray = [];
		for (let key in filterTypes) {
			filterTypesArray.push(filterTypes[key]);
		}
		insertParameters(filterTypesArray, selectElement);
	}

	function createParameters(event) {

		const htmlElement = event.target;
		const parameterCategory = htmlElement.value;

		let row = htmlElement;
		while (row.nodeName != "TR") row = row.parentNode;
		selectElement = row.getElementsByClassName(searchParameterClass)[0];

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
		let searchFilterTypes = getSearchFilterTypes();

		fetchDatabase((database) => {

			const tupelList = database;
			const results = getSearchMatchesFromTupelList(tupelList, searchCategories, searchParameters, searchInputFields, searchFilterTypes);

			renderResultTable(results, resultTableId);
		})
	}

	function renderResultTable(results, resultTableId) {

		let indexOfLongestTupel = findLongestTupel();
		const firstTupel = results[indexOfLongestTupel];
		let resultHeaderTupel = [];

		if (firstTupel === undefined) return;
		firstTupel.forEach(tupelElement => {
			resultHeaderTupel.push(capitalize(tupelElement[categoryKey]));
		})

		function findLongestTupel() {

			let indexOfBiggestResultTupel = 0;
			let biggestSize = 0;
			for (let i = 0; i < results.length; i++) {
				let tupel = results[i];
				if (tupel.length <= biggestSize) continue;
				biggestSize = tupel.length;
				indexOfBiggestResultTupel = i;
			}
			return indexOfBiggestResultTupel
		}

		const parameters = getSearchParameters();
		const categories = getSearchCategories();

		// categories und parameter nach Name sortieren: [a,b,a] -> [a,a,b]
		for (let i = 0; i < categories.length - 1; i++) {
			let categoryPivot = categories[i];

			for (let j = i + 1; j < categories.length; j++) {
				let category = categories[j];
				let parameter = parameters[j];

				if (category === categoryPivot) {
					let switchCategory = categories[i + 1];
					categories[i + 1] = category;
					categories[j] = switchCategory;

					let switchParameter = parameters[i + 1];
					parameters[i + 1] = parameter;
					parameters[j] = switchParameter;
					break;
				}
			}
		}

		let newResults = [];

		// Ergebnisse mit Parametern heraussuchen und "display" key-value setzen
		results.forEach(tupel => {
			let newTupel = [];
			let found = 0;
			for (let i = 0; i < categories.length; i++) {

				let category = categories[i];

				for (let j = 0; j < tupel.length; j++) {
					let element = tupel[j];
					if (element[categoryKey].toLowerCase() !== category.toLowerCase()) continue;
					found++;
					let elementAsJson = { ...element };
					elementAsJson[displayKey] = element[parameters[i]];
					newTupel.push(elementAsJson);
					break;
				}

				// Platzhalter generieren damit die Spalten des Tupels nicht in eine falsche Reihenfolge verrutschen
				if (found == i) {
					let elementAsJson = { categoryKey: category };
					elementAsJson[displayKey] = "";
					newTupel.push(elementAsJson);
					found++;
				}
			}
			newResults.push(newTupel);
		})

		addTupelAsTableHeader(categories, resultTableId, firstHeaderClass, mergeHeader = true);
		addTupelAsTableHeader(parameters, resultTableId, secondHeaderClass, mergeHeader = false, addSortFunction = true);
		addResultsToTable(newResults, resultTableId, resultRowClass);
		mergeRedundantRows(resultTableId);
	}

	function mergeRedundantRows(resultTableId) {

		let table = document.getElementById(resultTableId);
		let rows = table.rows;

		for (let i = rows.length - 1; i > sortFunctionSkipRows; i--) {

			let row = rows[i];

			for(let j = i - 1; j > sortFunctionSkipRows - 1; j--){

				let nextRow = rows[j];
	
				if (row.innerText === nextRow.innerText) {
					table.deleteRow(i);
					break;
				}
			}

		}
	}

	function addTupelAsTableHeader(tupel, tableId, className, mergeHeader, addSortFunction) {

		let table = document.getElementById(tableId);
		let row = table.insertRow(-1);
		if (className !== undefined) row.className = className;

		let index = 0;
		tupel.forEach(tupelElement => {
			let cell = row.insertCell(-1);
			cell.append(tupelElement);
			let n = index;	// "n" muss angelegt werden da "index" außerhalb der Schleife definiert ist und somit nur "Pass-By-Reference"
			if (addSortFunction !== undefined && addSortFunction === true) cell.addEventListener("click", () => sortTable(resultTableId, n));
			index++;
		})
		if (mergeHeader !== undefined && mergeHeader === true) mergeRedundantRowCells(row);
	}

	function mergeRedundantRowCells(row) {

		let childNodes = row.childNodes;
		let colSpan = 1;
		for (let i = childNodes.length - 1; i > 0; i--) {
			let child = childNodes[i];
			let nextChild = childNodes[i - 1];
			if (child.innerHTML === nextChild.innerHTML) {
				colSpan++;
				row.removeChild(nextChild);
				child.colSpan = colSpan;
			} else {
				colSpan = 1;
			}
		}
	}

	function addResultsToTable(results, resultTableId, className) {
		addTupelArrayToResults(results, resultTableId, className, displayKey, addShowDetailsCallbackFunction = true);
	}

	function addTupelArrayToResults(results, tableId, className, onlyThisKey, addShowDetailsCallbackFunction) {

		let table = document.getElementById(tableId);

		results.forEach(tupel => {
			let row = table.insertRow(-1);
			if (className !== undefined) row.className = className;
			tupel.forEach(tupelElement => {

				for (let key in tupelElement) {

					let cellContent = tupelElement[key];

					if (onlyThisKey === undefined) {
						let cell = addToNewTableCell(cellContent, row);
						if (addShowDetailsCallbackFunction !== undefined && addShowDetailsCallbackFunction === true && tupelElement[displayKey] !== "") addShowDetailsListener(cell, tupelElement);
						addCallbackFunction(cell, cellContent);
					}

					if (onlyThisKey === key) {
						let cell = addToNewTableCell(cellContent, row)
						if (addShowDetailsCallbackFunction !== undefined && addShowDetailsCallbackFunction === true && tupelElement[displayKey] !== "") addShowDetailsListener(cell, tupelElement);
						break;
					}
				}
			})

			function addToNewTableCell(tupelElement, row) {
				let cell = row.insertCell(-1);
				cell.append(tupelElement);
				return cell;
			}

			function addCallbackFunction(cell, cellContent) {
				cell.addEventListener("click", () => {
					if (globalCallbackInputMask === undefined) return;
					globalCallbackInputMask(cellContent);
					globalCallbackInputMask = undefined;
				})
			}

			function addShowDetailsListener(cell, tupelElement) {
				cell.addEventListener("click", () => {
					clearResultTable();
					let tupelElementArray = [];
					let tableName = tupelElement[categoryKey];
					delete tupelElement[categoryKey];
					delete tupelElement[displayKey];
					for (let key in tupelElement) {
						tupelElementArray.push(key);
					}

					// create header for each column -> addTupelAsTableHeader(); will merge with colSpan later
					tableNameHeaderArray = [];
					for (let key in tupelElement) {
						tableNameHeaderArray.push(tableName);
					}

					addTupelAsTableHeader(tableNameHeaderArray, resultTableId, firstHeaderClass, mergeHeader = true);
					addTupelAsTableHeader(tupelElementArray, resultTableId, secondHeaderClass);
					addTupelArrayToResults([[tupelElement]], resultTableId, resultRowClass, onlyThisKey = undefined, addShowDetailsCallbackFunction = false);
				});
			}
		})
	}

	function capitalize(string) {
		return string[0].toUpperCase() + string.slice(1);
	}

	function getSearchMatchesFromTupelList(tupelList, searchCategories, searchParameters, searchInputFields, searchFilterTypes) {

		let filteredRelationDatabase = [];

		tupelList.forEach(tupel => {
			if (foundSearchValuesInTupel(tupel, searchCategories, searchParameters, searchInputFields, searchFilterTypes)) filteredRelationDatabase.push(tupel);
		})

		return filteredRelationDatabase;
	}

	function foundSearchValuesInTupel(tupel, searchCategories, searchParameters, searchInputFields, searchFilterTypes) {

		for (let i = 0; i < searchCategories.length; i++) {

			let searchCategory = searchCategories[i];
			let searchParameter = searchParameters[i];
			let searchInputField = searchInputFields[i];
			let filterType = searchFilterTypes[i];

			if (!foundSearchValueInTupel(tupel, searchCategory, searchParameter, searchInputField, filterType)) return false;
		}
		return true;
	}

	function foundSearchValueInTupel(tupel, searchCategory, searchParameter, searchInputField, filterType) {

		let foundSearchValue = false;

		searchCategory = searchCategory.trim();
		searchCategory = searchCategory.toLowerCase();
		searchParameter = searchParameter.trim();
		searchParameter = searchParameter.toLowerCase();
		searchInputField = searchInputField.trim();
		searchInputField = searchInputField.toLowerCase();

		if (searchInputField === "") return true;

		for (let key in tupel) {

			let tupelElement = tupel[key];
			let category = tupelElement[categoryKey].toLowerCase();

			if (category !== searchCategory) continue;
			let parameter = tupelElement[searchParameter].toLowerCase();

			if (filterType === filterTypes.matches) {
				if (parameter !== searchInputField) continue;
			}

			if (filterType === filterTypes.contains) {
				if (!parameter.includes(searchInputField)) continue;
			}

			foundSearchValue = true;
			break;
		}
		return foundSearchValue;
	}

	function htmlCollectionToArray(htmlCollection) {
		htmlCollection = Array.from(htmlCollection).map((element) => element.value);
		return htmlCollection;
	}

	function getSearchInputFields() {
		let searchInputFields = getSearchTable().getElementsByClassName(searchValueClass);
		searchInputFields = htmlCollectionToArray(searchInputFields);
		return searchInputFields;
	}

	function getSearchParameters() {
		let searchParameters = getSearchTable().getElementsByClassName(searchParameterClass);
		searchParameters = htmlCollectionToArray(searchParameters);
		return searchParameters;
	}

	function getSearchFilterTypes() {
		let searchInputFields = getSearchTable().getElementsByClassName(searchFilterClass);
		searchInputFields = htmlCollectionToArray(searchInputFields);
		return searchInputFields;
	}

	function getSearchCategories() {
		let searchCategories = getSearchTable().getElementsByClassName(categorySelectClass);
		searchCategories = htmlCollectionToArray(searchCategories);
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

		var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
		table = document.getElementById(tableId);
		rows = table.rows;
		switching = true;
		// Set the sorting direction to ascending:
		dir = "asc";
		/* Make a loop that will continue until
		no switching has been done: */

		// rows with undefined or empty cell to bottom
		for (i = rows.length - 1; i > 0; i--) {
			let tableData = rows[i].getElementsByTagName("TD")[n];
			if (tableData === undefined || tableData.innerHTML === "") rows[i].parentNode.insertBefore(rows[i], null);
		}

		// index to exclude rows with undefinded or empty fields
		let maxIndexOfDefinedRows = 0;
		for (let i = sortFunctionSkipRows; i < (rows.length); i++) {
			let td = rows[i].getElementsByTagName("TD")[n];
			if (td === undefined || td.innerHTML === "") break;
			maxIndexOfDefinedRows = i;
		}

		while (switching) {
			// Start by saying: no switching is done:
			switching = false;

			/* Loop through all table rows (except the
			first, which contains table headers): */
			// for (i = 1; i < (rows.length - 1); i++) {
			for (i = sortFunctionSkipRows; i < maxIndexOfDefinedRows; i++) {
				// Start by saying there should be no switching:
				shouldSwitch = false;
				/* Get the two elements you want to compare,
				one from current row and one from the next: */
				x = rows[i].getElementsByTagName("TD")[n];
				y = rows[i + 1].getElementsByTagName("TD")[n];
				/* Check if the two rows should switch place,
				based on the direction, asc or desc: */
				if (dir == "asc") {
					if (y === undefined) {
						shouldSwitch = true;
						break;
					}
					if (x === undefined) {
						shouldSwitch = false;
						break;
					}
					if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
						// If so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				} else if (dir == "desc") {
					if (x === undefined) {
						shouldSwitch = true;
						break;
					}
					if (y === undefined) {
						shouldSwitch = false;
						break;
					}
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

//GlobaleSuche.init();
// const template = [
// 	{ "Partner": "id" },
// 	{ "Probe": "id" },
// 	{ "Experiment": "typ" },
// 	{ "Experiment": "id" },
// 	{ "Projekte": "vertragsnummer" },
// 	{ "Partner": "email" },
// 	{ "Partner": "name" }
// ];
// GlobaleSuche.initTemplateParameters(template);