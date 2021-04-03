export default class Parameter {
	constructor(category, parameter, id) {
		this.category = category;
		this.parameter = parameter;
		this.value = id;
	}
}
export const PARTNER = {
	CATEGORY: "partner",
	PK: "id",
	NAME: "name",
	EMAIL: "email"
}
export const PROJEKT = {
	CATEGORY: "projekte",
	PK: "id",
	VERTRAGSNUMMER: "vertragsnummer",
	FK: "projektpartner"
}
export const SUBSTANZ = {
	CATEGORY: "substanz",
	PK: "id",
	FK: "projekt_id",
	WIRKSTOFF: "wirkstoff"
}
export const PROBE = {
	CATEGORY: "probe",
	PK: "id",
	FK: "substanz_id"
}
export const EXPERIMENT = {
	CATEGORY: "experiment",
	PK: "id",
	FK: "proben_nr",
	TYP: "typ"
}
