
function doRegistration() {
	var username = $('#registration_username').val();
	var password = $('#registration_password').val();

	var url = "//" + window.location.host + "/spring-security/api/registration";
	var registrationDto = '{"username":"' + username + '","password":"' + password + '"}';

	showInfoNotification("regWaitMsg");
	$('#registration_register').attr("disabled", "disabled");
	handleAjaxRequest('POST', url, registrationDto, registrationSuccessHandler, registrationFailedHandler);
}

function registrationSuccessHandler(msg, textStatus, xhr) {
	if (xhr.status == 201) {
		window.location.href = 'login.html?registered=true';
	} else {
		$('#registration_register').removeAttr("disabled");
	}
}

function registrationFailedHandler(xhr) {
	if (xhr.status == 409) {
		var error = xhr.statusText;
		if (error == "duplicate.email" || error == "DuplicateOrganization") {
			window.alert("Error during registration" + error);
		} else {
			window.alert("Unknown error during registration" + error);
		}
	}
	$('#registration_register').removeAttr("disabled");
}
