var GlobaleSuche = (function () {

	public = {};

	const addButton = "global_search_add_parameter_button";
	const searchButton = "global_search_button";
	// const tableDataClass = "table_globale_suche_td";
	const parameterTableId = "global_search_parameter_table";
	// const tableRowClass = "table_globale_suche_tr";
	// const tableHeaderClass = "table_globale_suche_th";
	const resultTableId = "global_search_result_table";
	const rowTemplateId = "global_search_parameter_row_template";
	const categorySelectClass = "global_search_select_main_category"
	const deleteButtonClass = "global_search_delete_parameter_button";
	const searchParameterClass = "global_search_parameter";
	const searchValueClass = "global_search_parameter_input";

	const database = initDatabase();

	const databaseIndexTable = [
		"projektpartner",
		"projekt",
		"probe",
		"experiment",
		"methode"
	]

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

	function initMainCategorySelect(element) {
		element.addEventListener("change", (event) => createParameters(event));
		createParameters({ target: element });
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

	function createTableHeaderRowWithValues(relationDatabase) {
		let headerRow = createTableHeaderRow();
		relationDatabase[0].forEach(element => {
			for (let key in element) {
				let tableData = createTableData();
				tableData.append(key);
				headerRow.append(tableData);
			}
		});
		return headerRow;
	}

	function createTableData() {
		let tableData = document.createElement("div");
		tableData.classList.add(tableDataClass);
		return tableData;
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
			"fk": "1",
			"operator": "John Doe"
		};
		const methode2 = {
			"pk": "2",
			"name": "Methode 2",
			"fk": "1",
			"operator": "Jane Doe"
		};
		const methode3 = {
			"pk": "3",
			"name": "Methode 3",
			"fk": "2",
			"operator": "Jane Doe"
		};
		const methode4 = {
			"pk": "4",
			"name": "Methode 4",
			"fk": "2",
			"operator": "Jane Doe"
		};
		const database = {
			"projektpartner": [partner1],
			"projekt": [projekt1],
			"probe": [probe1],
			"experiment": [experiment1, experiment2],
			"methode": [methode1, methode2, methode3, methode4]
		};
		return database;
	}

	function createTableRow() {
		let tableRow = document.createElement("div");
		tableRow.classList.add(tableRowClass);
		return tableRow;
	}

	function createTableHeaderRow() {
		let tableHeaderRow = document.createElement("div");
		tableHeaderRow.classList.add(tableRowClass);
		tableHeaderRow.classList.add(tableHeaderClass);
		return tableHeaderRow;
	}

	function addRowToHTMLResultList(row) {

		const tableGlobaleSuche = document.getElementById(resultTableId);
		tableGlobaleSuche.append(row);
	}

	function clearHTMLErgebnisListe() {

		const ergebnisListe = document.getElementById(resultTableId);

		while (ergebnisListe.hasChildNodes()) {
			ergebnisListe.removeChild(ergebnisListe.lastChild);
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

			if (child.className == categorySelectClass) mainCategorySelect = child;
			if (child.className == deleteButtonClass) initDeleteButton(child);
		}

		initMainCategorySelect(mainCategorySelect);
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

	function removeOptions(selectElement) {


		let selectOptions = selectElement.querySelectorAll("option");
		selectOptions.forEach(option => option.remove());
	}

	function getParameters(parameterCategory) {
		const parameters = {
			"projektpartner": ["pk", "name"],
			"projekt": ["pk", "name", "fk"],
			"probe": ["pk", "name", "fk"],
			"experiment": ["pk", "name", "fk"],
			"methode": ["pk", "name", "fk", "operator"]
		}
		return parameters[parameterCategory.toLowerCase()];
	}

	function search() {

		clearHTMLErgebnisListe();

		let searchCategories = getSearchCategories();
		let searchParameters = getSearchParameters();
		let searchInputFields = getSearchInputFields();

		const tupelList = getDatabaseAsTupelList(database);

		// const resultHeader = createResultHeader(tupelList);
		// showResultHeader(resultHeader);

		// const results = getResults(tupelList, searchCategories, searchParameters, searchInputFields);
		// showResults(results);
	}

	function createResultHeader(relationDatabase) {
		return createTableHeaderRowWithValues(relationDatabase);
	}

	function showResultHeader(headerRow) {
		addRowToHTMLResultList(headerRow);
	}

	function showResults(filteredRelationDatabase) {

		filteredRelationDatabase.forEach(tupel => {
			let tableRow = createTableRow();
			tupel.forEach(tupelElement => {
				for (let key in tupelElement) {
					let tableData = createTableData();
					tableData.append(tupelElement[key]);
					tableRow.append(tableData);
				}
			});
			addRowToHTMLResultList(tableRow);
		});
	}

	function getResults(relationDatabase, searchCategories, searchParameters, searchInputFields) {

		let filteredRelationDatabase = [];

		relationDatabase.forEach(tupel => {
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

	return public;

})();

GlobaleSuche.init();