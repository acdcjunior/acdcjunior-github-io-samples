<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
<head>
<title>Novo Usuario</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1>Novo Usuario</h1>
	<form action="<c:url value = '/usuarios' />" method="post">
		Nome: <input name="nome" /> <input type="submit" />
	</form>
</body>
</html>