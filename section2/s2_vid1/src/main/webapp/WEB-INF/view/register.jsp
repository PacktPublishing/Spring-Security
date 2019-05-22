<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Register Page</title>
<meta charset="UTF-8">

<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/latest/css/bootstrap-combined.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<spring:url value="/css/styles.css"/>" />

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
<script type="text/javascript" src="//netdna.bootstrapcdn.com/twitter-bootstrap/latest/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.11.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="<spring:url value="/js/jquery.json-2.4.js"/>"></script>

<script type="text/javascript" src="<spring:url value="/js/internal/ajaxHandler.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/internal/common.js"/>"></script>
<script type="text/javascript" src="<spring:url value="/js/internal/registration.js"/>"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#registerForm').validate({
			submitHandler : function() {
				doRegistration();
			}
		});
	})
</script>

<%@include file="includes/common.jsp"%>
</head>
<body>

  <div class="navbar">
    <div class="navbar-inner">
      <div class="container">
        <div class="nav-collapse">
          <ul class="nav pull-left">
            <li><a href="<spring:url value="/"/>"><strong><spring:message code="home.goto" /></strong></a></li>
          </ul>
          <ul class="nav pull-right">
            <li><a href="<spring:url value="/login.html"/>"><strong><spring:message code="ui.login" /></strong></a></li>
            <li class="active"><a href="<spring:url value="/register.html"/>"><strong><spring:message code="ui.register" /></strong></a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>

  <div class="container">
    <form id="registerForm" name="registerForm" action="" method="POST" class="form-horizontal">
      
      <fieldset>
        <legend>Register</legend>

        <label class="control-label" for="registration_username"><spring:message code="account.username" /></label>
        <input id="registration_username" type="text" name="registration_username" class="required" />
        <br/>

        <label class="control-label" for="registration_password"><spring:message code="account.password" /></label>
        <input id="registration_password" type="password" name="registration_password" class="required" />
        <p/>
          
        <label class="control-label" for="registration_password2"><spring:message code="account.password.confirm" /></label>
        <input id="registration_password2" type="password" name="registration_password2" class="required" />

        <div class="form-actions">
          <input id="registration_register" type="submit" class="btn btn-primary" value="<spring:message code="account.register"/>" />
        </div>
      </fieldset>
    </form>

  </div>
</body>
</html>