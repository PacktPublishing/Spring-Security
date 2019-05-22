<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title><spring:message code="admin.console" /></title>
<meta charset="UTF-8">

<!-- CSS -->
<link href="<spring:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />
<link href="//ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css" rel="stylesheet" type="text/css" />
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/latest/css/bootstrap-combined.min.css" rel="stylesheet" type="text/css" />

<!-- JS -->
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="//netdna.bootstrapcdn.com/twitter-bootstrap/latest/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.json-2.4.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/mustache.js"/>"></script>

<script type="text/javascript" src="<spring:url value="/js/internal/ajaxHandler.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/internal/common.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/api/user.api.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/admin/admin.js"/>"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var linkTmpl = '<a href="<spring:url value="/admin/user-details.html?uuid={{uuid}}"/>" class="btn btn-mini pull-right"><spring:message code="admin.view"/></a>';
						Admin.loadUsersPage(linkTmpl);
					});

	adminLoadErrMsg = '<spring:message code="admin.js.users.loadError"/>';
	adminNotFoundMsg = '<spring:message code="admin.js.users.notFound"/>';
	adminEnterEmailMsg = '<spring:message code="admin.js.users.promptEmail"/>';
</script>
<%@include file="../includes/common.jsp"%>
</head>

<body>

  <div class="navbar">
    <div class="navbar-inner">
      <div class="container">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
        </a>
        <div class="nav-collapse">
          <ul class="nav pull-left">
            <li class="active"><a href="<spring:url value="/index.html"/>"><spring:message code="admin.home" /></a></li>
          </ul>
          <ul class="nav pull-right">
            <%-- <li><a id="userLoggedIn"><spring:message code="logged.in.as" /><b>${requestScope.username}</b></a></li> --%>
            <%-- <%@include file="../_logout.jsp"%> --%>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <h1>
      <spring:message code="admin.console" />
    </h1>

    <hr />
    <h3>
      <spring:message code="admin.title" />
    </h3>
    <div style="padding: 10px 0;">
      <div id="admin_organizations_table">
        <table id="datatable"></table>
      </div>
      <div id="admin_organizations_table_footer">
        <spring:message code="ui.page" />
        <span id="currentPage">1</span>
      </div>

      <ul class="pager">
        <li class="disabled previous"><a id="prevPage" href="javascript:void(0);">&larr; <spring:message code="ui.prev" /></a></li>
        <li class="next"><a id="nextPage" href="javascript:void(0);"><spring:message code="ui.next" /> &rarr;</a></li>
      </ul>
    </div>
  </div>

</body>
</html>