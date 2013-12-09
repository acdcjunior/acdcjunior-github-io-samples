<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="usuario">
	<h1><spring:message code="usuario.cadastrados" /></h1>
	<table>
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
</div>