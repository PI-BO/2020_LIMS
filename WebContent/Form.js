const Form = {};

Form.addMultipartSubmit = function (url, formId, messageId, callbackOnSuccess) {

	let form = document.getElementById(formId);

	form.addEventListener('submit', function (e) {
		
		e.preventDefault();

		const disabledList = [];

		for (let i = 0; i < form.length; i++) {
			if (form[i].disabled === true) {
				form[i].disabled = false;
				disabledList.push(form[i]);
			}
		}

		var formData = new FormData(form);

		for (let i = 0; i < disabledList.length; i++) {
			disabledList[i].disabled = true;
		}

		fetch(url, {
			method: "post",
			body: formData
		})
			.then(response => {

				response.json().then(data => {

					if (data["status"] === "error") addErrorMessage(messageId, data["message"]);

					if (data["status"] === "success") {

						setRequiredInputFieldsToGreen(form);
						addSuccessMessage(messageId, data["message"]);
						removeSubmitButton(form);

						if (callbackOnSuccess !== undefined) callbackOnSuccess();
					}
				})
			})
			.catch(error => {
				replaceContent("button_probeneingang_speichern", "Fehler:" + error, "red");
			});


	}, false);
}

Form.addSubmitAlt = function (url, formId, messageId, callbackOnSuccess) {

	$("#" + formId).submit(function (e) {

		e.preventDefault();

		var submitData = {};
		const form = document.getElementById(formId);

		for (var i = 0; i < e.target.length; i++) {

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
}

Form.addSubmit = function (submitType, url, formId, messageId, callbackOnSuccess) {

	let form = document.getElementById(formId);

	form.addEventListener('submit', function (e) {
		
		e.preventDefault();

		const disabledList = [];

		for (let i = 0; i < form.length; i++) {
			if (form[i].disabled === true) {
				form[i].disabled = false;
				disabledList.push(form[i]);
			}
		}

		var formData = new FormData(form);

		for (let i = 0; i < disabledList.length; i++) {
			disabledList[i].disabled = true;
		}

		fetch(url, {
			method: submitType,
			body: formData
		})
			.then(response => {

				response.json().then(data => {

					if (data["status"] === "error") addErrorMessage(messageId, data["message"]);

					if (data["status"] === "success") {

						setRequiredInputFieldsToGreen(form);
						addSuccessMessage(messageId, data["message"]);
						removeSubmitButton(form);

						if (callbackOnSuccess !== undefined) callbackOnSuccess();
					}
				})
			})
			.catch(error => {
				replaceContent("button_probeneingang_speichern", "Fehler:" + error, "red");
			});


	}, false);
}

function removeSubmitButton(form) {

	for (let i = 0; i < form.length; i++) {
		if (form[i].type === "submit") form[i].remove();
	}
}

function setRequiredInputFieldsToGreen(form) {

	let requiredFields = form.querySelectorAll("input:required");

	for (let i = 0; i < requiredFields.length; i++)	requiredFields[i].style["border-color"] = "green";
}

function addSuccessMessage(messageId, message) {
	$("#" + messageId).empty().append("<div style=\"color:green\">" + message + "</div>");
}

function addErrorMessage(messageId, message) {
	$("#" + messageId).empty().append("<h3 style=\"color:red\">" + message + "</h3>");
}

export default Form;