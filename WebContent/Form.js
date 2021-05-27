const Form = {};

Form.addSubmit = function (url, formId, messageId, callbackOnSuccess) {

	$("#" + formId).submit(function (e) {

		e.preventDefault();

		var submitData = {};
		const form = document.getElementById(formId);

		for (var i = 0; i < e.target.length; i++) {

			console.log(e.target[i].name, e.target[i].value); //TODO: 
			submitData[e.target[i].name] = e.target[i].value;
		}

		var posting = $.post(url, submitData);
		posting.done(function (data) {

			if (data["status"] === "error") addErrorMessage(messageId, data["message"]);

			if (data["status"] === "success") {

				setRequiredInputFieldsToGreen(form);	
				addSuccessMessage(messageId, data["message"]);
				removeSubmitButton(form);
				
				if (callbackOnSuccess !== undefined) callbackOnSuccess();
			}
		});
	})
	
	function removeSubmitButton(form){
		
		for(let i = 0; i < form.length; i++){
			if(form[i].type === "submit") form[i].remove();
		}
	}
	
	function setRequiredInputFieldsToGreen(form){
		
		let requiredFields = form.querySelectorAll("input:required");

		for (let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
	}
	
	function addSuccessMessage(messageId, message){
		$("#" + messageId).empty().append("<div style=\"color:green\">" + message + "</div>");
	}
	
	function addErrorMessage(messageId, message){
		$("#" + messageId).empty().append("<h3 style=\"color:red\">" + message + "</h3>");
	}
}