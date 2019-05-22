<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Login Page</title>
<meta charset="UTF-8">

<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/latest/css/bootstrap-combined.min.css" rel="stylesheet" type="text/css" />
<link href="<spring:url value="/css/styles.css"/>" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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
          </ul>
        </div>
      </div>
    </div>
  </div>

  <div class="container">

    <form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>

      <table>
        <tr>
          <td>User:</td>
          <td><input type='text' name='j_username' value=''></td>
        </tr>
        <tr>
          <td>Password:</td>
          <td><input type='password' name='j_password' /></td>
        </tr>
        <tr>
          <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
      </table>

    </form>

  </div>
</body>
</html>