const GlobaleSuche = (function () {

	const public = {};

	// IDs
	const addButtonId = "global_search_add_parameter_button";
	const searchButtonId = "global_search_button";
	const closeSearchButtonId = "close_search_button";
	const minimizeSearchButtonId = "minimize_search_button";
	const parameterTableId = "global_search_parameter_table";
	const resultTableId = "global_search_result_table";
	const rowTemplateId = "global_search_parameter_row_template";
	const globalSearchMainContentContainerId = "main-content-global-search";
	const globalSearchMainContainerId = "global_search_main_container";
	const globalSearchMainHeaderId = "global_search_main_header";
	const globalSearchMainHeaderTextId = "global_search_main_header_text";

	// Classes
	const categorySelectClass = "global_search_select_main_category";
	const deleteButtonClass = "global_search_delete_parameter_button";
	const searchParameterClass = "global_search_select_parameter";
	const searchFilterClass = "global_search_select_parameter_filter";
	const searchValueClass = "global_search_parameter_input";
	const firstHeaderClass = "global_search_result_table_first_header";
	const secondHeaderClass = "global_search_result_table_second_header";
	const resultCellClass = "global_search_result_table_result_cell";
	const parameterRowClass = "global_search_parameter_row"

	// Close Button
	const closeButtonInputModeValue = "beenden";
	const closeButtonDefaultValue = "x";

	// Minimize Button
	const minimizeButtonInputModeColor = "red";
	const minimizeButtonInputModeValue = "Formular-Modus";
	const minimizeButtonDefaultValue = "_";

	// Drag-Threshold
	const widthThresholdRightSide = 100;
	const heightThresholdBottomSide = 100;

	// Table Header
	const mainHeaderdefaultText = "Suche";

	// Table Parameters
	const categoryKey = "table";
	const displayKey = "display";
	const sortFunctionSkipRows = 2;

	let parameters = {}

	public.MODEL = {
		PARTNER: {
			CATEGORY: "partner",
			PK: "id",
			NAME: "name",
			EMAIL: "email"
		},
		PROJEKT: {
			CATEGORY: "projekte",
			PK: "id",
			VERTRAGSNUMMER: "vertragsnummer",
			FK: "projektpartner"
		},
		PROBE: {
			CATEGORY: "probe",
			PK: "id",
			FK: "projekt_id",
			WIRKSTOFF: "wirkstoff"
		},
		EXPERIMENT: {
			CATEGORY: "experiment",
			PK: "id",
			FK: "proben_nr",
			TYP: "typ"
		}
	}

	const filterTypes = {
		matches: "entspricht",
		contains: "beinhaltet"
	};

	// InputMask-Callback
	let searchCallbackForInputMasks = undefined;
	let searchCallbackReturnParameterForInputMasks = new Parameter("", "", "");

	// URL
	let servletURL = "";

	public.init = function init(servletAddress) {

		return new Promise(resolve => {

			servletURL = servletAddress;

			fetchDatabase((tupelArray) => {

				initAddParameterButton();
				initSearchButton();
				fetchParameters(tupelArray);
				addParameterRow();
				initCloseButton();
				initMinimizeButton();
				initMouseDrag();

				resolve();
			})
		})
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

	function initCloseButton() {
		let closeButton = document.getElementById(closeSearchButtonId);
		closeButton.addEventListener("click", () => {
			if (searchCallbackForInputMasks === undefined) {
				NavigationMenu.hide("#" + globalSearchMainContentContainerId);
				public.resetPosition();
				clearParameterRows();
				clearResultTable();
				addParameterRow();
			} else {
				public.disableCallbackMode();
			}
		})
	}

	function initMinimizeButton() {
		let minimizeButton = document.getElementById(minimizeSearchButtonId);
		minimizeButton.addEventListener("click", () => {
			NavigationMenu.hide("#" + globalSearchMainContentContainerId);
		})
	}

	function initMouseDrag() {

		var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
		const parentContainer = document.getElementById(globalSearchMainContainerId);
		document.getElementById(globalSearchMainHeaderId).onmousedown = dragMouseDown;

		function dragMouseDown(e) {

			e = e || window.event;

			if (e.target === document.getElementById(closeSearchButtonId)) return;

			e.preventDefault();
			// get the mouse cursor position at startup:
			pos3 = e.clientX;
			pos4 = e.clientY;

			document.onmouseup = closeDragElement;
			// call a function whenever the cursor moves:
			document.onmousemove = elementDrag;
		}

		function elementDrag(e) {
			e = e || window.event;
			e.preventDefault();
			// calculate the new cursor position:
			pos1 = pos3 - e.clientX;
			pos2 = pos4 - e.clientY;
			pos3 = e.clientX;
			pos4 = e.clientY;

			// set the element's new position:
			parentContainer.style.top = (parentContainer.offsetTop - pos2) + "px";
			parentContainer.style.left = (parentContainer.offsetLeft - pos1) + "px";

			parentContainer.style.border = "solid";
			parentContainer.style.borderColor = "rgb(53, 149, 245)";

			// limit to left and top screenborders
			if (parentContainer.offsetTop < 0) parentContainer.style.top = 0 + "px";
			if (parentContainer.offsetLeft < 0) parentContainer.style.left = 0 + "px";

			const searchContainer = document.getElementById(globalSearchMainContainerId)
			let containerHeight = searchContainer.offsetHeight;
			let containerWidth = searchContainer.offsetWidth;
			const documentWidth = document.body.clientWidth;
			const documentHeight = document.body.clientHeight;

			containerHeight = containerHeight + (heightThresholdBottomSide - containerHeight);
			containerWidth = containerWidth + (widthThresholdRightSide - containerWidth);

			if (parentContainer.offsetTop > documentHeight - containerHeight) parentContainer.style.top = documentHeight - containerHeight + "px";
			if (parentContainer.offsetLeft > documentWidth - containerWidth) parentContainer.style.left = documentWidth - containerWidth + "px";
		}

		function closeDragElement() {
			/* stop moving when mouse button is released:*/
			document.onmouseup = null;
			document.onmousemove = null;
		}
	}

	public.resetPositionIfOutOfBounds = function resetPositionIfOutOfBounds() {

		const parentContainer = document.getElementById(globalSearchMainContainerId);

		let containerHeight = parentContainer.offsetHeight;
		let containerWidth = parentContainer.offsetWidth;
		const documentWidth = document.body.clientWidth;
		const documentHeight = document.body.clientHeight;

		// limit to right and bottom screenborders
		if (parentContainer.offsetTop > documentHeight - containerHeight) parentContainer.style.top = documentHeight - containerHeight + "px";
		if (parentContainer.offsetLeft > documentWidth - containerWidth) parentContainer.style.left = documentWidth - containerWidth + "px";
		
		// limit to left and top screenborders
		if (parentContainer.offsetTop < 0) parentContainer.style.top = 0 + "px";
		if (parentContainer.offsetLeft < 0) parentContainer.style.left = 0 + "px";
	}

	public.resetPosition = function resetPosition() {
		const parentContainer = document.getElementById(globalSearchMainContainerId);
		parentContainer.style.removeProperty('top');
		parentContainer.style.removeProperty('left');
		parentContainer.style.removeProperty('border');
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

	public.addGenerierenLinkToInputWithName = function addGenerierenLinkToInputWithName(inputElementName, templateParameters, returnParameter) {

		let inputElements = document.getElementsByName(inputElementName);

		inputElements.forEach(inputElement => {

			let searchLink = document.createElement("a")
			searchLink.text = "generieren";
			searchLink.href = "javascript:void(0);"
			searchLink.style.paddingLeft = "0.3em"
			inputElement.insertAdjacentElement("afterend", searchLink);
			addListener(searchLink, templateParameters, inputElement, returnParameter);
		})

		function addListener(searchLink, templateParameters, inputElement, returnParameter) {

			searchLink.addEventListener("click", () => {
				
				public.backgroundSearch(templateParameters, (resultArray)=>{
					
					let highestId = 0;

					resultArray.forEach(resultTupel => {

						const resultElement = resultTupel[0];
						if(resultElement[returnParameter.parameter] > highestId) highestId = resultElement[returnParameter.parameter];
					})

					highestId++;
					inputElement.value = highestId;
				});
			});
		}
	}

	public.deleteSearchLink = function deleteSearchLink(inputElementName){

		let inputElements = document.getElementsByName(inputElementName);

		inputElements.forEach(inputElement => {

			inputElement.parentElement;
			console.log(inputElement.parentElement);
			let searchLinks = inputElement.parentElement.getElementsByClassName("search_link");
			console.log({searchLinks})
			for(let i = 0; i < searchLinks.length; i++){
				searchLinks[i].remove();
			}
		});
	}

	public.addSearchLinkToInputWithName = function addSearchLinkToInputWithName(inputElementName, templateParameters, returnParameter = new Parameter("", displayKey), linkText = "suchen", optionalCallbackBeginning, optionalCallbackEnd) {

		let inputElements = document.getElementsByName(inputElementName);

		inputElements.forEach(inputElement => {

			// public.deleteSearchLink(inputElementName);

			// inputElement.parentElement;
			// console.log(inputElement.parentElement);
			// let searchLinks = inputElement.parentElement.getElementsByClassName("search_link");
			// console.log({searchLinks})
			// for(let i = 0; i < searchLinks.length; i++){
			// 	searchLinks[i].remove();
			// }
			
			let searchLink = document.createElement("a")
			searchLink.text = linkText;
			searchLink.href = "javascript:void(0);"
			searchLink.style.paddingLeft = "0.3em"
			searchLink.classList = "search_link"
			inputElement.insertAdjacentElement("afterend", searchLink);
			addListener(searchLink, templateParameters, inputElement, returnParameter, optionalCallbackBeginning, optionalCallbackEnd);
		})

		function addListener(searchLink, templateParameters, inputElement, returnParameter, optionalCallbackBeginning, optionalCallbackEnd) {

			searchLink.addEventListener("click", () => {
				
				if (optionalCallbackBeginning !== undefined) optionalCallbackBeginning();
				NavigationMenu.show("#" + globalSearchMainContentContainerId);
				GlobaleSuche.initTemplateParameters(templateParameters);
				GlobaleSuche.resetPositionIfOutOfBounds();
				GlobaleSuche.addSearchCallback((callbackContent) => {
					NavigationMenu.hide("#" + globalSearchMainContentContainerId);
					inputElement.value = callbackContent[returnParameter.parameter];
					inputElement.dispatchEvent(new Event("change"))
					setTimeout(function () {
						inputElement.scrollIntoView({
							block: 'center',
							inline: 'center'
						});
					}, 100);
					if (optionalCallbackEnd !== undefined) optionalCallbackEnd();
				}, startSearch=true, returnParameter)
			});
		}
	}

	public.addSearchCallback = function addSearchCallback(callback, startSearch=false, returnParameter) {
		searchCallbackForInputMasks = callback;
		searchCallbackReturnParameterForInputMasks = returnParameter;
		enableCallbackMode();
		if (startSearch) search();
	}

	function enableCallbackMode() {
		setMinimizeButtonInputMode();
		setCloseButtonInputMode();
		// Header des Such-Fensters einen String anhaengen
		// setMainHeaderTextInputMode(searchTargetString);
	}

	public.disableCallbackMode = function disableCallbackMode() {
		searchCallbackForInputMasks = undefined;
		resetMinimizeButtonInputMode();
		resetCloseButtonInputMode();
		// Header des Such-Fensters zuruecksetzen
		// resetMainHeaderText();
	}

	function setMinimizeButtonInputMode() {

		let minimizeButton = document.getElementById(minimizeSearchButtonId);
		minimizeButton.style.color = minimizeButtonInputModeColor;
		minimizeButton.value = minimizeButtonInputModeValue;
		minimizeButton.disabled = true;
	}

	function resetMinimizeButtonInputMode() {

		let minimizeButton = document.getElementById(minimizeSearchButtonId);
		minimizeButton.style.removeProperty('color');
		minimizeButton.value = minimizeButtonDefaultValue;
		minimizeButton.disabled = false;
	}

	function setCloseButtonInputMode() {

		let minimizeButton = document.getElementById(closeSearchButtonId);
		minimizeButton.value = closeButtonInputModeValue;
	}

	function resetCloseButtonInputMode() {

		let minimizeButton = document.getElementById(closeSearchButtonId);
		minimizeButton.value = closeButtonDefaultValue;
	}

	function setMainHeaderTextInputMode(searchTargetString) {
		let mainHeaderText = document.getElementById(globalSearchMainHeaderTextId);
		if (searchTargetString === undefined) searchTargetString = "";
		mainHeaderText.innerText = mainHeaderdefaultText + " " + searchTargetString;
		mainHeaderText.style.backgroundColor = "red";
	}

	function resetMainHeaderText() {
		let mainHeaderText = document.getElementById(globalSearchMainHeaderTextId);
		mainHeaderText.innerText = mainHeaderdefaultText;
		mainHeaderText.style.removeProperty("background-color");
	}

	public.hasCallbackMethod = function hasCallbackMethod() {
		return (searchCallbackForInputMasks !== undefined)
	}

	public.initTemplateParameters = function initTemplateParameters(templateParameters) {

		clearParameterRows();
		clearResultTable();

		addRowsForTemplate(templateParameters);
		setTemplateParameters(templateParameters);

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
				let valueInputField = row.getElementsByClassName(searchValueClass)[0];

				let templateElement = template[templateIndex++];
				let category = templateElement["category"].toLowerCase();
				let parameter = templateElement["parameter"].toLowerCase();
				let value = templateElement["value"];

				if (typeof (value) === "function") value = value();

				selectOption(categorySelect, category);
				addOptions(parameterSelect, category);
				selectOption(parameterSelect, parameter);
				setTextInputField(valueInputField, value);
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

		function addOptions(selectElement, category) {

			removeSelectOptions(selectElement);
			addSelectOptions(category, selectElement);

		}

		function addSelectOptions(category, selectElement) {
			let searchParameters = parameters[category];
			searchParameters.forEach(searchParameter => {
				let option = document.createElement("option");
				option.text = searchParameter;
				selectElement.add(option);
			})
		}

		function setTextInputField(valueInputField, value) {
			valueInputField.value = value;
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

		removeSelectOptions(selectElement);

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

	function removeSelectOptions(selectElement) {

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
			const results = getMatchingTupels(tupelList, searchCategories, searchParameters, searchInputFields, searchFilterTypes);

			renderResultTable(results, resultTableId);
		})
	}

	public.backgroundSearch = function backgroundSearch(parameters, callbackFunction) {

		let searchCategories = [];
		let searchParameters = [];
		let searchInputFields = [];
		let searchFilterTypes = [];

		parameters.forEach(parameterElement => {
			const category = parameterElement["category"];
			const parameter = parameterElement["parameter"];
			const value = parameterElement["value"];

			searchCategories.push(category);
			searchParameters.push(parameter);
			searchInputFields.push(value);
			searchFilterTypes.push(filterTypes.matches);
		})

		fetchDatabase((database) => {

			const tupelList = database;
			const results = getMatchingTupels(tupelList, searchCategories, searchParameters, searchInputFields, searchFilterTypes);

			const filteredResults = filterResultsAndReturnOnlyRequestedParameters(results, searchCategories, searchParameters)

			const mergedResults = mergeRedundantTupel(filteredResults);

			callbackFunction(mergedResults);
		})
	}

	function mergeRedundantTupel(tupelList) {

		let mergedTupelList = [].concat(tupelList);

		for (let i = mergedTupelList.length - 1; i > 0; i--) {

			let tupel = mergedTupelList[i];

			for (let j = i - 1; j >= 0; j--) {

				let nextTupel = mergedTupelList[j];

				if (!areEqual(tupel, nextTupel)) continue;

				mergedTupelList.splice(j, 1);
				i--;
			}
		}

		return mergedTupelList;

		function areEqual(tupel, nextTupel) {

			if (tupel.length !== nextTupel.length) return false;

			for (let i = 0; i < tupel.length; i++) {

				let elementA = JSON.stringify(tupel[i]);
				let elementB = JSON.stringify(nextTupel[i]);

				if (elementA !== elementB) return false;
			}

			return true;
		}
	}

	function renderResultTable(results, resultTableId) {

		let indexOfLongestTupel = findLongestTupel();
		const firstTupel = results[indexOfLongestTupel];
		let resultHeaderTupel = [];

		if (firstTupel === undefined) return;
		firstTupel.forEach(tupelElement => {
			resultHeaderTupel.push(capitalize(tupelElement[categoryKey]));
		})

		const parameters = getSearchParameters();
		const categories = getSearchCategories();
		// categories und parameters sortieren und gruppieren: [a,b,a] -> [a,a,b]
		sortAndGroup(parameters, categories);

		// Ergebnisse mit Parametern heraussuchen und "display" key-value setzen 
		// (damit nicht der gesamte Ergebnis-Tupel angezeigt wird, sonder nur die Parameter nach denen gesucht wurde)
		const filteredResults = filterResultsAndReturnOnlyRequestedParameters(results, categories, parameters);

		addTupelAsTableHeader(categories, resultTableId, firstHeaderClass, mergeEqualCells = true);
		addTupelAsTableHeader(parameters, resultTableId, secondHeaderClass, mergeEqualCells = false, addSortFunction = true);
		addResultsToTable(filteredResults, resultTableId);
		mergeRedundantRows(resultTableId);

		// ------ functions ------

		function sortAndGroup(parameters, categories) {

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
		}

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
	}

	function filterResultsAndReturnOnlyRequestedParameters(results, categories, parameters) {

		let newResults = [];
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
		return newResults;
	}

	function mergeRedundantRows(resultTableId) {

		let table = document.getElementById(resultTableId);
		let rows = table.rows;

		for (let i = rows.length - 1; i > sortFunctionSkipRows; i--) {

			let row = rows[i];

			for (let j = i - 1; j > sortFunctionSkipRows - 1; j--) {

				let nextRow = rows[j];

				if (row.innerText === nextRow.innerText) {
					table.deleteRow(i);
					break;
				}
			}

		}
	}

	function addTupelAsTableHeader(tupel, tableId, className, mergeEqualCells, addSortFunction) {

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
		if (mergeEqualCells !== undefined && mergeEqualCells === true) mergeRedundantCells(row);
	}

	function mergeRedundantCells(row) {

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

	function addResultsToTable(results, resultTableId) {
		addTupelArrayToResults(results, resultTableId, displayKey, addShowDetailsCallbackFunction = true);
	}

	function addTupelArrayToResults(results, tableId, onlyThisKey, addShowDetailsCallbackFunction) {

		let table = document.getElementById(tableId);

		results.forEach(tupel => {
			let row = table.insertRow(-1);
			// if (className !== undefined) row.className = className;
			tupel.forEach(tupelElement => {

				for (let key in tupelElement) {

					let cellContent = tupelElement[key];

					if (onlyThisKey !== undefined && onlyThisKey !== key) continue;

					if (onlyThisKey === undefined) {
						let cell = addToNewTableCell(cellContent, row);
						if (addShowDetailsCallbackFunction !== undefined && addShowDetailsCallbackFunction === true && tupelElement[displayKey] !== "") addShowDetailsListener(cell, tupelElement);
						// addCallbackFunction(cell, cellContent);
						if(tupelElement.table === searchCallbackReturnParameterForInputMasks.category) addCallbackFunction(cell, tupelElement);
					}
					
					if (onlyThisKey === key) {
						let cell = addToNewTableCell(cellContent, row)
						if (addShowDetailsCallbackFunction !== undefined && addShowDetailsCallbackFunction === true && tupelElement[displayKey] !== "") addShowDetailsListener(cell, tupelElement);
						// addCallbackFunction(cell, cellContent);
						if(tupelElement.table === searchCallbackReturnParameterForInputMasks.category) addCallbackFunction(cell, tupelElement);
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
				cell.className = resultCellClass;
				cell.addEventListener("click", () => {
					if (searchCallbackForInputMasks === undefined) return;
					searchCallbackForInputMasks(cellContent);
					searchCallbackForInputMasks = undefined;
					public.disableCallbackMode();
				})
			}
			
			function addShowDetailsListener(cell, tupelElement) {
				
				cell.addEventListener("mouseenter", () => {
					if (GlobaleSuche.hasCallbackMethod()) return;
					if (searchCallbackForInputMasks !== undefined) return;
					cell.className = resultCellClass;
				});

				cell.addEventListener("click", () => {

					if (GlobaleSuche.hasCallbackMethod()) return;
					if (searchCallbackForInputMasks !== undefined) return;
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
					addTupelArrayToResults([[tupelElement]], resultTableId, onlyThisKey = undefined, addShowDetailsCallbackFunction = false);
				});
			}
		})
	}

	function capitalize(string) {
		return string[0].toUpperCase() + string.slice(1);
	}

	function getMatchingTupels(tupelList, searchCategories, searchParameters, searchInputFields, searchFilterTypes) {

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