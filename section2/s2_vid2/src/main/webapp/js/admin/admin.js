var authorizationKey_admin = null;
var table;

// Pagination
var page = 0;
var pageSize = 5;

const
prevPageId = "#prevPage";
nextPageId = "#nextPage";
currentPageId = "#currentPage";

// Search/Filter
filterId = '#filter';

var Admin = {

	loadUsersPage : function(viewLinkTmpl) {
		table = $('#datatable').dataTable({
			aoColumns : [ {
				sTitle : "Name",
				bSearchable : false,
				bSortable : false
			}, {
				sTitle : "Enabled",
				bSearchable : false,
				bSortable : false
			} ],
			bJQueryUI : true,
			bLengthChange : false,
			bFilter : false,
			bInfo : false,
			bAutoWidth : true,
			bPaginate : false
		});

		// load data handlers
		var successFindResources = function(data, textStatus, xhr) {
			if (!data || data.length == 0) {
				// if there are no data, then don't change table and page number
				page--;
				$(nextPageId).parent().addClass("disabled");
			} else {
				var users = Convertors.toUsers(data);
				TablePagination.updateNextPaginationControll(users);
				table.fnClearTable();

				// add new data to the grid
				$.each(users, function(index, elem) {
					table.fnAddData([ elem.name, elem.enabled ]);
				});

				// change info - a page number
				$(currentPageId).text(page + 1);
			}

			TablePagination.updatePrevPaginationControll(page);
		};
		var errorFindResources = function(xhr, textStatus) {
			showErrorNotification(adminLoadErrMsg);
			page--;
			TablePagination.managePaginationControlls(page);
		};

		// filtering
		var filter = $('#filterTmpl').html();
		$($('#datatable_wrapper div.ui-widget-header')[0]).append(filter);
		AdminFiltering.setupFiltering(successFindResources);
		AdminFiltering.setupClearFiltering(successFindResources, errorFindResources);

		// pagination
		AdminTable.setupNavigationControlls(successFindResources, errorFindResources);

		// load data
		UserApi.findAll(page, pageSize, successFindResources, errorFindResources, authorizationKey_admin);
	}
};

// Filtering

var AdminFiltering = {
	setupFiltering : function(successHandler) {
		$('#filterForm').submit(function(e) {
			e.preventDefault();

			hideNotification();
			page = 0;
			var query = $(filterId).val();
			if (query) {
				$(prevPageId).parent().addClass("disabled");

				var searchErrorHandler = function(xhr) {
					if (xhr.status == 404) {
						showErrorNotification(adminNotFoundMsg);
					} else {
						showErrorNotification(adminLoadErrMsg);
					}
				};
				UserApi.findByName(query, successHandler, searchErrorHandler, authorizationKey_admin);
			} else {
				showWarningNotification(adminEnterEmailMsg);
			}

			return false;
		});
	},
	setupClearFiltering : function(successHandler, errorHandler) {
		$('#clearSearch').click(function() {
			page = 0;
			$(filterId).val("");
			hideNotification();
			UserApi.findAll(page, pageSize, successHandler, errorHandler, authorizationKey_admin);
		});
	}

};

// Pagination

var AdminTable = {
	setupNavigationControlls : function(successHandler, errorHandler) {
		$(prevPageId).click(function() {
			$(filterId).val("");
			if (page > 0) {
				UserApi.findAll(--page, pageSize, successHandler, errorHandler, authorizationKey_admin);
			}
		});
		$(nextPageId).click(function() {
			$(filterId).val("");
			UserApi.findAll(++page, pageSize, successHandler, errorHandler, authorizationKey_admin);
		});
	}
};
var TablePagination = {
	updatePrevPaginationControll : function(pageLocal) {
		if (pageLocal == 0) {
			$(prevPageId).parent().addClass("disabled");
		} else {
			$(prevPageId).parent().removeClass("disabled");
		}
	},
	updateNextPaginationControll : function(data) {
		if (data.length < pageSize) {
			$(nextPageId).parent().addClass("disabled");
		} else {
			$(nextPageId).parent().removeClass("disabled");
		}
	}
};

var Convertors = {

	toUsers : function(data) {
		if ($.isArray(data)) {
			return $.map(data, Convertors.toUser);
		}

		// if single user is found and convert an array with 1 element
		return [ Convertors.toUser(data) ];
	},

	toUser : function(elem) {
		return {
			name : elem.name,
			enabled : !elem.suspended,
		};
	}
	
};
