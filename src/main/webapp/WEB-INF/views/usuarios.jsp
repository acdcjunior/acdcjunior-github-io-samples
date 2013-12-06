<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="titulo_aplicacao" /> - <spring:message code="usuario" text="Usuario" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1><spring:message code="usuarios_cadastrados" text="Usuarios Cadastrados" /></h1>
	<table border="1">
		<thead>
			<tr>
				<td><spring:message code="id" /></td>
				<td><spring:message code="nome" /></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="usuario" items="${usuarios}">
				<tr>
					<td>${usuario.id}</td>
					<td>${usuario.nome}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<a href="<c:url value = '/usuarios/novo' />">[<spring:message code="usuario_novo" />]</a>
</body>
</html>