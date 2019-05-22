<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Login Page</title>
<meta charset="UTF-8">

<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/latest/css/bootstrap-combined.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<spring:url value="/css/styles.css"/>" />

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
<script type="text/javascript" src="//netdna.bootstrapcdn.com/twitter-bootstrap/latest/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<spring:url value="/js/internal/common.js"/>"></script>

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
            <li class="active"><a href="<spring:url value="/login.html"/>"><strong><spring:message code="ui.login" /></strong></a></li>
            <li><a href="<spring:url value="/register.html"/>"><strong><spring:message code="ui.register" /></strong></a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>

  <div class="container">

    <form name="loginForm" action="j_spring_security_check" method="post" class="form-horizontal">
      <fieldset>
        <legend>Please Login</legend>
        <br />

        <div class="control-group">
          <label class="control-label" for="login_email"><spring:message code="login.username" /></label>
          <div class="controls">
            <input type="text" name="j_username" autofocus="autofocus" class="required" />
          </div>
        </div>

        <div class="control-group">
          <label class="control-label" for="login_password"><spring:message code="login.password" /></label>
          <div class="controls">
            <input type="password" name="j_password" class="required" />
          </div>
        </div>

        <div class="form-actions">
          <input type="submit" class="btn btn-primary" value="Login" />
        </div>

      </fieldset>
    </form>

  </div>
</body>
</html>