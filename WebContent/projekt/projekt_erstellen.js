import Parameter from "../JavaScript/Parameter.js";
import * as Parameters from "../JavaScript/Parameter.js";

GlobaleSuche.backgroundSearch(
    [
        new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "1")
        // {
        // 	"category": "partner",
        // 	"parameter": "id",
        // 	"value": "1"
        // },
        // {
        // 	"category": "projekte",
        // 	"parameter": "id",
        // 	"value": "A"
        // }
    ],
    (requestedData) => {
        console.log({ requestedData })
    }
);