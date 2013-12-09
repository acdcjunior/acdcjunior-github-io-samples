<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
	<h1><spring:message code="layout.title" /></h1>
	<div id="menu">
		<ul>
		<li class="sembarra">Menu:</li>
		<li class="sembarra"><a href="<c:url value = '/usuarios' />"><spring:message code="usuario.cadastrados" /></a></li>
		<li><a href="<c:url value = '/usuarios/novo' />"><spring:message code="usuario.novo" /></a></li>
		<li><a href="<c:url value = '/usuarios-string' />"><spring:message code="usuario.string.title" /></a></li>
		<li><a href="<c:url value = '/usuarios-map' />"><spring:message code="usuario.map" /></a></li>
		</ul>
	</div>
</div>