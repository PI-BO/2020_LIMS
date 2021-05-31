export default class Model {
    constructor(parameter) {
        for (let key in parameter) {
            if (key === "CATEGORY") {
                this["table"] = parameter[key];
            } else {
                this[parameter[key].toLocaleLowerCase()] = "";
            }
        }
    }
}