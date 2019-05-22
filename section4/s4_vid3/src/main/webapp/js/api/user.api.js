var UserApi = {

	root : "/spring-security/api/users",

	/**
	 * Find a page of users.
	 * 
	 * @param page the page number.
	 * @param pageSize the items per page.
	 */
	findAll : function(page, pageSize, successHandler, errorHandler, authorizationKeyArg) {
		var serverURL = UserApi.root + "/?page=" + page + "&size=" + pageSize;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler, authorizationKeyArg);
	},

	/**
	 * Find a user by id.
	 * 
	 * @param id the user id.
	 */
	findById : function(id, successHandler, errorHandler, authorizationKeyArg) {
		var serverURL = UserApi.root + "/" + id;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler, authorizationKeyArg);
	},

	/**
	 * Find a user by uuid.
	 * 
	 * @param uuid the user uuid.
	 */
	findByUuid : function(uuid, successHandler, errorHandler, authorizationKeyArg) {
		var serverURL = UserApi.root + "?uuid=" + uuid;
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler, authorizationKeyArg);
	},

	/**
	 * Find user by email.
	 * 
	 * @param email the email address.
	 */
	findUserByEmail : function(email, successHandler, errorHandler, authorizationKeyArg) {
		var serverURL = UserApi.root + "/?email=" + encodeURIComponent(email);
		handleAjaxRequest('GET', serverURL, null, successHandler, errorHandler, beforeSendHandler, authorizationKeyArg);
	},

	/**
	 * Retrieve the current logged in user
	 */
	current : function(successHandler, authorizationKeyArg) {
		var serverURL = UserApi.root + "/current";
		handleAjaxRequest('GET', serverURL, null, successHandler, null, beforeSendHandler, authorizationKeyArg);
	}

};