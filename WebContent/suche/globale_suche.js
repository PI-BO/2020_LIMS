
class GlobaleSuche{
		
	static init(){
			
		this.initAddParameterButton();
		this.initFirstParameters();
		this.initMainCategorySelect();
		this.initSearchButton();
	}
	
	static initAddParameterButton(){
		document.getElementById("globale_suche_add_parameter_button").addEventListener("click", () => GlobaleSuche.addSuchParameter());
		document.getElementById("globale_suche_add_parameter_button").click();
	}
	
	static initFirstParameters(){
		this.createParameters(document.getElementById("select_main_category"));		
	}
	
	static initMainCategorySelect(){
		document.getElementById("select_main_category").addEventListener("change", (event) => this.createParameters(event.target));
	}
	
	static initSearchButton(){
		document.getElementById("globale_suche_button").addEventListener("click", () => GlobaleSuche.search());
	}
	
	static initDeleteButton(element){
		element.addEventListener("click", (element) => GlobaleSuche.deleteSuchParameter(element.target));
	}
	
	static addEnterListener(element){
		
		element.addEventListener("keyup", (event) => {
			
			if(event.keyCode === 13){
				document.getElementById("globale_suche_button").click();
			}
		})
	}
	
	static search(){
		
		const partner1 = {
				"pk" : "1",
				"name" : "Partner A",
		}
		
		const projekt1 = {
				"pk" : "1",
				"name" : "Projekt 1",
				"fk" : "1"
		}
		
		const probe1 = {
				"pk" : "1",
				"name" : "Probe 1",
				"fk" : "1"
		}
		
		const experiment1 = {
				"pk" : "1",
				"name" : "Experiment 1",
				"fk" : "1"
		}
		
		const experiment2 = {
				"pk" : "2",
				"name" : "Experiment 2",
				"fk" : "1"
		}
		
		const methode1 = {
				"pk" : "1",
				"name" : "Methode 1",
				"fk" : "1",
				"operator" : "John Doe"
		}
		
		const methode2 = {
				"pk" : "2",
				"name" : "Methode 2",
				"fk" : "1",
				"operator" : "Jane Doe"
		}
		
		const methode3 = {
				"pk" : "3",
				"name" : "Methode 3",
				"fk" : "2",
				"operator" : "Jane Doe"
		}
		
		const methode4 = {
				"pk" : "4",
				"name" : "Methode 4",
				"fk" : "2",
				"operator" : "Jane Doe"
		}
		
		let database = {
				"projektpartner" : [partner1],
				"projekt" : [projekt1],
				"probe" : [probe1],
				"experiment" : [experiment1, experiment2],
				"methode" : [methode1, methode2, methode3, methode4]
		}
		
		const searchCategory = document.getElementById("select_main_category").value.toLowerCase();
		
		const parameter = document.getElementsByClassName("such_parameter")[0].value.toLowerCase();
		const parameterInhalt = document.getElementsByClassName("such_parameter_inhalt")[0].value.toLowerCase();
		
		console.log(parameter, parameterInhalt);			
		
		const ergebnisListe = document.getElementById("table_globale_suche");
		
		
		while(ergebnisListe.hasChildNodes()){
			ergebnisListe.removeChild(ergebnisListe.lastChild);
		}
		
		let results = database[searchCategory];
		
		console.log(results);

		if(parameterInhalt != ""){
			
			results = results.filter((result) => {
				return result[parameter].toLowerCase() == parameterInhalt;
			})
		}
		
		
		console.log(results);
		
		const tableGlobaleSuche = document.getElementById("table_globale_suche");
		
		let tableHeaderRow = document.createElement("div");
		tableHeaderRow.classList.add("table_globale_suche_tr");
		tableHeaderRow.classList.add("table_globale_suche_th");
		
		for(let key in results[0]){
			
			let tableData = document.createElement("div");
			tableData.classList.add("table_globale_suche_td");
			tableData.append(key);
			tableHeaderRow.append(tableData);
		}
		
		tableGlobaleSuche.append(tableHeaderRow);
		
		results.forEach(result => {
			
			let tableRow = document.createElement("div");
			tableRow.classList.add("table_globale_suche_tr");
			
			for(let key in result){
				let tableData = document.createElement("div");
				tableData.classList.add("table_globale_suche_td");
				tableData.append(result[key]);
				tableRow.append(tableData);
			}
			
			tableGlobaleSuche.append(tableRow);

		});
	}


	static addSuchParameter() {
		
		let container = document.getElementById("such_parameter_table");
		
		let template = document.getElementById("such_parameter_row_template");
		
		let childNodes = template.children;
		
		const containerChildrenCount = container.children.length;
		const templateChildrenCount = childNodes.length;
		
		let row = container.insertRow(-1);
		
		for(let i = 0; i < childNodes.length; i++){
			
			let cell = row.insertCell(i);
			var child = childNodes[i].cloneNode(true);
			this.addEnterListener(child);
			cell.append(child);
			
			if(child.className == "globale_suche_delete_parameter_button") this.initDeleteButton(child);
		}
	}
	
	static deleteSuchParameter(suchParameterElement) {
		
		console.log({suchParameterElement});
		
		let parent = suchParameterElement.parentElement.parentElement;
		parent.remove();
	}
	
	static resetSelects(element){
		
		let allSelects = element.querySelectorAll("select");
		allSelects.forEach(element => {
			resetSelect(element);
		})
	}
	
	static resetSelect(element){
		element.options[0].selected="true";
	}
	
	static createParameters(event){
		
		const parameterCategory = event.value;
		
	 	let selectElements = findSelectElements(event, "such_parameter");
	 	removeOptions(selectElements);
	 	let parameters = getParameters(parameterCategory); 
	 	insertParameters(parameters, selectElements);
	 	
	 	function insertParameters(parameters, selectElements){
	 		
	 		Array.from(selectElements).forEach(selectElement => {
	 			
	 			parameters.forEach(parameter => {
			 		let selectOption = document.createElement("option");
			 		selectOption.text = parameter;
			 		selectOption.value = parameter;
			 		selectElement.add(selectOption);
		 		});
	 		})
	 		
	 	}
	 	
	 	function findSelectElements(event, className){
	 		
	 		let table = document.getElementById("such_parameter_table");
	 		let selectElements = document.getElementsByClassName(className);
	 		return selectElements;
	 	}
	 	
		function removeOptions(selectElements){
			
			Array.from(selectElements).forEach(element => {
				let selectOptions = element.querySelectorAll("option");
				selectOptions.forEach(option => option.remove());
			})
			
		}
		
		function getParameters(parameterCategory){
			const parameters = {
				    "projektpartner" : ["pk", "name"],
				    "projekt" : ["pk", "name", "fk"],
				    "probe" : ["pk", "name", "fk"],
				    "experiment" : ["pk", "name", "fk"],
				    "methode" : ["pk", "name", "fk", "operator"]
			}
			  return parameters[parameterCategory.toLowerCase()];
			}
	}
}

GlobaleSuche.init();