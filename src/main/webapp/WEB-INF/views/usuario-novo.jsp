<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="usuario_novo" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1><spring:message code="usuario_novo" /></h1>
	<form action="<c:url value = '/usuarios' />" method="post">
		<spring:message code="nome" />: <input name="nome" /> <input type="submit" />
	</form>
</body>
</html>