<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- <%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%> --%>
<li><a id="logout_link" href="<spring:url value="/j_spring_security_logout"/>"><spring:message code="ui.logout" /></a></li>