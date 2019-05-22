$(document).ready(function () {

	// ---- Setup defaults for jQuery Validator plugin
	if (jQuery.validator) {
		jQuery.validator.setDefaults({
			errorClass : 'help-inline',
			highlight : function (element, errorClass) {
				$(element).closest(".control-group").addClass("error");
			},
			unhighlight : function (element) {
				$(element).closest(".control-group").removeClass("error");
			}
		});
	}

	// ----- blockUI defaults

	// styling
	if (jQuery.blockUI) {
		$.blockUI.defaults.message = "<h1>"+waitMsg+"</h1>";
		$.blockUI.defaults.css.border = 'none';
		$.blockUI.defaults.css.padding = '15px';
		$.blockUI.defaults.css.backgroundColor = '#000';
		$.blockUI.defaults.css.opacity = 0.5;
		$.blockUI.defaults.css.color = "#fff";
		$.blockUI.defaults.css["-webkit-border-radius"] = '10px';
		$.blockUI.defaults.css["-moz-border-radius"] = '10px';

		$.blockUI.defaults.fadeIn = 0;
		$.blockUI.defaults.fadeOut = 0;
	}

	// Add a notification message wrapper
	$('body').prepend('<div class="notification"><div class="alert alert-error" style="display: none;"></div></div>');

	// show incompatibility message for IE9
	if ($('#showBrowserIncompatibilityWarn').length > 0) {
		if ($.browser.msie && $.browser.version == "9.0") {
			$('#showBrowserIncompatibilityWarn').show();
		}
	}
});

function beforeSendHandler(xhr, authorizationKey) {
	// use basic authorization
	xhr.setRequestHeader("Authorization", "Basic " + authorizationKey);
}


var UIWorker = {

	setText : function(arrayOfIds, text) {
		$.each(arrayOfIds, function(index, value) {
			$(value).text(text);
		});
	}

};

// ---------------------------------------------- Notifications

var hideNotificationTimeout;
var userDetails;

function showNotification(msg, styleClass, timeout) {
	hideNotification();

	$(".notification .alert")
		.removeClass("alert-error")
		.removeClass("alert-success")
		.removeClass("alert-block")
		.removeClass("alert-info")
		.addClass(styleClass)
		.text(msg).fadeIn();

	$(".notification").center(false);

	hideNotificationTimeout = setTimeout(function() {
		$(".notification .alert").fadeOut();
	}, timeout);
}

function hideNotification() {
	clearTimeout(hideNotificationTimeout);

	if ($(".notification .alert").is(":visible")) {
		$(".notification .alert").hide();
	}
}

function showErrorNotification(msg) {
	showNotification(msg, "alert-error", 10000);
}

function showSuccessNotification(msg) {
	showNotification(msg, "alert-success", 5000);
}

function showWarningNotification(msg) {
	showNotification(msg, "alert-block", 8000);
}

function showInfoNotification(msg, timeout) {
	var actualTimeout = timeout ? timeout : 5000;
	showNotification(msg, "alert-info", actualTimeout);
}

// ------------------------------------------------------
// Custom jQuery plugins
// ------------------------------------------------------

jQuery.fn.center = function(vertical) {
	this.css("left", (($(window).width() - this.outerWidth()) / 2) + $(window).scrollLeft() + "px");
	if (vertical) {
		this.css("top", (($(window).height() - this.outerHeight()) / 2) + $(window).scrollTop() + "px");
	}
	return this;
};

