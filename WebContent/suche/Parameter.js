class Parameter {
	constructor(category, parameter, id) {
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
	SUBSTANZ: {
		CATEGORY: "substanz",
		PK: "id",
		FK: "projekt_id",
		WIRKSTOFF: "wirkstoff"
	},
	PROBE: {
		CATEGORY: "probe",
		PK: "id",
		FK: "substanz_id"
	},
	EXPERIMENT: {
		CATEGORY: "experiment",
		PK: "id",
		FK: "proben_nr",
		TYP: "typ"
	}
}