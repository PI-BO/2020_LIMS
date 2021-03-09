class GlobaleSuche {

	static init() {

		this.initAddParameterButton();
		this.initSearchButton();
		this.initFirstParameterRow()
	}

	static initAddParameterButton() {
		document.getElementById("globale_suche_add_parameter_button").addEventListener("click", () => GlobaleSuche.addParameterRow());
	}

	static initSearchButton() {
		document.getElementById("globale_suche_button").addEventListener("click", () => GlobaleSuche.search());
	}

	static initFirstParameterRow() {
		document.getElementById("globale_suche_add_parameter_button").click();
	}

	static initMainCategorySelect(element) {
		element.addEventListener("change", (event) => this.createParameters(event));
		this.createParameters({ target: element });
	}

	static initDeleteButton(element) {
		element.addEventListener("click", (element) => GlobaleSuche.deleteSuchParameter(element.target));
	}

	static addEnterListener(element) {

		element.addEventListener("keyup", (event) => {

			if (event.keyCode === 13) {
				document.getElementById("globale_suche_button").click();
			}
		})
	}

	static search() {

		const database = this.getDatabase();
		const databaseRelationIndexTable = this.getDatabaseRelationIndexTable()

		const searchTable = this.getSearchTable();
		this.resetHTMLErgebnisListe();
		
		let searchCategories = this.getSearchCategories(searchTable);
		let searchParameters = this.getSearchParameters(searchTable);
		let searchInputFields = this.getSearchInputFields(searchTable);
		
		searchCategories = this.htmlCollectionToLowerCaseArray(searchCategories);
		searchParameters = this.htmlCollectionToLowerCaseArray(searchParameters);
		searchInputFields = this.htmlCollectionToLowerCaseArray(searchInputFields);
		
		let databaseResults = [];
		
		let relationDatabase = []
		
		relationDatabase = this.getDatabaseInRelations();
		
		let headerRow = this.createTableHeaderRow();
		relationDatabase[0].forEach(element => {
			for(let key in element){
				let tableData = this.createTableData();
				tableData.append(key);
				headerRow.append(tableData);
			}
		});
		this.addRowToHTMLResultList(headerRow);

		relationDatabase.forEach(element => {
			let tableRow = this.createTableRow();
			element.forEach(element => {
				for(let key in element){
					let tableData = this.createTableData();
					tableData.append(element[key]);
					tableRow.append(tableData);
				}
			});
			this.addRowToHTMLResultList(tableRow);
		});

		let tableHeaderRow = this.createTableHeaderRow();
		this.addRowToHTMLResultList(tableHeaderRow);
		
		for (let i = 0; i < searchCategories.length; i++) {

			let category = searchCategories[i];
			let parameter = searchParameters[i];
			let searchValue = searchInputFields[i];

			databaseResults[category] = database[category].filter(element => {
				const elementValue = element[parameter].toLowerCase();
				return elementValue == searchValue;
			});

			let tableRow = this.createTableRow();

			databaseResults[category].forEach(categoryData => {
				for(const key in categoryData){
					let tableData = this.createTableData();
					tableData.append(categoryData[key]);
					tableRow.append(tableData);
				}
			})

			this.addRowToHTMLResultList(tableRow);
		}



		// let tableHeaderRow = this.createTableHeaderRow();

		// for (let key in databaseCopy[0]) {

		// 	let tableData = document.createElement("div");
		// 	tableData.classList.add("table_globale_suche_td");
		// 	tableData.append(key);
		// 	tableHeaderRow.append(tableData);
		// }

		// this.addRowToHTMLResultList(tableHeaderRow);

		// databaseCopy.forEach(result => {

		// 	let tableRow = this.createTableRow();

		// 	for (let key in result) {
		// 		let tableData = document.createElement("div");
		// 		tableData.classList.add("table_globale_suche_td");
		// 		tableData.append(result[key]);
		// 		tableRow.append(tableData);
		// 	}

		// 	tableGlobaleSuche.append(tableRow);

		// });

	}

	static createTableData() {
		let tableData = document.createElement("div");
		tableData.classList.add("table_globale_suche_td");
		return tableData;
	}

	static getDatabaseInRelations(){

		let database = this.getDatabase();
		let databaseIndexTable = this.getDatabaseRelationIndexTable();
		let relationDatabase = [];

		let lowestBranchArray = getLowestCategory(databaseIndexTable, database);

		lowestBranchArray.forEach( lowestCategoryElement => {

			let relationTupel = [];
			relationTupel.push(lowestCategoryElement);
			
			let fk = lowestCategoryElement["fk"];

			for(let i = databaseIndexTable.length - 2; i >= 0; i--){

				let category = databaseIndexTable[i];
				
				let categoryElementArray = database[category];

				for(let key in categoryElementArray){
					let element = categoryElementArray[key];
					if(element["pk"] == fk){
						relationTupel.push(element);
						fk = element["fk"];
						break;
					}
				}
			}
			relationDatabase.push(relationTupel);
		})

		return relationDatabase;

		function getLowestCategory(databaseIndexTable, database) {
			const lowestBranchIndex = databaseIndexTable.length - 1;
			const lowestBranchCategory = databaseIndexTable[lowestBranchIndex];
			const lowestBranchArray = database[lowestBranchCategory];
			return lowestBranchArray;
		}

	}

	static getSearchTable() {
		return document.getElementById("such_parameter_table");
	}

	static getDatabaseRelationIndexTable() {

		let indexTable = [];
		indexTable[0] = "projektpartner";
		indexTable[1] = "projekt";
		indexTable[2] = "probe";
		indexTable[3] = "experiment";
		indexTable[4] = "methode";

		return indexTable;
	}

	static getDatabase() {
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

	static createTableRow() {
		let tableRow = document.createElement("div");
		tableRow.classList.add("table_globale_suche_tr");
		return tableRow;
	}

	static createTableHeaderRow() {
		let tableHeaderRow = document.createElement("div");
		tableHeaderRow.classList.add("table_globale_suche_tr");
		tableHeaderRow.classList.add("table_globale_suche_th");
		return tableHeaderRow;
	}

	static addRowToHTMLResultList(row) {

		const tableGlobaleSuche = document.getElementById("table_globale_suche");
		tableGlobaleSuche.append(row);
	}

	static htmlCollectionToLowerCaseArray(htmlCollection) {
		htmlCollection = Array.from(htmlCollection).map((element) => element.value.toLowerCase());
		return htmlCollection;
	}

	static getSearchInputFields(searchTable) {
		let searchInputFields = searchTable.getElementsByClassName("such_parameter_inhalt");
		return searchInputFields;
	}

	static getSearchParameters(searchTable) {
		let searchParameters = searchTable.getElementsByClassName("such_parameter");
		return searchParameters;
	}

	static getSearchCategories(searchTable) {
		let searchCategorys = searchTable.getElementsByClassName("select_main_category");
		return searchCategorys;
	}

	static resetHTMLErgebnisListe() {

		const ergebnisListe = document.getElementById("table_globale_suche");

		while (ergebnisListe.hasChildNodes()) {
			ergebnisListe.removeChild(ergebnisListe.lastChild);
		}
	}

	static addParameterRow() {

		let container = document.getElementById("such_parameter_table");

		let template = document.getElementById("such_parameter_row_template");

		let childNodes = template.children;

		const containerChildrenCount = container.children.length;
		const templateChildrenCount = childNodes.length;

		let row = container.insertRow(-1);

		let mainCategorySelect;

		for (let i = 0; i < childNodes.length; i++) {

			let cell = row.insertCell(i);
			var child = childNodes[i].cloneNode(true);
			this.addEnterListener(child);
			cell.append(child);

			if (child.className == "select_main_category") mainCategorySelect = child;
			if (child.className == "globale_suche_delete_parameter_button") this.initDeleteButton(child);
		}

		this.initMainCategorySelect(mainCategorySelect);
	}

	static deleteSuchParameter(suchParameterElement) {

		let parent = suchParameterElement.parentElement.parentElement;
		parent.remove();
	}

	static resetSelects(element) {

		let allSelects = element.querySelectorAll("select");
		allSelects.forEach(element => {
			resetSelect(element);
		})
	}

	static resetSelect(element) {
		element.options[0].selected = "true";
	}

	static createParameters(event) {

		const element = event.target;

		const parameterCategory = event.target.value;

		let selectElement = element.parentElement.parentElement.getElementsByClassName("such_parameter")[0];

		removeOptions(selectElement);
		let parameters = getParameters(parameterCategory);
		insertParameters(parameters, selectElement);

		function insertParameters(parameters, selectElement) {

			parameters.forEach(parameter => {
				let selectOption = document.createElement("option");
				selectOption.text = parameter;
				selectOption.value = parameter;
				selectElement.add(selectOption);
			});
		}

		function findSelectElements(event, className) {

			let table = document.getElementById("such_parameter_table");
			let selectElements = document.getElementsByClassName(className);
			return selectElements;
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
	}
}

GlobaleSuche.init();
