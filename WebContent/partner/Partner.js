import Suche from '../suche/GlobaleSuche.js'
import { Parameter } from '../suche/Parameter.js';
import { Parameters } from '../suche/Parameter.js';
import Form from '../Form.js';

export default class Partner {


    render(htmlElement) {

        const url = "http://localhost:8080/2020_LIMS/partner/partner_erstellen.jsp";

        fetch(url, {
            method: "post",
        })
            .then(response => response.text())
            .then(response => {

                htmlElement.innerHTML = response

                Suche.addGenerierenLinkToInputWithName(
                    "partner_id_input_field",
                    [
                        new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
                    ],
                    new Parameter(Parameters.PARTNER.CATEGORY, Parameters.PARTNER.PK, "")
                );

                const servletUrl = "http://localhost:8080/2020_LIMS/partner_erstellen_servlet";
                const formId = "form_partner_erstellen";
                const messageId = "partner_erstellen_save_message";
                const callbackOnSuccess = () => {
                    // const partnerID = document.getElementsByName("<%=Partner.COLUMN_PRIMARY_KEY%>")[0].value;
                    // MainState.setProjektPartner(partnerID);
                }
                
                Form.addSubmit(
                    servletUrl,
                    formId,
                    messageId,
                    callbackOnSuccess
                );
            });
    }
}