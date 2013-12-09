<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<h1><spring:message code="usuario.novo" /></h1>
	<form action="<c:url value = '/usuarios' />" method="post">
		<spring:message code="nome" />: <input name="nome" /> <input type="submit" />
	</form>
</div>