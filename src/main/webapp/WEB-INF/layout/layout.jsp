<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"
%><%@ taglib prefix="spring" uri="http://www.springframework.org/tags"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
	 	<c:set var="titleKey"><tiles:getAsString name="title.key"/></c:set>
		<spring:message code="${titleKey}" />
	</title>
	<tiles:importAttribute name="arquivos_css" />
	<c:forEach var="arq_css" items="${arquivos_css}">
		<link rel="stylesheet" href="<c:url value = '/static/css/${arq_css}' />" />
	</c:forEach>
	<script src="//code.jquery.com/jquery-1.10.1.min.js"></script>
</head>
<body>
	<div id="header"><tiles:insertAttribute name="header" /></div>
	<div id="body"><tiles:insertAttribute name="body" /></div>
	<div id="footer"><tiles:insertAttribute name="footer" /></div>
	
	<!-- Ver: http://developer.yahoo.com/performance/rules.html#js_bottom  -->
	<script type="text/javascript" src="<c:url value = '/static/js/layout.main.js' />"></script>
</body>
</html>