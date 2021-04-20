class Parameter {
	constructor(category, parameter, id = "") {
		this.category = category;
		this.parameter = parameter;
		this.value = id;
	}
}

const Parameters = {
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
		FK: "projekt_id"
	},
	EXPERIMENT: {
		CATEGORY: "experiment",
		PK: "id",
		FK: "api_startmaterial",
		TYP: "typ"
	},
	ANALYSE: {
		CATEGORY: "analysen",
		PK: "id",
		FK: "experiment",
		TYP: "typ"
	},
}